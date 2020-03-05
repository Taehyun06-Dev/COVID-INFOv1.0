package covid.util;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import covid.main.COVID_DATA;

public class Jsoup_Util2 implements Callable<Integer>{
	
	private String URL = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=&brdGubun=&ncvContSeq=&contSeq=&board_id=&gubun=";
	@Override
	public Integer call() throws IOException {
		Document doc;
		try {
			doc = Jsoup.connect(URL).timeout(5000).get();
			String[] list = doc.toString().split("국외");
			String[] list2 = list[0].split("\\n");
			int count = 0;
			for(String a : list2) {
				count++;
				if(a.contains("<td class=\"w_bold\">")&&Jsoup.parse(list2[count-2]).text().equals("확진환자")){
					String[] split = Jsoup.parse(a).text().split(" ");
					new COVID_DATA().setDataWhole(Integer.parseInt(split[0].replaceAll(",", "")));		
					return Integer.parseInt(split[0].replaceAll(",", ""));
				}
			}
				
		} catch(Exception e) {
			e.printStackTrace();	
			return 0;
		}
		return 0;
	}
}
