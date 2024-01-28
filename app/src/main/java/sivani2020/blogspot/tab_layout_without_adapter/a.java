package sivani2020.blogspot.tab_layout_without_adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class a extends Fragment implements View.OnClickListener{

    public a() {
    }
    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    public static a newInstance(String param1, String param2) {
        a fragment = new a();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_a, container, false);

        resultTv = (TextView)view.findViewById(R.id.result_tv);
        solutionTv =(TextView)view.findViewById(R.id.solution_tv);
        buttonC=view.findViewById(R.id.button_c);
        buttonC.setOnClickListener(this);
        buttonBrackClose=view.findViewById(R.id.button_close_bracket);
        buttonBrackClose.setOnClickListener(this);
        buttonBrackOpen=view.findViewById(R.id.button_open_bracket);
        buttonBrackOpen.setOnClickListener(this);
        buttonDivide=view.findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(this);
        buttonMinus=view.findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(this);
        buttonMultiply=view.findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(this);
        buttonPlus=view.findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(this);
        buttonEquals=view.findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(this);
        button0=view.findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        button1=view.findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2=view.findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3=view.findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4=view.findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5=view.findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6=view.findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7=view.findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8=view.findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9=view.findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        buttonDot=view.findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(this);
        buttonAC=view.findViewById(R.id.button_ac);
        buttonAC.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonText=button.getText().toString();
        String datatocalculate=solutionTv.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("X")){
            if (datatocalculate.length()>0){
                StringBuilder sb=new StringBuilder(solutionTv.getText());
                sb.deleteCharAt(solutionTv.getText().length()-1);
                datatocalculate=sb.toString();
                solutionTv.setText(datatocalculate);
                resultTv.setText(datatocalculate);
            }
            return;
        }
        else{
            datatocalculate=datatocalculate+buttonText;
        }


        solutionTv.setText(datatocalculate);
        String finalResult=getResult(datatocalculate);
        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}