package com.emproducciones.entradaspapi.db.entidad;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;


@Entity(tableName = "entradasNocheVigente")
public class nocheVigente {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NotNull
    public int nroNoche;

    public int cantidad;

    public nocheVigente(int nroNoche, int cantidad) {
        this.nroNoche = nroNoche;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroNoche() {
        return nroNoche;
    }

    public void setNroNoche(int nroNoche) {
        this.nroNoche = nroNoche;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
