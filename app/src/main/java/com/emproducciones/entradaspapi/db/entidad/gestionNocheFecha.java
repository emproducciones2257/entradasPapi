package com.emproducciones.entradaspapi.db.entidad;

import android.app.Application;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.emproducciones.entradaspapi.UI.MainActivity;
import com.emproducciones.entradaspapi.entradaRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;


@Entity
public class gestionNocheFecha {

    @PrimaryKey(autoGenerate = true)
    public int idNocheFecha;
    @NotNull
    public String fecha;
    @NotNull
    public int numeroNoche;
    @NotNull
    public int estado;

    @Ignore
    public gestionNocheFecha(@NotNull String fecha, @NotNull int numeroNoche, @NotNull int estado) {
        this.fecha = fecha;
        this.numeroNoche = numeroNoche;
        this.estado = estado;
    }

    public gestionNocheFecha(@NotNull int idNocheFecha, @NotNull String fecha, @NotNull int numeroNoche, @NotNull int estado) {
        this.idNocheFecha = idNocheFecha;
        this.fecha = fecha;
        this.numeroNoche = numeroNoche;
        this.estado = estado;
    }

    @Ignore
    public gestionNocheFecha(){}

    public int getIdNocheFecha() {
        return idNocheFecha;
    }

    public void setIdNocheFecha(Integer idNocheFecha) {
        this.idNocheFecha = idNocheFecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumeroNoche() {
        return numeroNoche;
    }

    public void setNumeroNoche(int numeroNoche) {
        this.numeroNoche = numeroNoche;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public gestionNocheFecha verificarFecha(Date hoy, Application application) {
        gestionNocheFecha noche;
        entradaRepository entradaRepository = new entradaRepository(application);
        noche = entradaRepository.obtenerUltimaFecha(); //obtengo, si existe ultimo registro

        // en este ultimo punto contemplo el caso que la base de fechas esta vacia, registro la noche de hoy
        //seria el primer caso. iniciar el primer registros

        if(noche==null){
            System.out.println("ESTOY NULO EN EL MODELO");

            return noche;
            //consulto en este punto si la ultima fecha registrada en la base esta
            //disponible aun y ademas, si la fecha recbida es igual a la del ultimo
            //registro. Si se cumple, devuelvo el numero de la noche del ultimo registro

        }else if (noche.getFecha().equals(hoy.toString())&&(noche.getEstado()==1)){
            System.out.println("La ultima fecha esta disponible");
            return noche;

            //en este punto evaluo, si la fecha es distinta pero la ultima jugada esta disponible.
            //si se cumple, registro la fecha nueva

        }else if (!noche.getFecha().equals(hoy.toString())&&(noche.getEstado()==1)) {
            entradaRepository.registrarNoche(new gestionNocheFecha(hoy.toString(),noche.getNumeroNoche(),1));
            noche = entradaRepository.obtenerUltimaFecha();
            System.out.println("Se cambio la fecha pero sigue vigente la noche");
            return noche;

            //aca contemplo el caso que la noche ya no esta disponible, de ser asi,
            //habilito una nueva noche
        }else if (noche.getEstado()==0) {
            entradaRepository.registrarNoche(new gestionNocheFecha(hoy.toString(),noche.getNumeroNoche()+1,1));
            System.out.println("Carga nueva");
            noche = entradaRepository.obtenerUltimaFecha();
            return noche;
        }
        return noche;
    }

    public gestionNocheFecha cargaInicialParaLaBase(){
        return new gestionNocheFecha(MainActivity.fechaActual().toString(),1,1);
    }
}
