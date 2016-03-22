package com.blstream.stairwaytoheaven.CustomProgressBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.blstream.stairwaytoheaven.R;


public class TextProgressBar extends ProgressBar {
    private String text;
    private Paint textPaint;
    Rect bounds = new Rect();
    private static final int TEXT_COLOR_DEFAULT = Color.BLACK;

    /**
     * Constructor
     */
    public TextProgressBar(Context context) {
        super(context);
        text = "";
        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR_DEFAULT);
        setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.custom_progress_bar));

    }
    /**
     * Constructor
     */
    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = "";
        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR_DEFAULT);
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.custom_progress_bar));
    }
    /**
     * Constructor
     */
    public TextProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        text = "";
        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR_DEFAULT);
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.custom_progress_bar));
    }

    /**
     * Method draw text on ProgressBar
     * @param canvas instance of Canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setTextSize(getHeight() * 0.75f);
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        int x = getWidth() / 2 - bounds.centerX();
        int y = getHeight() / 2 - bounds.centerY();
        canvas.drawText(text, x, y, textPaint);
    }

    /**
     * Method set Text which is written on progress bar
     * @param text text as string.
     */
    public synchronized void setText(String text) {
        this.text = text;
        drawableStateChanged();
    }

    /**
     * Method change color of text which is written on progress bar
     * @param color color as integer.
     */
    public void setTextColor(int color) {
        textPaint.setColor(color);
        drawableStateChanged();
    }
}
