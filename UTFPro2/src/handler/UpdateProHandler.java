package handler;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDaoMysql;
import model.BoardDataBean;

public class UpdateProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res) throws Exception{
		
		
		request.setCharacterEncoding("utf-8");
		BoardDataBean article = new BoardDataBean();
	
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setEmail(request.getParameter("email"));

		System.out.print(article);
		BoardDaoMysql dbPro = BoardDaoMysql.getInstance(); 
		int check = dbPro.updateArticle(article); //비밀번호 체크체크
		System.out.print(check); /* 콘솔에 찍어보기위함  */
		
		request.setAttribute("check", check);
		return "/view/board/updatePro.jsp";
	}
}
