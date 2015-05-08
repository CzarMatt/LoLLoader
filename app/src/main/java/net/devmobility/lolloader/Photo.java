package net.devmobility.lolloader;

public class Photo {

    public Photo(String farmId, String serverId, String id, String secret) {
        this.id = id;
        this.farmId = farmId;
        this.serverId = serverId;
        this.secret = secret;
    }

    private String serverId;
    private String id;
    private String secret;
    private String farmId;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getServerId() {
        return serverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

}
