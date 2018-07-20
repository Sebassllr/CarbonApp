package com.example.usuario.carbonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityResult extends AppCompatActivity {

    private Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initializer();
    }

    private void initializer(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            result = bundle.getDouble("total");
            TextView tv = findViewById(R.id.tvResult);
            Integer inte = (int) Math.floor(result);
            tv.setText(inte.toString());
            setText();
        }
    }

    /**
     *
     */
    private void setText(){
        String resultText = "Consumo ";
        if(result < 6000){
            resultText += "Muy bajo";
        }else if(result < 16000){
            resultText += "Bajo";
        }else if(result < 20000){
            resultText += "Medio";
        }else{
            resultText += "Muy alto";
        }
        TextView tv = findViewById(R.id.tvConsume);
        tv.setText(resultText);
    }
}
