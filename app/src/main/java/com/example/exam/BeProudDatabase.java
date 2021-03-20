package com.example.exam;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.exam.BeProud;
import com.example.exam.BeProudDao;
import com.example.exam.R;

@Database(entities = BeProud.class, version = 1)
public abstract class BeProudDatabase extends RoomDatabase {

    private static BeProudDatabase instance;

    public abstract BeProudDao beProudDao();

    public static synchronized BeProudDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BeProudDatabase.class, "currency_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(rooCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback rooCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private BeProudDao beProudDao;

        private PopulateDbAsyncTask (BeProudDatabase db){
            beProudDao = db.beProudDao();
        }

        @Override
        public Void doInBackground(Void... voids) {
            beProudDao.insert(new BeProud());
            beProudDao.insert(new BeProud());
            beProudDao.insert(new BeProud());
            beProudDao.insert(new BeProud());
            beProudDao.insert(new BeProud());
            beProudDao.insert(new BeProud());

            return null;
        }
    }
}
