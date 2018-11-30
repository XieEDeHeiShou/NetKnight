package com.pencilbox.netknight.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.pencilbox.netknight.R;

public class DairyTabbed extends AppCompatActivity implements View.OnClickListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private PopupWindow popupWindow;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_tabbed);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.dariy_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        init();
        TabLayout tabLayout = findViewById(R.id.dariy_tabs);
        tabLayout.setupWithViewPager(mViewPager);

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dariy_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }

    private void init() {
        ImageButton btn_diarytopleft = findViewById(R.id.btn_diarytopleft);
        btn_diarytopleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                    return;
                } else {
                    initmPopupWindowViewleft();
                    popupWindow.showAsDropDown(v, 0, 5);
                }

            }
        });
    }

    private void initmPopupWindowViewleft() {
        View customView = getLayoutInflater().inflate(R.layout.netleft_top,
                null, false);
        popupWindow = new PopupWindow(customView, 500, 600);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow.setAnimationStyle(R.style.ways);
        popupWindow.setOutsideTouchable(true);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                return false;
            }
        });


        /** 在这里可以实现自定义视图的功能 */
        Button btn_wall = customView.findViewById(R.id.netbtn_wall);
        Button btn_bag = customView.findViewById(R.id.netbtn_bag);
        btn_wall.setOnClickListener(this);
        btn_bag.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dairy_tabbed, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.netbtn_wall:
                Intent intent = new Intent();
                startActivity(intent.setClass(this, MainTabbed.class));
                popupWindow.dismiss();
                this.finish();
                break;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment();
                case 1:
                    return new Fragment();
                case 2:
                    return new Fragment();
                case 3:
                    return new Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "应用流量";
                case 1:
                    return "移动网络";
                case 2:
                    return "WIFI网络";
                case 3:
                    return "移动+WIFI网络";
            }
            return null;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            startActivity(intent.setClass(this, MainTabbed.class));
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
