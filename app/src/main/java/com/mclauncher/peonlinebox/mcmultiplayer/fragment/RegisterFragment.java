package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.RegisterActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.util.AuthCode;
import com.mclauncher.peonlinebox.mcmultiplayer.util.NetCallBack;
import com.mclauncher.peonlinebox.mcmultiplayer.util.RequestUtils;
import com.mclauncher.peonlinebox.mcmultiplayer.util.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static TextView tv_code, tv_register_account_warn, tv_register_password_warn, tv_register_code_warn;
    public static EditText ed_register_account, ed_register_password, ed_register_code;
    private Button btn_next;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_code = (TextView) getView().findViewById(R.id.tv_code);
        tv_register_account_warn = (TextView) getView().findViewById(R.id.tv_register_account_warn);
        tv_register_password_warn = (TextView) getView().findViewById(R.id.tv_register_password_warn);
        tv_register_code_warn = (TextView) getView().findViewById(R.id.tv_register_code_warn);

        btn_next = (Button) getView().findViewById(R.id.btn_next);
        ed_register_account = (EditText) getView().findViewById(R.id.ed_register_account);
        ed_register_password = (EditText) getView().findViewById(R.id.ed_register_password);
        ed_register_code = (EditText) getView().findViewById(R.id.ed_register_code);

        AuthCode.authCodes(tv_code);
        tv_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthCode.authCodes(tv_code);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed_register_password.getText().length() < 6) {
                    tv_register_password_warn.setVisibility(View.VISIBLE);
                    tv_register_password_warn.setText("密码长度过短！");
                    return;
                }
                if (!ed_register_code.getText().toString().equalsIgnoreCase(tv_code.getText().toString())) {
                    tv_register_code_warn.setVisibility(View.VISIBLE);
                    tv_register_code_warn.setText("验证码不正确！");
                    return;
                }
                accountExists(ed_register_account.getText().toString());
            }
        });
    }

    public static boolean verifyPassword(String password) {

        return password.equals(ed_register_password.getText().toString());
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void accountExists(String account) {
        if (account.length() < 6) {
            tv_register_account_warn.setVisibility(View.VISIBLE);
            tv_register_account_warn.setText("账号名过短");
        } else {
            tv_register_account_warn.setVisibility(View.GONE);
            tv_register_password_warn.setVisibility(View.GONE);
            tv_register_code_warn.setVisibility(View.GONE);
            RequestParams params = new RequestParams();
            params.add("account", account);
            RequestUtils.clientPost("http://192.168.1.103/tools/ImgUpload.php", params, new NetCallBack() {
                @Override
                public void myOnSuccess(String str) {
                    if (str.equalsIgnoreCase("true")) {
                        RegisterActivity.linear_all.setVisibility(View.GONE);
                        NextFragment nextFragment = (NextFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.frame_next);
                        if (nextFragment == null) {
                            nextFragment = new NextFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            ft.addToBackStack(null);
                            ft.replace(R.id.frame_next, nextFragment);
                            ft.commit();
                        }
                        RegisterActivity.frame_next.setVisibility(View.VISIBLE);
                        ToastUtils.toast(getActivity(), str);
                    } else {
                        tv_register_account_warn.setVisibility(View.VISIBLE);
                        tv_register_account_warn.setText("账号已存在");
                    }
                }

                @Override
                public void myOnFailure(Throwable throwable) {
                    ToastUtils.toast(getActivity(), "网络连接失败，请重试！");
                }
            });
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
