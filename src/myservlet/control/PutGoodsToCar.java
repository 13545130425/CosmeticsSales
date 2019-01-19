package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Login;

/**
 * ���ܣ���Ʒ��ӵ����ﳵ
 */
public class PutGoodsToCar extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
		String goods=request.getParameter("java");//��ȡ��ӹ��ﳵʱ���ص�����
		System.out.println(goods);
		Login loginBean=null;
		HttpSession session=request.getSession(true);
		try{
			loginBean=(Login) session.getAttribute("loginBean");
			boolean b=loginBean.getLogname()==null||loginBean.getLogname().length()==0;
			if(b)
				response.sendRedirect("login.jsp");
			LinkedList<String> car=loginBean.getCar();//��ȡ�û����ﳵ˫���б�
			car.add(goods);//���ﳵ�����Ʒ��������
			speakSomeMess(request,response,goods);
		}catch(Exception e){}
	}

	private void speakSomeMess(HttpServletRequest request,
			HttpServletResponse response, String goods) {
		response.setContentType("text/html;charset=utf-8");
		try{
			PrintWriter out=response.getWriter();
			out.print("<html><head>" +
					"<div align='center'>" +
					"<font color=red><h3>'��ɽ��ˮ'��ױƷ������</h3></font> "+
                    "<table cellSpacing='1' cellPadding='1' width='660' align='center' border='0'>"+
                    "<tr align='bottom'>"+  
                    "<td><a href='index.jsp'>��ҳ</a></td>"+
                    "<td><a href='lookCosmetic.jsp'>�����ױƷ</a></td>"+
                    "<td><a href='searchCosmetic.jsp'>��ѯ��ױƷ</a></td>"+
                    "<td><a href='lookShoppingCar.jsp'>�鿴���ﳵ</a></td>"+
                    "<td><a href='lookOrderForm.jsp'>�鿴����</a></td>"+
                    "<td><a href='login.jsp'>��¼</a></td>"+
                    "<td><a href='inputRegisterMess.jsp'>ע��</a></td>"+
                    "<td><a href='exitServlet'>�˳�</a></td>"+
                    "</tr></table></div><br><hr><br></head>");
			out.println("<body background='image/back.jpg' style='color:white'><center>");
			out.println("<h2>��Ʒ�ѷ��빺�ﳵ</h2>");
			out.println("�鿴���ﳵ�򷵻������ױƷ<br><br>");
			out.println("<a href='lookShoppingCar.jsp'>�鿴���ﳵ</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='byPageShow.jsp'>�����ױƷ</a>");
			out.println("</center></body></html>");
		}catch(Exception e){}
	}
}
