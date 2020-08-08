package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.listDBUtil;

@WebServlet("/deleteItem")
public class deleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public deleteItem() {
        super();
    }
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("index"));
    	listdb.deleteItem(id);
    	response.sendRedirect("displayItem");
	}

	

}
