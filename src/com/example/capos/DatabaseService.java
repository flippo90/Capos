package com.example.capos;

public class DatabaseService {
	private static DatabaseService instance;
	private DBAdapter adapter;
	private DatabaseService(){}
	public static DatabaseService getInstance(){
		if (instance == null){
			instance = new DatabaseService();
		}
		return instance;
	}
	public DBAdapter getAdapter() {
		return adapter;
	}
	public void setAdapter(DBAdapter adapter) {
		this.adapter = adapter;
	}
}
