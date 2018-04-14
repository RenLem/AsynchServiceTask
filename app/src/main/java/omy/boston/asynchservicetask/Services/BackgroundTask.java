package omy.boston.asynchservicetask.Services;

import android.app.Service;
import android.os.AsyncTask;
import android.widget.Toast;

import omy.boston.asynchservicetask.R;

/**
 * Created by LosFrancisco on 25-Jan-17.
 */

public class BackgroundTask extends AsyncTask<Integer, Integer, String> {

    private Service service;

    public  BackgroundTask(Service service){
        this.service = service;
    }

    private void simulDugogProcesa(){
        try {
            Thread.sleep(2980);
        }catch (InterruptedException e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(service, "OnPreExecute - metoda", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Integer... params) {
        int taskCount = params[0];
        for (int i = 0; i < taskCount; i++){
            simulDugogProcesa();
            publishProgress((int)((i + 1) / (float)taskCount * 100));
        }

        String result = taskCount + " " + service.getResources().getString(R.string.zavrseno);
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        String tekst = values[0] + " % " + service.getResources().getString(R.string.zavrseno);
        Toast.makeText(service, tekst, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(service, s, Toast.LENGTH_SHORT).show();
        service.stopSelf();
    }
}
