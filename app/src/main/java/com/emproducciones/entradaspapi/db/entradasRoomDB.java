package com.emproducciones.entradaspapi.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.emproducciones.entradaspapi.db.DAO.entradasDAO;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.nocheVigente;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;

@Database(entities = {nocheVigente.class,
                resumenNoches.class,
                gestionNocheFecha.class},version = 1, exportSchema = false)

public abstract class entradasRoomDB extends RoomDatabase {

    public abstract entradasDAO entradaDao();// creo una instancia de la interfaz entradasDAO

    public static volatile entradasRoomDB INSTANCE;

    public static  entradasRoomDB getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (entradasRoomDB.class){
                if(INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            entradasRoomDB.class,"entrada_db")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new iniciarDBAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class iniciarDBAsync extends AsyncTask<Void, Void, Void> {
        private final entradasDAO entradasDAO;

        public iniciarDBAsync(entradasRoomDB instance) {
            entradasDAO = instance.entradaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            entradasDAO.registrarNoche(new gestionNocheFecha().cargaInicialParaLaBase());
            System.out.println("CARGUE");
            return null;
        }
    }
}

