package com.example.hilos_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView counterTextView ;
    Button btnDetener , btnImprimir;
    int counterValue  = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler(getMainLooper());
        counterTextView = findViewById(R.id.txtcounter);
        btnDetener = findViewById(R.id.btnDetener);
        btnImprimir = findViewById(R.id.btnImprimir);
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counterValue < 100 && !Thread.interrupted()) {
                    counterValue++;

                    counterTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            counterTextView.setText("contador: " + counterValue);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // Restablecer la interrupción, ya que Thread.sleep() también borra la interrupción.
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        hilo.start();

        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(50000);
                    System.out.println("Hola mundo");
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println("Ocurrio un error");
                }
            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDetener.setText("detenido");
                hilo.interrupt();
            }
        });
    }
}