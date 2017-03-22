package com.qf.viewpagertest01;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(vp.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,5000);
        }
    };

    private ViewPager vp;
    private int[] imgs = new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.pp1,R.drawable.tangyixin};
    private List<ImageView> imageViews;
    private LinearLayout dotLayout;
    private int prePosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotLayout = ((LinearLayout) findViewById(R.id.dot_layou));
        vp = ((ViewPager) findViewById(R.id.viewpager));
        initData();
        MyAdapter adapter = new MyAdapter(imageViews);
        vp.setAdapter(adapter);
        vp.setCurrentItem(6*10000);
        dotLayout.getChildAt(0).setEnabled(false);
//        handler.sendEmptyMessage(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotLayout.getChildAt(prePosition).setEnabled(true);
                dotLayout.getChildAt(position%6).setEnabled(false);
                prePosition = position%6;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i]);
            imageViews.add(imageView);
            View dotView = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            params.leftMargin = 10;
            dotView.setLayoutParams(params);
            dotView.setBackgroundResource(R.drawable.dot_background);
            dotLayout.addView(dotView);
        }

    }


}
