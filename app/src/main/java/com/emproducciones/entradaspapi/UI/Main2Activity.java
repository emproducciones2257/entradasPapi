package com.emproducciones.entradaspapi.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.emproducciones.entradaspapi.R;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import static com.emproducciones.entradaspapi.UI.MainActivity.fechaActual;

public class Main2Activity extends AppCompatActivity {

    public static gestionNocheFecha fe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        verificarFechaVigente();
    }

    public void eventoBtnConsulta(View view){
        Intent intent = new Intent(getApplicationContext(), DashboardEntradasVendidas.class);
        startActivity(intent);
    }

    public void eventoBtnVentaTicket(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void verificarFechaVigente() {
        gestionNocheFecha mod = new gestionNocheFecha();
        fe = mod.verificarFecha(fechaActual(),getApplication());
    }
}
