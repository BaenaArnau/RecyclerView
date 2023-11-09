package com.example.recyclerview;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerValoracionFragment extends RecyclerElementosFragment {
    @Override
    LiveData<List<Elemento>> obtenerElementos() {
        return elementosViewModel.masValorados();
    }
}
