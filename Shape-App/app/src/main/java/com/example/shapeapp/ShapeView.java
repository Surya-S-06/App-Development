package com.example.shapeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class ShapeView extends View {

    private String shapeType;
    private int color;
    private Paint paint;

    public ShapeView(Context context, String shape, int color) {
        super(context);
        this.shapeType = shape;
        this.color = color;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) / 2;
        int cx = width / 2;
        int cy = height / 2;

        if (shapeType.equalsIgnoreCase("Circle")) {
            canvas.drawCircle(cx, cy, size, paint);
        } else if (shapeType.equalsIgnoreCase("Square")) {
            canvas.drawRect(cx - size, cy - size, cx + size, cy + size, paint);
        } else if (shapeType.equalsIgnoreCase("Triangle")) {
            Path path = new Path();
            path.moveTo(cx, cy - size); // Top
            path.lineTo(cx - size, cy + size); // Bottom Left
            path.lineTo(cx + size, cy + size); // Bottom Right
            path.close();
            canvas.drawPath(path, paint);
        }
    }
}
