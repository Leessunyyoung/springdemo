package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Menu2Controller {
	
	@RequestMapping("/menu2.do")
	
	public String execute() {
		return "menu2"; //tiles.xml name
	}//end execute

}//end class
