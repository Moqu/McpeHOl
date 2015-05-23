package com.mclauncher.peonlinebox.mcmultiplayer.entity;



public class ServerInfo {

    private long id;
    private String serverType;
    private String serverName;
    private String internetType;
    private String serverDetail;
    private String serverAuthor;
    private String serverMode;
    private String serverOnlinePlayer;
    private String serverMaxPlayer;
    private String phoneType;
    private String mapsSize;
    private boolean serverPassword;

    public ServerInfo() {

    }

    public ServerInfo(long id, String serverType, String serverName, String internetType, String serverDetail, String serverAuthor, String serverMode,String serverOnlinePlayer, String serverMaxPlayer, String phoneType, String mapsSize, boolean serverPassword) {
        this.id = id;
        this.serverType = serverType;
        this.serverName = serverName;
        this.internetType = internetType;
        this.serverDetail = serverDetail;
        this.serverAuthor = serverAuthor;
        this.serverMode = serverMode;
        this.serverOnlinePlayer = serverOnlinePlayer;
        this.serverMaxPlayer = serverMaxPlayer;
        this.phoneType = phoneType;
        this.mapsSize = mapsSize;
        this.serverPassword = serverPassword;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getInternetType() {
        return internetType;
    }

    public void setInternetType(String internetType) {
        this.internetType = internetType;
    }

    public String getServerDetail() {
        return serverDetail;
    }

    public void setServerDetail(String serverDetail) {
        this.serverDetail = serverDetail;
    }

    public String getServerAuthor() {
        return serverAuthor;
    }

    public void setServerAuthor(String serverAuthor) {
        this.serverAuthor = serverAuthor;
    }

    public String getServerMode() {
        return serverMode;
    }

    public void setServerMode(String serverMode) {
        this.serverMode = serverMode;
    }

    public String getServerOnlinePlayer() {
        return serverOnlinePlayer;
    }

    public void setServerOnlinePlayer(String serverOnlinePlayer) {
        this.serverOnlinePlayer = serverOnlinePlayer;
    }

    public String getServerMaxPlayer() {
        return serverMaxPlayer;
    }

    public void setServerMaxPlayer(String serverMaxPlayer) {
        this.serverMaxPlayer = serverMaxPlayer;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getMapsSize() {
        return mapsSize;
    }

    public void setMapsSize(String mapsSize) {
        this.mapsSize = mapsSize;
    }

    public boolean isServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(boolean serverPassword) {
        this.serverPassword = serverPassword;
    }
}
