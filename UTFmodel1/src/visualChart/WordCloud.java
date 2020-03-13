package visualChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordCloud {
	public static void main(String[] args){
		String url = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=032&aid=0002987102";
		String selector = "div[class~=article_txt]";
		System.out.println(stringLine(url, selector, 0));
}

public static String stringLine(String url, String selector, int num){
	Document doc = null;
	String output = "";
	try {
		doc = Jsoup.connect(url).get();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String line = doc.data();
	System.out.println(line);
	line = line.substring(line.indexOf("cardList_wrap"));
	System.out.println(line);
	return line;
}

public static Map<String, Long> map(String url, String selector, int num){
	Document doc = null;
	String output = "";
	List<String> list = new ArrayList();
	Map<String, Long> map = null;
	int count = 0;
	try {
		doc = Jsoup.connect(url).get();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	Elements titles = doc.select(selector);
	for(Element e : titles) {
		String line = e.text();
		System.out.println(line);
		line = line.replace(",,", ",");
		line = line.replace("\"", " ");
		line = line.replace('1', ' ');
		line = line.replace('2', ' ');
		line = line.replace('3', ' ');
		line = line.replace('4', ' ');
		line = line.replace('5', ' ');
		line = line.replace('6', ' ');
		line = line.replace('7', ' ');
		line = line.replace('8', ' ');
		line = line.replace('9', ' ');
		line = line.replace('0', ' ');
		
		String[] strs = line.split(" ");
		list.addAll(Arrays.asList(strs));
	}

	map = list.stream().filter(name -> !name.equals("")).collect(
			Collectors.groupingBy(String::toString,
					Collectors.mapping(String::toString, Collectors.counting())));
					Map<String, Long> map2 = map.entrySet().stream().sorted(new Comparator<Map.Entry>() {
				
						@Override
						public int compare(Entry o1, Entry o2){
							long num1 = 0; 
							long num2 = 0;
							
							try {
								num1 = (long)(o1.getValue());
								num2 = (long)(o2.getValue());
							} catch (Exception e) {
								num1 = 0;
								num2 = 0;
							} finally {	return (int)(num2 - num1);
							} 		} 	}) 
							//.limit(num)
	.collect(Collectors.toMap(s->s.getKey(), s-> s.getValue()));
	return map2;}
}