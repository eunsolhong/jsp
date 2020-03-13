package handler;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDaoMysql;
import model.BoardDataBean;

public class WriteProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res){
		
	String realdFolder = "";
	String saveFolder = "uploadFile";
	String encType = "UTF-8"; //저장되는 인코딩타입 
	int maxsize = 10*1024*1024;//10M
	ServletContext context =request.getServletContext();
	realdFolder = context.getRealPath(saveFolder);

	try{
		MultipartRequest multi
		= new MultipartRequest(request, realdFolder, maxsize, encType,
		new DefaultFileRenamePolicy());
		BoardDataBean article = new BoardDataBean();
		Enumeration files = multi.getFileNames(); //첨부파일이름을 가져오는거
		
		if(files.hasMoreElements()){ //form문에 네임이잇을때
			String name = (String)files.nextElement();
			File file = multi.getFile(name);
			
		if(file != null){ //네임은 잇지만 파일이 없으면 파일이 null이 됨
			article.setFilename(file.getName());
			article.setFilesize((int)file.length());
		}else{
			article.setFilename("");
			article.setFilesize(0);
		}
		}

		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setRef(Integer.parseInt(multi.getParameter("ref")));
		article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
		article.setWriter(multi.getParameter("writer"));
		article.setContent(multi.getParameter("content"));
		article.setPasswd(multi.getParameter("passwd"));
		article.setSubject(multi.getParameter("subject"));
		article.setEmail(multi.getParameter("email"));
		article.setBoardid((String) request.getSession().getAttribute("boardid"));
		article.setIp(request.getRemoteAddr());
		
		BoardDaoMysql service = BoardDaoMysql.getInstance();
		service.insertArticle(article);
		
		} catch(Exception e){
		e.printStackTrace();
		}
		return "redirect:/board/list";
		}
	}
