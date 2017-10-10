package com.mannydev.dailyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FirstActivity extends AppCompatActivity {

    private static final String APP_CACHE = "cache";
    private static final String USER_NAME = "userName";
    private static final String ZODIAC = "zodiac";
    private static final String CITY = "location";
    private SharedPreferences appCache;

    @BindView(R.id.txtSettings)
    TextView txtSettings;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.txtZodiac)
    TextView txtZodiac;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.btnAccept)
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        ButterKnife.bind(this);
        appCache = getSharedPreferences(APP_CACHE, MODE_PRIVATE);
        if (!appCache.contains(USER_NAME)) {
            etName.setText(appCache.getString(USER_NAME,null));
            etCity.setText(appCache.getString(CITY,null));
        }

    }

    @OnClick(R.id.btnAccept)
    public void onViewClicked() {
        Intent intent = new Intent(this,MainActivity.class);
        SharedPreferences.Editor editor = appCache.edit();
        editor.putString(USER_NAME,etName.getText().toString());
        editor.putString(CITY,etCity.getText().toString());
        editor.putString(ZODIAC,spinner.getSelectedItem().toString());
        editor.apply();
        startActivity(intent);
    }
}
