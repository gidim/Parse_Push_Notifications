package th.in.santi.parse;

import java.util.Date;

import th.in.santi.parse.Parse;
import th.in.santi.parse.ParsePush;
import th.in.santi.parse.constants.ParseConstants;

/**
 * Created by Santi.in.th on 11/24/2014 AD.
 */
public class Example {
    public static void main(String[] args) {
        Parse.initialize("",""); //menu --> Settings >> Keys
        ParsePush p = new ParsePush();
        p.setMessage("Let me know if you got it!");
        p.setDeviceToken("b0063c43bf78038c83e57262b177dfe9b35197658b9382ab69a7143047deddba");
        p.send();
    }
}
