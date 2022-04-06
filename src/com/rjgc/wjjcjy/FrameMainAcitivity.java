package com.rjgc.wjjcjy;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class FrameMainAcitivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final TabHost tabHost = getTabHost();
        tabHost.getTabWidget().bringToFront();
        Intent itShow = new Intent(this,ShowActivity.class);
        Intent itDesign = new Intent(this,DesignActivity.class);
        Intent itHome = new Intent(this,HomeAcitivy.class);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getResources().
        		getText(R.string.tabtitle1),getResources().
        		getDrawable(R.drawable.show1)).setContent(itShow));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getResources()
        		.getText(R.string.tabtitle2),getResources().
        		getDrawable(R.drawable.design1)).setContent(itDesign));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getResources()
        		.getText(R.string.tabtitle3),getResources().
        		getDrawable(R.drawable.person1)).setContent(itHome));
        tabHost.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
