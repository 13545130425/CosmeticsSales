package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Login;

/**
 * ���ܣ���¼����
 */
public class HandleLogin extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){}
	}
	
	/**
	 * �������봦��
	 */
	public String handleString(String s){
		try{
			byte[] bb=s.getBytes("iso-8859-1");
			s=new String(bb,"UTF-8");
		}catch(Exception e){}
		return s;
	}
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        Connection con;
        Statement sql;
        String logname=request.getParameter("logname").trim();
        String password=request.getParameter("password").trim();
		logname=handleString(logname);
		password=handleString(password);
		String uri="jdbc:mysql://127.0.0.1/shop?"+"user=root&password=dpl1215&characterEncoding=utf-8";
		boolean boo=(logname.length()>0)&&(password.length()>0);
		try{
			con=DriverManager.getConnection(uri);
			String condition="select * from user where logname='"+logname+"'and password='"+password+"'";
			sql=con.createStatement();
			if(boo){
		       ResultSet rs=sql.executeQuery(condition);
		       boolean m=rs.next();
		       if(m){
		    	   //���õ�½�ɹ��ķ���
		    	   success(request,response,logname,password);
		    	   RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		    	   dispatcher.forward(request, response);
		       }else{
		    	   String backNews="��������˺Ų����ڣ����������";
		    	   fail(request,response,logname,backNews);
		       }
			}else{
				String backNews="�������˺ź�����";
				fail(request,response,logname,backNews);
			}
			con.close();
		}catch(Exception e){
			String backNews=""+e;
			fail(request,response,logname,backNews);
		}
	}

	private void fail(HttpServletRequest request, HttpServletResponse response,
			String logname, String backNews) {
		response.setContentType("text/html;charset=utf-8");
		try{
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<h2>"+logname+"��½�������<br>"+backNews+"</h2>");
			out.println("���ص�¼�������ҳ<br>");
			out.println("<a href=login.jsp>��¼����</a>");
			out.println("<br><a href=index.jsp>��ҳ</a>");
			out.println("</body></html>");
		}catch(Exception e){}
	}

	private void success(HttpServletRequest request,
			HttpServletResponse response, String logname, String password) {
		Login loginBean=null;
		HttpSession session=request.getSession();
		try{
			loginBean=(Login) session.getAttribute("loginBean");
			if(loginBean==null){
				loginBean=new Login();
				session.setAttribute("loginBean", loginBean);
				loginBean=(Login) session.getAttribute("loginBean");
			}
			String name=loginBean.getLogname();
			if(name.equals(logname)){
				loginBean.setBackNews(logname+"�Ѿ���½��");
				loginBean.setLogname(logname);
			}else{
				loginBean.setBackNews(logname+"��½�ɹ�");
				loginBean.setLogname(logname);
			}
		}catch(Exception e){
			loginBean=new Login();
			session.setAttribute("loginBean", loginBean);
			loginBean.setBackNews(logname+"��½�ɹ�");
			loginBean.setLogname(logname);
		}
	}
}
