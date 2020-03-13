package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDaoMysql;
import dao.MybatisBoardDaoMysql;
import model.BoardDataBean;
import model.RequestMapping;
import model.RequestMapping.RequestMethod;

public class BoardController extends model.ActionAnnotation {
	// 액션메소드(API)에 의해 상속받음

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse arg1) {
		System.out.println("========initProcess");

		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (request.getParameter("boardid") != null // boardid가 들어왔을때
				&& !request.getParameter("boardid").equals("")) {
			session.setAttribute("boardid", request.getParameter("boardid")); // 들어온 값으로 세션을 정의시키고
			session.setAttribute("pageNum", 1);
		}

		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null) {
			boardid = "1";
			session.setAttribute("boardid", "1");
		}
	}

	// board/list -> board_list
	@RequestMapping(value = "list", method = RequestMethod.GET) // 끝단이 리스트이면 실행을 해라
	public String board_list(HttpServletRequest request, HttpServletResponse res) {

		HttpSession session = request.getSession();

		int pageSize = 6;
		int currentPage = 0;

		// "null"
		try {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
			session.setAttribute("pageNum", currentPage);

		} catch (Exception e) {

			if (session.getAttribute("pageNum") == null) {
				session.setAttribute("pageNum", 1);
			}
		}

		currentPage = (int) session.getAttribute("pageNum");

		String boardid = (String) session.getAttribute("boardid");

		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		// int endRow =currentPage*pageSize;

		MybatisBoardDaoMysql service = MybatisBoardDaoMysql.getInstance();

		int count = service.getArticleCount(boardid);
		List li = service.getArticles(startRow, endRow, boardid);
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

		int number = count - (currentPage - 1) * pageSize; // 게시글 번호

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;

		if (endPage > pageCount)
			endPage = pageCount;

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

	@RequestMapping(value = "content", method = RequestMethod.GET)
	public String board_content(HttpServletRequest request, HttpServletResponse res) {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		BoardDaoMysql dbPro = BoardDaoMysql.getInstance();
		
		BoardDataBean article = dbPro.getArticle(num);

		request.setAttribute("article", article);

		return "/view/board/content.jsp";
	}

	@RequestMapping(value = "deleteForm", method = RequestMethod.GET)
	public String board_deleteForm(HttpServletRequest request, HttpServletResponse res) {

		request.setAttribute("num", request.getParameter("num"));

		return "/view/board/deleteForm.jsp";
	}

	@RequestMapping(value = "deletePro", method = RequestMethod.POST)
	public String board_deletePro(HttpServletRequest request, HttpServletResponse res) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");

		BoardDaoMysql dbPro = BoardDaoMysql.getInstance();
		int check = dbPro.deleteArticle(num, passwd);

		request.setAttribute("check", check);
		return "/view/board/list.jsp";
	}

	@RequestMapping(value = "updateForm", method = RequestMethod.GET)
	public String board_updateForm(HttpServletRequest request, HttpServletResponse res) {

		// 두개 받아오기
		// pageNum 받아왔지만 넘겨주지않아서 pageNum은 실행되지않음
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDaoMysql dbPro = BoardDaoMysql.getInstance();

		BoardDataBean article = dbPro.getUpdateArticle(num);

		request.setAttribute("article", article);

		return "/view/board/updateForm.jsp";
	}

	@RequestMapping(value = "updatePro", method = RequestMethod.POST)
	public String board_updatePro(HttpServletRequest request, HttpServletResponse res) throws Exception {

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
		int check = dbPro.updateArticle(article); // 비밀번호 체크체크

		request.setAttribute("check", check);
		return "/view/board/updatePro.jsp";
	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET) // 가독성을 위함
	public String board_writeForm(HttpServletRequest request, HttpServletResponse res) {
		int num = 0, ref = 1, re_step = 0, re_level = 0;
		if (request.getParameter("num") != null) { // 글을 쓰면 num이 할당됨
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}

		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);

		return "/view/board/writeUploadForm.jsp"; 
	}

	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String board_writePro(HttpServletRequest request, HttpServletResponse res) {

		String realdFolder = "";
		String saveFolder = "uploadFile";
		String encType = "UTF-8"; // 저장되는 인코딩타입
		int maxsize = 10 * 1024 * 1024;// 10M
		ServletContext context = request.getServletContext();
		realdFolder = context.getRealPath(saveFolder);

		try {
			MultipartRequest multi = new MultipartRequest(request, realdFolder, maxsize, encType,
					new DefaultFileRenamePolicy());
			BoardDataBean article = new BoardDataBean();
			Enumeration files = multi.getFileNames(); // 첨부파일이름을 가져오는거

			if (files.hasMoreElements()) { // form문에 네임이잇을때
				String name = (String) files.nextElement();
				File file = multi.getFile(name);

				if (file != null) { // 네임은 잇지만 파일이 없으면 파일이 null이 됨
					article.setFilename(file.getName());
					article.setFilesize((int) file.length());
				} else {
					article.setFilename("");
					article.setFilesize(0);
				}
			}

			article.setNum(Integer.parseInt(multi.getParameter("num")));
			article.setRef(Integer.parseInt(multi.getParameter("ref")));
			article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
			article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
			article.setWriter(multi.getParameter("writer"));
			article.setContent(multi.getParameter("content"));
			article.setPasswd(multi.getParameter("passwd"));
			article.setSubject(multi.getParameter("subject"));
			article.setEmail(multi.getParameter("email"));
			article.setBoardid((String) request.getSession().getAttribute("boardid"));
			article.setIp(request.getRemoteAddr());

			BoardDaoMysql service = BoardDaoMysql.getInstance();
			service.insertArticle(article);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String board_index(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("hello", "안녕하세요!");
		return "/view/hello.jsp";
	}
}
