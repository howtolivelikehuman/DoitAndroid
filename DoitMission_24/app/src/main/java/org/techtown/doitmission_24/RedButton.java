package org.techtown.doitmission_24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RedButton extends View {
    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;

    float rectLeftX;
    float rectRightX;
    float rectTopY;
    float rectBottomY;
    Rect pastrect;


    public RedButton(Context context) {
        super(context);
        init(context);
    }

    public RedButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        Bitmap img = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawColor(Color.WHITE);
        mBitmap = img;
        mCanvas = canvas;
        pastrect = DrawRect(200,200);
    }

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        Rect rect;
        float x = event.getX();
        float y = event.getY();
        if(x > rectLeftX && x < rectRightX && y < rectBottomY && y > rectTopY){
            Log.d("RedButton" , rectRightX + " " + rectLeftX + " " + rectTopY + " " + rectBottomY);
            switch (action){
                case MotionEvent.ACTION_UP : {
                    rect = touchUp(event,false);
                    if(rect!= null){
                        invalidate(rect);
                    }
                    return true;
                }

                case MotionEvent.ACTION_DOWN :{
                    rect = touchDown(event);
                    if(rect!= null){
                        invalidate(rect);
                    }
                    return true;
                }
                case MotionEvent.ACTION_MOVE : {
                    rect = touchMove(event);
                    if(rect!= null){
                        invalidate(rect);
                    }
                    return true;
                }
            }
        }
        return true;
    }
    private Rect touchDown(MotionEvent event){
        Rect rect = processMove(event);
        return rect;
    }
    private Rect touchMove(MotionEvent event){
        Rect rect = processMove(event);
        return rect;
    }
    private Rect touchUp(MotionEvent event, boolean cancel){
        Rect rect = processMove(event);
        return rect;
    }
    private Rect processMove(MotionEvent event){
        final float x = event.getX();
        final float y = event.getY();
        pastrect = DrawRect(x,y);
        return pastrect;
    }

    protected Rect DrawRect(float x, float y){
        if(pastrect != null){ //이전 사각형을 하얀색으로 색칠하고 (없애버림)
            mPaint.setColor(Color.WHITE);
            mCanvas.drawRect(pastrect,mPaint);
        }

        mPaint.setColor(Color.RED);
        rectTopY = y-100;
        rectBottomY = y+100;
        rectLeftX = x-100;
        rectRightX = x+100;

        Rect rect = new Rect(); //터치 지점을 가운데로하여 새 사각형을 그리기
        rect.set((int)rectLeftX, (int)rectTopY, (int)rectRightX, (int)rectBottomY);
        mCanvas.drawRect(rect , mPaint);
        return rect;
    }

    protected void onDraw(Canvas canvas){
        if(mBitmap != null){
            canvas.drawBitmap(mBitmap,0,0,null);
        }
    }
}
