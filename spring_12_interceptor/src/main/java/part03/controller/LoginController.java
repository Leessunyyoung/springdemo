package part03.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import part03.dto.PersonDTO;

@Controller
public class LoginController {
	public LoginController() {
	
	}
//http://localhost:8090/myapp/login.do
	@RequestMapping(value="/login.do")
	public String loginProcess() {
		return "part03/loginForm";
	}
	
//http://localhost:8090/myapp/logpro.do
	@RequestMapping("/logpro.do")
	public String loginExecution(String returnUrl,PersonDTO dto,HttpSession session) {
		if(dto.getId().equals("kim") && dto.getPass().equals("1234")) {
			session.setAttribute("chk", dto.getId());//session에 저장
			session.setMaxInactiveInterval(1000*60*30);//시간정해줌(30분)
			System.out.println(session.getAttribute("chk"));
			if(returnUrl !="") {
				return "redirect:/" + returnUrl;
			}
		}
		return "redirect:/index.do";
	}
	
	@RequestMapping("/logout.do")
	public String logoutProcess(HttpSession session) {
		session.removeAttribute("chk");
		
		return "redirect:/index.do";
	}
}//end class
