package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res){
		req.setAttribute("input", "inputForm!");
		return "/WEB-INF/view/list.jsp";
	}
}
