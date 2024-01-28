package sivani2020.blogspot.tab_layout_without_adapter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment{
    public static TimePickerFragment newInstance(){
        return new TimePickerFragment();
    }

    private DateFormat DateFormate;
    private TimePickerDialog.OnTimeSetListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
       // return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener)
         //     getActivity(),hour,minute,android.text.format.DateFormat.is24HourFormat(getActivity()));
       /* return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        },hour,minute,DateFormate.is24HourFormat(getActivity()));
    }
*/
        return new TimePickerDialog(getActivity(),listener,hour,minute,
                android.text.format.DateFormat.is24HourFormat(getActivity()));
    //@Override
    //public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener){
        this.listener=listener;
    }
}
