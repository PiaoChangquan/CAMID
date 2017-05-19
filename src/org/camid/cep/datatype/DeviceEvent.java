package org.camid.cep.datatype;

public class DeviceEvent {
	private String DeviceId;
	private String Timestamp;
	private String Value;
	private String EventType;
	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getEventType() {
		return EventType;
	}
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	@Override
	public String toString() {
		return "DeviceEvent [DeviceId=" + DeviceId + ", Timestamp=" + Timestamp + ", Value=" + Value + ", EventType="
				+ EventType + "]";
	} 
	
	
	
}
