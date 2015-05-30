package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.util.AuthCode;
import com.mclauncher.peonlinebox.mcmultiplayer.util.NetCallBack;
import com.mclauncher.peonlinebox.mcmultiplayer.util.RequestUtils;
import com.mclauncher.peonlinebox.mcmultiplayer.util.StringUtils;
import com.mclauncher.peonlinebox.mcmultiplayer.util.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static TextView tv_login_code, tv_login_account_warn, tv_login_password_warn, tv_login_code_warn;
    public static EditText ed_login_account, ed_login_password, ed_login_code;
    private Button btn_login_done;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_login_code = (TextView) getView().findViewById(R.id.tv_login_code);
        tv_login_account_warn = (TextView) getView().findViewById(R.id.tv_login_account_warn);
        tv_login_password_warn = (TextView) getView().findViewById(R.id.tv_login_password_warn);
        tv_login_code_warn = (TextView) getView().findViewById(R.id.tv_login_code_warn);

        ed_login_account = (EditText) getView().findViewById(R.id.ed_login_account);
        ed_login_password = (EditText) getView().findViewById(R.id.ed_login_password);
        ed_login_code = (EditText) getView().findViewById(R.id.ed_login_code);

        btn_login_done = (Button) getView().findViewById(R.id.btn_login_done);
        btn_login_done.setOnClickListener(onClickListener);

        AuthCode.authCodes(tv_login_code);

        tv_login_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthCode.authCodes(tv_login_code);
            }
        });

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv_login_code_warn.setVisibility(View.GONE);
            tv_login_password_warn.setVisibility(View.GONE);
            if (!tv_login_code.getText().toString().equalsIgnoreCase(ed_login_code.getText().toString())) {
                tv_login_code_warn.setVisibility(View.VISIBLE);
                tv_login_code_warn.setText("验证码输入错误！");
            } else {
                String str = StringUtils.stringMD5(ed_login_password.getText().toString());
                Log.i("login",str);
                passwordExists(ed_login_account.getText().toString(), str);
            }
        }
    };

    private void passwordExists(String account,String password){
        String url = "http://192.168.1.100/tools/ImgUpload.php";
        RequestParams params = new RequestParams();
        params.add("account",account);
        params.add("password",password);

        RequestUtils.clientPost(url, params, new NetCallBack() {
            @Override
            public void myOnSuccess(String str) {
                if (str.equalsIgnoreCase("true")) {
                    ToastUtils.toast(getActivity(),"登录成功！");
                    getActivity().finish();
                } else {
                    tv_login_password_warn.setVisibility(View.VISIBLE);
                    tv_login_password_warn.setText("账号或密码不正确！");
                }
            }

            @Override
            public void myOnFailure(Throwable throwable) {
                ToastUtils.toast(getActivity(),"网络连接失败，请重试！");
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
