package service;


import java.io.File;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.PageDTO;

public class BoardServiceImp implements BoardService{
	private BoardDAO dao;
	
	public BoardServiceImp() {
		
	}
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countProcess() {
		return dao.count();//BoardDaoImp에서
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		// TODO Auto-generated method stub
		return dao.list(pv);
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		//BoardDaoImp에 save받기
		//답변글이면
		if(dto.getRef()  != 0) {
			dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level() +1);
		}
		dao.save(dto);
	}

	@Override
	public BoardDTO contentProcess(int num) {
		dao.readCount(num);//조회수
		return dao.content(num);
	}

	@Override
	public void reStepProcess(BoardDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardDTO updateSelectProcess(int num) {
		return dao.content(num);//수정
	}

	@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		String filename = dto.getUpload();
		
		//수정한 파일 있으면
		if(filename !=null) {
			String path = dao.getFile(dto.getNum());
			//기존 첨부파일이 있으면 지워야댐
			if(path!=null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		}
		dao.update(dto);
	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		//첨부파일 삭제
		String path = dao.getFile(num);
		//num컬럼에 해당하는 첨부파일이 있으면
		if(path != null) {
			File fe = new File(urlpath,path);
			fe.delete();
		}
		dao.delete(num);
	}

	@Override
	public String fileSelectprocess(int num) {
		return dao.getFile(num);
	}

	
}//end class
