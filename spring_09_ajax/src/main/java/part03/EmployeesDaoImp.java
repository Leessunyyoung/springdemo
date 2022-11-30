package part03;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class EmployeesDaoImp implements EmployeesDAO  {
	
	private SqlSessionTemplate sqlSession;
	
	public EmployeesDaoImp() {
		
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public List<EmployeesDTO> search(String data) {
		return sqlSession.selectList("emp.search",data); //empMapper이름과 동일한지 확인
	}
}
