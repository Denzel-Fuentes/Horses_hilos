package com.example.hilos_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class MainActivity2 extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable,runnable2;
    TextView txtmensaje;
    Button btnInicar;
    private List<Runnable> listRunnable = new ArrayList<>();
    private List<String> listnombre = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        handler = new Handler(Looper.getMainLooper());
        txtmensaje = findViewById(R.id.textView);
        btnInicar = findViewById(R.id.button);
        //startProcessWithHandler();
        listnombre.add("kevin");
        listnombre.add("Omar");
        listnombre.add("Danel");
        listnombre.add("pablo");
        listnombre.add("kevin4");
        listnombre.add("kevin5");
        listnombre.add("kevin6");
        listnombre.add("kevin7");
        listnombre.add("kevin8");
        listnombre.add("kevin9");
        int time = 2000;
        for (int i = 0; i <10; i++) {
            final int index = i; // Guarda el valor de 'i' en una variable final para usarlo dentro de la implementación del Runnable
            time+=2000;
            final  int delay = time;
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (index < listnombre.size()) {
                        handler.postDelayed(this,delay);
                        System.out.println(listnombre.get(index));
                        String nombre = listnombre.get(index);
                        txtmensaje.setText("Hola " + nombre);


                    }
                }
            };
            listRunnable.add(runnable);
        }
        btnInicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProcess();
            }
        });

    }
    private  void startProcess(){

        for (Runnable elemento: listRunnable) {
            handler.post(elemento);
        }
    }



    private void startProcessWithHandler(){
        runnable = new Runnable() {
            @Override
            public void run() {
                showToast("Trabajo en segundo plano completado");
                handler.postDelayed(this,5000);
            }
        };
        runnable2 = new Runnable() {
            @Override
            public void run() {
                showToast("hola mundo");
                handler.postDelayed(this,2000);
            }
        };

        handler.post(runnable);
        handler.post(runnable2);
    }



    private void stopProcessWithHandler(){
        handler.removeCallbacks(runnable,runnable2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopProcessWithHandler();
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




}