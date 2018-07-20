package com.example.usuario.carbonapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityWhatIsIt extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_it);
        initilizer();
    }

    private void initilizer(){
        findViewById(R.id.btnActivity).setOnClickListener(this);
    }

    private void backToActivity(){
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }

    @Override
    public void onClick(View v) {
        backToActivity();
    }
}
