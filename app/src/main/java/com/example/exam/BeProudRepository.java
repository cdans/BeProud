package com.example.exam;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class BeProudRepository {
    private BeProudDao beProudDao;
    private List<BeProud> allBeProud;

    public BeProudRepository(Application application){
        BeProudDatabase database = BeProudDatabase.getInstance(application);
        beProudDao = database.beProudDao();
        allBeProud = beProudDao.getAllCurrencies();
    }

    public void insert (BeProud beProud){
        new InsertCurrencyAsyncTask(beProudDao).execute(beProud);
    }

    public void update (BeProud beProud){
        new UpdateCurrencyAsyncTask(beProudDao).execute(beProud);
    }

    public void delete (BeProud beProud){
        new DeleteCurrencyAsyncTask(beProudDao).execute(beProud);

    }

    public List<BeProud> getAllCurrencies(){
        return allBeProud;
    }

    private static class InsertCurrencyAsyncTask extends AsyncTask<BeProud, Void, Void>{
        private BeProudDao beProudDao;

        private InsertCurrencyAsyncTask(BeProudDao currencyDao){
            this.beProudDao = currencyDao;
        }

        @Override
        protected Void doInBackground(BeProud... currencies) {
            beProudDao.insert(currencies[0]);
            return null;
        }
    }

    private static class UpdateCurrencyAsyncTask extends AsyncTask<BeProud, Void, Void>{
        private BeProudDao beProudDao;

        private UpdateCurrencyAsyncTask(BeProudDao currencyDao){
            this.beProudDao = currencyDao;
        }

        @Override
        protected Void doInBackground(BeProud... currencies) {
            beProudDao.update(currencies[0]);
            return null;
        }
    }

    private static class DeleteCurrencyAsyncTask extends AsyncTask<BeProud, Void, Void>{
        private BeProudDao beProudDao;

        private DeleteCurrencyAsyncTask(BeProudDao beProudDao){
            this.beProudDao = beProudDao;
        }

        @Override
        protected Void doInBackground(BeProud... currencies) {
            beProudDao.delete(currencies[0]);
            return null;
        }
    }
}
