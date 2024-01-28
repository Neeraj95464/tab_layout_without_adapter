package sivani2020.blogspot.tab_layout_without_adapter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BatteryAlarm extends Service {
     Integer alarmHour;
     Integer alarmMinut;
    private Ringtone ringtone;
     Timer t=new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Context context=getApplicationContext();
        Intent intent2=new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent2);
        }


        alarmHour=intent.getIntExtra("alarmHour",0);
        alarmMinut=intent.getIntExtra("alarmMinut",0);

        ringtone= RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (Calendar.getInstance().getTime().getHours()==alarmHour &&
                        Calendar.getInstance().getTime().getMinutes()==alarmMinut){
                    ringtone.play();
                }
                else {
                    ringtone.stop();
                }

            }
        },0,2000);


        //startService s=new startService();
        return super.onStartCommand(intent, flags, startId);
        //return START_NOT_STICKY;
    }
    public void onDestroy(){
        ringtone.stop();
        t.cancel();

        super.onDestroy();
    }
}
