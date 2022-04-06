package com.rjgc.wjjcjy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DesignActivity extends Activity {
	
	private EditText et;
	ListView lv;
	ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	Map<String,Object> map = new HashMap<String,Object>();
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design);
        et = (EditText)this.findViewById(R.id.editText);
        lv= (ListView)this.findViewById(R.id.listView);
        Button btnEdit = (Button)this.findViewById(R.id.btnEdit);
        Button btnSave = (Button)this.findViewById(R.id.btnSave);
        DatePicker dp = (DatePicker)this.findViewById(R.id.datePicker1);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);  
        int year = calendar.get(Calendar.YEAR);  
        int monthOfYear = calendar.get(Calendar.MONTH);  
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
        et.setText("请设置你的日期");
        dp.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener()  
        {   
	       public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)   
	       {  
	    	   et.setText("");
	    	   et.setText("今天是"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
	       }  
        });
        
        

        btnEdit.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/msg.db", null);
				String sql = "create table message(mid integer primary key autoincrement,"
						+"info text)";
				db.execSQL(sql);
				db.close();
			}
		});
        btnSave.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/msg.db", null);
				String message = et.getText().toString().trim();
				
				String sql = "insert into message(info) values('"+message+"')";
				db.execSQL(sql);
				db.close();
				map.put("title", "5");
		    	map.put("info", message);
		    	list.add(map);
		    	
				Toast.makeText(DesignActivity.this, "保存成功", Toast.LENGTH_LONG).show();
			}
		});
    }
	

}
