package th.in.santi.parse;

import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.santi.parse.callback.ParsePushCallback;
import th.in.santi.parse.constants.DeviceType;
import th.in.santi.parse.constants.ParseConstants;

/**
 * Created by Santi.in.th on 11/25/2014 AD.
 */
public class ParsePush {
	JSONObject json = new JSONObject();

	public void setTitle(String title) {
		addValue("data", "title", title);
	}

	public void setMessage(String message) {
		addValue("data", "alert", message);
	}

	public void setBadge(String badge) {
		addValue("data", "badge", badge);
	}

	public void setDeviceToken(String deviceToken) {
		addValue("where", "deviceToken", deviceToken);
	}

	public void setDeviceType(DeviceType deviceType) {
		addValue("where", "deviceType", deviceType.toString());
	}

	public void setObjectId(String objectId) {
		addValue("where", "objectId", objectId);
	}

	public void setChannels(String[] channels) {
		try {
			JSONArray jsonArray = new JSONArray(Arrays.asList(channels));
			addValue("where", "channels", jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPushTime(Date date) {
		addValue("push_time", ParseConstants.DATE_FORMAT.format(date));
	}

	private void addValue(String rootTag, String valueData) {
		addValue(rootTag, null, valueData);
	}

	private void addValue(String rootTag, String valueTag, String valueData) {
		try {
			if (valueTag == null) {
				if (json.has(rootTag)) {
					json.remove(rootTag);
				}
				json.put(rootTag, valueData);
			} else {
				JSONObject rootNode;
				if (json.has(rootTag)) {
					rootNode = json.getJSONObject(rootTag);
					json.remove(rootTag);
					if (rootNode.has(valueTag)) {
						rootNode.remove(valueTag);
					}
				} else {
					rootNode = new JSONObject();
				}
				rootNode.put(valueTag, valueData);
				json.put(rootTag, rootNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send() {
		System.out.println(json.toString());
		// send(null);
	}

	public void send(final ParsePushCallback callback) {
		new Thread() {
			public void run() {
				String resp = ParseHttp.conn().sendPush(json.toString());
				if (callback != null) {
					boolean sentStatus = false;
					try {
						JSONObject json = new JSONObject(resp);
						if (json.has("result")) {
							sentStatus = json.getBoolean("result");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					callback.onParsePush(sentStatus);
				}
			};
		}.start();

	}
}
