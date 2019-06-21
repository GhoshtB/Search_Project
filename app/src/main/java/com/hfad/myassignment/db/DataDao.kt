package com.hfad.myassignment.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: Datas)

    @Query("SELECT * FROM data WHERE fundname =:fundnames")
    fun getDataByfundname(fundnames:String):LiveData<List<Datas>>
    @Query("SELECT * FROM data ORDER BY id DESC")
    fun getDataByfundid():LiveData<List<Datas>>


}