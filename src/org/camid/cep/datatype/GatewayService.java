package org.camid.cep.datatype;

public class GatewayService {

	private String GatewayId;
	private String Timestamp;
	private String Value;
	private String EventType;
	public String getGatewayId() {
		return GatewayId;
	}
	public void setGatewayId(String gatewayId) {
		GatewayId = gatewayId;
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
		return "Gatewayservice [GatewayId=" + GatewayId + ", Timestamp=" + Timestamp + ", Value=" + Value
				+ ", EventType=" + EventType + "]";
	}
	
}
