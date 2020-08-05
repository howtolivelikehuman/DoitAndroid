package org.techtown.doitmission_26;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Cupons extends View {
    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;
    int x;
    int y;

    public Cupons(Context context) {
        super(context);
    }

    public Cupons(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    protected void onDraw(Canvas canvas){
        Resources res = getResources();
        BitmapDrawable bt = (BitmapDrawable)res.getDrawable(R.drawable.cuponicon,null);
        mBitmap = bt.getBitmap();
        mBitmap = Bitmap.createScaledBitmap(mBitmap,150,150,true);
        canvas.drawBitmap(mBitmap,100, 250, null);
        canvas.drawBitmap(mBitmap,800, 800, null);
    }
}
