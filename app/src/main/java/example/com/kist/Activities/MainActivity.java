package example.com.kist.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import example.com.kist.Constant.NonSwipingViewPager;
import example.com.kist.R;

/**
 * Created by pr0 on 2/8/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DrawerLayout.DrawerListener {
    NonSwipingViewPager mainPager;
    RelativeLayout main;

    ImageView booking, currency, info, map, home, menu;                                             //all toolbar options
    LinearLayout menuHome, menuDetails, menuGuide, menuBookings,
            menuNotice, menuContact, menuFeedback, menuDeveloper;                                   //all menu items

    DrawerLayout rootLay;

    MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.home_screen);

        initializeViews();
        setOnClickListeners();

        rootLay.addDrawerListener(this);
        changeAlphaAll();

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPager.setAdapter(adapter);

        home.setAlpha(0.5f);
    }

    private void initializeViews() {
        rootLay = (DrawerLayout) findViewById(R.id.root);

        booking = (ImageView) findViewById(R.id.booking);
        currency = (ImageView) findViewById(R.id.currency);
        info = (ImageView) findViewById(R.id.info);
        map = (ImageView) findViewById(R.id.map);
        home = (ImageView) findViewById(R.id.home);
        menu = (ImageView) findViewById(R.id.menu);

        menuHome = (LinearLayout) findViewById(R.id.menu_home);
        menuDetails = (LinearLayout) findViewById(R.id.menu_details);
        menuGuide = (LinearLayout) findViewById(R.id.menu_guide);
        menuBookings = (LinearLayout) findViewById(R.id.menu_bookings);
        menuNotice = (LinearLayout) findViewById(R.id.menu_notice);
        menuContact = (LinearLayout) findViewById(R.id.menu_contact);
        menuFeedback = (LinearLayout) findViewById(R.id.menu_feedback);
        menuDeveloper = (LinearLayout) findViewById(R.id.menu_developer);

        main = (RelativeLayout) findViewById(R.id.main);

        mainPager = (NonSwipingViewPager) findViewById(R.id.main_pager);
    }
    private void setOnClickListeners() {
        booking.setOnClickListener(this);
        currency.setOnClickListener(this);
        info.setOnClickListener(this);
        map.setOnClickListener(this);
        home.setOnClickListener(this);
        menu.setOnClickListener(this);

        menuBookings.setOnClickListener(this);
        menuDeveloper.setOnClickListener(this);
        menuFeedback.setOnClickListener(this);
        menuContact.setOnClickListener(this);
        menuNotice.setOnClickListener(this);
        menuDetails.setOnClickListener(this);
        menuGuide.setOnClickListener(this);
        menuHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == booking) {
            changeAlphaAll();
            v.setAlpha(0.5f);
        } else if(v == currency) {
            changeAlphaAll();
            v.setAlpha(0.5f);
        } else if(v == info) {
            changeAlphaAll();
            v.setAlpha(0.5f);
        } else if(v == map) {
            changeAlphaAll();
            v.setAlpha(0.5f);
        } else if(home == v) {
            changeAlphaAll();
            v.setAlpha(0.5f);
        } else if(v == menu) {
            if(rootLay.isDrawerOpen(GravityCompat.END))
                rootLay.closeDrawer(GravityCompat.END);
            else
                rootLay.openDrawer(GravityCompat.END);
        } else if(v == menuBookings) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuDeveloper) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuFeedback) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuContact) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuNotice) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuDetails) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuHome) {
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuGuide) {
            rootLay.closeDrawer(GravityCompat.END);
        }
    }

    private void changeAlphaAll() {
        booking.setAlpha(1f);
        currency.setAlpha(1f);
        info.setAlpha(1f);
        map.setAlpha(1f);
        home.setAlpha(1f);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if(rootLay.isDrawerVisible(GravityCompat.START)) {
            rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
            main.setTranslationX(slideOffset * drawerView.getWidth());
        } else if(rootLay.isDrawerVisible(GravityCompat.END)) {
            main.setTranslationX(-slideOffset * drawerView.getWidth());
            rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
        }

        if(!rootLay.isDrawerVisible(GravityCompat.START) && !rootLay.isDrawerVisible(GravityCompat.END)) {
            rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);
            rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
        }

        rootLay.bringChildToFront(drawerView);
        rootLay.requestLayout();
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        main.setTranslationX(0);
        rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);
        rootLay.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        private int TOTAL_PAGES = 1;

        public MainPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return TOTAL_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();

            if(position == 0) {
                fragment = new HomeFragment();
            }
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}