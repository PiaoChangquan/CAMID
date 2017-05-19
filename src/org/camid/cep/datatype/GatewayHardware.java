package org.camid.cep.datatype;

public class GatewayHardware {

	private String GatewayId;
	private String Timestamp;
	private Double Value;
	private String DataType;
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
		return "GatewayHardware [GatewayId=" + GatewayId + ", Timestamp=" + Timestamp + ", Value=" + Value
				+ ", DataType=" + DataType + "]";
	}
	
	
	
}
