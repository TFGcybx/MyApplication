package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleSpiriter {
    float x,y,radius;
    double direction;
    float maxWeight,maxHeight;
    public CircleSpiriter(float x, float y, float radius, float maxWeight, float maxHeight){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.direction=Math.random();
        this.maxWeight=maxWeight;
        this.maxHeight=maxHeight;
    }
    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(x,y,radius,paint);
    }
    public void move(){
        this.x+=20*Math.cos(direction);
        this.y+=20*Math.sin(direction);
        if(this.x<0){
            this.x+=maxWeight;
        }
        if(this.y<0){
            this.y+=maxHeight;
        }
        if(this.x>=maxWeight){
            this.x-=maxWeight;
        }
        if(this.y>=maxHeight){
            this.y-=maxHeight;
        }
    }

    public boolean isShot(float touchedX, float touchedY) {
        double distance=(touchedX-this.x)*(touchedX-this.x)+(touchedY-this.y)*(touchedY-this.y);
        return distance<radius*radius;
    }
}
