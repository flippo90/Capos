package com.example.capos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MyOtherActivity extends Activity {
	
	private Button doneBtn;
	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.myform);
		
		doneBtn = (Button) this.findViewById(R.id.finished);
		doneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent();
				data.putExtra("edu.gvsu.cis.uiintro.message", editText.getText().toString());
				setResult(Activity.RESULT_OK, data);
				finish();
			}
		});
		editText = (EditText) this.findViewById(R.id.name_tv);
	}
}
