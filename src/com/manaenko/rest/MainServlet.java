package com.manaenko.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class MainServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public DiskService diskService;  
    
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
		
		diskService = new DiskService(jdbcURL, jdbcUsername, jdbcPassword);
		
		

	}
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<h2>в Main Сервлете</h2>");
		writer.println("<hr>");
		writer.println("<p><a href='rest-api_03/resources/disks/test'>Тест Ресурсов API</a></p>");
		writer.println("<p><a href='rest-api_03/resources/disks/alldisks'>Все диски XML</a></p>");
		writer.println("<p><a href='rest-api_03/resources/disks/alljson'>Все диски JSON</a></p>");
		writer.println("<p><a href='rest-api_03/resources/disks/getdisk/2'>Один диск JSON</a></p>");
		writer.println("<hr>");
		writer.println("<p><a href='rest-api_03/resources/director/getdirector/2'>Один директор JSON</a></p>");
		writer.println("<p><a href='rest-api_03/resources/director/alljson'>Все директора JSON</a></p>");
		writer.println("<p><a href='rest-api_03/resources/director/alldirectors'>Все директора XML</a></p>");
		writer.println("<p><a href='rest-api_03/resources/director/post'>Только для постовых запросов</a></p>");
		writer.println("<p><a href='rest-api_03/resources/disks/director_id/3'>Диски по директору</a></p>");
		writer.println("<hr>");
		writer.println("<p><a href='rest-api_03/resources/client/getclient/2'>Один Client JSON</a></p>");
		writer.println("<p><a href='rest-api_03/resources/client/alljson'>Все Clients JSON</a></p>");
		writer.println("<p><a href='rest-api_03/resources/client/allclients'>Все Clients XML</a></p>");
		writer.println("<p><a href='rest-api_03/resources/client/post'>Только для постовых запросов</a></p>");
		writer.println("<p><a href='rest-api_03/resources/disks/client_id/3'>Диски по Клиенту</a></p>");
		writer.println("<hr>");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<h2>Пост в действии</h2>");
		//doGet(request, response);
	}

}
