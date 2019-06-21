package com.hfad.myassignment.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {
    private DataDao noteDao;
    private LiveData<List<Datas>> allNotes;

  public  DataRepository(Application application){
LoanDataBase dataBase=LoanDataBase.getInstance(application);
noteDao=dataBase.dataDao();
allNotes=noteDao.getDataByfundid();
    }

    public LiveData<List<Datas>> getAllDatas(String name){
        return noteDao.getDataByfundname(name);
    }
    public LiveData<List<Datas>> getAllData(){
        return allNotes;
    }
    public void insert(Datas note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    class InsertNoteAsyncTask extends AsyncTask<Datas,Void,Void>{
        private DataDao dataDao;

        public InsertNoteAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Datas...datas) {
            noteDao.insertData(datas[0]);
            return null;
        }
    }
}
