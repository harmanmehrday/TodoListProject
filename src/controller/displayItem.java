package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.listDBUtil;
import model.todolist;

@WebServlet("/displayItem")
public class displayItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public displayItem() {super();}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}


    @Resource(name="jdbc/social")
    private DataSource datasource;
    private listDBUtil listdb;

    @Override
	public void init() throws ServletException {
		super.init();
		
		try {
			listdb = new listDBUtil(datasource);
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArrayList<todolist> list = listdb.displayItem();
		request.setAttribute("list",list);
		String strViewPage="/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		dispatcher.forward(request, response); 	
    }
}
