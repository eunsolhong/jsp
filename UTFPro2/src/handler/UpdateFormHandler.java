package handler;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDaoMysql;
import model.BoardDataBean;

public class UpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res){
		
	
		//두개 받아오기
	//pageNum 받아왔지만 넘겨주지않아서 pageNum은 실행되지않음
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	BoardDaoMysql dbPro = BoardDaoMysql.getInstance();

	BoardDataBean article = dbPro.getUpdateArticle(num);

	request.setAttribute("article", article);
	System.out.println(article);
	
		return "/view/board/updateForm.jsp";
	}
}
