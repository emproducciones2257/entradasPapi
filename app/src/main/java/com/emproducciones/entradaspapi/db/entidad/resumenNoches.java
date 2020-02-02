package com.emproducciones.entradaspapi.db.entidad;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "resumenNoches")

public class resumenNoches {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NotNull
    public int id_fecha;
    @NotNull
    public int totalEntradasNoche;


    @Ignore
    public resumenNoches (int id_fecha, int totalEntradasNoche){
        this.id_fecha=id_fecha;
        this.totalEntradasNoche=totalEntradasNoche;
    }

    public resumenNoches (int totalEntradasNoche){
        this.totalEntradasNoche=totalEntradasNoche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_fecha() {
        return id_fecha;
    }

    public void setId_fecha(int id_fecha) {
        this.id_fecha = id_fecha;
    }

    public int getTotalEntradasNoche() {
        return totalEntradasNoche;
    }

    public void setTotalEntradasNoche(int totalEntradasNoche) {
        this.totalEntradasNoche = totalEntradasNoche;
    }
}
