package com.wzp.myselfcode.selfView.uiDesign;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SelfView03 extends androidx.appcompat.widget.AppCompatTextView{

    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private float mTranslate;
    private float DELTAX = 20;

    public SelfView03(Context context) {
        super(context);
    }

    public SelfView03(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfView03(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint();
        String text = getText().toString();
        float textWidth = mPaint.measureText(text);
        //三个文字的宽度
        int gradientSize = (int) (textWidth/text.length()*3);
        // 从左边-gradientSize开始，即左边距离文字gradientSize开始渐变
        mLinearGradient = new LinearGradient(-gradientSize,0,0,0,new int[]{
                0x22ffffff, 0xffffffff, 0x22ffffff},null, Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate +=DELTAX;
        float textWidth = getPaint().measureText(getText().toString());
        if(mTranslate >textWidth+1 || mTranslate<1){
            DELTAX = -DELTAX;
        }
        mMatrix = new Matrix();
        mMatrix.setTranslate(mTranslate,0);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(50);
    }
}