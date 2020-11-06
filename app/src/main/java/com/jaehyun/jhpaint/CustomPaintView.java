package com.jaehyun.jhpaint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CustomPaintView extends View {
    private boolean mPalette;
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    float oldX,oldY = -1;

    private float mTextWidth;
    private float mTextHeight;

    public CustomPaintView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomPaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomPaintView, defStyle, 0);

        // 추후 옵션
        mPalette = a.getBoolean(
                R.styleable.CustomPaintView_Palette,true);



//        mExampleColor = a.getColor(
//                R.styleable.CustomPaintView_exampleColor,
//                mExampleColor);
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.CustomPaintView_exampleDimension,
//                mExampleDimension);
//
//        if (a.hasValue(R.styleable.CustomPaintView_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.CustomPaintView_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }
//
//        a.recycle();
//
//        // Set up a default TextPaint object
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);


//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
    }

//    private void invalidateTextPaintAndMeasurements() {
//        mTextPaint.setTextSize(mExampleDimension);
//        mTextPaint.setColor(mExampleColor);
//        mTextWidth = mTextPaint.measureText(mExampleString);
//
//        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
//        mTextHeight = fontMetrics.bottom;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        if(mBitmap != null)
            canvas.drawBitmap(mBitmap,0,0,null);

        // Draw the text.
//        canvas.drawText(mExampleString,
//                paddingLeft + (contentWidth - mTextWidth) / 2,
//                paddingTop + (contentHeight + mTextHeight) / 2,
//                mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("test","onSizeChanged 호출");
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.d("test", "터치다운");
            oldX = x; oldY = y;

        } else if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.d("test", "터치이동" +event.getY() + ","+ event.getY());
            if(oldX != -1)
            {
                mCanvas.drawLine(oldX,oldY,x,y,mPaint);
                invalidate();
                oldX = x; oldY = y;
            };
        } else if(event.getAction() == MotionEvent.ACTION_UP){
            Log.d("test", "터치업");
            if(oldX != -1){
                mCanvas.drawLine(oldX,oldY,x,y,mPaint);
            }
            invalidate();
            oldX = -1; oldY = -1;
        }

        return true;
    }
}
