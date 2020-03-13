package handler;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDaoMysql;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res){
		
	HttpSession session = request.getSession();

		int pageSize= 6;
		int currentPage = 0;

		//"null"
		try{
		currentPage = Integer.parseInt(request.getParameter("pageNum"));
		session.setAttribute("pageNum", currentPage);	

		} catch(Exception e){
			
		if(session.getAttribute("pageNum")==null){
			session.setAttribute("pageNum",1);
		}
		}

		currentPage = (int)session.getAttribute("pageNum");
		
		String boardid = (String) session.getAttribute("boardid");
		
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = startRow + pageSize - 1;
		// int endRow =currentPage*pageSize;

		
		BoardDaoMysql service = BoardDaoMysql.getInstance();
		
		int count = service.getArticleCount(boardid);
		List li = service.getArticles(startRow, endRow, boardid);
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		
		int number = count-(currentPage-1)*pageSize; //게시글 번호
		
		
		int bottomLine = 3;
		int pageCount = count/pageSize +(count % pageSize == 0 ? 0 : 1);
		int startPage = 1+(currentPage -1)/bottomLine * bottomLine;
		int endPage = startPage + bottomLine -1;

		if(endPage>pageCount) endPage=pageCount;
		
	request.setAttribute("currentPage", currentPage);
	request.setAttribute("startRow", startRow);
	request.setAttribute("endRow", endRow);
	request.setAttribute("count", count);
	request.setAttribute("pageSize", pageSize);
	request.setAttribute("number", number);
	request.setAttribute("bottomLine", bottomLine);
	request.setAttribute("li", li);
	request.setAttribute("startPage", startPage);
	request.setAttribute("endPage", endPage);
	request.setAttribute("pageCount", pageCount);
	
		
		return "/view/board/list.jsp";
		
	}
}
