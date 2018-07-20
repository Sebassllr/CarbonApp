package com.example.usuario.carbonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityQuestions extends AppCompatActivity implements View.OnClickListener {

    /**
     * Lista que contiene todos los text view de la lista
     */
    private ArrayList<TextView> textViews = new ArrayList<>();

    /**
     * Double que contiene el valor fuinal del cálculo
     */
    private Double finalValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initializer();
    }

    /**
     * Funcion encargada de inicializar los componentes de la interfaz
     */
    private void initializer() {
        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.btnCalculate).setOnClickListener(this);
        findViewById(R.id.cbCar).setOnClickListener(this);
    }

    /**
     * Función encargada de capturar el click de los botones
     *
     * @param v Elemento de la vista a la cuál se le hizo click
     */
    @Override
    public void onClick(View v) {
        Integer id = v.getId();
        switch (id) {
            case R.id.fab: {
                startActivityForResult(new Intent(this, ActivityWhatIsIt.class), 1);
                break;
            }
            case R.id.btnCalculate: {
                makeCalculations();
                Intent intent = new Intent(this, ActivityResult.class);
                intent.putExtra("total", finalValue);
                startActivity(intent);
                finalValue = 0.0;
                break;
            }
            case R.id.cbCar: {
                CheckBox cb = findViewById(R.id.cbCar);
                Boolean status = cb.isChecked();
                if (status) {
                    showLayout();
                } else {
                    hideLayout();
                }
                break;
            }
        }
    }

    /**
     * Funcion encargada de mostrar el linear layout
     */
    private void showLayout() {
        LinearLayout linearLayout = findViewById(R.id.llShow);
        linearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Función encargada de esconder el linear layout
     */
    private void hideLayout() {
        LinearLayout linearLayout = findViewById(R.id.llShow);
        linearLayout.setVisibility(View.GONE);
    }

    /**
     * Realiza los cálculos necesarios para calcular la huella de carbono
     */
    private void makeCalculations() {
        TextView electricbill = findViewById(R.id.inputElectricBill);
        textViews.add(electricbill);
        TextView gasBill = findViewById(R.id.inputGasBill);
        textViews.add(gasBill);
        TextView shortFlights = findViewById(R.id.shortFlights);
        textViews.add(shortFlights);
        TextView longFlights = findViewById(R.id.longFlights);
        textViews.add(longFlights);
        CheckBox recycle = findViewById(R.id.chRecycle);
        CheckBox hasCar = findViewById(R.id.cbCar);
        if (hasCar.isChecked()) {
            TextView carKm = findViewById(R.id.inputCarValue);
            textViews.add(carKm);
        }
        if(validateValues()){
            if(!recycle.isChecked()){
                finalValue += 184 + 166;
            }
            finalValue += (convertToDollars(Double.parseDouble(electricbill.getText().toString())) * 105);
            finalValue += (convertToDollars(Double.parseDouble(gasBill.getText().toString())) * 105);
            finalValue += (Double.parseDouble(shortFlights.getText().toString()) * 1100);
            finalValue += (Double.parseDouble(longFlights.getText().toString()) * 4400);
            if (hasCar.isChecked()) {
                TextView carKm = findViewById(R.id.inputCarValue);
                finalValue += (Double.parseDouble(carKm.getText().toString()) * 12);
            }
        }

    }

    /**
     * Valida si los campos han sido ingresados correctamente
     * @return True si están diligenciados todos los campos, False en caso contrario
     */
    private Boolean validateValues(){
        Boolean isEmpty = Boolean.TRUE;
        for (TextView tv : textViews) {
            if(tv.getText().toString().isEmpty()){
                tv.setError("Ingrese este campo");
                isEmpty = Boolean.FALSE;
            }
        }
        return isEmpty;
    }

    /**
     * Convierte de COP a USD
     * @param doubleNum COP
     * @return Conversión a USD
     */
    private Double convertToDollars(Double doubleNum){
        return doubleNum * 0.000346843;
    }
}
