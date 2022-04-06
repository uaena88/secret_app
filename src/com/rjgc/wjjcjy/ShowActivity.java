package com.rjgc.wjjcjy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class ShowActivity extends Activity {
	
	Button btnLook;
	Button btmessage;
	EditText etlook;
	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
			"data/data/com.rjgc.wjjcjy/msg.db", null);
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        ListView lv = (ListView)this.findViewById(R.id.listView);
        btnLook=(Button)this.findViewById(R.id.btnlook);
        btmessage=(Button)this.findViewById(R.id.btnmessage);
        etlook=(EditText)this.findViewById(R.id.etlook);
      //生成适配器
        SimpleAdapter adapter = new SimpleAdapter(this,
        		getData(),R.layout.listitem,new String[]{"title","info"},
        		new int[]{R.id.title,R.id.info});
        
        
      //添加适配器
        lv.setAdapter(adapter);
        
        btnLook.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etlook.setText("");
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/account.db", null);
				Cursor c = db.rawQuery("select * from user",null);
				while (c.moveToNext()){  
				    /*etlook.setText(c.getString(c.getColumnIndex("uname")));*/
				    etlook.append(c.getString(c.getColumnIndex("uname"))+" ");
				}
				db.close();
			}
		});

        btmessage.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				etlook.setText("");
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/msg.db", null);
				Cursor c = db.rawQuery("select * from message",null);
				while (c.moveToNext()){  
				    etlook.append(c.getString(c.getColumnIndex("info"))+"\n");
				}
				db.close();
				
			}
		});
        
    }
	
	//生成多维动态数据
    private List<Map<String,Object>> getData(){
    	
    	
    	ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("title", "1");
    	map.put("info", "今天下一整天的雨，太糟糕了！");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("title", "2");
    	map.put("info", "今天的饭好好吃呀！！！");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("title", "3");
    	map.put("info", "啊啊啊啊啊，我的选课多没上选！");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("title", "4");
    	map.put("info", "今天是普通的一天呢！");
    	list.add(map);
    	
    	
    	
    	return list;
    }
}
