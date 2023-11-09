package com.example.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.function.Function;

import kotlin.jvm.functions.Function1;

public class ElementosViewModel extends AndroidViewModel {
    LiveData<List<Elemento>> obtener(){
        return elementosRepositorio.obtener();
    }
    LiveData<List<Elemento>> masValorados(){
        return elementosRepositorio.masValorados();
    }
    ElementosRepositorio elementosRepositorio;
    MutableLiveData<Elemento> elementoSeleccionado = new MutableLiveData<>();

    public ElementosViewModel(@NonNull Application application) {
        super(application);

        elementosRepositorio = new ElementosRepositorio(application);
    }
    void seleccionar(Elemento elemento){
        elementoSeleccionado.setValue(elemento);
    }

    MutableLiveData<Elemento> seleccionado(){
        return elementoSeleccionado;
    }

    void insertar(Elemento elemento){
        elementosRepositorio.insertar(elemento);
    }

    void eliminar(Elemento elemento){
        elementosRepositorio.eliminar(elemento);
    }

    void actualizar(Elemento elemento, float valoracion){
        elementosRepositorio.actualizar(elemento, valoracion);
    }

    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Elemento>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function1<String, LiveData<List<Elemento>>>() {
        @Override
        public LiveData<List<Elemento>> invoke(String input) {
            return elementosRepositorio.buscar(input);
        }
    });

    LiveData<List<Elemento>> buscar(){
        return resultadoBusqueda;
    }

    void establecerTerminoBusqueda(String t){
        terminoBusqueda.setValue(t);
    }
}
