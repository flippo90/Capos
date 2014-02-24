package com.example.capos;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button barBtn;
	private static final int MESSAGE_REQUEST = 1;
	private static final int CHOOSE_LOCATION = 1;
	private Button addLocationBtn;
	private Button killDBBtn;
	
	final CharSequence[] items = {"party","chillen"};
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barBtn = (Button) this.findViewById(R.id.bar_btn);
        barBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, MapsActivity.class);
				startActivityForResult(intent, MESSAGE_REQUEST);
			}
		});
        
        addLocationBtn = (Button) this.findViewById(R.id.add_location_btn);
        addLocationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CreateEvent.class);
				startActivityForResult(intent, MESSAGE_REQUEST);		
			}
		});
        
        killDBBtn = (Button) findViewById(R.id.killdb_btn);
        killDBBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DatabaseService.getInstance().getAdapter().DropDatabase();
			}
		});
        
        String destPath = "/data/data" + getPackageName() + "/databases/CaposDB";
        File f = new File(destPath);
        if (!f.exists()){
        	//CopyDB(getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath));
        }
        
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String message = null;
		if (requestCode == MESSAGE_REQUEST){
			
			if (data != null){
				message = (String)data.getExtras().get("edu.gvsu.cis.uiintro.message");
			}
			else
				message = "nothing got back";
		}
		
		//Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
