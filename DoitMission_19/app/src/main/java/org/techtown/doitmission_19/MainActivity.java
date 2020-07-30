package org.techtown.doitmission_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    WebView webView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView1);
        webView = findViewById(R.id.webView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlStr = editText.getText().toString();
                //webView.loadUrl(urlStr);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });
    }
    public void request(String urlStr){
        StringBuilder output = new StringBuilder();
        try{
            URL url  = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con != null){
                con.setConnectTimeout(10000);
                con.setRequestMethod("GET");
                con.setDoInput(true);

                int resCode = con.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while(true){
                    line = reader.readLine();
                    if(line ==null) break;
                    output.append(line+"\n");
                }
                reader.close();
                con.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //스레드 내에서는 xml을 변경할 수 없어서 handler로 해야함
        println(output.toString());
        printView(output.toString());
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });
    }
    public void printView(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.loadDataWithBaseURL(null,data,"text/html","utf-8",null);
            }
        });
    }
}