package com.wzp.myselfcode.selfView.uiDesign;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SelfView02 extends ViewGroup {


    public SelfView02(Context context) {
        super(context);
    }

    public SelfView02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfView02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1:完成自己的宽高测量
        //得到宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //当前控件宽高（布局自己）
        int measureWidth=0;
        int measureHeight=0;
        //实时宽高
        int nowWidth = 0;
        int nowHeight = 0;

        if(widthMode == MeasureSpec.EXACTLY && heightMode ==MeasureSpec.EXACTLY){
            measureWidth = widthSize;
            measureHeight = heightSize;
        }else {
            //当前控件宽高
            int iChildWidth=0;
            int iChildHeight =0;
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
                    //更新新的行信息
                    nowWidth = iChildWidth;
                    nowHeight = iChildHeight;
                }else {
                    nowWidth+=iChildWidth;
                    nowHeight = Math.max(nowHeight,iChildHeight);
                }
                if(i==childCount-1){
                    measureWidth = Math.max(measureWidth,nowWidth);
                    measureHeight+=nowHeight;
                }
            }

        }
        setMeasuredDimension(measureWidth,measureHeight);
    }

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int startX = getPaddingLeft();
        int startY = getPaddingTop();

        int measureWidth = getMeasuredWidth();
        
        int LineWidth = 0;
        int LineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if(childAt.getVisibility() == GONE){
                continue;
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childAtWidth = childAt.getMeasuredWidth();
            int childAtHeight = childAt.getMeasuredHeight();
            LineWidth += childAtWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            if(startX+LineWidth>measureWidth-getPaddingRight()){
                startX = getPaddingLeft();
                startY += LineHeight;
            }
            int leftChildView = startX + layoutParams.leftMargin;//考虑自己的margin
            int topChildView = startY + layoutParams.topMargin;
            int rightChildView = leftChildView + childAtWidth;
            int bottomChildView = topChildView + childAtHeight;

            childAt.layout(leftChildView, topChildView, rightChildView, bottomChildView);
            startX+=childAtWidth;
            LineHeight = Math.max(LineHeight,childAtHeight+layoutParams.topMargin+layoutParams.bottomMargin);

        }

    }



}
