package com.emproducciones.entradaspapi.db.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.nocheVigente;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;

import java.util.List;

@Dao
public interface entradasDAO {

    @Insert
    void insertarEntradaNocheVigente(nocheVigente nocheVigente);

    @Insert
    void insertarFinNoche(resumenNoches resumenNoches);

    @Insert
    void registrarNoche(gestionNocheFecha gestionNocheFecha);

    @Query("SELECT * FROM entradasNocheVigente")// aca lo que recupero son las entradas de todas las noches
    LiveData<List<nocheVigente>> entradasTodas();

    @Query("SELECT * FROM resumenNoches")
    LiveData<List<resumenNoches>> nochesEntradasTodas();

    @Query("SELECT * FROM gestionNocheFecha WHERE idNocheFecha = (SELECT MAX(idNocheFecha) FROM gestionNocheFecha)")
    gestionNocheFecha ultimaFechaVitente();


    @Query("SELECT SUM (cantidad) FROM entradasNocheVigente")// aca la cantidad de entradas vendidas en la noche
    int entradasToda();

    @Update
    void cerrarNoche(gestionNocheFecha gestionNocheFecha);

    @Query("DELETE FROM entradasNocheVigente")
    void eliminarEntradasNocheActual();
}
