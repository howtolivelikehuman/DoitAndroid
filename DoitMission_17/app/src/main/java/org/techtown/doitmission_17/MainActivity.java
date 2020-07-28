package org.techtown.doitmission_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout panel1;
    LinearLayout panel2;
    TextView  textNum;
    Animation visibleLeft;
    Animation invisibleLeft;
    boolean turn = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panel1 = findViewById(R.id.panel1);
        panel2 = findViewById(R.id.panel2);

        textNum = findViewById(R.id.textNum);
        visibleLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible_left);
        invisibleLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible_left);
        PanelAnimationListener animationListener = new PanelAnimationListener();
        invisibleLeft.setAnimationListener(animationListener);
        visibleLeft.setAnimationListener(animationListener);

        MyThread myThread = new MyThread();
        handler = new Handler();
        myThread.start();
    }
    class MyThread extends Thread{
        public void run(){
            while(true){
                try{
                    Thread.sleep(2000);
                } catch(Exception e){};
                if(!turn){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            panel1.startAnimation(invisibleLeft);
                            panel2.startAnimation(visibleLeft);
                            turn = !turn;
                            textNum.setText("2/2");
                        }
                    });
                }
                else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            panel2.startAnimation(invisibleLeft);
                            panel1.startAnimation(visibleLeft);
                            turn = !turn;
                            textNum.setText("1/2");
                        }
                    });
                }
            }
        }
    }
    private class PanelAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
                panel2.setVisibility(View.VISIBLE);
                panel1.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            if(turn){
                panel1.setVisibility(View.INVISIBLE);
            }else{
                panel2.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}