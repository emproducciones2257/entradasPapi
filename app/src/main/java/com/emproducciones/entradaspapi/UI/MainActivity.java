package com.emproducciones.entradaspapi.UI;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.emproducciones.entradaspapi.R;
import com.emproducciones.entradaspapi.db.entidad.constantes;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.nocheVigente;
import com.emproducciones.entradaspapi.entradasNocheVigenteReal;
import com.emproducciones.entradaspapi.entradasViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView total,cantidad,txtNroNoche;
    private  Button confirmar;
    private int entradas = 0;
    private FloatingActionButton btn;
    private gestionNocheFecha fecha;
    private entradasViewModel viewModel;

    public static gestionNocheFecha fechaBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = findViewById(R.id.total);
        cantidad = findViewById(R.id.cantidad);
        btn = findViewById(R.id.flotante);
        confirmar = findViewById(R.id.btnConfirmar);
        txtNroNoche=findViewById(R.id.txtNroNoche);
        viewModel = ViewModelProviders.of(this).get(entradasViewModel.class);

        verificarFechaVigente();

        txtNroNoche.setText("Noche: "+ MainActivity.fechaBD.getNumeroNoche());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), entradasNocheVigenteReal.class);
                startActivity(intent);
            }
        });
        }

    public void flotante(View v){

       if(btn.getRotation()==60)
           btn.setRotation(-90);
       else {
           btn.setRotation(60);
       }
    }

    public void sumar (View v){
        entradas++;
        mostrar();
    }
    public void restar (View v){
        if(entradas<=0){
            entradas=0;
            mostrar();
        }else {
            entradas--;
            mostrar();
        }
    }

    void mostrar (){
        cantidad.setText("Cantidad: " + entradas);
        precion();
    }

    void precion(){
        total.setText((entradas* constantes.PRECIO_ENTRADA)+"");
    }

    public void confirmar(View v) {
        if (entradas!=0){
            viewModel.insertarEntrada(new nocheVigente(MainActivity.fechaBD.getNumeroNoche(), entradas));
            entradas=0;
            mostrar();
            Toast mensaje = Toast.makeText(getApplicationContext(),"Imprimiendo Entradas",Toast.LENGTH_SHORT);
            mensaje.show();
            //imprimir();
        }
    }

    public void verificarFechaVigente() {

        gestionNocheFecha mod = new gestionNocheFecha();
        fechaBD = mod.verificarFecha(fechaActual(),getApplication());
    }

    public static java.sql.Date fechaActual(){
        Date h = new Date();
        java.sql.Date fechaActual = new java.sql.Date(h.getTime());
        return fechaActual;
    }

    void imprimir (){
        String textToPrint = "<BIG>Text Title<BR>Testing <BIG>BIG<BR><BIG><BOLD>" +
                "string <SMALL> text<BR><LEFT>Left aligned<BR><CENTER>" +
                "Center aligned<BR><UNDERLINE>underline text<BR><QR>12345678<BR>" +
                "<CENTER>QR: 12345678<BR>Line<BR><LINE><BR>Double Line<BR><DLINE><BR><CUT>";
        Intent intent = new Intent("pe.diegoveloper.printing");
        //intent.setAction(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT,textToPrint);
        startActivity(intent);
    }
}
