package com.example.usuario.carbonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityResult extends AppCompatActivity implements View.OnClickListener{

    private Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initializer();
    }

    private void initializer(){
        findViewById(R.id.addReminder).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addReminder){
            Calendar cal = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", cal.getTimeInMillis() + 86400000);
            intent.putExtra("allDay", true);
            intent.putExtra("rrule", "FREQ=WEEKLY;COUNT=10;WKST=SU");
            intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000+ 86400000);
            intent.putExtra("title", "¡Ayudar al planeta!");
            startActivity(intent);
        }
    }
}
