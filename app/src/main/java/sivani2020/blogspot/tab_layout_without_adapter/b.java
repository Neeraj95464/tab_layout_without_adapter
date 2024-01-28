package sivani2020.blogspot.tab_layout_without_adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class b extends Fragment {
    Spinner spn1, spn2,spn11,spn21;
    String firstunit[], secondunit[];
    String firstunit1[],secondunit1[];
    Button convert,convert1;
    TextView inputj,inputj1;
    EditText inputda,inputda1;

    public b() {
        // Required empty public constructor
    }

    public static b newInstance(String param1, String param2) {
        b fragment = new b();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        convert = view.findViewById(R.id.conversionButton);
        convert1 = view.findViewById(R.id.conversionButton1);
        convert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratio1[] = {1.0f,
                        0.01f,
                        0.0001f,
                        0.000001f,
                        0.00155f,
                        0.000011f,
                        0.000001f,
                        2.47110000000000f,
                        100000000.0f,
                        10000000000f,
                        1000000000000f,
                        9.8842000000000f,
                        2.471100000000f,
                        1.1861000000000f,
                        6.17760000000000f};
                int index11 = spn11.getSelectedItemPosition();
                int index21 = spn21.getSelectedItemPosition();
                float value1 = Float.parseFloat(inputda1.getText().toString());

                float result = value1 / ratio1[index11] * ratio1[index21];
                String resultstr1 = String.valueOf(result);

                if (resultstr1.endsWith(".0")) {
                    resultstr1 = resultstr1.replace(".0", "");
                    inputj1.setText(resultstr1);
                } else {
                    inputj1.setText(resultstr1 + "");
                }

            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert(v);
                float ratio[] = {10.0f,
                        1.0f,
                        0.1f,
                        0.01f,
                        0.393701f,
                        0.032808f,
                        0.010936f,
                        0.005468f,
                        0.000006f,
                        0.00001f,
                        0.000005f,
                        0.04971f,
                        0.000497f,
                        0.00005f,
                        393.700787f};
                //convert(v);



                int index1 = spn1.getSelectedItemPosition();
                int index2 = spn2.getSelectedItemPosition();
                float value = Float.parseFloat(inputda.getText().toString());

                float result = value / ratio[index1] * ratio[index2];
                String resultstr = String.valueOf(result);

                if (resultstr.endsWith(".0")) {
                    resultstr = resultstr.replace(".0", "");
                    inputj.setText(resultstr);
                } else {
                    inputj.setText(resultstr + "");
                }
            }
        });

        spn1 = view.findViewById(R.id.convert_from_dropdown_menu);
        spn2 = view.findViewById(R.id.convert_to_dropdown_menu);
        inputj = view.findViewById(R.id.conversionRateText);
        inputda = view.findViewById(R.id.amountToConvertValueEditText);

        spn11 = view.findViewById(R.id.convert_from_dropdown_menu1);
        spn21 = view.findViewById(R.id.convert_to_dropdown_menu1);
        inputj1 = view.findViewById(R.id.conversionRateText1);
        inputda1 = view.findViewById(R.id.amountToConvertValueEditText1);


        ArrayAdapter<CharSequence> dapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.secondUnit, android.R.layout.simple_spinner_item);
        dapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn2.setAdapter(dapter);

        ArrayAdapter<CharSequence> dapter1 = ArrayAdapter.createFromResource(this.getActivity(), R.array.secondUnit1, android.R.layout.simple_spinner_item);
        dapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn21.setAdapter(dapter1);

        // spn2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.firstUnit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(), R.array.firstUnit1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn11.setAdapter(adapter1);
        // spn1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        return view;
    }
}