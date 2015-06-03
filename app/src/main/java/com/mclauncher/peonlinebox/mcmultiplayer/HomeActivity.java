package com.mclauncher.peonlinebox.mcmultiplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mclauncher.peonlinebox.mcmultiplayer.fragment.HomeFragment;
import com.mclauncher.peonlinebox.mcmultiplayer.fragment.PersonalFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.Locale;


public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener,OnFragmentInteractionListener{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public static ViewPager mViewPager;
    public static FrameLayout fragment_personal_center,fragment_online_games;
//    public static CheckBox main_tab_online_games,main_tab_tools,main_tab_personal_center;
    public static ActionBar actionBar;
    public static TabPageIndicator tabs;
    public static LinearLayout linear_home,linear_personal_center;
    public static TextView tv_home,tv_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        actionBar = getSupportActionBar();


        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setVisibility(View.GONE);

        tabs = (TabPageIndicator) findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);
        tabs.setVisibility(View.GONE);

        linear_home = (LinearLayout) findViewById(R.id.linear_home);
        linear_personal_center = (LinearLayout) findViewById(R.id.linear_personal_center);

        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_person = (TextView) findViewById(R.id.tv_person);
        tv_home.setTextColor(0xffffffff);
        tv_person.setTextColor(0xffababab);

        linear_home.setBackgroundResource(R.drawable.tab_home_linear_pressed);
        linear_personal_center.setBackgroundResource(R.drawable.tab_home_linear_normal);

        fragment_personal_center = (FrameLayout) findViewById(R.id.fragment_personal_center);
        fragment_personal_center.setVisibility(View.GONE);
        fragment_online_games= (FrameLayout) findViewById(R.id.fragment_online_games);
        fragment_online_games.setVisibility(View.VISIBLE);

//        main_tab_online_games = (CheckBox) findViewById(R.id.main_tab_online_games);
////        main_tab_tools = (CheckBox) findViewById(R.id.main_tab_tools);
//        main_tab_personal_center = (CheckBox) findViewById(R.id.main_tab_personal_center);
//        main_tab_online_games.setChecked(true);


        linear_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_online_games.setVisibility(View.VISIBLE);
                fragment_personal_center.setVisibility(View.GONE);
                mViewPager.setVisibility(View.GONE);

                linear_home.setBackgroundResource(R.drawable.tab_home_linear_pressed);
                linear_personal_center.setBackgroundResource(R.drawable.tab_home_linear_normal);
                tabs.setVisibility(View.GONE);

                tv_home.setTextColor(0xffffffff);
                tv_person.setTextColor(0xffababab);

                HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_online_games);
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.replace(R.id.fragment_online_games, homeFragment);
                    ft.commit();
                }
            }
        });
        linear_personal_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_online_games.setVisibility(View.GONE);
                fragment_personal_center.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.GONE);
                tabs.setVisibility(View.GONE);

                tv_home.setTextColor(0xffababab);
                tv_person.setTextColor(0xffffffff);

                linear_home.setBackgroundResource(R.drawable.tab_home_linear_normal);
                linear_personal_center.setBackgroundResource(R.drawable.tab_home_linear_pressed);

                PersonalFragment personalFragment = (PersonalFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_personal_center);
                if (personalFragment == null){
                    personalFragment = new PersonalFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.replace(R.id.fragment_personal_center, personalFragment);
                    ft.commit();
                }
            }
        });


//        main_tab_online_games.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragment_online_games.setVisibility(View.VISIBLE);
//                fragment_personal_center.setVisibility(View.GONE);
//                mViewPager.setVisibility(View.GONE);
//
//                main_tab_online_games.setChecked(true);
////                main_tab_tools.setChecked(false);
//                main_tab_personal_center.setChecked(false);
//                tabs.setVisibility(View.GONE);
//
//                HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_online_games);
//                if (homeFragment == null){
//                    homeFragment = new HomeFragment();
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    ft.replace(R.id.fragment_online_games, homeFragment);
//                    ft.commit();
//                }
//
//            }
//        });
//
////        main_tab_tools.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                fragment_online_games.setVisibility(View.GONE);
////                fragment_personal_center.setVisibility(View.GONE);
////                mViewPager.setVisibility(View.VISIBLE);
////                main_tab_online_games.setChecked(false);
////                main_tab_tools.setChecked(true);
////                main_tab_personal_center.setChecked(false);
////                tabs.setVisibility(View.VISIBLE);
////            }
////        });
//
//        main_tab_personal_center.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragment_online_games.setVisibility(View.GONE);
//                fragment_personal_center.setVisibility(View.VISIBLE);
//                mViewPager.setVisibility(View.GONE);
//                main_tab_online_games.setChecked(false);
////                main_tab_tools.setChecked(false);
//                main_tab_personal_center.setChecked(true);
//                tabs.setVisibility(View.GONE);
//
//                PersonalFragment personalFragment = (PersonalFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_personal_center);
//                if (personalFragment == null){
//                    personalFragment = new PersonalFragment();
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    ft.replace(R.id.fragment_personal_center, personalFragment);
//                    ft.commit();
//                }
//            }
//        });

        HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_online_games);
        if (homeFragment == null){
            homeFragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.replace(R.id.fragment_online_games, homeFragment);
            ft.commit();
        }

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //actionBar.setSelectedNavigationItem(position);
                tabs.setCurrentItem(position);
                //MobclickAgent.onEvent(mContext, "home_fragment_"+String.valueOf(position));
            }
        });

    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case android.R.id.home:
//                finish();
//                break;
//            case R.id.start:
//                Toast.makeText(this,"开始游戏",Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_recommend).toUpperCase(l);
                case 1:
                    return getString(R.string.title_challenge).toUpperCase(l);
                case 2:
                    return getString(R.string.title_survival).toUpperCase(l);
                case 3:
                    return getString(R.string.title_minigames).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
            return rootView;
        }
    }

}
