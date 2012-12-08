package com.example.alarmmenager;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText text;
	int uniqueInteger = 1;
	AlarmHolder objAlarmHolder = AlarmHolder.getInstance();
	ArrayList<Alarm> tempAlarmHolder = new ArrayList<Alarm>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (EditText) findViewById(R.id.editText1);

		Button btn = (Button) findViewById(R.id.button1);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startAlert(uniqueInteger);
				uniqueInteger++;
			}
		});
	}

	public void startAlert(int rnd) {
		int i = Integer.parseInt(text.getText().toString());
		Bundle bundle = new Bundle();
		bundle.putInt("key", uniqueInteger);

		objAlarmHolder.registerAlarm(uniqueInteger);
		
		int alarmCount = 0;
		tempAlarmHolder.clear();
		
		for (Alarm alarm : objAlarmHolder.getAlarms()) {
			
			if(alarmCount < objAlarmHolder.getAlarms().size()-1)
			{
				Alarm objAlarm = new Alarm();
				objAlarm.setState(0);
				objAlarm.setUniqueID(alarmCount);				
				tempAlarmHolder.add(objAlarm);				
			}
			else
			{
				tempAlarmHolder.add(alarm);				
			}			
			alarmCount++;
		}
		
		objAlarmHolder.replaceList(tempAlarmHolder);
		
		
		

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ (i * 1000), getPendingIntent(bundle, uniqueInteger));
		Toast.makeText(this, "Alarm set in " + i + " seconds",
				Toast.LENGTH_LONG).show();
	}

	private PendingIntent getPendingIntent(Bundle bundle, int rc) {
		Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
		intent.putExtras(bundle);
		return PendingIntent.getBroadcast(this, rc, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
	}
}
