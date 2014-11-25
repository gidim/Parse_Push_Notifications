package th.in.santi.parse;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Santi.in.th on 11/25/2014 AD.
 */
class ParseHttp {
	public final static String HOST = "https://api.parse.com";
	public final static String PUSH_PATH = "/1/push";
	private final static String METHOD_POST = "POST";
	private static ParseHttp parseHttp = new ParseHttp();

	public static ParseHttp conn() {
		return parseHttp;
	}

	public String sendPush(String jsonData) {
		return sendHttp(PUSH_PATH, METHOD_POST, jsonData);
	}

	private String sendHttp(String path, String method, String jsonData) {
		String resp = null;
		try {
			URL url = new URL(HOST + path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-Parse-Application-Id", Parse.APP_ID);
			conn.setRequestProperty("X-Parse-REST-API-Key", Parse.API_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			OutputStream out = conn.getOutputStream();
			out.write(jsonData.toString().getBytes("UTF-8"));
			if (Parse.DEBUG) {
				System.out.println("conn :: " + conn.getResponseMessage());
			}
			if (conn.getResponseCode() == 200) {
				InputStreamReader in = new InputStreamReader(conn.getInputStream());
				char[] c = new char[1024];
				int i;
				StringWriter string = new StringWriter();
				while ((i = in.read(c)) > 0) {
					string.write(c, 0, i);
				}
				resp = string.toString();
				in.close();
			}
			// sentStatus = ;
			conn.disconnect();
		} catch (Exception e) {
			if (Parse.DEBUG) {
				e.printStackTrace();
			}
		}
		return resp;
	}
}
