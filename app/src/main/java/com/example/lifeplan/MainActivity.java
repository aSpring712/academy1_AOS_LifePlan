package com.example.lifeplan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String IdealType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("생활 계획");

        final CheckBox chkCoding = (CheckBox) findViewById(R.id.chkCoding);
        final CheckBox chkReading = (CheckBox) findViewById(R.id.chkReading);
        final CheckBox chkEnglish = (CheckBox) findViewById(R.id.chkEnglish);
        final CheckBox chkWorking = (CheckBox) findViewById(R.id.chkWorkout);

        final EditText edtSleep = (EditText) findViewById(R.id.edtSleep);
        final EditText edtCoding = (EditText) findViewById(R.id.edtCoding);
        final EditText edtReading = (EditText) findViewById(R.id.edtReading);
        final EditText edtEnglish = (EditText) findViewById(R.id.edtEnglish);
        final EditText edtWorkout = (EditText) findViewById(R.id.edtWorkout);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        final TextView tvSleep = (TextView) findViewById(R.id.tvSleep);
        final TextView tvDream = (TextView) findViewById(R.id.tvDream);
        final TextView tvIdeal = (TextView) findViewById(R.id.tvIdaltype);

        Button btnResult = (Button) findViewById(R.id.btnResult);

        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this, R.array.IdealType_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                IdealType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chkCoding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkCoding.isChecked()) {
                    edtCoding.setFocusableInTouchMode(true);
                } else {
                    edtCoding.setFocusable(false);
                    edtCoding.setText("");
                }
            }
        });
        chkReading.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkReading.isChecked()) {
                    edtReading.setFocusableInTouchMode(true);
                } else {
                    edtReading.setFocusable(false);
                    edtReading.setText("");
                }
            }
        });
        chkEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkEnglish.isChecked()) {
                    edtEnglish.setFocusableInTouchMode(true);
                } else {
                    edtEnglish.setFocusable(false);
                    edtEnglish.setText("");
                }
            }
        });
        chkWorking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkWorking.isChecked()) {
                    edtWorkout.setFocusableInTouchMode(true);
                } else {
                    edtWorkout.setFocusable(false);
                    edtWorkout.setText("");
                }
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(view.getWindowToken(),0);

                ArrayList<Integer> mID = new ArrayList<Integer>();

                int a0 = 0; // 수면

                int a1 = 0; // 코딩
                int a2 = 0; // 독서
                int a3 = 0; // 영어
                int a4 = 0; // 운동

                int sum = 0;

                if(edtSleep.getText().toString().equals("")) {
                    tvSleep.setText("1. 나는 ?시간 잠을 잡니다!");

                    new AlertDialog.Builder(MainActivity.this).setTitle("수면시간")
                            .setView(getLayoutInflater().inflate(R.layout.custom_layout, null))
                            .show();

                } else {
                    String str0 = edtSleep.getText().toString();
                    a0 = Integer.parseInt(str0);
                    tvSleep.setText("1. 나는 " + a0 + "시간 잠을 잡니다!");
                }

                if(chkCoding.isChecked() && !edtCoding.getText().toString().equals("")) {
                    String str1 = edtCoding.getText().toString();
                    a1 = Integer.parseInt(str1);
                    mID.add(R.drawable.coding);
                } else {
                    a1 = 0;
                }

                if(chkReading.isChecked() && !edtReading.getText().toString().equals("")) {
                    String str2 = edtReading.getText().toString();
                    a2 = Integer.parseInt(str2);
                    mID.add(R.drawable.reading);
                } else {
                    a2 = 0;
                }

                if(chkEnglish.isChecked() && !edtEnglish.getText().toString().equals("")) {
                    String str3 = edtEnglish.getText().toString();
                    a3 = Integer.parseInt(str3);
                    mID.add(R.drawable.english);
                } else {
                    a3 = 0;
                }

                if(chkWorking.isChecked() && !edtWorkout.getText().toString().equals("")) {
                    String str4 = edtWorkout.getText().toString();
                    a4 = Integer.parseInt(str4);
                    mID.add(R.drawable.workout);
                } else {
                    a4 = 0;
                }

                sum = a1 + a2 + a3 + a4;

                tvDream.setText("2. 나는 꿈을 위해 " + sum + "시간 투자합니다!");
                tvIdeal.setText("3. 나의 이상형은 " + IdealType + "입니다!");

                GridView gridView = (GridView) findViewById(R.id.gridView);
                gridView.setAdapter(new ImageAdapter(MainActivity.this, mID));
            }
        });
    }
}