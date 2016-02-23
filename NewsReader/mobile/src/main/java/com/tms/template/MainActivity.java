package com.tms.template;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    public static MainActivity _Instance;
    public static final String mainLink = "http://www.istinomer.rs/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _Instance = this;
        setContentView(R.layout.activity_main);
        InitTabs();
    }

    private void InitTabs() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Istinomer - Da se ne lazemo"); // set Text for top toolbar

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addTab(tabLayout.newTab().setText("OBEĆANJA")); // 1st tab
        tabLayout.addTab(tabLayout.newTab().setText("ISTINITOST")); // 2nd tab
        tabLayout.addTab(tabLayout.newTab().setText("DOSLEDNOST")); // 3rd tab
        tabLayout.addTab(tabLayout.newTab().setText("AKTERI")); // 4th tab
        tabLayout.addTab(tabLayout.newTab().setText("RETROVIZOR")); // 5th tab
        tabLayout.addTab(tabLayout.newTab().setText("AKTUELNO")); // 6th tab
        tabLayout.addTab(tabLayout.newTab().setText("U FOKUSU")); // 7th tab
        tabLayout.addTab(tabLayout.newTab().setText("VIDEO")); // 8th tab
        tabLayout.addTab(tabLayout.newTab().setText("AMNEZIJA")); // 9th tab
        tabLayout.addTab(tabLayout.newTab().setText("AKCIJA")); // 10th tab
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(10);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // here he gets the whole text from a class and extracts the href link from it
    // not used!
    public String reg(String Element){
        String data1 = Element;
        //Log.d("TMS", "reg element is: " + Element);

        //String data = "<div class=\"item-2 ocena_1\"><a href=\"ocena/3331/Zvucne-barijere-do-kraja-2015\"><div class=\"picture\">";
        String tra = "";

        String regex = "\\bhref=\"\\b([\\s\\S]*?)\\bdiv\\b";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(data1);

        while (matcher.find())
        {
            //Log.d("TMS", "Start index: " + matcher.start());
            //Log.d("TMS", " End index: " + matcher.end() + " ");
            Log.d("TMS","gropu: "+ matcher.group());
            tra = data1.substring(matcher.start()+6,matcher.end()-10);
            Log.d("TMS","substring is: "+ tra);
            return tra;
        }

        return "0";
    }
}
