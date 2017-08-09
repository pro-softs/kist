package example.com.kist.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Stack;

import example.com.kist.Constant.NonSwipingViewPager;
import example.com.kist.R;

/**
 * Created by pr0 on 2/8/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        DrawerLayout.DrawerListener, example.com.kist.Constant.ViewPager.OnPageChangeListener {
    NonSwipingViewPager mainPager;
    RelativeLayout main;

    ImageView booking, currency, info, map, home, menu, login;                                             //all toolbar options
    LinearLayout menuHome, menuDetails, menuGuide, menuBookings,
            menuNotice, menuContact, menuFeedback, menuDeveloper, back, bottomLayHome;
    RelativeLayout bottomLayNotice;

    DrawerLayout rootLay;

    TextView header;

    MainPagerAdapter adapter;
    String roomType = "4 Bed Dorm", guideitemDetails = "", itemType = "FOOD",
            transportItemDetails = "", nextItemDetails = "";

    private Stack<Integer> stackkk = new Stack<>(); // Edited
    private int tabPosition = 0;

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.home_screen);

        initializeViews();
        setOnClickListeners();

        rootLay.addDrawerListener(this);
        changeAlphaAll();

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPager.setOffscreenPageLimit(0);
        mainPager.setAdapter(adapter);

        mainPager.setOnPageChangeListener(this);

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
        login = (ImageView) findViewById(R.id.login);

        menuHome = (LinearLayout) findViewById(R.id.menu_home);
        menuDetails = (LinearLayout) findViewById(R.id.menu_details);
        menuGuide = (LinearLayout) findViewById(R.id.menu_guide);
        menuBookings = (LinearLayout) findViewById(R.id.menu_bookings);
        menuNotice = (LinearLayout) findViewById(R.id.menu_notice);
        menuContact = (LinearLayout) findViewById(R.id.menu_contact);
        menuFeedback = (LinearLayout) findViewById(R.id.menu_feedback);
        menuDeveloper = (LinearLayout) findViewById(R.id.menu_developer);
        bottomLayHome = (LinearLayout) findViewById(R.id.home_lay);

        bottomLayNotice = (RelativeLayout) findViewById(R.id.message_post_lay);

        main = (RelativeLayout) findViewById(R.id.main);
        header = (TextView) findViewById(R.id.headerName);

        back = (LinearLayout) findViewById(R.id.back);

        header.setText("THE BLOCKS APP");
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

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == booking) {
            setPage(3);
        } else if(v == currency) {
            setPage(15);
        } else if(v == info) {
            setPage(11);
        } else if(v == map) {
        } else if(home == v) {
            setPage(0);
        } else if(v == menu) {
            if(rootLay.isDrawerOpen(GravityCompat.END))
                rootLay.closeDrawer(GravityCompat.END);
            else
                rootLay.openDrawer(GravityCompat.END);
        } else if(v == menuBookings) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(3);
        } else if(v == menuDeveloper) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(13);
        } else if(v == menuFeedback) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(14);
        } else if(v == menuContact) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(12);
        } else if(v == menuNotice) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(16);
        } else if(v == menuDetails) {
            setPage(1);
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuHome) {
            setPage(0);
            rootLay.closeDrawer(GravityCompat.END);
        } else if(v == menuGuide) {
            rootLay.closeDrawer(GravityCompat.END);
            setPage(4);
        } else if(v == back) {
            if (stackkk.size() > 1) {
                stackkk.pop();
                mainPager.setCurrentItem(stackkk.lastElement());
            } else {
                back.setVisibility(View.GONE);
            }
        }
    }

    private void changeAlphaAll() {
        booking.setAlpha(1f);
        currency.setAlpha(1f);
        info.setAlpha(1f);
        map.setAlpha(1f);
        home.setAlpha(1f);

        menu.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);

        bottomLayNotice.setVisibility(View.GONE);
        bottomLayHome.setVisibility(View.VISIBLE);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0) {
            changeAlphaAll();
            home.setAlpha(0.5f);

            header.setText("THE BLOCKS APP");
            back.setVisibility(View.GONE);
        } else if(position == 1) {
            changeAlphaAll();
            header.setText("ABOUT US");

            back.setVisibility(View.VISIBLE);
        } else if(position == 2) {
            changeAlphaAll();
            header.setText("ROOMS");
            back.setVisibility(View.VISIBLE);
        } else if(position == 3) {
            changeAlphaAll();

            booking.setAlpha(0.5f);
            header.setText("THE BLOCKS APP");
            back.setVisibility(View.VISIBLE);
        } else if(position == 4) {
            changeAlphaAll();

            header.setText("LOCAL GUIDE");
            back.setVisibility(View.VISIBLE);
        } else if(position == 5) {
            changeAlphaAll();

            header.setText("DIRECTIONS");
            back.setVisibility(View.VISIBLE);
        } else if(position == 6) {
            changeAlphaAll();

            header.setText(itemType);
            back.setVisibility(View.VISIBLE);
        } else if(position == 7) {
            changeAlphaAll();
            header.setText("Get Around");
            back.setVisibility(View.VISIBLE);
        } else if(position == 8) {
            changeAlphaAll();

            header.setText("Get Around");
            back.setVisibility(View.VISIBLE);
        } else if(position == 9) {
            changeAlphaAll();

            header.setText("Where To Next");
            back.setVisibility(View.VISIBLE);

        } else if(position == 10) {
            changeAlphaAll();

            header.setText("Where To Next");
            back.setVisibility(View.VISIBLE);
        } else if(position == 11) {
            changeAlphaAll();

            info.setAlpha(0.5f);

            header.setText("Travel Tips");
            back.setVisibility(View.VISIBLE);
        } else if(position == 12) {
            changeAlphaAll();

            header.setText("About Us");
            back.setVisibility(View.VISIBLE);
        } else if(position == 13) {
            changeAlphaAll();

            header.setText("Developer");
            back.setVisibility(View.VISIBLE);
        } else if(position == 14) {
            changeAlphaAll();

            header.setText("Feedback");
            back.setVisibility(View.VISIBLE);
        } else if(position == 15) {
            changeAlphaAll();
            currency.setAlpha(0.5f);

            header.setText("Currency Converter");
            back.setVisibility(View.VISIBLE);
        } else if(position == 16) {
            changeAlphaAll();

            menu.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);

            header.setText("Notifications");
            back.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        private int TOTAL_PAGES = 17;

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
            } else if(position == 1) {
                fragment = new HostelDetailsFragment();
            } else if(position == 2) {
                fragment = new RoomFragment();
                Bundle b = new Bundle();
                b.putString("type", roomType);
                fragment.setArguments(b);
            } else if(position == 3) {
                fragment = new BookingFragment();
            } else if(position == 4) {
                fragment = new LocalGuideFragment();
            } else if(position == 5) {
                fragment = new DirectionFragment();
            } else if(position == 6) {
                fragment = new LocalGuideItemDetailsFrag();
                Bundle b = new Bundle();
                b.putString("item", guideitemDetails);
                fragment.setArguments(b);
            } else if(position == 7) {
                fragment = new TransportFragment();
            } else if(position == 8) {
                fragment = new TransportItemDetailsFragment();
                Bundle b = new Bundle();

                b.putString("item", transportItemDetails);
                fragment.setArguments(b);
            } else if(position == 9) {
                fragment = new NextFragment();
            } else if(position == 10) {
                fragment = new NextItemDetailsFragment();
                Bundle b = new Bundle();

                b.putString("item", nextItemDetails);
                fragment.setArguments(b);
            } else if(position == 11) {
                fragment = new TravelTipFragment();
            } else if(position == 12) {
                fragment = new AboutUsFragment();
            } else if(position == 13) {
                fragment = new DeveloperFragment();
            } else if(position == 14) {
                fragment = new FeedbackFragment();
            } else if(position == 15) {
                fragment = new CurrencyConverterFragment();
            } else if(position == 16) {
                fragment = new NotificationFrament();
            }

            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    private void pushToStack() {
        if (stackkk.empty())
            stackkk.push(0);

        if (stackkk.contains(tabPosition)) {
            stackkk.remove(stackkk.indexOf(tabPosition));
            stackkk.push(tabPosition);
        } else {
            stackkk.push(tabPosition);
        }
    }

    @Override
    public void onBackPressed() {
        if (stackkk.size() > 1) {
            stackkk.pop();
            mainPager.setCurrentItem(stackkk.lastElement());
        } else
            super.onBackPressed();
    }

    public void setPage(int position) {

        mainPager.setCurrentItem(position);
        tabPosition = position;

        pushToStack();
    }

    public void setDetailsPage(int position, String type) {
        roomType = type;
        mainPager.setCurrentItem(position);
        tabPosition = position;

        pushToStack();
    }

    public void setItemFrag(int position, String details, String type) {
        guideitemDetails = details;
        itemType = type;

        mainPager.setCurrentItem(position);
        tabPosition = position;

        pushToStack();
    }


    public void setTransportItemFrag(int position, String details) {
        transportItemDetails = details;

        mainPager.setCurrentItem(position);
        tabPosition = position;

        pushToStack();
    }

    public void setNextItemFrag(int position, String details) {
        nextItemDetails = details;

        mainPager.setCurrentItem(position);
        tabPosition = position;

        pushToStack();
    }
}
