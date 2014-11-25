package th.in.santi.parse;

/**
 * Created by Santi.in.th on 11/25/2014 AD.
 */
public class Parse {
    public static boolean DEBUG = true;
   
    protected static String APP_ID;
    protected static String API_KEY;
    
    public static void initialize(String APP_ID, String API_KEY){
        Parse.APP_ID = APP_ID;
        Parse.API_KEY = API_KEY;
    }

    public static String getAPP_ID(){
        return Parse.APP_ID;
    }
    public static String getAPI_KEY(){
        return Parse.API_KEY;
    }
}
