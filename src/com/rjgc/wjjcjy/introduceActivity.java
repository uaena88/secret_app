package com.rjgc.wjjcjy;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class introduceActivity extends Activity{
	private Button  introduce;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.interview);
		introduce = (Button)findViewById(R.id.introduce);
		introduce.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				finish();
			}
		});
		
	}

}
