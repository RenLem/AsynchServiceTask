package omy.boston.asynchservicetask.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AsynchService extends Service {
    public AsynchService() {
    }

    private static final int TASK_COUNT = 7;
    private boolean serviceOn = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!serviceOn){
            Toast.makeText(this, "Servis je pokrenut", Toast.LENGTH_SHORT).show();
            BackgroundTask bt = new BackgroundTask(this);
            bt.execute(TASK_COUNT);
            serviceOn = true;
        }else {
            Toast.makeText(this, "Servis je već odavno pokrenut!", Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
