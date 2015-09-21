package org.scifly7.variocity;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by erwin on 21/09/2015.
 */
public class SensorLoggerService extends Service implements SensorEventListener {

    private SensorManager mSensorManager = null;

    private Sensor mAccel = null;
    private Sensor mBarom = null;
    private Sensor mGyros = null;



    // Extends overrides:

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("**** Logger onStartCommand Called! ****");

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mBarom = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mGyros = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mBarom, SensorManager.SENSOR_DELAY_FASTEST);
       // mSensorManager.registerListener(this, mGyros, SensorManager.SENSOR_DELAY_FASTEST);



        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }



    // Implementation overrides:

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // do nothing
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // grab the values and timestamp ... off the main thread (using AsyncTask)
/*
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.out.println("Accelerometer data");
        }
*/

        if(event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            System.out.println("Barometer data");
        }

        /*
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            System.out.println("Gyroscope data");
        }
*/


        //System.out.println(sensorEvent.values[0]);

        //new SensorEventLoggerTask().execute(sensorEvent);

        //System.out.println("hmmmm");


        // ....
        // stop the sensor and service.
    }

    private class SensorEventLoggerTask extends AsyncTask<SensorEvent, Void, Void> {
        @Override
        protected Void doInBackground(SensorEvent... sensorEvents) {

            //System.out.println("blah");
            System.out.println(sensorEvents[0].values[0]);


            return null;
        }

        /*
        @Override
        protected Void doInBackground(SensorEvent... events) {
            SensorEvent event = events[0];
            // log the value.

                System.out.println("blah");
                System.out.println(event.values[0]);

            return null;
        }
    */


    }

    /*
    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
    */


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
