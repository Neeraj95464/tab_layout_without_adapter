package sivani2020.blogspot.tab_layout_without_adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
    Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {
        ringtone= RingtoneManager.getRingtone(context.getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        boolean isCharging=false;
        int status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        if(status==2){
            isCharging=true;
            Toast.makeText(context,"Status :"+status+"\nCharging : "+isCharging,Toast.LENGTH_SHORT).show();

            //ringtone.play();
            setAlarm(intent);
        }
        setAlarm(intent);
    }
    private void setAlarm(Intent intent) {
        int alarm=intent.getIntExtra("level",0);
        if (99<alarm){
            ringtone.play();
        }
    }

}
