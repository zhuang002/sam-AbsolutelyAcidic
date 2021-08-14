import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static ArrayList<Sensor> sensors = new ArrayList<>();
	static HashMap<Integer, Integer> valueMap = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		
		for (int i=0;i<n;i++) {
			int value = sc.nextInt();
			if (valueMap.containsKey(value)) {
				valueMap.put(value, valueMap.get(value)+1);
			} else {
				valueMap.put(value, 1);
			}
		}
		
		for (Integer value:valueMap.keySet()) {
			Sensor sensor = new Sensor();
			sensor.value=value;
			sensor.count=valueMap.get(value);
			sensors.add(sensor);
		}
		
		Collections.sort(sensors, (x,y)->{ 
			int countDifference = x.count-y.count;
			if (countDifference>0) return -1;
			else if (countDifference<0) return 1;
			else {
				return x.value-y.value;
			}
		});
		
		int maxCount1 = sensors.get(0).count;
		ArrayList<Sensor> maxSensors1 = getSensorsByCount(0, maxCount1);
		int result;
		if (maxSensors1.size()>1) {
			result=getMaxAbsoluteValueDifference(maxSensors1);
		} else {
			int maxCount2 = sensors.get(maxSensors1.size()).count;
			ArrayList<Sensor> maxSensors2 = getSensorsByCount(maxSensors1.size(), maxCount2);
			if (maxSensors2.size()>1) {
				result=getAbsoluteValueDifference(maxSensors1.get(0).value, maxSensors2);
			} else {
				result = Math.abs(maxSensors1.get(0).value - maxSensors2.get(0).value);
			}
		}
		System.out.println(result);

	}
	

	private static int getAbsoluteValueDifference(int value, ArrayList<Sensor> maxSensors) {
		// TODO Auto-generated method stub
		int diff1 = Math.abs(value - maxSensors.get(0).value);
		int diff2 = Math.abs(value - maxSensors.get(maxSensors.size()-1).value);
		return diff1>diff2?diff1:diff2;
	}


	private static int getMaxAbsoluteValueDifference(ArrayList<Sensor> sensors) {
		// TODO Auto-generated method stub
		return Math.abs(sensors.get(0).value - sensors.get(sensors.size()-1).value);
	}

	private static ArrayList<Sensor> getSensorsByCount(int start, int count) {
		// TODO Auto-generated method stub
		ArrayList<Sensor> ret = new ArrayList<>();
		for (int i=start;i<sensors.size();i++) {
			Sensor sensor = sensors.get(i);
			if (sensor.count == count) {
				ret.add(sensor);
			} else if (sensor.count<count) {
				return ret;
			}
		}
		return ret;
	}
}

class Sensor {
	int count;
	int value;
}