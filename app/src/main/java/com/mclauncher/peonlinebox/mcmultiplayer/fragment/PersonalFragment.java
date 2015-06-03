package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mclauncher.peonlinebox.mcmultiplayer.AboutActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.RegisterActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.util.ToastUtils;
import com.mclauncher.peonlinebox.mcmultiplayer.view.CheckUpdateDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int LOGIN_tREQUEST_CODE = 2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ImageView iv_personal;
    public static FrameLayout linear_login, linear_name;
    public static TextView tv_nick, tv_login, tv_register;
    public static RelativeLayout relative_myServer, relative_password_change, relative_feedback, relative_version_update, relative_about, relative_logout;
    public static RelativeLayout relative_login, relative_register;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public PersonalFragment() {
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
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_personal = (ImageView) getView().findViewById(R.id.iv_personal);
        tv_nick = (TextView) getView().findViewById(R.id.tv_nick);
        tv_login = (TextView) getView().findViewById(R.id.tv_login);
        tv_register = (TextView) getView().findViewById(R.id.tv_register);

        linear_login = (FrameLayout) getView().findViewById(R.id.linear_login);
        linear_name = (FrameLayout) getView().findViewById(R.id.linear_name);

        relative_login = (RelativeLayout) getView().findViewById(R.id.relative_login);
        relative_register = (RelativeLayout) getView().findViewById(R.id.relative_register);

        relative_myServer = (RelativeLayout) getView().findViewById(R.id.relative_myServer);
        relative_password_change = (RelativeLayout) getView().findViewById(R.id.relative_password_change);
        relative_feedback = (RelativeLayout) getView().findViewById(R.id.relative_feedback);
        relative_version_update = (RelativeLayout) getView().findViewById(R.id.relative_version_update);
        relative_about = (RelativeLayout) getView().findViewById(R.id.relative_about);
        relative_logout = (RelativeLayout) getView().findViewById(R.id.relative_logout);

        relative_login.setOnClickListener(onClickListener);
        relative_register.setOnClickListener(onClickListener);
        relative_myServer.setOnClickListener(onClickListener);
        relative_password_change.setOnClickListener(onClickListener);
        relative_feedback.setOnClickListener(onClickListener);
        relative_version_update.setOnClickListener(onClickListener);
        relative_about.setOnClickListener(onClickListener);
        relative_logout.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.relative_login:
                    Intent loginIntent = new Intent(getActivity(), RegisterActivity.class);
                    loginIntent.putExtra("type", tv_login.getText());
                    startActivityForResult(loginIntent, LOGIN_tREQUEST_CODE);
                    break;
                case R.id.relative_register:
                    Intent registerIntent = new Intent(getActivity(), RegisterActivity.class);
                    registerIntent.putExtra("type", tv_register.getText());
                    startActivityForResult(registerIntent, LOGIN_tREQUEST_CODE);
                    break;
                case R.id.relative_myServer:
                    Toast.makeText(getActivity(), "My服务器", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_password_change:
                    Toast.makeText(getActivity(), "密码修改", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_feedback:
                    Toast.makeText(getActivity(), "反馈建议", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_version_update:
                    if (true){
                        CheckUpdateDialog.Builder builder = new CheckUpdateDialog.Builder(getActivity());
                        builder.setMessage("检测到新版本，是否更新！");
                        builder.setTitle("版本更新");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder.setProgressBarPrice(50, true, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    } else {
                        ToastUtils.toast(getActivity(),"当前已是最新版本！");
                    }
                    break;
                case R.id.relative_about:
                    Intent aboutIntent = new Intent(getActivity(), AboutActivity.class);
                    startActivity(aboutIntent);
                    break;
                case R.id.relative_logout:
                    if (!tv_nick.getText().equals(getString(R.string.nick))) {
                        CheckUpdateDialog.Builder builder = new CheckUpdateDialog.Builder(getActivity());
                        builder.setMessage("是否退出当前登录账号？");
                        builder.setTitle("版本更新");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                        builder.create().show();
                    } else {
                        ToastUtils.toast(getActivity(),"当前未登录账号！");
                    }
                    break;
            }
        }
    };

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                while (true){
                    i++;
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private int i = 0;

    private void logout(){
        linear_login.setVisibility(View.VISIBLE);
        linear_name.setVisibility(View.GONE);
        tv_nick.setText("昵称");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 2) {
                if (data!=null) {
                    Bundle bundle = data.getExtras();
                    String nickName = bundle.getString("nickName");
                    String playerIcon = bundle.getString("playerIcon");
                    if (nickName != null && playerIcon != null) {
                        linear_login.setVisibility(View.GONE);
                        linear_name.setVisibility(View.VISIBLE);
                        tv_nick.setText(nickName);
                    }
                }else {
                    Log.e("login", "null");
                }
            }
        }
    }
}
