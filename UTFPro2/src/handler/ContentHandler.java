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

public class ContentHandler implements CommandHandler {

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse res){
		
	
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null || pageNum.equals("")) { pageNum = "1";}

	BoardDaoMysql dbPro = BoardDaoMysql.getInstance();
	BoardDataBean article = dbPro.getArticle(num);
	
	
		request.setAttribute("article", article);
		
	return "/view/board/content.jsp";
		}
}