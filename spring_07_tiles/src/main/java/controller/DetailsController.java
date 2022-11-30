package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailsController {
	
	@RequestMapping("/detail.do")
	public String execute() {
		return "details"; //views에 details.jsp
	}//end execute()

}//end class
