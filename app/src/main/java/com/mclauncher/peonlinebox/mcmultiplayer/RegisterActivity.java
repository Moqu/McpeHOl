package com.mclauncher.peonlinebox.mcmultiplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mclauncher.peonlinebox.mcmultiplayer.fragment.LoginFragment;
import com.mclauncher.peonlinebox.mcmultiplayer.fragment.RegisterFragment;

public class RegisterActivity extends ActionBarActivity implements OnFragmentInteractionListener {

    public static LinearLayout linear_all, linear_close, linear_register, linear_login;
    public static FrameLayout frame_next, frame_register, frame_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String str = getIntent().getExtras().getString("type");

        frame_next = (FrameLayout) findViewById(R.id.frame_next);
        frame_register = (FrameLayout) findViewById(R.id.frame_register);
        frame_register.setVisibility(View.GONE);
        frame_login = (FrameLayout) findViewById(R.id.frame_login);

        linear_all = (LinearLayout) findViewById(R.id.linear_all);
        linear_close = (LinearLayout) findViewById(R.id.linear_close);

        linear_register = (LinearLayout) findViewById(R.id.linear_register);
        linear_register.setBackgroundResource(R.drawable.activity_register_selecte);

        linear_login = (LinearLayout) findViewById(R.id.linear_login);

        linear_close.setOnClickListener(onClickListener);
        linear_register.setOnClickListener(onClickListener);
        linear_login.setOnClickListener(onClickListener);

        if (str.equalsIgnoreCase("注册")) {

            linear_register.setBackgroundResource(R.drawable.activity_register_selecte);
            linear_close.setBackgroundResource(R.drawable.activity_register_default);
            linear_login.setBackgroundResource(R.drawable.activity_register_default);
            frame_register.setVisibility(View.VISIBLE);
            frame_login.setVisibility(View.GONE);

            RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.frame_register);
            if (registerFragment == null) {
                registerFragment = new RegisterFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.frame_register, registerFragment);
                ft.commit();
            }

        } else {
            linear_register.setBackgroundResource(R.drawable.activity_register_default);
            linear_close.setBackgroundResource(R.drawable.activity_register_default);
            linear_login.setBackgroundResource(R.drawable.activity_register_selecte);
            frame_register.setVisibility(View.GONE);
            frame_login.setVisibility(View.VISIBLE);
            LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frame_login);
            if (loginFragment == null) {
                loginFragment = new LoginFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_login, loginFragment);
                ft.commit();
            }
        }



    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linear_close:
                    linear_register.setBackgroundResource(R.drawable.activity_register_default);
                    linear_close.setBackgroundResource(R.drawable.activity_register_default);
                    linear_login.setBackgroundResource(R.drawable.activity_register_default);
                    finish();
                    break;
                case R.id.linear_register:
                    linear_register.setBackgroundResource(R.drawable.activity_register_selecte);
                    linear_close.setBackgroundResource(R.drawable.activity_register_default);
                    linear_login.setBackgroundResource(R.drawable.activity_register_default);

                    frame_register.setVisibility(View.VISIBLE);
                    frame_login.setVisibility(View.GONE);

                    RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.frame_register);
                    if (registerFragment == null) {
                        registerFragment = new RegisterFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_register, registerFragment);
                        ft.commit();
                    }
                    break;
                case R.id.linear_login:
                    linear_register.setBackgroundResource(R.drawable.activity_register_default);
                    linear_close.setBackgroundResource(R.drawable.activity_register_default);
                    linear_login.setBackgroundResource(R.drawable.activity_register_selecte);
                    frame_register.setVisibility(View.GONE);
                    frame_login.setVisibility(View.VISIBLE);

                    LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frame_login);
                    if (loginFragment == null) {
                        loginFragment = new LoginFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_login, loginFragment);
                        ft.commit();
                    }
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
