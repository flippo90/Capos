package com.example.capos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper{
	public static final String KEY_ROWID = "id" ;
	public static final String KEY_NAME = "name";
	public static final String KEY_SPECIALS = "specials";
	public static final String KEY_LOCATION = "location";
	public static final String TAG = "DBAdapter";
	
	public static final String DATABASE_NAME = "BlaDB";
	public static final String DATABASE_TABLE = "events";
	public static final int DATABASE_VERSION = 2;
	
	public static final String DATABASE_CREATE = "create table if not exists"
			+ "events (id integer primary key autoincrement, "
			+ "name VARCHAR not null, specials VARCHAR not null"
			+ ", location VARCHAR not null);";

	Context context;
	//private final Context context;
	
	public DBAdapter(Context context){		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_BOOK_TABLE = "CREATE TABLE " + DATABASE_TABLE + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name VARCHAR NOT NULL, "+
                "specials VARCHAR NOT NULL, "+
                "location VARCHAR NOT NULL)";
		
		try{
			db.execSQL(CREATE_BOOK_TABLE);				
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading");
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
	}
	
	public long insertRecord(String name, String specials, String location){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_SPECIALS, specials);
		initialValues.put(KEY_LOCATION, location);
		 
		long id = db.insert(DATABASE_TABLE, null, initialValues);
		db.close();
		return id;
	}
	
	public MyEvent getRecord(long rowId){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(true, DATABASE_TABLE, 
				new String[]{KEY_ROWID, KEY_NAME,  KEY_SPECIALS,  
				KEY_LOCATION}, KEY_ROWID + "=" + rowId, null, null, 
				null, null, null);
		if (cursor != null){
			cursor.moveToFirst();
		}
		return new MyEvent(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
	}

	
	public List<MyEvent> getAllRecords(){
		List<MyEvent> events = new ArrayList<MyEvent>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,  KEY_SPECIALS,  
				KEY_LOCATION}, null, null, null, null, null);
		
		if (cursor.moveToFirst()){
			do{
				MyEvent event = new MyEvent(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
				events.add(event);
			} while (cursor.moveToNext());
		}
		
		return events;
	}
	
	public int getContactsCount(){
		String countQuery = "SELECT * FROM " + DATABASE_TABLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();		
	}
	
	public boolean updateRecord(long rowId, String name, String specials, String location){
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_SPECIALS, specials);
		args.put(KEY_LOCATION, location);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;

	}

	public void deleteContact(MyEvent event) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(DATABASE_TABLE, KEY_ROWID + " = ?",
	            new String[] { String.valueOf(event.getId()) });
	    db.close();
	}

	public void DropDatabase() {
		boolean deleted = context.deleteDatabase(DATABASE_NAME);
		if (deleted){
			
		}
	}
	
}
