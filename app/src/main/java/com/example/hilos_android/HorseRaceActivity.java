package com.example.hilos_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hilos_android.clases.Race;
import com.example.hilos_android.models.Horse;

import java.util.ArrayList;
import java.util.List;

public class HorseRaceActivity extends AppCompatActivity {
    private static final int NUM_CABALLOS = 10;
    EditText inputStopHorse;
    String[] namesHorse = {
            "Relámpago",
            "Espíritu",
            "Tormenta",
            "Aurora",
            "Trovador",
            "Diamante",
            "Valiente",
            "Mariposa",
            "Ébano",
            "Pegaso"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horse_race);
        Race race = new Race();
        LinearLayout layout = findViewById(R.id.horsesLayout);
        race.addNumHorses(NUM_CABALLOS);
        for (int i = 0; i < NUM_CABALLOS; i++) {
            // Inflar el diseño del caballo
            View horseView = getLayoutInflater().inflate(R.layout.horse_layout, layout, false);
            // Obtener el ID del recurso drawable (reemplaza "nombre_del_drawable" con el nombre real)
            String nombreDrawable = "horse";
            int drawableId = getResources().getIdentifier(nombreDrawable + (i + 1), "drawable", getPackageName());
            // Crear una instancia de Horse y pasar el ID del drawable como segundo parámetro
            race.addHorse(i,new Horse(horseView, drawableId,namesHorse[i]));
            // Agregar la vista del caballo al diseño principal
            layout.addView(horseView);
        }
        Button startRaceButton = findViewById(R.id.startRaceButton);
        inputStopHorse = findViewById(R.id.inputHorse);
        startRaceButton.setOnClickListener(v -> race.startRace());
        Button stopHorses = findViewById(R.id.stopHorses);
        stopHorses.setOnClickListener(v -> race.stopHosrses());
        findViewById(R.id.btnStopOneHorse).setOnClickListener(v -> race.stopOneHorse(Integer.parseInt(inputStopHorse.getText().toString())));
    }



}