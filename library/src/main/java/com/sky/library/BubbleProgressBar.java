package com.sky.library;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by tank on 2017/10/14.
 */

public class BubbleProgressBar extends View {

    private int width, height;
    private int x, y; // 圆的坐标
    private Paint circlePaint; // 圆的画笔
    private Paint bgPaint; // 背景的画笔
    private int bollColor; // 圆的颜色
    private Path path;
    private boolean needBackground;
    private boolean needDifferentColor;
    private int[] radiusArray = {12, 11, 10, 9, 8, 7, 6, 5};
    private int[] angles = {0, 45, 90, 135, 180, 225, 270, 315, 360};
    private String color_0, color_1, color_2, color_3, color_4, color_5, color_6, color_7;
    private String[] differentColors = {"#FF1493", "#8A2BE2", "#1E90FF", "#008080", "#008000", "#FFFF00", "#FFE4B5", "#FF0000"};
    private String[] colors;
    private static boolean IS_PLAYING = false;

    public BubbleProgressBar(Context context) {
        this(context, null);
    }

    public BubbleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        init();
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleProgressBar);
        bollColor = typedArray.getInteger(R.styleable.BubbleProgressBar_bollColor, Color.parseColor("#00FFFF"));
        needBackground = typedArray.getBoolean(R.styleable.BubbleProgressBar_needBackground, false);
        needDifferentColor = typedArray.getBoolean(R.styleable.BubbleProgressBar_needDifferentColor, false);
        color_0 = typedArray.getString(R.styleable.BubbleProgressBar_color_0);
        color_1 = typedArray.getString(R.styleable.BubbleProgressBar_color_1);
        color_2 = typedArray.getString(R.styleable.BubbleProgressBar_color_2);
        color_3 = typedArray.getString(R.styleable.BubbleProgressBar_color_3);
        color_4 = typedArray.getString(R.styleable.BubbleProgressBar_color_4);
        color_5 = typedArray.getString(R.styleable.BubbleProgressBar_color_5);
        color_6 = typedArray.getString(R.styleable.BubbleProgressBar_color_6);
        color_7 = typedArray.getString(R.styleable.BubbleProgressBar_color_7);
        typedArray.recycle();
    }

    private void init() {
        path = new Path();

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.BLACK);
        bgPaint.setAlpha(90);

        colors = new String[]{color_0, color_1, color_2, color_3, color_4, color_5, color_6, color_7};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics());
                break;

            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;

            case MeasureSpec.UNSPECIFIED:
                width = widthSize;
                break;

            default:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics());
                break;

            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;

            case MeasureSpec.UNSPECIFIED:
                height = heightSize;
                break;

            default:
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, getResources().getDisplayMetrics());
        int circleRadius;
        int centerX = width / 2 + getPaddingStart() - getPaddingEnd();
        int centerY = height / 2 + getPaddingTop() - getPaddingBottom();

        if (needBackground) {
            int distance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics());
            path.moveTo(centerX - distance, centerY + distance);
            path.lineTo(centerX + distance, centerY + distance);
            path.lineTo(centerX + distance, centerY - distance);
            path.lineTo(centerX - distance, centerY - distance);
            path.close();
            canvas.drawPath(path, bgPaint);
        }

        for (int i = 0; i < 8; i++) {
            if (needDifferentColor) {
                if (TextUtils.isEmpty(colors[i])) {
                    circlePaint.setColor(Color.parseColor(differentColors[i]));
                } else {
                    try {
                        circlePaint.setColor(Color.parseColor(colors[i]));
                        Log.i("TAG", i + colors[i]);
                    } catch (Exception e) {
                        circlePaint.setColor(Color.parseColor(differentColors[i]));
                    }
                }
            } else {
                circlePaint.setColor(bollColor);
            }
            circleRadius = radiusArray[i];
            x = (int) (centerX + radius * Math.cos(angles[i] * 3.14 / 180));
            y = (int) (centerY + radius * Math.sin(angles[i] * 3.14 / 180));
            canvas.drawCircle(x, y, circleRadius, circlePaint);
        }
    }

    public void setBollColor(int color) {
        bollColor = color;
    }

    public void needBackground(boolean background) {
        needBackground = background;
    }

    public void needDifferentColor(boolean differentColor) {
        needDifferentColor = differentColor;
    }

    public void setDifferentColors(String... c) {
        if (c.length == 0 || c.length > colors.length) {
            return;
        }
        for (int i = 0; i < colors.length; i++) {
            if (c.length == colors.length) {
                colors = c;
            } else {
                if (c.length - 1 < i) {
                    colors[i] = "";
                } else {
                    colors[i] = c[i];
                }
            }
        }
    }

    public void start(final Activity activity) {
        if (IS_PLAYING) {
            return;
        }
        IS_PLAYING = true;
        if (getVisibility() != View.VISIBLE) {
            setVisibility(View.VISIBLE);
        }
        new Thread() {
            @Override
            public void run() {
                while (IS_PLAYING) {
                    for (int i = 0; i < 8; i++) {
                        if (i == 7) {
                            radiusArray[i] = radiusArray[0];
                        } else {
                            radiusArray[i] = radiusArray[i + 1];
                        }
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            invalidate();
                        }
                    });
                    try {
                        Thread.sleep(120);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void stop() {
        if (!IS_PLAYING) {
            return;
        }
        IS_PLAYING = false;
        if (getVisibility() == View.VISIBLE) {
            setVisibility(View.GONE);
        }
    }
}
