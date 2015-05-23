package com.mclauncher.peonlinebox.mcmultiplayer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mclauncher.peonlinebox.mcmultiplayer.MobileActivity;
import com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener;
import com.mclauncher.peonlinebox.mcmultiplayer.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mclauncher.peonlinebox.mcmultiplayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener,OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CheckBox checkbox_recently_the_login,checkbox_mobile_online,checkbox_pm_online;
    private ListView list_recently_the_login,list_mobile_online,list_pm_online;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        checkbox_mobile_online.setChecked(false);
        checkbox_pm_online.setChecked(false);
        checkbox_recently_the_login.setChecked(false);
        loginFlag =true;
        mobileFlag=true;
        pmFlag=true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private boolean loginFlag = true;
    private boolean mobileFlag = true;
    private boolean pmFlag = true;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkbox_recently_the_login = (CheckBox) this.getView().findViewById(R.id.checkbox_recently_the_login);
        checkbox_mobile_online = (CheckBox) this.getView().findViewById(R.id.checkbox_mobile_online);
        checkbox_pm_online = (CheckBox) this.getView().findViewById(R.id.checkbox_pm_online);

        //private ListView list_recently_the_login,list_mobile_online,list_pm_online;
        list_recently_the_login = (ListView) this.getView().findViewById(R.id.list_recently_the_login);
        list_mobile_online = (ListView) this.getView().findViewById(R.id.list_mobile_online);
        list_pm_online = (ListView) this.getView().findViewById(R.id.list_pm_online);

        checkbox_recently_the_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_recently_the_login.setChecked(loginFlag);
                if (loginFlag == true) {
                    list_recently_the_login.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_expandable_list_item_1,new String[]{"测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4","测试1","测试2","测试3","测试4"});
                    list_recently_the_login.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(list_recently_the_login);
                } else {
                    list_recently_the_login.setVisibility(View.GONE);
                }
                loginFlag = !loginFlag;
            }
        });

        checkbox_mobile_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mobile_online.setChecked(mobileFlag);
                if (mobileFlag == true) {
                    list_mobile_online.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_expandable_list_item_1,new String[]{"测试1","测试2","测试3","测试4"});
                    list_mobile_online.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(list_mobile_online);
                } else {
                    list_mobile_online.setVisibility(View.GONE);
                }
                mobileFlag = !mobileFlag;
            }
        });

        checkbox_pm_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_pm_online.setChecked(pmFlag);
                if (pmFlag == true) {
                    list_pm_online.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_expandable_list_item_1,new String[]{"测试1","测试2","测试3","测试4"});
                    list_pm_online.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(list_pm_online);
                } else {
                    list_pm_online.setVisibility(View.GONE);
                }
                pmFlag = !pmFlag;
            }
        });

        list_mobile_online.setOnItemClickListener(this);
        list_recently_the_login.setOnItemClickListener(this);
        list_pm_online.setOnItemClickListener(this);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = (totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1)))*2;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.list_recently_the_login:
                Toast.makeText(getActivity(),position+"",Toast.LENGTH_LONG).show();
                break;
            case R.id.list_mobile_online:
//                getFragmentManager().beginTransaction().remove(this);
//                getFragmentManager().beginTransaction().addToBackStack("mobile").replace(R.id.fragment_online_games, new MobileFragment()).commit();
//                Toast.makeText(getActivity(),position+"",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MobileActivity.class);
                this.startActivity(intent);
//                HomeActivity.mViewPager.setVisibility(View.VISIBLE);
//                HomeActivity.tabs.setVisibility(View.VISIBLE);
//                HomeActivity.fragment_online_games.setVisibility(View.GONE);

                break;
            case R.id.list_pm_online:
                Toast.makeText(getActivity(),position+"",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

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

}
