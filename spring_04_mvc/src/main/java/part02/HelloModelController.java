package part02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//http://localhost:8090/myapp/part02/helloModel.htm
@Controller
public class HelloModelController {
	
	@RequestMapping("/part02/helloModel.htm")
	public Model search(Model model) {
		model.addAttribute("id","korea");
		return model;
	}

}
/*		/WEB-INF/view/part02/helloModel.jsp*/
