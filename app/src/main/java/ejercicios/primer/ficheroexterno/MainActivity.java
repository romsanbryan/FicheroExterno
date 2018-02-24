package ejercicios.primer.ficheroexterno;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Clase que recoge datos y guarda en documentos en memoria externa
 * @author romsanbryan
 */
public class MainActivity extends AppCompatActivity {
   private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.text);
    }

    /**
     * Accion de boton para escribir en un fichero en la ruta por defecto
     * @param v
     */
    public void enviar(View v){
            try {
                String estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.MEDIA_MOUNTED)) {
                    File file = new File(getExternalFilesDir(null), "fichero.txt");
                    OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file));
                    fout.write(et.getText().toString());
                    fout.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        et.setText("");
    }

    /**
     * Accion de boton para leer un fichero que esta en la ruta por defecto
     * @param v
     */
    public void leer(View v){
        String texto = "";
            try {
                String estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.MEDIA_MOUNTED)) {
                    File file = new File(getExternalFilesDir(null), "fichero.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    texto = texto + br.readLine().toString();
                    et.setText(texto);
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

        }
    }

    /**
     * Accion de boton que escribe en un fichero externo y publico (downloads)
     * @param v
     */
    public void escribir_publico(View v){
        try {
            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS), "fichero_publico.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file));
                fout.write(et.getText().toString());
                fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        et.setText("");
    }
}
