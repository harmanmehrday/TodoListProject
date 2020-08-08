package db;

import javax.sql.DataSource;

import model.todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class listDBUtil {
	private DataSource datasource;
	
	public listDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public boolean insertItem(todolist list){
		
		boolean result;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("INSERT INTO todo.listitems (item,datetime) value('%s','%s')",list.getItem(),list.getDatetime());
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);

			result = true;
		} 
		catch(Exception e){
			result = false;
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		return result;
	}
	
	public boolean deleteItem(int index){
		
		boolean result;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("DELETE FROM todo.listitems WHERE id = ?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,index);
			
			pstmt.executeUpdate();
			
			result = true;
		} 
		catch(Exception e){
			result = false;
		}
		finally {
			close(conn,stmt,pstmt,res);
		}
		return result;
	}
	
	
	public ArrayList<todolist> displayItem(){
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<todolist> returnedlist = new ArrayList<>();
		
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("Select * from todo.listitems");
			
			stmt = conn.createStatement();
			
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				
					int id = result.getInt("id");
					String item = result.getString("item");
					String datetime = result.getString("datetime");	
				
					returnedlist.add(new todolist(id,item,datetime));
			}
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn,stmt,pstmt,result);
		}
		return returnedlist;
	}
	
	private void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet res) {
		
		try {
			
			if(conn != null){				
				conn.close();
			}
			
			if(stmt != null) {				
				stmt.close();
			}
			
			if(pstmt != null) {				
				pstmt.close();
			}
			
			if(res != null) {				
				res.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
}
