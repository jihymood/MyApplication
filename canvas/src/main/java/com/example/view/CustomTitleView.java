package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangjh on 2017/1/20 0020 17:04
 */

public class CustomTitleView extends View {
    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);// 设置红色
        p.setTextSize(30);
        //drawText(String text, float x, floaty, Paint paint)
        // 渲染文本，Canvas类除了上面的还可以描绘文字，参数一是String类型的文本，参数二x轴，参数三y轴，参数四是Paint对象。
        canvas.drawText("画圆：", 10, 60, p);// 画文本
        //drawCircle(float cx, float cy, float radius,Paint paint)// 绘制圆，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象；
        canvas.drawCircle(150, 50, 20, p);// 小圆
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(250, 50, 30, p);// 大圆
        //**********************************************
        canvas.drawText("画线及弧线：", 10, 150, p);
        p.setColor(Color.GREEN);// 设置绿色
        //drawLine(float startX, float startY, float stopX, float stopY, Paintpaint)
        // 画线，参数一起始点的x轴位置，参数二起始点的y轴位置，参数三终点的x轴水平位置，参数四y轴垂直位置，最后一个参数为Paint 画刷对象。
        canvas.drawLine(210, 160, 280, 80, p);// 画线
        canvas.drawLine(280, 80, 350, 160, p);// 斜线
        //画笑脸弧线
        p.setStyle(Paint.Style.STROKE);//设置空心
        //RectF（）构造一个无参的矩形 RectF（float left,float top,float right,float bottom）构造一个指定了4个参数的矩形 RectF(Rect F r)
        // 根据指定的RectF对象来构造一个RectF对象
        RectF oval1 = new RectF(380, 120, 410, 140);
        //drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)//画弧
        //参数一是RectF对象，一个矩形区域椭圆形的界限用于定义在形状、大小、电弧，参数二是起始角(度)在电弧的开始，
        //参数三扫描角(度)开始顺时针测量的，参数四是如果这是真的话,包括椭圆中心的电弧,并关闭它,如果它是假这将是一个弧线,参数五是Paint对象；
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(430, 120, 460, 140);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(395, 130, 445, 160);
        canvas.drawArc(oval1, 0, 180, false, p);//小弧形

        canvas.drawText("画矩形：", 10, 220, p);
        p.setColor(Color.GRAY);// 设置灰色
        p.setStyle(Paint.Style.FILL);//设置填满
        //drawRect(RectF rect, Paint paint) //绘制区域，参数一为RectF一个区域
        canvas.drawRect(150, 200, 170, 220, p);// 正方形
        canvas.drawRect(190, 200, 290, 220, p);// 长方形

        canvas.drawText("画扇形和椭圆:", 10, 350, p);
        /* 设置渐变色 这个正方形的颜色是改变的 */
        Shader mShader = new LinearGradient(0, 0, 100, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color
                .YELLOW, Color.LTGRAY}, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        p.setShader(mShader);

        // p.setColor(Color.BLUE);
        RectF oval2 = new RectF(250, 290, 390, 430);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 200, 150, true, p);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval2.set(450, 300, 550, 350);
        canvas.drawOval(oval2, p);

        canvas.drawText("画三角形：", 10, 480, p);
        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(180, 400);// 此点为多边形的起点
        path.lineTo(250, 480);
        path.lineTo(180, 480);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

        /*
         * Path类封装复合(多轮廓几何图形的路径
         * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
         * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
         */
        //画圆角矩形
        p.reset();//重置
        p.setTextSize(30);
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(Color.LTGRAY);
        p.setAntiAlias(true);// 设置画笔的锯齿效果
        canvas.drawText("画圆角矩形:", 10, 600, p);
        RectF oval3 = new RectF(180, 570, 300, 610);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径
        //画贝塞尔曲线
        canvas.drawText("画贝塞尔曲线:", 10, 750, p);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GREEN);
        Path path2 = new Path();
        path2.moveTo(250, 700);//设置Path的起点
        path2.quadTo(290, 700, 310, 850); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, p);//画出贝塞尔曲线
        //画点
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth((float) 5.0);
        canvas.drawText("画点：", 10, 900, p);
        //drawPoint(float x, float y, Paint paint) //画点，参数一水平x轴，参数二垂直y轴，第三个参数为Paint对象。
        canvas.drawPoint(100, 900, p);//画一个点
        canvas.drawPoints(new float[]{100, 920, 125, 920, 145, 920}, p);//画多个点


    }
}
