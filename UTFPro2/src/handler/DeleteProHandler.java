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

public class DeleteProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res) throws Exception{
	
	int num = Integer.parseInt(request.getParameter("num"));
	String passwd = request.getParameter("passwd");

	BoardDaoMysql dbPro = BoardDaoMysql.getInstance();
	int check = dbPro.deleteArticle(num, passwd);
	
		request.setAttribute("check", check);
		return "/view/board/updatePro.jsp";
		}
	}
