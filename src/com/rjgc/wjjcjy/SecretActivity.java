package com.rjgc.wjjcjy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecretActivity extends Activity {
	private EditText etUserName;
    private EditText etUserPass;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etUserName = (EditText)this.findViewById(R.id.etName);
        etUserPass = (EditText)this.findViewById(R.id.etPass);
        Button btCreateDb = (Button)this.findViewById(R.id.btnDb);
        btCreateDb.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/account.db", null);
				String sql = "create table user(uid integer primary key autoincrement,"
						+"uname text,upwd text)";
				db.execSQL(sql);
				db.close();
			}
		});
        
        Button btInsert = (Button)this.findViewById(R.id.btnReg);
        btInsert.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/account.db", null);
				String userName = etUserName.getText().toString().trim();
				String userPass = etUserPass.getText().toString().trim();
				String sql = "insert into user(uname,upwd) values('"+userName+"','"
						+userPass+"')";
				db.execSQL(sql);
				db.close();
				Toast.makeText(SecretActivity.this, "注册成功", Toast.LENGTH_LONG).show();
			}
		});
        Button bt = (Button)this.findViewById(R.id.btnLog);
        bt.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(SecretActivity.this,FrameMainAcitivity.class);
				String userName = etUserName.getText().toString().trim();
				String userPass = etUserPass.getText().toString().trim();
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
						"data/data/com.rjgc.wjjcjy/account.db", null);
				Cursor qc = db.query("user", null, "uname=? and upwd=?", 
						new String[]{userName,userPass}, null, null, null);
	    		if(qc.getCount()>0)
	    		{
	    			Toast.makeText(SecretActivity.this,"登录成功", Toast.LENGTH_LONG).show();
	    			qc.close();
	    			db.close();
	    			startActivity(it);
	    		}
	    		else
	    		{
	    			Toast.makeText(SecretActivity.this,"用户名或密码不正确，登录失败", 
	    					Toast.LENGTH_SHORT).show();
	    			qc.close();
	    			db.close();
	    			etUserName.setText("");
	    			etUserPass.setText("");
	    		}
			}
		});
        Button btAbout = (Button)this.findViewById(R.id.btnAbout);
        btAbout.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				Toast.makeText(SecretActivity.this, "作者：王佳佳 蔡君奕", Toast.LENGTH_LONG).show();
			}
		});
    }
}