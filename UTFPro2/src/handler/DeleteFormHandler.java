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

public class DeleteFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res){
	
		request.setAttribute("num", request.getParameter("num"));
		
		return "/view/board/deleteForm.jsp";
		}
	}
