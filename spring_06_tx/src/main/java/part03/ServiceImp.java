package part03;

import model.MemDAO;
import model.MemDTO;

public class ServiceImp implements Service {
	
	private MemDAO memDAO;
	
	
	public ServiceImp() {
		
	}
	public void setMemDAO(MemDAO memDAO) {
		this.memDAO = memDAO;
	}
	

	@Override
	public void insertProcess() {
				memDAO.insertMethod(new MemDTO(51,"용팔이",50,"경기"));
				memDAO.insertMethod(new MemDTO(52,"유대위",20,"대전"));
	}//end insertProcess()
}//end class















