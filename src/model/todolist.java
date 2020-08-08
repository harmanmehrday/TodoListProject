package model;
import java.util.Date;

import db.listDBUtil;

public class todolist {
	int id;
	String item;
	String datetime;
	
	public todolist(String item) {
		this.item = item;
		Date dNow = new Date();
		datetime = dNow.toString();
	}
	
	public todolist(int id,String item,String datetime) {
		this.id = id;
		this.item = item;
		this.datetime = datetime;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public String getDatetime() {
		return datetime;
	}
	
	public boolean addItem(listDBUtil listdb) {
		try {
			 listdb.insertItem(this);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
