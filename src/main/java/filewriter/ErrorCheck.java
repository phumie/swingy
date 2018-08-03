package filewriter;

import utilities.Global;

public class ErrorCheck {
	public static boolean errorCheck(String[] items){
		int index = 0;

		while (items[index] != null){
			String[] data = items[index].split("\\s");
			if (data.length > 8 || data.length < 8 || data.length == 0)
				return false;
				index++;
		}
		// for (String line : items){
		// 	String[] data = line.split("\\s");

		// 	if (data.length > 8 || data.length < 8 || data.length == 0)
		// 		return false;
		// 	// if (!data[0].equals("Protector") || !data[0].equals("Warrior") || !data[0].equals("Master"))
		// 	// 	return false;
		// 	// if (Global.isNumber(data[2]) == false || Global.isNumber(data[3]) == false || Global.isNumber(data[4]) == false ||
		// 	// 	Global.isNumber(data[5]) == false || Global.isNumber(data[6]) == false)
		// 	// 	return false;
		// 	// if (Integer.parseInt(data[2]) <= 0 || Integer.parseInt(data[2]) > 5)
		// 	// 	return false;
		// 	// if (Integer.parseInt(data[3]) < 0)
		// 	// 	return false;
		// 	// if (Integer.parseInt(data[4]) < 0)
		// 	// 	return false;
		// 	// if (Integer.parseInt(data[5]) < 0)
		// 	// 	return false;
		// 	// if (Integer.parseInt(data[6]) < 0 || Integer.parseInt(data[6]) > 12200)
		// 	// 	return false;
		// 	// if (!data[7].equals("WEAPON") || !data[7].equals("ARMOR") || !data[7].equals("HELM"))
		// 	// 	return false;	
		// }
		return true;
	}
}