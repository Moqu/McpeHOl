package com.mclauncher.peonlinebox.mcmultiplayer.entity;

public class PlayerInfo {

    private long id;
    private String playerAccount;
    private String playerNick;
    private String playerIconUrl;

    public PlayerInfo() {

    }

    public PlayerInfo(long id, String playerAccount, String playerNick, String playerIconUrl) {
        this.id = id;
        this.playerAccount = playerAccount;
        this.playerNick = playerNick;
        this.playerIconUrl = playerIconUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(String playerAccount) {
        this.playerAccount = playerAccount;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public String getPlayerIconUrl() {
        return playerIconUrl;
    }

    public void setPlayerIconUrl(String playerIconUrl) {
        this.playerIconUrl = playerIconUrl;
    }
}
