package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class inputFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res){
		req.setAttribute("input", "inputForm!");
		return "/WEB-INF/view/inputForm.jsp";
	}
}
