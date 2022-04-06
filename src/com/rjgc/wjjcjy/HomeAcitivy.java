package com.rjgc.wjjcjy;



import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class HomeAcitivy extends Activity {
	
	EditText editText_name;
	Button bt_add;
	Button bt_interview;
	Button bt_back;
	ImageView iv_add;
	public static final int SELECT_PIC=1;
	GridView bookShelf;
	private int[] data={
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary,
			R.drawable.diary,R.drawable.diary,R.drawable.diary,R.drawable.diary};
	String[] name={"my_diary","travel","school","friends","study","eat"};
	GridView gv;
	SlidingDrawer sd;
	Button iv;
	List<ResolveInfo> apps;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        editText_name=(EditText)this.findViewById(R.id.editText1);
        
        
        editText_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(HomeAcitivy.this,"用户名修改成功", Toast.LENGTH_LONG).show();
				editText_name.setCursorVisible(false);
				return true;
			}
		});
        
        editText_name.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(MotionEvent.ACTION_DOWN==event.getAction()){
					editText_name.setCursorVisible(true);
				}
				return false;
			}
		});
        bookShelf=(GridView)this.findViewById(R.id.myGridView1);
        ShelfAdapter adapter=new ShelfAdapter();
        bookShelf.setAdapter(adapter);
        bookShelf.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2>=data.length){
					
				}else{
					arg2=arg2+1;
					Toast.makeText(getApplicationContext(), ""+arg2, Toast.LENGTH_SHORT).show();
				}
			}
		});
        loadApps();
        gv=(GridView)this.findViewById(R.id.allApps);
        sd=(SlidingDrawer)this.findViewById(R.id.sliding);
        iv=(Button)this.findViewById(R.id.imageViewIcon);
        gv.setAdapter(new GridAdapter());
        sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				iv.setText("返回");
				iv.setBackgroundResource(R.drawable.btn_down);
			}
		});
        sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				iv.setText("本地");
			}
		});
        bt_add=(Button)this.findViewById(R.id.bt_add);
        bt_interview=(Button)this.findViewById(R.id.bt_interview);
        bt_interview.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(HomeAcitivy.this,introduceActivity.class);
				startActivityForResult(intent,SELECT_PIC);
			}
		});
        
        bt_back=(Button)this.findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
		iv_add=(ImageView)this.findViewById(R.id.iv_add);
		bt_add.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(HomeAcitivy.this,DialogActivity.class);
				startActivityForResult(intent,SELECT_PIC);
				
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==SELECT_PIC&&resultCode==RESULT_OK){
			int imgid=data.getIntExtra("image", -1);
			if(imgid!=-1){
				iv_add.setImageResource(imgid);
			}
		}
	}
	class ShelfAdapter extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return data.length+5;
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		public View getView(int position, View contentView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			contentView=LayoutInflater.from(getApplicationContext()).
					inflate(R.layout.item, null);
			TextView view=(TextView) contentView.findViewById(R.id.iv1);
			if(data.length>position){
				if(position<name.length){
				   view.setText(name[position]);
				}
				view.setBackgroundResource(data[position]);
			}else{
				view.setBackgroundResource(data[0]);
				view.setClickable(false);
				view.setVisibility(View.INVISIBLE);
			}
			return contentView;

		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("你确定退出吗？")
					.setCancelable(false)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							})
					.setNegativeButton("返回",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
	
	private void loadApps(){
		Intent intent =new Intent(Intent.ACTION_MAIN,null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		apps=getPackageManager().queryIntentActivities(intent, 0);
		
	}
	public class GridAdapter extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return apps.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return apps.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView = null;  
            if (convertView == null) {  
                imageView = new ImageView(HomeAcitivy.this);  
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
                imageView.setLayoutParams(new GridView.LayoutParams(50, 50));  
            } else {  
                imageView = (ImageView) convertView;  
            }  
  
            ResolveInfo ri = apps.get(position);  
            imageView.setImageDrawable(ri.activityInfo  
                    .loadIcon(getPackageManager()));  
  
            return imageView;  
        
		}
		
	}
	
}