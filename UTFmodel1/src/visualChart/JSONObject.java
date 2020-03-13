package visualChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//제이손방식을 뷰단에 쉽게 붙이기위해 코딩하는거
public class JSONObject {
private List<HashMap<String, String>> list =
new ArrayList<HashMap<String,String>>();

private String name = "Object";
private String[] keyName;

public void putKey(String... json){
	keyName = new String[json.length];
	for(int i=0; i<json.length; i++){
		keyName[i] = json[i];
	}
}

public void putValue(Object[] json){
	try{
		HashMap map = new HashMap();
		for(int i=0; i<json.length; i++){
			map.put(keyName[i], json[i]);
		} list.add(map);
	}catch(Exception e){
		e.printStackTrace();
	}
}

public void setName(String name){
	this.name = name;
}

public String getName(){
	return name;
}

@Override
public String toString(){
	String line = "{\"" + getName() + "\":[{\n";
	for(HashMap map : list){
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Object> me = it.next();
			try {
				Integer.parseInt((String) me.getValue());
				line += "\t\"" + me.getKey() + "\":\"" + me.getValue() + "\",\n";
			} catch (Exception e) {
				line += "\t\"" + me.getKey() + "\":" + me.getValue() + ",\n";
			}
		}
		line = line.substring(0, line.length() -2);
		line += "\n},\n {";
	}
	line = line.substring(0, line.length() -3);
	line += "\n]};";
	return line;
}
}