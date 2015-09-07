import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rafsan.jani on 9/3/15.
 */
public class Simulation {
    Scanner scanner;
    ArrayList<Information>[]hourlyInfo;
    void userInterface(){
        System.out.println();
        System.out.println("*****************************");
        System.out.println("*\tPress:\t\t\t\t\t*");
        System.out.println("*\t1. For user Id\t\t\t*");
        System.out.println("*\t2. For hourly report.\t*");
        System.out.println("*\t3. To quit\t\t\t\t*");
        System.out.println("*****************************");
        System.out.print("Select option: ");
    }
    void processUser(String userId){
        int totalTime=0,getRequest=0,postRequest=0;
        for(int hour=0;hour<24;hour++){
            for(int j=0;j<hourlyInfo[hour].size();j++)
            {
                if(hourlyInfo[hour].get(j).userId.compareTo(userId)==0){
                    totalTime+=hourlyInfo[hour].get(j).time;
                    if(hourlyInfo[hour].get(j).method.compareTo("Get")==0){
                        getRequest+=hourlyInfo[hour].get(j).time;
                    }
                    else{
                        postRequest+=hourlyInfo[hour].get(j).time;
                    }
                }
            }
        }
        if(totalTime==0){
            System.out.println("This user never used  the system.");
        }
        else {
            System.out.println("User "+userId+" used the server total "+totalTime+"ms");
            System.out.println("Get request: "+getRequest+"ms");
            System.out.println("Post request: "+postRequest+"ms");
        }
    }
    void hourlyReport(){
        for(int hour=0;hour<24;hour++)
        {
            if(hourlyInfo[hour].size()>0)System.out.println("Hour: "+(hour+1));
            for(int j=0;j<hourlyInfo[hour].size();j++){
                hourlyInfo[hour].get(j).print();
            }
        }
    }
    public void run(){
        ParseFile parseFile=new ParseFile();
        hourlyInfo=parseFile.readFile();
        scanner=new Scanner(System.in);
        int option;
        String userId;
        do {
            userInterface();
            option=scanner.nextInt();
                if(option==1){
                    System.out.print("Enter user Id: ");
                    userId=scanner.next();
                    processUser(userId);
                }
                else if(option==2) {
                 hourlyReport();
                }
                else if(option==3){
                /**/
                }
                else {
                System.out.println("Invalid option. please try again...");
                }
        }while(option!=3);
    }
}
