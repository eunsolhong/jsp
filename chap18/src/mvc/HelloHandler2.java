package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloHandler2 implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res){
		req.setAttribute("hello2", "HelloHandler2==========>안녕하세요!");
		return "/WEB-INF/view/hello2.jsp";
	}
}
