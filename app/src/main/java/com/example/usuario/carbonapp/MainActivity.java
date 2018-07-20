package com.example.usuario.carbonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    /**
     * Función encargada de inicalizar los componentes
     */
    private void initialize(){
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    /**
     * Función encargada de capturar los eventos de click
     * @param v objeto de la vista en la cuál se hizo click
     */
    @Override
    public void onClick(View v) {
        Integer id = v.getId();
        switch (id){
            case R.id.btnStart: {
                startActivity(new Intent(this, ActivityQuestions.class));
                break;
            }
        }
    }
}
