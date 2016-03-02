package com.czyapp.drex.find;


import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.czyapp.drex.R;
import com.czyapp.drex.find.drink.DrinkFragment;
import com.czyapp.drex.find.recommend.RecommendFragment;
import com.czyapp.drex.find.topic.TopicFragment;

import java.util.ArrayList;

/**
 * Created by Pssskrl1991 on 2016/2/22.
 */
public class FindFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ImageView cursor;
    private int cursorWidth;
    private int offset;
    private int currentIndex = 0;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArray = new ArrayList<Fragment>();
    private int offsetOne;
    private ImageView scan;
    private TextView recommend, topic, drink;
    private TextView tabs[];
    private boolean flash = false;
    private Camera camera;
    private Camera.Parameters parameter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        cursor = (ImageView) view.findViewById(R.id.cursor);
        scan = (ImageView) view.findViewById(R.id.scan);
        viewPager = (ViewPager) view.findViewById(R.id.find_ViewPager);
        recommend = (TextView) view.findViewById(R.id.find_tab_recommend);
        topic = (TextView) view.findViewById(R.id.find_tab_topic);
        drink = (TextView) view.findViewById(R.id.find_tab_drink);
        tabs = new TextView[]{recommend, topic, drink};
       /* camera = Camera.open();
        camera.startPreview();
        parameter = camera.getParameters();*/
        //注册设置监听
        scan.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
        recommend.setOnClickListener(this);
        topic.setOnClickListener(this);
        drink.setOnClickListener(this);
        //初始化数据
        fragmentArray.add(new RecommendFragment());
        fragmentArray.add(new TopicFragment());
        fragmentArray.add(new DrinkFragment());
        //
        initCursor();
        offsetOne = offset * 2 + cursorWidth;
        showViewPager();
        return view;
    }

    //初始化标签指示器位置
    private void initCursor() {
        cursorWidth = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int part = metrics.widthPixels / 5 * 3;
        offset = (part / 3 - cursorWidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);
    }

    //绑定适配器显示ViewPager
    private void showViewPager() {
        MFragmentPagerAdapter fragmentPagerAdapter = new MFragmentPagerAdapter(getFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //页面滑动监听，动态改变游标位置
    @Override
    public void onPageSelected(int position) {
        TranslateAnimation translate = new TranslateAnimation(currentIndex * offsetOne, position * offsetOne, 0, 0);
        currentIndex = position;
        translate.setFillAfter(true);
        translate.setDuration(200);
        cursor.startAnimation(translate);
        for (int i = 0; i < tabs.length; i++) {
            if (i == position) {
                tabs[i].setTextColor(getResources().getColor(R.color.白色));
            } else {
                tabs[i].setTextColor(getResources().getColor(R.color.发现Tab));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //设置二维码、标题栏点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan:  //点击进入二维码扫描
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(4);
                scan.startAnimation(scaleAnimation);
/*
                if (!flash) {
                    flash = true;
                    parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameter);
                } else {
                    parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameter);
                    flash = false;
                }*/

               /* Intent intent = new Intent(getActivity(),MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                break;
        }
    }


    //自定义FragmentPagerAdapter类
    public class MFragmentPagerAdapter extends FragmentPagerAdapter {

        public MFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArray.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArray.size();
        }
    }

}
