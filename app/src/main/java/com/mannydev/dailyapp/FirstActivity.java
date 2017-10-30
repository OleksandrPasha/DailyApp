package com.mannydev.dailyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FirstActivity extends AppCompatActivity {

    private static final String APP_CACHE = "cache";
    private static final String USER_NAME = "userName";
    private static final String ZODIAC = "zodiac";
    private static final String CITY = "location";
    private SharedPreferences appCache;
    private InterstitialAd mInterstitialAd;
    private AdView adView;

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
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8078146669032188/7421807292");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        appCache = getSharedPreferences(APP_CACHE, MODE_PRIVATE);
        if (!appCache.contains(USER_NAME)) {
            etName.setText(appCache.getString(USER_NAME,null));
            etCity.setText(appCache.getString(CITY,null));
        }
    }

    @Override
    protected void onPause() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onPause();
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
