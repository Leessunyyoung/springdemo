package part01_mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//http://localhost:8090/myapp/list.do

@Controller
public class MemController {
	
@Autowired//bean이 자동으로 넘겨준다
	private MemDAO memDAO;
	
	public MemController() {
		
	}
	public void setMemDAO(MemDAO memDAO) {
		this.memDAO=memDAO;
	}
	
	@RequestMapping(value="list.do")
	public ModelAndView process(ModelAndView mav) {
		mav.addObject("list",memDAO.list());
		mav.setViewName("part01/list");
		return mav;
	}
	
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String insertForm() {
		return "part01/insert";
	}
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public String insertData(MemDTO dto) {
		memDAO.insertMethod(dto);
		return "redirect:/list.do";
	} 
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	public ModelAndView updateForm(int num,ModelAndView mav) {
		mav.addObject("dto",memDAO.one(num)); //MemDaoImp
		mav.setViewName("part01/update");
		return mav;
	}
	
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String updateData(MemDTO dto) {
		memDAO.updateMethod(dto);
		return "redirect:/list.do";
	} 
	
	@RequestMapping(value="/delete.do")
	public String deleteData(int num) {
		memDAO.deleteMethod(num);
		return "redirect:/list.do";
	}

	
}//end class
