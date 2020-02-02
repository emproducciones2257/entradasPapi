package com.emproducciones.entradaspapi;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.emproducciones.entradaspapi.db.DAO.entradasDAO;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.nocheVigente;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;
import com.emproducciones.entradaspapi.db.entradasRoomDB;
import java.util.List;

public class entradaRepository {

    private entradasDAO entradasDAO;// creo instancia para acceder al DAO
    private LiveData<List<nocheVigente>> allEntradas;
    private LiveData<List<resumenNoches>> todasLasNoches;
    private gestionNocheFecha temp;
    private entradasRoomDB db;
    private Application application;

    public entradaRepository(Application application){
        this.application=application;
        db = entradasRoomDB.getDatabase(application);// obtengo una instancia de la base de datos
        entradasDAO = db.entradaDao();// obtengo la instancia de DAO que tengo en la base...no entendido del todo
    }

    public void cerrarConexion(){
        db.close();
    }

    public void abrir(){
        db = entradasRoomDB.getDatabase(application);
    }

    public LiveData<List<nocheVigente>> getAll(){return allEntradas = entradasDAO.entradasTodas();}

    public LiveData<List<resumenNoches>> getAllNochesConEntradas(){return todasLasNoches = entradasDAO.nochesEntradasTodas();}

    public int obtenerCantidadEntradasNocheVigente(){
        return entradasDAO.entradasToda();
    }

    public void registrarVenta (nocheVigente noche){
        new insertarVentaEntradaAsyncTask(entradasDAO).execute(noche);
    }

    public void registrarFinNoche(resumenNoches resumenNoches){
        new insertarFinNocheAsyncTask(entradasDAO).execute(resumenNoches);
    }

    public void cerrarNoche(gestionNocheFecha nroNoche){
       new cerrarNocheAsyncTask(entradasDAO).execute(nroNoche);
    }

    public gestionNocheFecha obtenerUltimaFecha() {
        return temp=entradasDAO.ultimaFechaVitente();
    }

    public void registrarNoche(gestionNocheFecha noche) {
        new registrarFechaAsyntack(entradasDAO).execute(noche);

    }

    public void eliminarEntradasNocheActual() {
        entradasDAO.eliminarEntradasNocheActual();
    }

    //public void eliminarEntradasNocheVigente() {entradasDAO.eliminarEntradasNocheVigente();}

    private static class insertarVentaEntradaAsyncTask extends AsyncTask<nocheVigente, Void, Void>{

        private entradasDAO asintakDAO;

        insertarVentaEntradaAsyncTask(entradasDAO DAO){
            asintakDAO = DAO;
        }

        @Override
        protected Void doInBackground(nocheVigente... nocheVigentes) {
            asintakDAO.insertarEntradaNocheVigente(nocheVigentes[0]);
            return null;
        }
    }

    private static class insertarFinNocheAsyncTask extends AsyncTask<resumenNoches, Void, Void>{
        private entradasDAO asintakDAO;
        insertarFinNocheAsyncTask(entradasDAO DAO){
            asintakDAO = DAO;
        }

        @Override
        protected Void doInBackground(resumenNoches... resumenNoches) {
            asintakDAO.insertarFinNoche(resumenNoches[0]);
            return null;
        }
    }

    private static class registrarFechaAsyntack extends AsyncTask<gestionNocheFecha, Void, Void>{
        private entradasDAO asintakDAO;
        registrarFechaAsyntack(entradasDAO DAO){
            asintakDAO = DAO;
        }
        @Override
        protected Void doInBackground(gestionNocheFecha... gestionNocheFechas) {
            asintakDAO.registrarNoche(gestionNocheFechas[0]);
            return null;
        }
    }

    private static class cerrarNocheAsyncTask extends AsyncTask<gestionNocheFecha, Void, Void>{
        cerrarNocheAsyncTask(entradasDAO DAO){
           asintakDAO=DAO;
        }

        private entradasDAO asintakDAO;

        @Override
        protected Void doInBackground(gestionNocheFecha... gestionNocheFechas) {
            asintakDAO.cerrarNoche(gestionNocheFechas[0]);
            return null;
        }
    }
}
