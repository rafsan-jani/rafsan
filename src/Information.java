/**
 * Created by rafsan.jani on 9/3/15.
 */
public class Information {
    private String timeStamp;
    private String userId;
    private String uri;
    private String method;
    private int time;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void print() {
        System.out.println("Timestamp: " + this.getTimeStamp());
        System.out.println("User ID: " + this.getUserId());
        System.out.println("URI: " + this.getUri());
        System.out.println("Method: " + this.getMethod());
        System.out.println("Time: " + this.getTime());
    }
}
