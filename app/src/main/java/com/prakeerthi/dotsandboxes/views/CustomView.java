package com.prakeerthi.dotsandboxes.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.prakeerthi.dotsandboxes.Gamescreen;

import static java.lang.Math.min;

public class CustomView extends View {

    private Rect rect,rec;
    private Paint paint;
    private Paint paint1,paint2,color1,color2,color3,color4;
    private Path path1,path2;
    private int[] flag ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] box ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] points ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private  int i,j,temp,prevpos=-1,pos=0,player=1,x,y;


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    private void init(@Nullable AttributeSet set)
    {
        rect= new Rect();
        paint =new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#fdfff0"));
        paint1 =new Paint();
        paint1.setColor(Color.parseColor("#41cce8"));
        paint1.setStrokeJoin(Paint.Join.ROUND);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(5f);
        paint2 =new Paint();
        paint2.setColor(Color.parseColor("#fa4d5c"));
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5f);
        color1 =new Paint(Paint.ANTI_ALIAS_FLAG);
        color1.setColor(Color.parseColor("#41cce8"));
        color2 =new Paint(Paint.ANTI_ALIAS_FLAG);
        color2.setColor(Color.parseColor("#fa4d5c"));
        path1=new Path();
        path2=new Path();
        color3 =new Paint();
        color3.setColor(Color.parseColor("#8041cce8"));
        color4 =new Paint();
        color4.setColor(Color.parseColor("#80fa4d5c"));



    }
    public void draw(float x, float y)
    {
        paint.setColor(paint.getColor()==Color.GRAY? Color.RED: Color.GRAY);
        paint1.setColor(Color.RED);
        postInvalidate();
    }
    public int getpl()
    {
        return player;
    }

    public void play(int temp) {

            x = temp / 5;
            y = temp % 5;
            if (prevpos == -1) {
                flag[temp] = player;
                if(player==1)
                path1.moveTo(100 + (200 * x), 100 + (200 * y));
                else
                    path2.moveTo(100 + (200 * x), 100 + (200 * y));
                prevpos = temp;
            } else {
                if (temp == prevpos + 1 || temp == prevpos + 5 || temp == prevpos - 1 || temp == prevpos - 5) {

                    flag[temp] = player;

                    pos= temp<prevpos?temp:prevpos;
                    if (temp == prevpos + 1 || temp == prevpos -1) {

                       box[pos]++;
                        if(box[pos]>=4&&points[pos]==0)
                            points[pos]=player;
                       if(pos>=5)
                       {pos-=5;
                       box[pos]++;
                           if(box[pos]>=4&&points[pos]==0)
                               points[pos]=player;
                       }
                    }
                    else
                    {
                        box[pos]++;
                        if(box[pos]>=4&&points[pos]==0)
                            points[pos]=player;
                        if(pos>=1)
                        {pos-=1;
                        box[pos]++;
                            if(box[pos]>=4&&points[pos]==0)
                                points[pos]=player;
                        }
                    }
                    pos= temp<prevpos?temp:prevpos;

                    if(player==1)
                        path1.lineTo(100 + (200 * x), 100 + (200 * y));
                    else
                        path2.lineTo(100 + (200 * x), 100 + (200 * y));
                    player = player == 1 ? 2 : 1;

                    prevpos = -1;


                } else {
                    flag[prevpos] = 0;
                    flag[temp] = player;
                    if(player==1)
                        path1.moveTo(100 + (200 * x), 100 + (200 * y));
                    else
                        path2.moveTo(100 + (200 * x), 100 + (200 * y));
                    prevpos = temp;
                }
            }

        }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#1Affffff"));


        for(i=0;i<5;i++)
            for(j=0;j<5;j++)
            {   temp=(i*5)+j;
                if(flag[temp]==1)
                canvas.drawCircle(100+(200*i),100+(200*j),10,  color1);
                else if(flag[temp]==2)
                    canvas.drawCircle(100+(200*i),100+(200*j),10,  color2);
            else
                canvas.drawCircle(100+(200*i),100+(200*j),10,  paint);
            }
         canvas.drawPath(path1,paint1);
        canvas.drawPath(path2,paint2);
        for(i=0;i<4;i++)
            for(j=0;j<4;j++)
            {   temp=i*5+j;
                rect.top=100+(200*(j));
                rect.left=(i)*200+100;
                rect.right=(i)*200+300;
                rect.bottom=300+(200*(j));
                if(box[temp]>=4) {
                    if (points[temp] == 1)
                        canvas.drawRect(rect, color3);
                    else canvas.drawRect(rect, color4);
                }
            }

        postInvalidate();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                for(i=0;i<5;i++)
                    for(j=0;j<5;j++)
                    {
                        if(x<=150+(200*i)&&x>=50+(200*i)&&y<=150+(200*j)&&y>=50+(200*j)) {
                            temp = (i * 5) + j;
                            play(temp);
                            break;
                        }
                    }

            default: break;
        }
        return true;
    }


}
