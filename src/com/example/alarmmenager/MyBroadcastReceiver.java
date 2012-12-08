package com.example.alarmmenager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
	AlarmHolder objAlarmHolder = AlarmHolder.getInstance();

	@Override
	public void onReceive(Context context, Intent intent) {
		int key = intent.getIntExtra("key",0);

		for (Alarm alarm : objAlarmHolder.getAlarms()) {
			if (alarm.getState() == 1 & alarm.getUniqueID() == key ) {
				Log.v("alarm", String.valueOf(String.valueOf(alarm.getUniqueID())));
				Toast.makeText(context, "your alarm id : " + String.valueOf(alarm.getUniqueID()),Toast.LENGTH_LONG).show();
				Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(2000);
			} else {
				Log.v("alarm", "canceled alarm number : " + String.valueOf(alarm.getUniqueID()));
			}

		}

	}

}