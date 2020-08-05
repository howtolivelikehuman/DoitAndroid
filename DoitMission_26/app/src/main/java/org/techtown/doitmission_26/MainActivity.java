package org.techtown.doitmission_26;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    CameraSurfaceView cameraView;
    FrameLayout cuponFrame;
    ConstraintLayout buttonLayout;
    Button imageButton1;
    Button imageButton2;
    //바보같이 Bitmap에 아이콘 넣으려고함.
    //Cupons cupons;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this,101);

        FrameLayout cameraFrame = findViewById(R.id.cameraFrame);
        buttonLayout = findViewById(R.id.Buttonlayout);
        //cuponFrame = findViewById(R.id.cuponFrame);
        //cupons= new Cupons(this);
        //cuponFrame.addView(cupons);

        cameraView = new CameraSurfaceView(this);
        cameraFrame.addView(cameraView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(View.VISIBLE);
                //cuponFrame.setVisibility(View.VISIBLE);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(View.INVISIBLE);
                //cuponFrame.setVisibility(View.INVISIBLE);
            }
        });

        imageButton1 = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "스타벅스!!!!!", Toast.LENGTH_LONG).show();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "이디야!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
            camera = Camera.open();
            setCameraOrientation();
            try {
                camera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void setCameraOrientation(){
            if(camera == null) return;
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0,info);
            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int rotation = manager.getDefaultDisplay().getRotation();

            int degrees = 0;
            switch (rotation){
                case Surface.ROTATION_0 : degrees = 0 ; break;
                case Surface.ROTATION_90: degrees=90; break;
                case Surface.ROTATION_180: degrees = 180; break;
                case Surface.ROTATION_270: degrees = 270; break;
            }
            int result;
            if(info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                result = (info.orientation+degrees) %360;
                result = (360-result) % 360;
            }
            else{
                result = (info.orientation+degrees + 360) %360;
            }
            camera.setDisplayOrientation(result);
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        public boolean capture(Camera.PictureCallback handler){
            if(camera != null){
                camera.takePicture(null,null, handler);
                return true;
            }
            else{
                return false;
            }
        }
    }
}