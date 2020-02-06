package com.emproducciones.entradaspapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.emproducciones.entradaspapi.UI.MainActivity;
import com.emproducciones.entradaspapi.db.entidad.constantes;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;

public class entradasNocheVigenteReal extends AppCompatActivity {

    TextView txtNocheActual,txtCantidadEntradasNocheVigente,txtRecaudacionNoche;
    int entradasVendidasNoche;
    entradaRepository respositorio = new entradaRepository(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas_noche_vigente);

        txtNocheActual=findViewById(R.id.txtNocheActual);
        txtCantidadEntradasNocheVigente=findViewById(R.id.txtCantidadEntradasNocheVigente);
        txtRecaudacionNoche=findViewById(R.id.txtRecaudacionNoche);

        generarInformeNocheActual();
    }

    void generarInformeNocheActual(){
        entradasVendidasNoche=respositorio.obtenerCantidadEntradasNocheVigente();
        txtNocheActual.setText("Noche: " + MainActivity.fechaBD.getNumeroNoche());
        txtCantidadEntradasNocheVigente.setText("Entradas: " + entradasVendidasNoche);
        txtRecaudacionNoche.setText("Recaudacion: $" + entradasVendidasNoche* constantes.PRECIO_ENTRADA);
    }

    public void cerrarNoche(View view){
        //metodo que finaliza la venta de entradas de la noche y carga al registro de entradas totales

        // pongo la fecha en false y cargo fecha nueva
        gestionNocheFecha fechaParaCerrar = MainActivity.fechaBD;
        fechaParaCerrar.setEstado(0);
        respositorio.cerrarNoche(fechaParaCerrar);
        gestionNocheFecha a = respositorio.obtenerUltimaFecha();
        respositorio.registrarNoche(new gestionNocheFecha(MainActivity.fechaActual().toString(),a.getNumeroNoche()+1,1));

        // registro el total de entradas en el historial

        respositorio.registrarFinNoche(new resumenNoches(fechaParaCerrar.getNumeroNoche(),entradasVendidasNoche));

        //elimino las entradas de la noche actual

        respositorio.eliminarEntradasNocheActual();

        //actualizo la fecha

        MainActivity mainActivity = new MainActivity();

        txtCantidadEntradasNocheVigente.setText("Entradas: " + 0);
        txtRecaudacionNoche.setText("Recaudacion: $" + 0);
        txtNocheActual.setText("Noche: " + (a.getNumeroNoche()+1) );

    }
}
