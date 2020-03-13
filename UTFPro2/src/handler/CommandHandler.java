package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface CommandHandler {

	public String process(HttpServletRequest req, HttpServletResponse res)
	throws Exception;
	
	public default void setRequest(HttpServletRequest request) throws Exception{
		//커맨드핸들러를 상속받은 모든 클래스가 이 메소드를 실행시
	
	HttpSession session = request.getSession();
	
		
		request.setCharacterEncoding("UTF-8");


		if(request.getParameter("boardid") != null //boardid가 들어왔을때
		&& !request.getParameter("boardid").equals("")){
			session.setAttribute("boardid", request.getParameter("boardid")); //들어온 값으로 세션을 정의시키고
			session.setAttribute("pageNum", 1);
		}
			
		String boardid = (String) session.getAttribute("boardid");
		if(boardid==null){
			boardid="1";
			session.setAttribute("boardid","1");
		}
	
	}
}
