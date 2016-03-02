package com.czyapp.drex.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.czyapp.drex.R;
import com.czyapp.drex.customView.FragmentTabHost;
import com.czyapp.drex.find.FindFragment;
import com.czyapp.drex.me.MeFragment;
import com.czyapp.drex.owner.OwnerFragment;
import com.czyapp.drex.talk.TalkFragment;

/**
 * Created by Pssskrl1991 on 2016/2/22.
 */
public class MainActivity extends FragmentActivity {
    private FragmentTabHost tabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {MeFragment.class, FindFragment.class, OwnerFragment.class, TalkFragment.class};
    private int mainTabsImages[] = new int[]{R.drawable.talk_selector, R.drawable.find_selector, R.drawable.owner_selector, R.drawable.me_selector};
    private String mainTabsText[] = new String[]{"动态", "发现", "牧场主", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainTabHost();
    }


    private void initMainTabHost() {
        layoutInflater = LayoutInflater.from(this);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.main_fragmentContent);
        for (int i = 0; i < fragmentArray.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mainTabsText[i]).setIndicator(getView(i));
            tabHost.addTab(tabSpec, fragmentArray[i], null);
        }
        tabHost.setCurrentTab(1);

    }

    private View getView(int v) {
        View view = layoutInflater.inflate(R.layout.main_tabs_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.main_tab_img);
        image.setImageResource(mainTabsImages[v]);
        TextView text = (TextView) view.findViewById(R.id.main_tab_text);
        text.setText(mainTabsText[v]);
        text.setTextColor(getResources().getColorStateList(R.color.main_tabs_color));
        return view;
    }

}
