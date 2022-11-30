package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.BoardDTO;
import dto.PageDTO;

public class BoardDaoImp implements BoardDAO{
	private SqlSessionTemplate sqlSession;
	
	public BoardDaoImp() {
		
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		
		return sqlSession.selectOne("board.count");
	}

	@Override
	public List<BoardDTO> list(PageDTO pv) {
		return sqlSession.selectList("board.list",pv);
	}

	@Override
	public void readCount(int num) {
		//조회수
		sqlSession.update("board.readCount",num);//boardMapper.xml에서 id
	}

	@Override
	public BoardDTO content(int num) {
		//제목클릭했을때 내용보기
		return sqlSession.selectOne("board.view",num);
	}

	@Override
	public void reStepCount(BoardDTO dto) {
		sqlSession.update("board.reStepCount",dto);
	}

	@Override
	public void save(BoardDTO dto) {
		sqlSession.insert("board.save",dto);
		//boardMapper에 save
		
	}

	@Override
	public BoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update",dto);
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("board.delete",num);
		
	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("board.uploadFile",num);
	}
}//end class
