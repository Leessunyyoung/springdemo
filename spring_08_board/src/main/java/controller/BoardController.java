package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.PageDTO;
import service.BoardService;
	//	http://localhost:8090/myapp/list.sb
	//http://localhost:8090/myapp/list.sb?currentPage=1
	
@Controller
public class BoardController {
	
	private BoardService service;
	private PageDTO pdto;
	private int currentPage;
	
	public BoardController() {
		
	}
	public void setService(BoardService service) {
		this.service = service;
	}
	
	@RequestMapping("/list.sb")
	public ModelAndView listMethod(PageDTO pv,ModelAndView mav) {
		int totalRecord = service.countProcess();
		//현재 페이지에 저장되있는 전체 레코드
		if(totalRecord >=1) {
			if(pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();
			
			
			this.pdto = new PageDTO(this.currentPage,totalRecord);//현재페이지와 현재테이블에 저장된 레코드수를 넘겨준다.
			List<BoardDTO> aList  = service.listProcess(this.pdto);
			mav.addObject("aList",aList);//"aList"는 list.jsp에 items="${aList}"를 받아오는것
			mav.addObject("pv",this.pdto);
		}
		mav.setViewName("board/list"); //BoardDaoImp
		return mav;
	}//end listMethod()
		
	
	@RequestMapping(value="/write.sb",method=RequestMethod.GET)
	//view페이지
		public ModelAndView writeMethod(BoardDTO dto,PageDTO pv,ModelAndView mav) {
		if(dto.getRef() != 0) {
			//답변글이면 
			mav.addObject("currentPage",pv.getCurrentPage());
			mav.addObject("dto",dto);
		}
			//제목글이면 mav.setViewName("board/write");만 실행
			mav.setViewName("board/write");
			return mav;
		}//end writeMethod()
	
	@RequestMapping(value="/update.sb",method=RequestMethod.GET)
	public ModelAndView updateMethod(int num,int currentPage,ModelAndView mav) {
		mav.addObject("dto",service.updateSelectProcess(num));
		mav.addObject("currentPage",currentPage);
		mav.setViewName("board/update");
		return mav;
	}//end updateMethod()
	
	//수정받았을때
	@RequestMapping(value="/update.sb",method=RequestMethod.POST)
	public String updateProMethod(BoardDTO dto,int currentPage,HttpServletRequest request){
		//첨부파일 받아서 처리
		MultipartFile file = dto.getFilename();
		if(!file.isEmpty()) {
			UUID random = saveCopyFile(file,request);
			dto.setUpload(random+"_"+file.getOriginalFilename());
		}
		service.updateProcess(dto, urlPath(request));
		return "redirect:/list.sb?currentPage="+currentPage;
	}//end updateProMethod
	
	@RequestMapping("/delete.sb")
	public String deleteMethod(int num,int currentPage,HttpServletRequest request) {
		service.deleteProcess(num,urlPath(request));
		
		int totalRecord = service.countProcess();
		this.pdto = new PageDTO(this.currentPage,totalRecord);
		
		return "redirect:/list.sb?currentPage=" +this.pdto.getCurrentPage();
	}//end deleteMethod()
	
	
	//글쓰기내용
	//write.jsp에서'#btnSave'를 받는다
	@RequestMapping(value="/write.sb",method=RequestMethod.POST)
	public  String writeProMethod(BoardDTO dto,PageDTO pv,HttpServletRequest request) {
		
		//첨부파일 write.jsp->name:filename값이 dto라는 멤버변수에 저장 
		//MultipartFile->oracle에선 varchar로 되어있음 string으로 변경해줘야댐
		MultipartFile file = dto.getFilename();
		if(!file.isEmpty()) {
			UUID random = saveCopyFile(file,request);
			dto.setUpload(random+"_"+file.getOriginalFilename());
		}
		
		//Client의 주소
		dto.setIp(request.getRemoteAddr());
		
		service.insertProcess(dto);
		
		//현재페이지값을 가지고 list.jsp로 돌아가야된다
		//답변글이면
		if(dto.getRef() !=0) {
			return "redirect:/list.sb?currentPage=" + pv.getCurrentPage();
		}else { //제목글
		return "redirect:/list.sb";
		}
	}//end writeProMethod()
	
	private UUID saveCopyFile(MultipartFile file,HttpServletRequest request) {
		String fileName = file.getOriginalFilename();//원래 파일이름을 가져옴
		//중복파일명을 처리하기 위해 난수값을 발생시키기
		UUID random = UUID.randomUUID();
		
		File fe  = new File(urlPath(request));
		if(!fe.exists()) {
			fe.mkdir();
		}
		File ff = new File(urlPath(request),random + "_" +fileName);
		
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return random;
	}//end saveCopyFile()
	private String urlPath(HttpServletRequest request) {
		//첨부파일 저장위치
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root:" +root);
		String saveDirectory = root+ "temp" + File.separator;
		return saveDirectory;
	}//end urlPath()
	
	@RequestMapping(value="/view.sb")
	public ModelAndView viewMethod(int currentPage,int num,ModelAndView mav) {
		mav.addObject("dto",service.contentProcess(num));
		mav.addObject("currentPage",currentPage);
		//view에대한정보
		mav.setViewName("board/view");
		return mav;
	}//end viewMethod()
	
	@RequestMapping("/contentdownload.sb")
	public ModelAndView downMethod(int num,ModelAndView mav) {
		mav.addObject("num",num);
		mav.setViewName("download"); //이름으로 되어있는 bean을 선언해라 (dispatch에있는)
		return mav;
	}//end downMethod()
	
}//end class









