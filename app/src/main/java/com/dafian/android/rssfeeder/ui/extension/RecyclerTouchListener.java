package com.dafian.android.rssfeeder.ui.extension;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Dafian on 10/6/17
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    public interface ClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    private ClickListener clickListener;

    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, RecyclerView recyclerView,
            ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(child, recyclerView
                                    .getChildAdapterPosition(child));
                        }
                    }

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
