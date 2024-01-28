package sivani2020.blogspot.tab_layout_without_adapter;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class ChannelClass extends Application {
    public static final String Channelid="MyChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel=new NotificationChannel(
                    Channelid,"service example",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);
        }
    }
}
