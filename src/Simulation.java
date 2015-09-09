import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rafsan.jani on 9/3/15.
 */
public class Simulation {
    private ArrayList<Information>[] hourlyInfo;

    void userInterface() {
        System.out.println();
        System.out.println("*****************************");
        System.out.println("*\tPress:\t\t\t\t\t*");
        System.out.println("*\t1. For user Id\t\t\t*");
        System.out.println("*\t2. For hourly report.\t*");
        System.out.println("*\t3. To quit\t\t\t\t*");
        System.out.println("*****************************");
        System.out.print("Select option: ");
    }

    void processByUserId(String userId) {
        int totalTime = 0, getRequest = 0, postRequest = 0;
        for (int hour = 0; hour < 24; hour++) {
            for (int j = 0; j < hourlyInfo[hour].size(); j++) {
                if (hourlyInfo[hour].get(j).getUserId().compareTo(userId) == 0) {
                    totalTime += hourlyInfo[hour].get(j).getTime();
                    if (hourlyInfo[hour].get(j).getMethod().compareTo("Get") == 0) {
                        getRequest += hourlyInfo[hour].get(j).getTime();
                    } else {
                        postRequest += hourlyInfo[hour].get(j).getTime();
                    }
                }
            }
        }
        if (totalTime == 0) {
            System.out.println("This user never used  the system.");
        } else {
            System.out.println("User " + userId + " used the server total " + totalTime + "ms");
            System.out.println("Get request: " + getRequest + "ms");
            System.out.println("Post request: " + postRequest + "ms");
        }
    }

    void generateHourlyReport() {
        for (int hour = 0; hour < 24; hour++) {
            if (hourlyInfo[hour].size() > 0) System.out.println("Hour: " + (hour + 1));
            for (int j = 0; j < hourlyInfo[hour].size(); j++) {
                hourlyInfo[hour].get(j).print();
            }
        }
    }

    public void run() {
        Scanner scanner;
        ParseFile parseFile = new ParseFile();
        int option;
        String userId;
        hourlyInfo = parseFile.readFile();
        scanner = new Scanner(System.in);
        do {
            userInterface();
            option = scanner.nextInt();
            if (option == 1) {
                System.out.print("Enter user Id: ");
                userId = scanner.next();
                processByUserId(userId);
            } else if (option == 2) {
                generateHourlyReport();
            } else if (option == 3) {
                /**/
            } else {
                System.out.println("Invalid option. please try again...");
            }
        } while (option != 3);
    }
}
