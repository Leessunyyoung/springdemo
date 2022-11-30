package part04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//http://localhost:8090/myapp/booksearch.do
@Controller
public class SearchDaumController {
	
	public SearchDaumController() {
		
	}	
	@RequestMapping(value="/booksearch.do",method=RequestMethod.GET)
	public String form() {
		return "part04/form";
	}
	
	@ResponseBody
	//GET요청 보내는 함수
	@RequestMapping(value="/searchOpen.do",method=RequestMethod.GET)
	//url:'searchOpen.do?query='+$('#search').val ->여기서 받은값을 넘겨준다
	public String process(String query) throws MalformedURLException, IOException {
		/*
		 * curl -v -X GET "https://dapi.kakao.com/v2/search/image" \
			data-urlencode "query=미움받을 용기" \
			H "Authorization: KakaoAK ${REST_API_KEY}"
	
		 */
		String url ="https://dapi.kakao.com/v3/search/book?target=title&query="+URLEncoder.encode(query,"UTF-8");
		//HttpURLConnection 객체생성 및 URL연결(웹페이지 URL 연결)
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		//authorization
		//Request Header값 셋팅 setRequestProperty(String key,String value)
		con.setRequestProperty("Authorization", "KakaoAK c2786eae19bae8ac9392e76dd8ce843f");
		//요청 방식 선택(GET,POST)
		con.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String input = null;
		String total = "";
		while((input = reader.readLine())!=null) {
			total += input;
		}
		System.out.println(total);
		
		return total;
		 
	}
}
