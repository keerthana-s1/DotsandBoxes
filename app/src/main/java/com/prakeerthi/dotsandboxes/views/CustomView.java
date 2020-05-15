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
import android.widget.TextView;

import com.prakeerthi.dotsandboxes.Gamescreen;

import static java.lang.Math.min;

public class CustomView extends View {
    private TextView text,s1,s2;
    private Rect rect;
    private Paint paint;
    private Paint paint1,paint2,color1,color2,color3,color4;
    private Path path1,path2;
    private int[] flag ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] box ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] points ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private  int i,j,temp,prevpos=-1,pos=0,player=1,x,y;
    private int p1=0,p2=0,b,n,space;

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
    public void size(int si)
    {
        n=si;
        b=n-1;
        space=900/b;
    }
    public void change(TextView playe,TextView scor1,TextView scor2)
    {
       text=playe;
        if(player==1)
            text.setText("Player 1 turn :)");
        else
            text.setText("Player 2 turn :)");
        s1=scor1;
        s2=scor2;
        s1.setText(String.valueOf(p1));
        s2.setText(String.valueOf(p2));
    }

    public void update(int n)
    {   if(n==1)
            p1++;
        else
            p2++;
        s1.setText(String.valueOf(p1));
        s2.setText(String.valueOf(p2));

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


    public void play(int temp) {

            x = temp / n;
            y = temp % n;
            if (prevpos == -1) {
                flag[temp] = player;
                if(player==1)
                path1.moveTo(50 + (space * x), 50 + (space * y));
                else
                    path2.moveTo(50 + (space * x), 50 + (space * y));
                prevpos = temp;
            } else {
                if (temp == prevpos + 1 || temp == prevpos + n || temp == prevpos - 1 || temp == prevpos - n) {

                    flag[temp] = player;

                    pos= temp<prevpos?temp:prevpos;
                    if (temp == prevpos + 1 || temp == prevpos -1) {

                       box[pos]++;
                        if(box[pos]>=4&&points[pos]==0)
                        {points[pos]=player;
                            update(player);}
                       if(pos>=n)
                       {pos-=n;
                       box[pos]++;
                           if(box[pos]>=4&&points[pos]==0)
                           {points[pos]=player;
                                update(player);}
                       }
                    }
                    else
                    {
                        box[pos]++;
                        if(box[pos]>=4&&points[pos]==0)
                        {points[pos]=player;
                            update(player);}
                        if(pos>=1)
                        {pos-=1;
                        box[pos]++;
                            if(box[pos]>=4&&points[pos]==0)
                            {points[pos]=player;
                            update(player);}

                        }
                    }
                    pos= temp<prevpos?temp:prevpos;

                    if(player==1)
                        path1.lineTo(50 + (space * x), 50 + (space * y));
                    else
                        path2.lineTo(50 + (space * x), 50 + (space * y));

                    player = player == 1 ? 2 : 1;
                    if(player==1)
                    text.setText("Player 1 turn :)");
                    else
                        text.setText("Player 2 turn :)");

                    if(p1+p2==(b*b))

                   {
                        if(p1>p2)
                            text.setText("Game Over.Player1 won");
                        else if(p1<p2)
                            text.setText("Game Over.Player2 won");
                        else
                            text.setText("Game Over.It's a draw");

                    }

                    prevpos = -1;


                } else {
                    flag[prevpos] = 0;
                    flag[temp] = player;
                    if(player==1)
                        path1.moveTo(50 + (space * x), 50 + (space * y));
                    else
                        path2.moveTo(50 + (space * x), 50 + (space * y));
                    prevpos = temp;
                }
            }

        }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#1Affffff"));


        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
            {   temp=(i*n)+j;
                if(flag[temp]==1)
                canvas.drawCircle(50+(space*i),50+(space*j),10,  color1);
                else if(flag[temp]==2)
                    canvas.drawCircle(50+(space*i),50+(space*j),10,  color2);
            else
                canvas.drawCircle(50+(space*i),50+(space*j),10,  paint);
            }
         canvas.drawPath(path1,paint1);
        canvas.drawPath(path2,paint2);
        for(i=0;i<b;i++)
            for(j=0;j<b;j++)
            {   temp=i*n+j;
                rect.top=50+(space*(j));
                rect.left=(i)*space+50;
                rect.right=((i+1)*space)+50;
                rect.bottom=50+(space*(j+1));
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
                for(i=0;i<n;i++)
                    for(j=0;j<n;j++)
                    {
                        if(x<=100+(space*i)&&x>=(space*i)&&y<=100+(space*j)&&y>=(space*j)) {
                            temp = (i * n) + j;
                            play(temp);
                            break;
                        }
                    }

            default: break;
        }
        return true;
    }


}
