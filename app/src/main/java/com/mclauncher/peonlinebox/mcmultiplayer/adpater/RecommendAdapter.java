package com.mclauncher.peonlinebox.mcmultiplayer.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mclauncher.peonlinebox.mcmultiplayer.R;
import com.mclauncher.peonlinebox.mcmultiplayer.entity.ServerInfo;
import java.util.List;


public class RecommendAdapter extends BaseAdapter {

    private Context rContext;
    private List<ServerInfo> recommendList;

    public RecommendAdapter(Context rContext,List<ServerInfo> serverlist){
        this.rContext = rContext;
        this.recommendList = serverlist;
    }

    @Override
    public int getCount() {
        return recommendList.size();
    }

    @Override
    public ServerInfo getItem(int position) {
        return recommendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(rContext).inflate(R.layout.list_item_recommend,null);
            holder.tv_recommend_id = (TextView) convertView.findViewById(R.id.tv_recommend_id);
            holder.tv_recommend_internetType = (TextView) convertView.findViewById(R.id.tv_recommend_internetType);
            holder.tv_recommend_serverName = (TextView) convertView.findViewById(R.id.tv_recommend_serverName);
            holder.tv_recommend_serverMode = (TextView) convertView.findViewById(R.id.tv_recommend_serverMode);
            holder.tv_recommend_serverDetail = (TextView) convertView.findViewById(R.id.tv_recommend_serverDetail);
            holder.tv_recommend_serverOnlinePlayer = (TextView) convertView.findViewById(R.id.tv_recommend_serverOnlinePlayer);
            holder.tv_recommend_serverMaxPlayer = (TextView) convertView.findViewById(R.id.tv_recommend_serverMaxPlayer);
            holder.tv_recommend_phoneType = (TextView) convertView.findViewById(R.id.tv_recommend_phoneType);
            holder.tv_recommend_serverPassword = (TextView) convertView.findViewById(R.id.tv_recommend_serverPassword);
            holder.tv_recommend_serverAuthor = (TextView) convertView.findViewById(R.id.tv_recommend_serverAuthor);
            holder.tv_recommend_mapsSize = (TextView) convertView.findViewById(R.id.tv_recommend_mapsSize);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ServerInfo info = recommendList.get(position);

        holder.tv_recommend_id.setText("["+info.getId()+"]");
        holder.tv_recommend_internetType.setText("["+info.getInternetType()+"]");
        holder.tv_recommend_serverName.setText(info.getServerName());
        holder.tv_recommend_serverMode.setText(info.getServerMode());
        holder.tv_recommend_serverDetail.setText(info.getServerDetail());
        holder.tv_recommend_serverOnlinePlayer.setText(info.getServerOnlinePlayer());
        holder.tv_recommend_serverMaxPlayer.setText(info.getServerMaxPlayer());
        holder.tv_recommend_phoneType.setText(info.getPhoneType());
        if(info.isServerPassword()){
            holder.tv_recommend_serverPassword.setText("已加密");
        } else {
            holder.tv_recommend_serverPassword.setText("非加密");
        }
        holder.tv_recommend_serverAuthor.setText(info.getServerAuthor());
        holder.tv_recommend_mapsSize.setText(info.getMapsSize());

        return convertView;
    }

    public class ViewHolder{
        TextView tv_recommend_id;
        TextView tv_recommend_internetType;
        TextView tv_recommend_serverName;
        TextView tv_recommend_serverMode;
        TextView tv_recommend_serverDetail;
        TextView tv_recommend_serverOnlinePlayer;
        TextView tv_recommend_serverMaxPlayer;
        TextView tv_recommend_phoneType;
        TextView tv_recommend_serverPassword;
        TextView tv_recommend_serverAuthor;
        TextView tv_recommend_mapsSize;
    }
}
