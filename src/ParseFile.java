import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by rafsan.jani on 9/3/15.
 */
public class ParseFile {
    String getUserID(String token){
        String userID="";
        int length=token.length(),i=0;
        while(i<length&&token.charAt(i)!=':'){
            i++;
        }
        i++;
        while(i<length){
            userID=userID+token.charAt(i);
            i++;
        }
        return userID;
    }
    String getMethod(String token){
        String str="G",method=null;
        if(token.length()>0 && token.charAt(0)=='G')method="Get";
        else method="Post";
        return method;
    }
    String getURI(String token){
        String URI="";
        int length=token.length(),i=0;
        while(i<length&&token.charAt(i)!='['){
            i++;
        }
        i++;
        while(i<length&&token.charAt(i)!=']'){
            URI=URI+token.charAt(i);
            i++;
        }
        return URI;
    }
    int getHour(String token){
        int hour=0;
        int length=token.length();
        for(int i=0;i<length;i++){
            if(token.charAt(i)==':'){
                break;
            }
            hour=hour*10+token.charAt(i)-'0';
        }
        return hour;
    }
    int parseTime(String token){
        int time=0;
        int length=token.length();
        for(int i=0;i<length;i++){
            if(token.charAt(i)>='0'&&token.charAt(i)<='9'){
                time=time*10+token.charAt(i)-'0';
            }
        }
        return time;
    }

    public ArrayList<Information>[] readFile()
    {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;
        ArrayList<Information> [] arrayList;
        int totalLine=0,cnt=0,wordCount=0,hour=0;

        arrayList=(ArrayList<Information>[]) new ArrayList[24];
        for(int i=0;i<24;i++){
            arrayList[i]=new ArrayList<Information>();
        }
        try{
            bufferedReader=new BufferedReader(new FileReader("/home/rafsan.jani/Documents/Rafsan/Work/03.09.2015/therap.log.ms-2.2013-10-21"));
            //bufferedReader=new BufferedReader(new FileReader("/home/rafsan.jani/Documents/Rafsan/Work/03.09.2015/test.txt"));
            String line=null,token=null;
            try{
                line=bufferedReader.readLine();
                while(line!=null) {
                    if (line.contains("PROFILER")) {
                        wordCount=0;
                        Information information=new Information();
//                        if(totalLine<11)
                        {
                            stringTokenizer=new StringTokenizer(line);
                            while(stringTokenizer.hasMoreTokens())
                            {
                                token=stringTokenizer.nextToken();
                                if(wordCount==1)
                                {
                                    /*
                                    * Timestamp
                                    * */
                                    information.timeStamp=token;
                                    hour=getHour(token);

                                }
                                else if(wordCount==9){
                                /*
                                * User Id
                                * */
                                    information.userId=getUserID(token);
                                }
                                else if(wordCount==14){
                                    /*
                                    * URI
                                    * */
                                    information.URI=getURI(token);
                                }
                                else if(wordCount==15){
                                    /*
                                    * Method
                                    * */
                                    information.method=getMethod(token);
                                }
                                else if(wordCount==16){
                                    /*
                                    * Time Spent
                                    * */
                                    information.time=parseTime(token);
                                }
                                wordCount++;
                            }
                            arrayList[hour].add(information);
                        }
                        totalLine++;
                    }
                    line=bufferedReader.readLine();
                }

            }
            catch(IOException e){
                System.out.println("IOException...");
            }
        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException...");
        }
    return arrayList;
    }
}
