package covid.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import covid.main.COVID_DATA;

public class Jsoup_Util implements Callable<Set<String>>{
	
	private String URL = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
	@Override
	public Set<String> call() throws IOException {
		Document doc;
		Set<String> relist = new HashSet<String>();
		COVID_DATA cd = new COVID_DATA();
		Map<String, Integer> cdm = cd.getDataMap();
		try {
			doc = Jsoup.connect(URL).timeout(5000).get();
			String[] list = doc.toString().split("\\n");
			int count = 0;
			for(String a : list) {
				count++;
				if(a.contains("<th scope=\"row\">")) {
					Integer value = Integer.parseInt(Jsoup.parse(list[count+1]).text().replaceAll(",", ""));
					if(a.contains("합계")) {
						cd.setDataTotal(value);
					}
					else if(cdm.size() !=0&&cdm.get(Jsoup.parse(a).text()) !=null&&(!(cdm.get(Jsoup.parse(a).text()).toString().equals(value.toString())))) {
						cdm.put(Jsoup.parse(a).text(), value);	
						relist.add(Jsoup.parse(a).text()+": "+(cdm.get(Jsoup.parse(a).text())-value)+"명");
					}else {
						cdm.put(Jsoup.parse(a).text(), value);	
					}
				}	
			}		
			return relist;
		} catch(Exception e) {
			e.printStackTrace();
			return relist;		
		} 
	}
}
