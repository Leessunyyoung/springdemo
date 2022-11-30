package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.MemDTO;

/*
 * view
 * 1. java + spring +jsp
 * 2. ajax + jsp
 * 2. angular, react, vue 
 */

//https://camel.apache.org/manual/json.html
//https://www.json.org/json-en.html
// https://github.com/google/gson

//json => apache
//gson => google

@Controller
public class RestClientController {
	public RestClientController() {

	}

	// GSON
	// http://localhost:8090/myclient/list.do
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView executeSelect(ModelAndView mav) throws IOException {
		
		//허락된 db를 가져온다
		URL url = new URL("http://localhost:8090/myapp/mem/list");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// System.out.println(reader.readLine());

			List<MemDTO> aList = new ArrayList<MemDTO>();

			// google
			JsonElement element = JsonParser.parseReader(reader);
			JsonArray jsonList = element.getAsJsonArray();

			Gson gson = new Gson();
			for (int i = 0; i < jsonList.size(); i++) {
				MemDTO dto = gson.fromJson(jsonList.get(i), MemDTO.class);
				aList.add(dto);
			}
			reader.close();
			mav.addObject("aList", aList);

		}
		mav.setViewName("list");
		return mav;
	}

	// simple-json
	// http://localhost:8090/myclient/list2.do
	@RequestMapping(value = "/list2.do", method = RequestMethod.GET)
	public ModelAndView executeSelect2(ModelAndView mav) throws IOException, ParseException {

		URL url = new URL("http://localhost:8090/myapp/mem/list");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// System.out.println(reader.readLine());

			List<MemDTO> aList = new ArrayList<MemDTO>();

			// json
			JSONParser parser = new JSONParser();
			// Object result =parser.parse(reader);
			// System.out.println(result instanceof JSONArray);
//			배열로받아온다
			JSONArray result = (JSONArray) parser.parse(reader);
			reader.close();
			// System.out.println(result.get(0));
			for (int i = 0; i < result.size(); i++) {
				JSONObject object = (JSONObject) result.get(i);
				MemDTO dto = new MemDTO();
				dto.setNum(Integer.parseInt(object.get("num").toString()));
				// dto.setName(object.get("name").toString());
				dto.setName((String) object.get("name"));
				// dto.setAge(Integer.parseInt(object.get("age").toString());
				dto.setAge(Integer.parseInt((String) object.get("age")));
				// System.out.println(object.get("loc"));
				// dto.setLoc(object.get("loc").toString());
				dto.setLoc((String) object.get("loc"));
				aList.add(dto);
			}

			mav.addObject("aList", aList); //객체를 list에 담아서 보낸다

		}
		mav.setViewName("list");
		return mav;
	}

	// http://localhost:8090/myclient/listone.do
	@RequestMapping(value = "/listone.do", method = RequestMethod.GET)
	public ModelAndView executeSelectOne(ModelAndView mav) throws IOException {

		URL url = new URL("http://localhost:8090/myapp/mem/list/" + 9);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// System.out.println(reader.readLine());

			List<MemDTO> aList = new ArrayList<MemDTO>();

			// google
			JsonElement element = JsonParser.parseReader(reader);
			reader.close();

			JsonObject jsonList = element.getAsJsonObject();
			Gson gson = new Gson();
			MemDTO dto = gson.fromJson(jsonList, MemDTO.class);
			mav.addObject("dto", dto);
		}
		mav.setViewName("listone");
		return mav;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// write
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String writeForm() {
		return "write";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writePro(MemDTO dto) throws IOException {
		URL url = new URL("http://localhost:8090/myapp/mem/insert");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");  //POST, DELETE, PUT, GET
		//서버에 request Data을 json 타입으로 전달(RequestBody에 전달시)
		conn.setRequestProperty("Content-Type", "application/json");
		//Output스트림으로 데이터를 넘겨준다는 의미
		conn.setDoOutput(true);
		
		Gson gson = new Gson();
		//{"num":0,"name":"we","age":3,"loc":"3"}
		String json = gson.toJson(dto);
		System.out.println(json);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(json);
		bw.close();

		//상태값받음
		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		if (responseCode == 200)
			return "redirect:/list.do";
		else {
			System.out.println(conn.getResponseMessage());
			return "redirect:/write.do";
		}
	}
	/////////////////////////////////////////////////////////
	//수정
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute("dto")  MemDTO dto) {		
		return "update";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String updatePro(MemDTO dto) throws IOException {
		URL url = new URL("http://localhost:8090/myapp/mem/update");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");  //POST, DELETE, PUT, GET
		//서버에 request Data을 json 타입으로 전달(RequestBody에 전달시)
		conn.setRequestProperty("Content-Type", "application/json");
		//Output스트림으로 데이터를 넘겨준다는 의미
		conn.setDoOutput(true);
		
		Gson gson = new Gson();
		//{"num":0,"name":"we","age":3,"loc":"3"}
		String json = gson.toJson(dto);
		System.out.println(json);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(json);
		bw.close();

		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		if (responseCode == 200)
			return "redirect:/list.do";
		else {
			System.out.println(conn.getResponseMessage());
			return "redirect:/update.do";
		}
	}
	//////////////////////////////////////
	//삭제
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(int num) throws IOException {
		URL url = new URL("http://localhost:8090/myapp/mem/delete/"+num);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");  //POST, DELETE, PUT, GET
		//서버에 request Data을 json 타입으로 전달(RequestBody에 전달시)
		conn.setRequestProperty("Content-Type", "application/json");
		//Output스트림으로 데이터를 넘겨준다는 의미
		conn.setDoOutput(false);
		

		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		if (responseCode == 200)
			return "redirect:/list.do";
		else {
			System.out.println(conn.getResponseMessage());
			return null;
		}
	}
}













