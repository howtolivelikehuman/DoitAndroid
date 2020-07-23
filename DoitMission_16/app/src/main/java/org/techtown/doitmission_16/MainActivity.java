package org.techtown.doitmission_16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;
    Animation moveup;
    Animation movedown;
    Button button2;
    LinearLayout page;
    boolean ispageOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);
        moveup = AnimationUtils.loadAnimation(this,R.anim.move_up);
        movedown = AnimationUtils.loadAnimation(this,R.anim.move_down);
        page = findViewById(R.id.page);

        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        movedown.setAnimationListener(animationListener);
        moveup.setAnimationListener(animationListener);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());

        Button button = findViewById(R.id.buttonMove);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText.getText().toString());

                if(ispageOpen){
                    page.startAnimation(moveup);

                }else{
                    page.startAnimation(movedown);
                }

            }
        });

        button2 = findViewById(R.id.buttonOpen);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ispageOpen){
                    page.startAnimation(moveup);
                }
                else{
                    page.startAnimation(movedown);
                }
            }
        });
    }
    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            if(ispageOpen){
                page.setVisibility(View.INVISIBLE);
                ispageOpen = false;
            }
            else{
                button2.setVisibility(View.INVISIBLE);
                ispageOpen = true;
            }
        }
        @Override
        public void onAnimationStart(Animation animation) {
            page.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    private class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url){
            view.loadUrl(url);
            return true;
        }
    }
}