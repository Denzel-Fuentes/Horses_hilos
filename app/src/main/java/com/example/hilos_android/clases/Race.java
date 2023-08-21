package com.example.hilos_android.clases;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hilos_android.HorseRaceActivity;
import com.example.hilos_android.R;
import com.example.hilos_android.models.Horse;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<Thread> hilos = new ArrayList<>();
    private volatile boolean raceFinished = false;
    private HorseRaceActivity activity;
    private static Horse winner = null;
    private List<Thread> horseThreads = new ArrayList<>();
    private Horse[] horses;

    public void addHorse(int i,Horse horse){
        horses[i] = horse;
    }
    public void addNumHorses(int NUM_CABALLOS){
         horses = new Horse[NUM_CABALLOS];
    }
    public void startRace() {
        for (Horse horse : horses) {
            Thread hilo = new Thread(horse);
            horseThreads.add(hilo);
            hilo.start();
        }
    }
    public  void stopHosrses(){
        for (Horse horse : horses) {
            horse.stopRunning();
        }
    }
    public  void stopOneHorse(int i ){
        horses[i].stopRunning();
    }
    // Método para determinar el ganador
    public void determineWinner(Horse horse) {
        if (winner == null) {
            winner = horse;
            // Muestra el nombre del ganador en la interfaz de usuario
            runOnUiThread(() -> {
                // Aquí puedes actualizar la UI con el nombre del ganador
                // por ejemplo, puedes mostrarlo en un TextView.
                TextView winnerTextView = findViewById(R.id.txtWinner);
                winnerTextView.setText("El ganador es: " + winner.getName());
            });
        }
    }
}
