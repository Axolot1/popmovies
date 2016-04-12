package com.axolotl.popmovies.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * https://github.com/chiuki/android-recyclerview
 */
public class AutoFitRecyclerView extends RecyclerView{
    private GridLayoutManager layoutManager;
    private int columnWidth;

    public AutoFitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }


    public AutoFitRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoFitRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs != null){
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }
        layoutManager = new GridLayoutManager(context, 1);
        setLayoutManager(layoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if(columnWidth > 0){
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            layoutManager.setSpanCount(spanCount);
        }
    }

    public static class MarginDecoration extends RecyclerView.ItemDecoration{
        private int margin;

        public MarginDecoration(int margin) {
            this.margin = margin;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            outRect.set(margin, margin, margin, margin);
        }
    }
}
