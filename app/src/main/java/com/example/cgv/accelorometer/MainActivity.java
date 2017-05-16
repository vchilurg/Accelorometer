package com.example.cgv.accelorometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor A;
    SensorManager AM;
    TextView X1,Y1,Z1, FI;
    Float x,y,z;
    double x1=0,y1=0,z1=0, g=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AM = (SensorManager)getSystemService(SENSOR_SERVICE);
        A = AM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        AM.registerListener(this, A, SensorManager.SENSOR_DELAY_NORMAL);

        X1 = (TextView)findViewById(R.id.Xdeg);
        Y1 = (TextView)findViewById(R.id.Ydeg);
        Z1 = (TextView)findViewById(R.id.Zdeg);
        FI = (TextView)findViewById(R.id.Front_I);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        g = Math.sqrt(x * x+y * y+z *z);// normalizing the value

        x1=x/g;
        y1=y/g;
        z1=z/g;

        int Xi = (int) Math.round(Math.toDegrees(Math.acos(x1)));
        int Yi = (int) Math.round(Math.toDegrees(Math.acos(y1)));
        int Zi = (int) Math.round(Math.toDegrees(Math.acos(z1)));


        X1.setText("X: "+Xi+" °");
        Y1.setText("Y: " +Yi+" °");
        Z1.setText("Z: " +Zi+" °");

        if(Xi>20 && Zi<70)
        {FI.setText("Robot is inclined Downwards");
        }
        else if(Yi<75 && Xi>15) {
            FI.setText("Robot is inclined Right");
        }
        else if(Yi>110 && Xi>20) {
            FI.setText("Robot is inclined Left");
        }
        else if(Zi>110 && Xi>20) {
            FI.setText("Robot is inclined Upwards");
        }
        else {
            FI.setText("Uninclined");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
