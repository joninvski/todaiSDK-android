package co.todai.client.java;

/*
 * The credentials used to authenticate to the todai server
 */
public class TodaiCredentials {

    private final String userId;
    private final String privateKey;

    public TodaiCredentials(String userId, String privateKey) {
        this.userId = projectId;
        this.privateKey = writeKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
