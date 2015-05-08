package net.devmobility.lolloader;

public class Photo {

    // farm id, server id, id, secret
    private static final String BASE_PHOTO_URL = "https://farm%s.staticflickr.com/7784/17394172705_c18ffe913d.jpg";

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

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
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

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
