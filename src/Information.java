/**
 * Created by rafsan.jani on 9/3/15.
 */
public class Information {
    public String timeStamp;
    public String userId;
    public String URI;
    public String method;
    public int time;
    public void print(){
        System.out.println("Timestamp: "+this.timeStamp);
        System.out.println("User ID: "+this.userId);
        System.out.println("URI: "+this.URI);
        System.out.println("Method: "+this.method);
        System.out.println("Time: "+this.time);
    }
}
