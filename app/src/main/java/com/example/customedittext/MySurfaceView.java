package com.example.customedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder surfaceHolder;
    Paint paint;
    int x = 0;
    boolean isRunning;
    Canvas canvas;
    Thread thread = null;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
        void init(){
            surfaceHolder = getHolder();
            paint = new Paint();
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    startThread();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
        }

        private void startThread() {
        thread = new Thread(this);
        thread.start();
        }

    @Override
    protected void onDraw(Canvas canvas) {
        if (surfaceHolder.getSurface().isValid()){
            canvas.drawColor(Color.RED);
            paint.setColor(Color.YELLOW);
            paint.setColor(Color.GREEN);
            canvas.drawArc(500,500,800,800,x,30,true,paint);
            canvas.drawArc(500,500,800,800,x+120,30,true,paint);
            canvas.drawArc(500,500,800,800,x+240,30,true,paint);
            x = x+10;
        }
    }

    @Override
    public void run() {
        do {
            try {
                synchronized (this){
                    Thread.sleep(300);
                    canvas = surfaceHolder.lockCanvas();
                    draw(canvas);
                }
            } catch(InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                if (canvas != null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    postInvalidate();
                }
            }
        }
        while (isRunning);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                isRunning = true;
                startThread();
                break;
            case MotionEvent.ACTION_UP:
                stopRunning();
                break;
        }
        return true;
    }

    public void stopRunning()
    {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
