import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by rafsan.jani on 9/3/15.
 */
public class ParseFile {
    String parseUserId(String token) {
        String[] array = token.split("U:");
        return array[1];
    }

    String parseMethod(String token) {
        if (token.length() > 0 && token.charAt(0) == 'G') {
            return "Get";
        } else {
            return "Post";
        }
    }

    String parseUri(String token) {
        if (token.length() < 6) return "";
        return token.substring(6, token.length() - 2);
    }

    int parseHour(String token) {
        String[] array = token.split(":|,");
        return Integer.parseInt(array[0]);
    }

    int parseTime(String token) {
        if (token.length() > 6) return Integer.parseInt(token.substring(5, token.length() - 2));
        else return 0;
    }

    public ArrayList<Information>[] readFile() {
        BufferedReader bufferedReader;
        ArrayList<Information>[] hourlyInfo;
        int hour = 0;

        hourlyInfo = (ArrayList<Information>[]) new ArrayList[24];
        for (int i = 0; i < 24; i++) {
            hourlyInfo[i] = new ArrayList<Information>();
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/home/rafsan.jani/Documents/Rafsan/Work/03.09.2015/therap.log.ms-2.2013-10-21"));
            String line = null;
            try {

                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("PROFILER")) {
                        Information information = new Information();
                        String[] array = line.split(" ");
                        information.setTimeStamp(array[1]);
                        hour = parseHour(array[1]);
                        information.setUserId(parseUserId(array[9]));
                        information.setUri(parseUri(array[14]));
                        information.setMethod(parseMethod(array[15]));
                        information.setTime(parseTime(array[16]));
                        hourlyInfo[hour].add(information);
                    }
                }

            } catch (IOException e) {
                System.out.println("IOException...");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException...");
        }
        return hourlyInfo;
    }
}
