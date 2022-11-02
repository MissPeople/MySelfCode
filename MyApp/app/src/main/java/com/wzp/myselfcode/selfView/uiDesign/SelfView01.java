package com.wzp.myselfcode.selfView.uiDesign;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SelfView01 extends ViewGroup {


    public SelfView01(Context context) {
        super(context);
    }

    public SelfView01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    List<Integer> listLineHeight = new ArrayList<>();
    List<List<View>> listLineView = new ArrayList<>();

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        Log.e("TAGSS", "onLayout: ");
        int left,top,right,bottom;
        int curLeft=0,curTop=0;
        for (int i = 0; i < listLineView.size(); i++) {
            List<View> lineViews = listLineView.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                left = curLeft + layoutParams.leftMargin;
                top = curTop +layoutParams.topMargin;
                right = left+view.getMeasuredWidth();
                bottom = top+view.getMeasuredHeight();

                view.layout(left,top,right,bottom);

                curLeft+=view.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            }
            curLeft=0;
            curTop+=listLineHeight.get(i);
        }
        listLineView.clear();
        listLineHeight.clear();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("TAGSS", "onMeasure: " );
        listLineView.clear();
        listLineHeight.clear();
        //1:完成自己的宽高测量
        //得到宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //父容器宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        //当前控件宽高（布局自己）
        int measureWidth=0;
        int measureHeight=0;

        if(widthMode == MeasureSpec.EXACTLY && heightMode ==MeasureSpec.EXACTLY){
            measureWidth = widthSize;
            measureHeight = heightSize;
        }else {
            //当前控件宽高
            int iChildWidth=0;
            int iChildHeight =0;
            //实时宽高
            int nowWidth = 0;
            int nowHeight = 0;
            //获取子VIEW数量用于迭代
            int childCount = getChildCount();
            List<View> viewList = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                //确定当前行宽，+行高
                View childAt = getChildAt(i);
                //先让子控件测量自己
                measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
                //MARGIN 获取到当前LayoutParams，获取XML资源
                MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                //获取实际宽高(MARGIN+WIDTH)
                iChildWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin+layoutParams.rightMargin;
                iChildHeight = childAt.getMeasuredHeight() + layoutParams.topMargin+layoutParams.bottomMargin;
                //是否需要换行
                if(nowWidth+iChildWidth>widthSize){
                    //保存当前行信息
                    measureWidth = Math.max(measureWidth,nowWidth);
                    measureHeight+=nowHeight;
                    listLineHeight.add(nowHeight);
                    listLineView.add(viewList);
                    //更新新的行信息
                    nowWidth = iChildWidth;
                    nowHeight = iChildHeight;
                    viewList = new ArrayList<>();
                    viewList.add(childAt);
                }else {
                    nowWidth+=iChildWidth;
                    nowHeight = Math.max(nowHeight,iChildHeight);
                    viewList.add(childAt);
                }
                if(i==childCount-1){
                    measureWidth = Math.max(measureWidth,nowWidth);
                    measureHeight+=nowHeight;
                    listLineHeight.add(nowHeight);
                    listLineView.add(viewList);
                }
            }
            
        }
        setMeasuredDimension(measureWidth,measureHeight);
    }

}
