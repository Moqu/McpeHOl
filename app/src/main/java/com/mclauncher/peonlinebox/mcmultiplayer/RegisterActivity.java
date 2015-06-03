package com.mclauncher.peonlinebox.mcmultiplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mclauncher.peonlinebox.mcmultiplayer.fragment.LoginFragment;
import com.mclauncher.peonlinebox.mcmultiplayer.fragment.RegisterFragment;
import com.mclauncher.peonlinebox.mcmultiplayer.util.ToastUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class RegisterActivity extends ActionBarActivity implements OnFragmentInteractionListener {

    public static LinearLayout linear_all, linear_close, linear_register, linear_login;
    public static FrameLayout frame_next, frame_register, frame_login;
    public static ImageView iv_qq_login;
    private final String QQ_APPID = "1104472561";
    private Intent intent;
    private String mUUID;
    private Context mContext;
    private String nickName;
    private String playerIcon;


    private Tencent mTencent;

    private Tencent getmTencent() {
        McOnlineApplication application = (McOnlineApplication) this.getApplication();
        return application.mTencent;
    }

    private void setmTencent(Tencent mTencent) {
        McOnlineApplication application = (McOnlineApplication) this.getApplication();
        application.mTencent = mTencent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mTencent = getmTencent();
        if (mTencent == null) {
            mTencent = Tencent.createInstance(QQ_APPID, this);
        }
        McOnlineApplication application = (McOnlineApplication) this.getApplication();
        mUUID = application.devicesId;

        mContext = this;

        intent = getIntent();
        String str = intent.getExtras().getString("type");

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

        iv_qq_login = (ImageView) findViewById(R.id.iv_qq_login);

        if (str.equalsIgnoreCase("注册") || str.equalsIgnoreCase("register")) {

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

        iv_qq_login.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linear_close:
                    linear_register.setBackgroundResource(R.drawable.activity_register_default);
                    linear_close.setBackgroundResource(R.drawable.activity_register_default);
                    linear_login.setBackgroundResource(R.drawable.activity_register_default);

                    Bundle bundle = new Bundle();
                    bundle.putString("nickName",nickName);
                    bundle.putString("playerIcon", playerIcon);
                    intent.putExtras(bundle);
                    RegisterActivity.this.setResult(2, intent);
                    //RegisterActivity.this.finish();
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
                case R.id.iv_qq_login:
                    onClickLogin();
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
        int id = item.getItemId();
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


    public void onClickLogin() {

        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", uiListener);
        } else {

        }
    }

    public void onClickLogout(){
        mTencent.logout(this);
    }


    BaseUiListener uiListener = new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values) {
            super.doComplete(values);
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {

            if (response == null) {
                ToastUtils.toast(RegisterActivity.this, "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                ToastUtils.toast(RegisterActivity.this, "登录失败");
                return;
            }
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {
            Intent intent = new Intent("LOGIN_DONE");
            LocalBroadcastManager.getInstance(mContext.getApplicationContext()).sendBroadcast(intent);
            finish();
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtils.toast(RegisterActivity.this, "登录失败");
        }

        @Override
        public void onCancel() {

        }
    }

}
