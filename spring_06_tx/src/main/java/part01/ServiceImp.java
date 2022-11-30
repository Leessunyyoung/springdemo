package part01;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import model.MemDAO;
import model.MemDTO;

public class ServiceImp implements Service {
	
	private MemDAO memDAO;
	private TransactionTemplate transactionTemplate;
	
	public ServiceImp() {
		
	}
	public void setMemDAO(MemDAO memDAO) {
		this.memDAO = memDAO;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void insertProcess() {
		String result = transactionTemplate.execute(new TransactionCallback<String>() {
			
			@Override
			public String doInTransaction(TransactionStatus status) {
				//template를 썻을때 여기서 try catch사용가능
				try {
				memDAO.insertMethod(new MemDTO(44,"용팔이",50,"경기"));
				memDAO.insertMethod(new MemDTO(45,"유대위",20,"대전"));
				return "OK";
				}catch (Exception e) {
					status.setRollbackOnly();
					return e.toString();
				}
				
			}
		});
		System.out.println("result:" + result);
		
	}//end insertProcess()
}//end class















