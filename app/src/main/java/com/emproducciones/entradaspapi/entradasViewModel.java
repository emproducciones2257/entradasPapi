package com.emproducciones.entradaspapi;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.emproducciones.entradaspapi.db.entidad.gestionNocheFecha;
import com.emproducciones.entradaspapi.db.entidad.nocheVigente;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;

import java.util.List;

public class entradasViewModel extends AndroidViewModel {
    private LiveData<List<resumenNoches>> allEntradas;
    private LiveData<List<resumenNoches>> allNochesEntradas;
    private entradaRepository entradaRepository;
    private gestionNocheFecha temp;

    public entradasViewModel(Application application){
        super(application);
        entradaRepository = new entradaRepository(application);
    }

    // El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<resumenNoches>> getAllEntradas (){
        return allEntradas=entradaRepository.getAllNochesConEntradas();}

    public LiveData<List<resumenNoches>> getAllNochesEntradas (){return allNochesEntradas=entradaRepository.getAllNochesConEntradas();}

    // el fragmente que inserte una nueva nota, debera comunicar a este viewModel
    public void insertarEntrada(nocheVigente nuevoIngresoEntrada){entradaRepository.registrarVenta(nuevoIngresoEntrada);}

    public void insertarFinNoche(resumenNoches resumenNoches){entradaRepository.registrarFinNoche(resumenNoches);}

    public gestionNocheFecha recuperarUltimoRegistroFecha(){return temp = entradaRepository.obtenerUltimaFecha();}

    public void registrarFecha(gestionNocheFecha noche) {entradaRepository.registrarNoche(noche);}
}
