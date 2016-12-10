package com.example.kingwen.test_2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * 手机传感器的管理器
     */
    private SensorManager sensorManager;

    /**
     * 显示当前光照强度的文本框
     */
    private TextView tv_light_leval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定view
        tv_light_leval= (TextView) findViewById(R.id.tv_light_leval);

        //通过系统服务来获得我们的传感器管理器的实例
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //通过传感器管理者实例获得光强传感器的实例
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        /**
         * 对传感器管理信号进行监听
         * listener :传感器输出信号接口
         * sensor  ：传感器对象
         * SENSOR_DELAY_NORMAL: 更新频率  UI  NORMAL  GAME  FASTEST  依次递增
         */
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

        private SensorEventListener listener=new SensorEventListener() {
            /**
             * 精度发生变化
             * @param event
             */
            @Override
        public void onSensorChanged(SensorEvent event) {
            float value=event.values[0];
            tv_light_leval.setText("current light level is " + value + " 1x");

                Log.e("sensor", event.values.length + "");


                if(value<10){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.grey5);
                }else if(value<20){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.grey1);
                }else if(value<30){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.grey2);
                }else if(value<40){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.grey3);
                }else if(value<50){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.grey4);
                }else if(value<60){
                    MainActivity.this.getWindow().setBackgroundDrawableResource(R.color.white);
                }

        }

            /**
             * 数值发生变化
             * @param sensor
             * @param accuracy
             */
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

            //Log.d("CURRENT",sensor.toString()+" "+accuracy);

        }
    };
}
