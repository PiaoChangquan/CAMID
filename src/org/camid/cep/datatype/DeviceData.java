package org.camid.cep.datatype;

public class DeviceData {
	private String DeviceId;
	private String Timestamp;
	private Double Value;
	private String DataType;
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
	public Double getValue() {
		return Value;
	}
	public void setValue(Double value) {
		Value = value;
	}
	public String getDataType() {
		return DataType;
	}
	public void setDataType(String dataType) {
		DataType = dataType;
	}
	@Override
	public String toString() {
		return "DeviceData [DeviceId=" + DeviceId + ", Timestamp=" + Timestamp + ", Value=" + Value + ", DataType="
				+ DataType + "]";
	}
	
	
	
	
}
