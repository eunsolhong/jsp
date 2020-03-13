package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ArticleContentNotFoundException;
import exception.ArticleNotFoundException;
import service.ArticleData;
import service.ReadArticleService;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readService = new ReadArticleService(); 
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	String noVal = req.getParameter("no");
	int articleNum = Integer.parseInt(noVal);
	
	try {
		ArticleData articleData = readService.getArticle(articleNum, true);
		req.setAttribute("articleData", articleData);
		return "/WEB-INF/view/readArticle.jsp";
	} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
		// TODO: handle exception
		req.getServletContext().log("no article", e);
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	}
}
