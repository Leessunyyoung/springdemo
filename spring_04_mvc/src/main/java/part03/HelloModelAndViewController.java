package part03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloModelAndViewController {
	//http://localhost:8090/myapp/helloModelAndView.htm
	@RequestMapping("/helloModelAndView.htm")
	
	public ModelAndView search(ModelAndView mav) {
		mav.addObject("name","kim");
		mav.addObject("age",30);
		mav.setViewName("part03/helloModelView");
		return mav;
		
	}
}
