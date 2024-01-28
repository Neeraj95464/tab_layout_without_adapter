package sivani2020.blogspot.tab_layout_without_adapter;

import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class c extends Fragment implements TimePickerDialog.OnTimeSetListener {
    TimePicker timePicker;
    TextView f1,s1;
    MediaPlayer m;
    PowerConnectionReceiver pcr,pcr2;
    Button button;
    Button Alarmstop;
    TextView textView1;
    Ringtone ringtone;
    BroadcastReceiver batteryBroadcast;
    IntentFilter intentFilter;
    TextView level,voltage,health,batteryType,chargingSource,tempareture,chargingStatus;


    public c() {
        // Required empty public constructor
    }

    public static c newInstance(String param1, String param2) {
        c fragment = new c();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_c, container, false);



       // timePicker=view.findViewById(R.id.timepicker);
        final Intent intent=new Intent(getActivity(),BatteryAlarm.class);

        m=MediaPlayer.create(getContext(),R.raw.song);
        ringtone= RingtoneManager.getRingtone(getActivity().getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        //textView=view.findViewById(R.id.textview);
        button=view.findViewById(R.id.SetAlarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  TimePickerFragment.newInstance().setListener();
                DialogFragment timepicker=new TimePickerFragment();
                timepicker.show(getChildFragmentManager(),"time picker");
                //timepicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });
        //TextView textView=view.findViewById(R.id.textview);
        level=view.findViewById(R.id.level);
        voltage=view.findViewById(R.id.voltage);
        health=view.findViewById(R.id.health);
        batteryType=view.findViewById(R.id.type);
        chargingSource=view.findViewById(R.id.ChargingSource);
        tempareture=view.findViewById(R.id.temperature);
        chargingStatus=view.findViewById(R.id.ChargingStatus);
        Alarmstop=view.findViewById(R.id.StopButton1);
        Alarmstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
            }
        });

        intentFilterAndBroadcast();

        return view;
    }
    private void intentFilterAndBroadcast() {
        intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        batteryBroadcast=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                    level.setText(String.valueOf(intent.getIntExtra("level",0))+"%");

                    float voltTemp=(float)(intent.getIntExtra("voltage",0)*0.001);
                    voltage.setText(voltTemp+"V");

                    setHealth(intent);

                    batteryType.setText(intent.getStringExtra("technology"));

                    getChargingSource(intent);

                    float tempTemp=(float) intent.getIntExtra("temperature",-1)/10;
                    tempareture.setText(tempTemp+"°C");

                    setChargingStatus(intent);

                    setAlarm(intent);

                }
            }
        };

        getActivity().registerReceiver(batteryBroadcast,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    private void setAlarm(Intent intent) {
        int alarm=intent.getIntExtra("level",0);
        if (99<alarm){
            ringtone.play();
        }
    }
    private void setChargingStatus(Intent intent) {

        int statusTemp=intent.getIntExtra("status",-1);

        switch (statusTemp){
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                chargingStatus.setText("Unkonwn");
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                chargingStatus.setText("Charging");
                m.start();
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                chargingStatus.setText("Discharging");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                chargingStatus.setText("Not Charging");
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                chargingStatus.setText("Full");
                break;

            default:chargingStatus.setText("Null");
        }
    }
    private void getChargingSource(Intent intent) {

        int sourceTemp=intent.getIntExtra("plugged",-1);

        switch (sourceTemp){
            case BatteryManager.BATTERY_PLUGGED_AC:
                chargingSource.setText("AC");
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                chargingSource.setText("USB");
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                chargingSource.setText("Wireless");
                break;
            default: chargingSource.setText("Null");
        }
    }
    private void setHealth(Intent intent) {

        int val=intent.getIntExtra("health",0);

        switch (val){

            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                health.setText("Unkonwn");
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                health.setText("Good");
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                health.setText("Over Heat");
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                health.setText("Dead");
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                health.setText("Over Voltage");
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                health.setText("Unspecified failure");
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                health.setText("Cold");


        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        textView1.setText("Hour: "+hourOfDay+"Munute: "+minute);
    }
//    m=MediaPlayer.create(this,R.raw.song);

}