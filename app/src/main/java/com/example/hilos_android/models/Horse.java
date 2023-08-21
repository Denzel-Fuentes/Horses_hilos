package com.example.hilos_android.models;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hilos_android.HorseRaceActivity;
import com.example.hilos_android.R;

public class Horse implements  Runnable{
    private volatile boolean isRunning = true;
    private final ImageView horseImage;
    public LinearLayout horsesLayout;
    private final TextView txtPorcentaje,txtname;
    private static final int MAX_DISTANCE = 1000;



    private String name;

    public Horse( View view, int ID, String name) {
            horseImage = view.findViewById(R.id.horseImage);
            horsesLayout = view.findViewById(R.id.racetrackLayout);
            horseImage.setImageResource(ID);
            txtPorcentaje = view.findViewById(R.id.txtPorcentaje);
            txtname=view.findViewById(R.id.txtNameHorse);
            txtname.setText(name);
            this.name = name;


    }

    @Override
    public void run() {
        int distance = 0;
        while (distance < MAX_DISTANCE && isRunning) {
            try {
                Thread.sleep((long) (Math.random() * 200)); // Simula el tiempo que tarda el caballo en avanzar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Simula la distancia que avanza el caballo
            distance += (int) (Math.random() * 10);
            int finalDistance = Math.min(distance, MAX_DISTANCE);
            if (finalDistance >= MAX_DISTANCE) {
                 // Llama al método en la instancia de HorseRaceActivity
                break; // Sale del bucle while
            }
            float progress = (float) finalDistance / MAX_DISTANCE;
            // Calcular el porcentaje
            int porcentaje = (int) ((float) finalDistance / MAX_DISTANCE * 100);

            // Actualizar la posición de la imagen y el porcentaje en la IU
            horseImage.post(() -> {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) horseImage.getLayoutParams();
                params.leftMargin = (int) (progress * (horsesLayout.getWidth() - horseImage.getWidth()));
                horseImage.setLayoutParams(params);
                txtPorcentaje.setText(porcentaje + "%");
            });
        }
    }

    public void stopRunning() {
        isRunning = false; // Establece isRunning en false para detener el hilo
    }
    public String getName() {
        return name;
    }
}
