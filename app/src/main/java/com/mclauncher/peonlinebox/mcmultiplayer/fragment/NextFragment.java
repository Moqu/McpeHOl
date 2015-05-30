package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.RegisterActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.util.AuthCode;
import com.mclauncher.peonlinebox.mcmultiplayer.util.IconCrop;
import com.mclauncher.peonlinebox.mcmultiplayer.util.ToastUtils;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LinearLayout btn_back;
    private ImageView iv_player_icon;
    private EditText ed_player_nick, ed_register_password_again;
    private Button btn_register_done;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NextFragment newInstance(String param1, String param2) {
        NextFragment fragment = new NextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NextFragment() {
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
        return inflater.inflate(R.layout.fragment_next, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_back = (LinearLayout) getView().findViewById(R.id.btn_back);
        iv_player_icon = (ImageView) getView().findViewById(R.id.iv_player_icon);
        ed_player_nick = (EditText) getView().findViewById(R.id.ed_player_nick);
        ed_register_password_again = (EditText) getView().findViewById(R.id.ed_register_password_again);
        btn_register_done = (Button) getView().findViewById(R.id.btn_register_done);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                AuthCode.authCodes(RegisterFragment.tv_code);
                RegisterFragment.ed_register_code.setText("");
            }
        });

        iv_player_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "选择图片"), 1);
            }
        });

        btn_register_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordAgain = ed_register_password_again.getText().toString();
                if (ed_player_nick.getText().length() != 0) {
                    if (RegisterFragment.verifyPassword(passwordAgain)) {
                        ToastUtils.toast(getActivity(), "注册成功！");
                        getActivity().finish();
                    } else {
                        ToastUtils.toast(getActivity(), "密码不正确，请重新输入!");
                    }
                } else {
                    ToastUtils.toast(getActivity(), "请输入昵称!");
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data == null) {
                return;
            }
            Uri uri;
            uri = data.getData();
            Uri fileUri = getRealPathFromURI(uri);
            startImageZoom(fileUri);
        }else if (requestCode == 2){
            if (data == null){
                return;
            } else {
                Bundle extras = data.getExtras();
                Bitmap bm = extras.getParcelable("data");
                IconCrop.saveBitmap(bm);
                iv_player_icon.setImageBitmap(bm);
                IconCrop.uploadImage(getActivity(),bm);
            }
        }
    }

    public Uri getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(contentUri,proj,null,null,null);
        int column_index;
        if (cursor !=null) {
            try {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String imgPath = cursor.getString(column_index);
                File img = new File(imgPath);
                Log.i("uri",img.getPath());
                return Uri.fromFile(img);
            } catch (Exception e) {
                return null;
            }
        } else {
            return contentUri;
        }
    }

    private void startImageZoom(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri , "image/*");
        intent.putExtra("corp", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,2);
    }

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
    public void onDestroyView() {
        super.onDestroyView();
        RegisterActivity.linear_all.setVisibility(View.VISIBLE);
        //getFragmentManager().popBackStack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RegisterActivity.linear_all.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
