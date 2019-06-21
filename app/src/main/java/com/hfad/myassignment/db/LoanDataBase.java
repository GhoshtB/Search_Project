package com.hfad.myassignment.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Datas.class},version = 1)
public abstract class LoanDataBase extends RoomDatabase {

    public abstract DataDao dataDao();

    public static LoanDataBase INSTANCE;

    public static LoanDataBase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),LoanDataBase.class,"datas.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
