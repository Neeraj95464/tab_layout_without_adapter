package sivani2020.blogspot.tab_layout_without_adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Ringtone ringtone1;
    PowerConnectionReceiver pcr,pcr2;
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,ServiceExample.class);
        ContextCompat.startForegroundService(this,intent);

        tabLayout=findViewById(R.id.tab_layout);
        ringtone1= RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        viewPager2=findViewById(R.id.vpager);

        myViewPagerAdapter=new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        IntentFilter ifilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus=registerReceiver(null,ifilter);
        pcr=new PowerConnectionReceiver();
        pcr.onReceive(getApplicationContext(),batteryStatus);
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            unregisterReceiver(pcr);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter ifilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus=getApplicationContext().registerReceiver(null,ifilter);
        pcr2=new PowerConnectionReceiver();
        pcr2.onReceive(getApplicationContext(),batteryStatus);
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            unregisterReceiver(pcr2);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
    private void showText(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}