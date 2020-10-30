package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Fragment {


    private Spinner spinner;
    private EditText heightTxt;
    private EditText weightTxt;
    private EditText ageTxt;
    private TextView resultTxt;

    private View itemMenuNews;
    private View itemMenuInfo;

    private static final int CONST_W = 10;
    private static final double CONST_H = 6.25;
    private static final int CONST_A = 5;
    private static final int CONST_FOR_MAN = 5;
    private static final int CONST_FOR_WOMAN = 161;
    private static final double[] CONST_PA = {1.375, 1.55, 1.725};

    private boolean isMan = true;
    private int positionElemet = 0;

    //(10 x weight (kg) + 6.25 x height (cm) – 5 x age (year) + 5) x A man
    //(10 x weight (kg) + 6.25 x height (cm) – 5 x age (year) – 161) x A woman


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);

        itemMenuNews = (View) findViewById(R.id.news);
        itemMenuInfo = (View) findViewById(R.id.information);

        itemMenuInfo.setOnClickListener(v -> toRefer(v));
        itemMenuNews.setOnClickListener(v -> toNews(v));

        String[] spinnerElements = {"Minimum activity", "Average activity", "High activity"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerElements);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        heightTxt = (EditText) findViewById(R.id.editTextHeight);
        weightTxt = (EditText) findViewById(R.id.editTextWeight);
        ageTxt = (EditText) findViewById(R.id.editTextAge);

        resultTxt = (TextView) findViewById(R.id.textResult);


        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Physical activity");
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Position = " + spinnerElements[position], Toast.LENGTH_SHORT).show();
                positionElemet = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void calculation(View view) {

        if(heightTxt.getText().length()>0 && weightTxt.getText().length()>0 && ageTxt.getText().length()>0){
        double h = Double.valueOf(heightTxt.getText().toString());
        double w = Double.valueOf(weightTxt.getText().toString());
        int a = Integer.valueOf(ageTxt.getText().toString());
        double result = 0;
        double pa = CONST_PA[positionElemet];
        double ostov = CONST_W*w+CONST_H*h-CONST_A*a;

        if(isMan){
            result = (ostov+CONST_FOR_MAN)*pa;
            System.out.println("--------- MAN("+CONST_W+" * "+w+" + "+CONST_H+" * "+h+" - "+CONST_A+" * "+a+" + "+CONST_FOR_MAN+") * "+pa);
        }else{
            result = (ostov-CONST_FOR_WOMAN)*pa;
            System.out.println("-------- WOMAN("+CONST_W+" * "+w+" + "+CONST_H+" * "+h+" - "+CONST_A+" * "+a+" - "+CONST_FOR_WOMAN+") * "+pa);
        }
        resultTxt.setText(String.valueOf(Math.round(result)));
        }else{
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSelect(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioM:
                if (checked)
                    isMan = true;
                    break;
            case R.id.radioW:
                if (checked)
                    isMan = false;
                    break;
        }

    }

    public void toRefer(View view) {
        Intent intent = new Intent(this, Informer.class);
        startActivity(intent);
    }

    public void toNews(View view) {
        Intent intent = new Intent(this, NewsLentActivity.class);
        startActivity(intent);
    }
}