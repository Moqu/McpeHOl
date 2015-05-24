package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mclauncher.peonlinebox.mcmultiplayer.MobileActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.RegisterActivity;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView iv_personal;
    LinearLayout linear_login, linear_name;
    Button btn_login, btn_register;
    TextView tv_nick;
    RelativeLayout relative_myServer, relative_feedback, relative_version_update, relative_about, relative_logout;

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

        linear_login = (LinearLayout) getView().findViewById(R.id.linear_login);
        linear_name = (LinearLayout) getView().findViewById(R.id.linear_name);

        btn_login = (Button) getView().findViewById(R.id.btn_login);
        btn_register = (Button) getView().findViewById(R.id.btn_register);

        btn_login.setOnClickListener(onClickListener);
        btn_register.setOnClickListener(onClickListener);

        relative_myServer = (RelativeLayout) getView().findViewById(R.id.relative_myServer);
        relative_feedback = (RelativeLayout) getView().findViewById(R.id.relative_feedback);
        relative_version_update = (RelativeLayout) getView().findViewById(R.id.relative_version_update);
        relative_about = (RelativeLayout) getView().findViewById(R.id.relative_about);
        relative_logout = (RelativeLayout) getView().findViewById(R.id.relative_logout);

        relative_myServer.setOnClickListener(onClickListener);
        relative_feedback.setOnClickListener(onClickListener);
        relative_version_update.setOnClickListener(onClickListener);
        relative_about.setOnClickListener(onClickListener);
        relative_logout.setOnClickListener(onClickListener);

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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_register:
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(registerIntent);
                    break;
                case R.id.relative_myServer:
                    Toast.makeText(getActivity(), "My服务器", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_feedback:
                    Toast.makeText(getActivity(), "反馈建议", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_version_update:
                    Toast.makeText(getActivity(), "版本更新", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_about:
                    Toast.makeText(getActivity(), "关于我们", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_logout:
                    Toast.makeText(getActivity(), "注销登录", Toast.LENGTH_SHORT).show();
                    Intent mobileIntent = new Intent(getActivity(), MobileActivity.class);
                    startActivity(mobileIntent);
                    break;
            }
        }
    };
}
