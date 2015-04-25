package co.todai.client.android;

/*
 * The credentials used to authenticate to the todai server
 */
public class TodaiCredentials {

    private final String userId;
    private final String privateKey;

    public TodaiCredentials(String userId, String privateKey) {
        this.userId = userId;
        this.privateKey = privateKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
