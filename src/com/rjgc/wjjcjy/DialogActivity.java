package com.rjgc.wjjcjy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class DialogActivity extends Activity{
	
	ImageView[] iv = new ImageView[3];
	
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iv_dialog);
		for(int i=0;i<iv.length;i++){
			final int finalI=i+1;
			String name="imageView"+finalI;
			try{
				Class cls=R.id.class;
				int id=cls.getField(name).getInt(null);
				iv[i]=(ImageView)this.findViewById(id);
				iv[i].setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=getIntent();
						Class cls2=R.drawable.class;
						try{
							int imgid2=cls2.getField("iv_"+finalI).getInt(null);
							intent.putExtra("image", imgid2);
							setResult(RESULT_OK,intent);
							finish();
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}});
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	

}
