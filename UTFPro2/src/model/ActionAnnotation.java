package model;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public abstract class ActionAnnotation extends HttpServlet{

   public void init() throws ServletException {}
   
   public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
  
    public abstract void initProcess(HttpServletRequest request, HttpServletResponse response);
   
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      initProcess(request, response);
      String command = request.getRequestURI();
      
      System.out.println("1: "+command);
      
      if(command.indexOf("/")!=-1) {
         command=command.substring(command.lastIndexOf("/")+1);
      }
      System.out.println("2: "+command);
      
      Method[] methods = this.getClass().getMethods();
      String viewPage = null;
      String tempMethod = null;
      
      try {
         for(Method method : methods) {
            //해당되는 메소드에 걸려있는 어노테이션 클래스를 가져온다.
            RequestMapping re = method.getAnnotation(RequestMapping.class);
            tempMethod =  request.getMethod();
            tempMethod = tempMethod.toUpperCase();
            if(!tempMethod.equals("POST")) {
               tempMethod ="GET";
            }
            if(re != null &&re.value().contentEquals(command) && re.method().name().equals(tempMethod))
               viewPage = (String) method.invoke(this, request, response);
         }
         
      } catch (Throwable e) {
         throw new ServletException(e);
         
      }
      
      if(viewPage != null) {
         if(viewPage.startsWith("redirect:")) {
            viewPage=viewPage.replace("redirect:", "");
            response.sendRedirect(request.getContextPath()+viewPage);
         } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
         }
      } else {
         try {
            throw new RequestNotMatch(command+"  not requestMapping in "+getClass().getName() + "for " +tempMethod);
         } catch (RequestNotMatch e) {
            e.printStackTrace();
            throw new ServletException(e);
         }
      }
    }
    
}

class RequestNotMatch extends Exception {
   RequestNotMatch(String name) {
      super(name);
   }
}