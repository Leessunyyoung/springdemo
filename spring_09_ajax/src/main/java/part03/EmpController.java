package part03;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//http://localhost:8090//myapp/empsearch.do
@Controller
public class EmpController {
	
	private EmployeesDAO dao;
	
	public EmpController() {
		// TODO Auto-generated constructor stub
	}
	public void setDao(EmployeesDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="/empsearch.do",method=RequestMethod.GET)
	public String execute() {
		return "part03/empList";//views에있는 part03/empList
	}//end execute()
	
	@ResponseBody
	@RequestMapping(value="/empsearch.do",method=RequestMethod.POST)
	public List<EmployeesDTO> process(String data){
		System.out.println(data);
		System.out.println(dao.search(data));
		return dao.search(data);
	}//end process()
}


/*[DTO,DTO] : List =>DTO ->배열로 넘겨야된다
 * @ResponseBody
 * [{employee_id:100, first_name:'Stevent',salary:24000,
 * 	 {employee_id:128, first_name:'Stevent',salary:2200}]
 * dto->object->array(즉  list는 배열로 저장해서 넘겨야된다)
 */
