package com.example.alarmmenager;

import java.util.ArrayList;

public class AlarmHolder{
	  private static AlarmHolder uniqueAlarmHolder;	  
	  private ArrayList<Alarm> lAlarms;
	  
	  private AlarmHolder() {		  
		  lAlarms = new ArrayList<Alarm>();
	  }
	  
	  public  synchronized static AlarmHolder getInstance() 	
	  {
		  if (uniqueAlarmHolder == null) {
			  uniqueAlarmHolder = new AlarmHolder();
	        }
	        return uniqueAlarmHolder;
	  }	  
	  
	  public void registerAlarm(int id) {
		  Alarm a = new Alarm();
		  a.setState(1);
		  a.setUniqueID(id);
		  
	      lAlarms.add(a);
	    }
	  
	  public void removeAlarm(int id,Alarm a) {
		  Alarm newAlarm = new Alarm();
		  a.setState(0);
		  a.setUniqueID(id);
		  
	      lAlarms.remove(id);
	      lAlarms.add(newAlarm);	      
	    }
	  
	  public void replaceList(ArrayList<Alarm> newList) {
		  lAlarms.clear();
	       lAlarms.addAll(newList);	      
	    }
	  
	  public ArrayList<Alarm> getAlarms() {
	        return lAlarms;
	    }
	  
	  public Alarm lastAlarmId() {
	        return lAlarms.get(lAlarms.size()-1);
	    }
  }  