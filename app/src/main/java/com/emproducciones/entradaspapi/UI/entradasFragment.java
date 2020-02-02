package com.emproducciones.entradaspapi.UI;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.emproducciones.entradaspapi.R;
import com.emproducciones.entradaspapi.db.entidad.resumenNoches;
import com.emproducciones.entradaspapi.entradasViewModel;
import java.util.ArrayList;
import java.util.List;


public class entradasFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    MyentradasRecyclerViewAdapter adapter;
    ArrayList<resumenNoches> entradas;
    entradasViewModel viewModel;



    public entradasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entradas_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            entradas = new ArrayList<>();

            adapter = new MyentradasRecyclerViewAdapter(entradas,getActivity());
            recyclerView.setAdapter(adapter);
        }
        lanzarViewModel();
        return view;
    }

    void lanzarViewModel (){
        viewModel = ViewModelProviders.of(this).get(entradasViewModel.class);

        viewModel.getAllEntradas().observe(getActivity(), new Observer<List<resumenNoches>>() {
            @Override
            public void onChanged(List<resumenNoches> resumenNoches) {
                adapter.setNuevasEntradas(resumenNoches);
            }
        }) ;

    }
}
