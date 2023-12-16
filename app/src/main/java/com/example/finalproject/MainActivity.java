package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Delayed;

import kotlinx.coroutines.Delay;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView accelerometer, score;
    private SensorManager sensorManager;
    private Sensor sensor;
    static int sequenceCount = 4;
    int arrayIndex = 0;
    int[] gameSequence = new int[100];
    private final int RED = 2;
    private final int BLUE = 1;
    private final int YELLOW = 4;
    private final int GREEN = 3;

    Button red, blue, green, yellow, play;
    int n = 0;
    final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);

        //accelerometer
        accelerometer = findViewById(R.id.accelerometerData);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,sensor,sensorManager.SENSOR_DELAY_NORMAL);

        //buttons
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        yellow = findViewById(R.id.yellow);
        play = findViewById(R.id.buttonPlay);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setVisibility(View.INVISIBLE);
                //red.setVisibility(View.VISIBLE);
                //blue.setVisibility(View.VISIBLE);
                //green.setVisibility(View.VISIBLE);
                //yellow.setVisibility(View.VISIBLE);

                StartSequence();
                score.setText("Sequence: "+ n);

            };
        });
    }

    public void StartSequence() {
        //RANDOM SEQUENCE
        n = getRandom(sequenceCount);
            switch (n) {
                case 1:
                    red.setVisibility(View.VISIBLE);
                    gameSequence[arrayIndex++] = RED;
                    break;
                case 2:
                    blue.setVisibility(View.VISIBLE);
                    gameSequence[arrayIndex++] = BLUE;
                    break;
                case 3:
                    green.setVisibility(View.VISIBLE);
                    gameSequence[arrayIndex++] = GREEN;
                    break;
                case 4:
                    yellow.setVisibility(View.VISIBLE);
                    gameSequence[arrayIndex++] = YELLOW;
                    break;
                 }
        }


        @Override
        public void onSensorChanged (SensorEvent event){
            accelerometer.setText(event.values[0]+"\n"+event.values[1]+event.values[2]);
        }

        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){

        }

        private int getRandom ( int maxValue){
            return ((int) ((Math.random() * maxValue) + 1));

        }

    }