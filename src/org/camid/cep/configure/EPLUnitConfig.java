package org.camid.cep.configure;

import java.util.List;

public class EPLUnitConfig {

	public static int eplCount = 0;
	public static List<EPLinformation> EPLinformation = null;
	public static void showSensorUnitConfigValue() {
		System.out.println("Epl Count: " + eplCount);
		System.out.println("EPL List Values:");
		System.out.println(EPLinformation);
	}
	
	
}
