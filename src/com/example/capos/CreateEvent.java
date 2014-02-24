package com.example.capos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends Activity {
	private Button createBtn;
	private EditText nameText;
	private EditText specialsText;
	private EditText locationText;

	private static final int CHOOSE_LOCATION = 1;
	private static final int MESSAGE_REQUEST = 1;
	
	final CharSequence[] items = {"capos","Zill"};
	private DBAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		createBtn = (Button)this.findViewById(R.id.create_btn);
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createDbEntry();
				finish();
			}			
		});
		nameText = (EditText)this.findViewById(R.id.name_tv);
		specialsText = (EditText)this.findViewById(R.id.specials_tv);
		locationText = (EditText)this.findViewById(R.id.adresse_tv);
		
		adapter = new DBAdapter(this);
		DatabaseService.getInstance().setAdapter(adapter);
	}
	
	private void createDbEntry() {
		String name = nameText.getText().toString();
		String specials = specialsText.getText().toString();
		String location = locationText.getText().toString();
		adapter.insertRecord(name, specials, location);
	}
}
