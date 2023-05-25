package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView lap_time, current_time;
    Button lap, start;
    ListView listView;
    ArrayAdapter arrayAdapter1;
    private boolean isWatchon=false;
    Handler handler;
    long timeMilli,startTime,timmerBuf=0l,timeUpdata;
    int minutes,seconds,millis;
    int elapesedmin=0,elapsedsec=0,elapsedmilli=0;

    long lapTimeMilli=0l,lapStartTime,lapTimeBuf=0l,lapTimeUpdate;
   int lapMin=0,lapSec=0,lapMili=0;
   long previousTime=0l;
    private  boolean isLapOn=false;
    private String newMinute;
    private String newSeconds;
    private boolean wantToReset=false;
    private boolean isHold=false;
    private boolean isStartPressed=false;
    Handler handlerlap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lap_time = findViewById(R.id.lap_time_display);
        current_time = findViewById(R.id.time_display);
        lap = findViewById(R.id.lap);
        start = findViewById(R.id.start);
        listView=findViewById(R.id.listView);

        handler =new Handler();
        handlerlap= new Handler();
        ArrayList<String> arrayList=new ArrayList<>();

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStartPressed=true;

                if(isWatchon){
                    isWatchon=false;
                    timmerBuf+=timeMilli;
                    handler.removeCallbacks(runnable);
                    start.setText("resume");
                    isHold=true;
                    //isLapOn=false;
                    lap.setText("reset");
                    lapTimeBuf += lapTimeMilli;
                    handlerlap.removeCallbacks(runnablelap);
                    wantToReset=true;
                }else{
                    wantToReset=false;
                    start.setText("Hold");
                    lap.setText("lap");
                    startTime=SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    isWatchon=true;
                    isHold=false;
                    if(isLapOn){
                        lapStartTime=SystemClock.uptimeMillis();
                        handlerlap.postDelayed(runnablelap,0);

                    }
                }
            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(isStartPressed){
                        if (wantToReset) {
                            handler.removeCallbacks(runnable);
                            handlerlap.removeCallbacks(runnablelap);
                            current_time.setText("00:00:00");
                            lap_time.setText("00:00:00");
                            lap.setText("lap");
                            start.setText("start");
                            isStartPressed = false;
                            isHold = false;
                            isLapOn = false;
                            isWatchon = false;
                            timeMilli = 0l;
                            startTime = 0l;
                            timmerBuf = 0l;
                            timeUpdata = 0l;
                            minutes = 0;
                            seconds = 0;
                            millis = 0;
                            lapTimeMilli = 0l;
                            lapStartTime = 0l;
                            lapTimeBuf = 0l;
                            lapTimeUpdate = 0l;
                            lapMin = 0;
                            lapSec = 0;
                            lapMili = 0;
                            arrayList.clear();
                            arrayAdapter.notifyDataSetChanged();
                        } else {
                            int currentMin=minutes;
                            int currentSec=seconds;
                            int currentMili=millis;
                            long currentTime=currentMin*60000+currentSec*1000+currentMili;
                            long diff=currentTime-previousTime;
                            lapMin= (int) (diff/ 60000);
                            lapSec= (int) (diff/1000);
                            lapMili= (int) (diff%1000);
                            String nlapMin=String.valueOf(lapMin);
                            if(lapMin<=9){
                                nlapMin="0"+nlapMin;
                            }
                            String nlapSec=String.valueOf(lapSec);
                            if(lapMin<=9){
                                nlapSec="0"+nlapSec;
                            }
                            String nlapMili=String.valueOf(lapMili);
                            previousTime=currentTime;
                            String newMinute=String.valueOf(currentMin);
                            if(currentMin<=9){
                                newMinute="0" + newMinute;

                            }
                            String newSeconds=String.valueOf(currentSec);
                            if(currentSec<=9){
                                newSeconds="0" + newSeconds;
                            }
                            String newMillis= String.valueOf(currentMili);
                            if(millis<=9){
                                newMillis="0"+newMillis;
                            }

                            String toList = "Lap: "+ (arrayList.size() + 1) + ".    " + newMinute + ":" + newSeconds + ":" + newMillis +"    "+"Time Elapsed is: "+nlapMin+":" + nlapSec +":" + nlapMili ;


                            arrayList.add(toList);
                            listView.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setSelection(arrayList.size() - 1);
//                            listView.smoothScrollToPosition(arrayList.size() - 1);
                            lap_time.setText(newMinute+":"+newSeconds+":"+currentMili);
                        }
//                            handlerlap.removeCallbacks(runnablelap);
//                            lapTimeMilli = 0l;
//                            lapStartTime = 0l;
//                            lapTimeBuf = 0l;
//                            lapTimeUpdate = 0l;
//                            lapMin = 0;
//                            lapSec = 0;
//                            lapMili = 0;
//                            lap_time.setText("00:00:00");
//                            lapStartTime = SystemClock.uptimeMillis();
//                            handlerlap.postDelayed(runnablelap, 0);

                            isLapOn=false;
//                        } else {
//                            lapStartTime = SystemClock.uptimeMillis();
//                            handlerlap.postDelayed(runnablelap, 0);
//                            isLapOn = true;
//                            lap.setText("Lap");
//                        }
//                        lapStartTime = SystemClock.uptimeMillis();
//                        handlerlap.postDelayed(runnablelap, 0);
                    }

                }
        });
        //        lap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(islapon){
//                    handlerlap.removeCallbacks(runnablelap);
//                    lapstartTime=0l;
//                    lapmili=0;
//                    lapTimeBuf=0l;
//                    lapTimeUpdate=0l;
//                    laptimemilli=0l;
//                    lapsec=0;
//                    lapmin=0;
//
//                    lap_time.setText("00:00:00");
//
//
//                }else{
//                    islapon=true;
//                    lapstartTime=SystemClock.uptimeMillis();
//                    handlerlap.postDelayed(runnablelap,0);
//                }
//
//
//            }
//        });

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeMilli= SystemClock.uptimeMillis()-startTime;
            timeUpdata= timmerBuf+timeMilli;
            seconds= (int) timeUpdata/1000;
            minutes=seconds/60;
            seconds=seconds%60;
            millis= (int) (timeUpdata%100);
            newMinute=String.valueOf(minutes);
            if(minutes<=9){
                newMinute="0" + newMinute;

            }
            newSeconds=String.valueOf(seconds);
            if(seconds<=9){
                newSeconds="0" + newSeconds;

            }


            current_time.setText(newMinute + ":" + newSeconds + ":"+ millis);

            handler.postDelayed(this,60);
        }
    };
    Runnable runnablelap = new Runnable() {
        @Override
        public void run() {
            lapTimeMilli= SystemClock.uptimeMillis()-startTime;
            lapTimeUpdate= lapTimeBuf+lapTimeMilli;
            lapSec= (int) lapTimeUpdate/1000;
            lapMin=lapSec/60;
            lapSec=lapSec%60;
            lapMili= (int) (lapTimeUpdate%100);
            String newMinute=String.valueOf(lapMin);
            if(lapMin<=9){
                newMinute="0" + newMinute;

            }
            String newSeconds=String.valueOf(lapSec);
            if(lapSec<=9){
                newSeconds="0" + newSeconds;

            }
            lapTimeBuf=lapTimeUpdate;

            lap_time.setText(newMinute + ":" + newSeconds + ":"+ lapMili);

            handlerlap.postDelayed(this,0);
        }
    };

}