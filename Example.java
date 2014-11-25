import java.util.Date;

import th.in.santi.parse.Parse;
import th.in.santi.parse.ParsePush;
import th.in.santi.parse.constants.ParseConstants;

/**
 * Created by Santi.in.th on 11/24/2014 AD.
 */
public class Example {
    public static void main(String[] args) {
        Parse.initialize(APP_ID, API_KEY);
        ParsePush p = new ParsePush();
        p.setMessage("Hello World");
        p.setObjectId("MbXIxd5Xxr");
        p.send();
    }
}
