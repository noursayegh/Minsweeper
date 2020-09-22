package com.example.sweeper;

import android.os.CountDownTimer;
import android.widget.TextView;

public class Timer {
    private static TextView t;
    private static long totalTime = 60*60000;
    private static long updateTime = 1000;
    private  static long timeElapsed;
    private  static CountDownTimer timer;
    public static boolean state;
    public static void createTimer(){
        timer=new CountDownTimer(totalTime,updateTime) {
            @Override
            public  void onTick(long millisUntilFinished) {
                timeElapsed=totalTime-millisUntilFinished;
                update(t);
            }

            @Override
            public void onFinish() {

            }
        };
    }
    public static long endGameTimer(){
        timer.cancel();
        timer=null;
        return timeElapsed/1000;
    }
    public static void startTimer(){
        timer.start();
        state=true;
    }
    public static void onPause(){
        timer.cancel();
    }
    public static void onResume() {
        if(state)
            timer.start();
    }

    public static void update(TextView t){
        String m,s;
        if(timeElapsed/60000<10)
            m="0"+timeElapsed/60000;
        else m=""+timeElapsed/60000;
        if(timeElapsed/1000<10)
            s="0"+(timeElapsed/1000)%60;
        else s=""+(timeElapsed/1000)%60;

        t.setText(m+":"+s);
    }

    public static void setTimerView(TextView t) {
        Timer.t = t;
    }
}
