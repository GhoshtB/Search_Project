package com.hfad.myassignment.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hfad.myassignment.model.LoanData;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<Datas>> allNotes;

    public DataViewModel(@NonNull Application application) {
        super(application);
        repository=new DataRepository(application);
        allNotes=repository.getAllData();
    }

    public void insert(Datas note) {
        repository.insert(note);
    }

    public LiveData<List<Datas>> getAllDatas(String name) {
        return repository.getAllDatas(name);
    }
    public LiveData<List<Datas>> getAllData(){
        return allNotes;
    }
}
