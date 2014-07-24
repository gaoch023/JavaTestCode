package EJB;

import java.io.IOException;
import java.io.PrintWriter;


public class EjbServlet /*extends HttpServlet */{
//	/*
//	private EjbService service;
//	/**
//	 * Constructor of the object.
//	 */
//	/*	public EjbServlet() {
//		super();
//	}
//
//	/**
//	 * Destruction of the servlet. <br>
//	 */
//	public void destroy() {
//		super.destroy(); // Just puts "destroy" string in log
//		// Put your code here
//	}
//
//	/**
//	 * The doGet method of the servlet. <br>
//	 *
//	 * This method is called when a form has its tag value method equals to get.
//	 * 
//	 * @param request the request send by the client to the server
//	 * @param response the response send by the server to the client
//	 * @throws ServletException if an error occurred
//	 * @throws IOException if an error occurred
//	 */
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		
//		service=new EjbService(request,response);
//		
//		String action="";
//		action=request.getParameter("action");
//		
//		if(action==null || action.equals(""))
//		{
//			return;
//		}
//		//集中处理请求类型
//		if(action.toUpperCase().equals("CALCRESULT"))
//		{
//			double x=Double.parseDouble(request.getParameter("x_value"));
//			double y=Double.parseDouble(request.getParameter("y_value"));
//			String operator=request.getParameter("operator");
//			String method=request.getParameter("method");
//			
//			int type=1;
//			if(method.equals("remote"))
//			{
//				type=2;
//			}
//			
//			service.calc(x, y, operator, type);
//		}
//	}
//
//	/**
//	 * The doPost method of the servlet. <br>
//	 *
//	 * This method is called when a form has its tag value method equals to post.
//	 * 
//	 * @param request the request send by the client to the server
//	 * @param response the response send by the server to the client
//	 * @throws ServletException if an error occurred
//	 * @throws IOException if an error occurred
//	 */
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		doGet(request,response);
//	}
//
//	/**
//	 * Initialization of the servlet. <br>
//	 *
//	 * @throws ServletException if an error occurs
//	 */
//	public void init() throws ServletException {
//		// Put your code here
//	} 

}
