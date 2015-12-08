package th.in.santi.parse.constants;
/**
 * Created by Santi.in.th on 11/25/2014 AD.
 */
public enum DeviceType {
	Android("android"), iOS("ios"), WindowsPhone("winphone");
	private String deviceType;

	DeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return deviceType;
	}
}
