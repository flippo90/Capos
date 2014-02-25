package com.example.capos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class CreateLocationActivity extends Activity implements OnItemClickListener  {
    private Button createLocationBtn;
    private AutoCompleteTextView autoCompView;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_location);
 
        createLocationBtn = (Button)findViewById(R.id.create_location_btn);
        createLocationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//create db entry in locations db
				finish();
			}
		});
        
        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.list_item));
        autoCompView.setOnItemClickListener(this);
    }    

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // event should not procress every click because that genereates a lot of requests...
    	// better wait 500ms and if no other key is pressed process event
    	String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
