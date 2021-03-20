package com.example.exam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.exam.BeProud;

import java.util.List;

@Dao
public interface BeProudDao {

    @Insert
    void insert(BeProud currency);

    @Update
    void update(BeProud currency);

    @Delete
    void delete(BeProud currency);

    @Query("SELECT * FROM beProud_table ORDER BY title ASC")
    List<BeProud> getAllCurrencies(); //LiveData<...>?

   /*@Query("SELECT * FROM currency_table WHERE title LIKE :currencyText")
    public List<Currency> getCurrenciesFilter (String currencyText);*/


}
