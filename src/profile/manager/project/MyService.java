package profile.manager.project;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener{
     public SensorManager sm;
     public AudioManager am;
     public Sensor LightSensor;
     public Sensor Proximity;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onCreate()
	{
		super.onCreate();
		Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
		sm=(SensorManager) getSystemService(this.SENSOR_SERVICE);
		am=(AudioManager) getSystemService(this.AUDIO_SERVICE);
		LightSensor=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		Proximity=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//return super.onStartCommand(intent, flags, startId);
		sm.registerListener(this,LightSensor,SensorManager.SENSOR_DELAY_NORMAL);
		Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
		
	}
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent arg0) {
		
		
		if(arg0.sensor.getType()==Sensor.TYPE_LIGHT)
		{
			float lux=arg0.values[0];
			if(lux==0)
			{
			  	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		    	for(int i =0 ;i<am.getStreamMaxVolume(AudioManager.STREAM_RING);i++){
		    	am.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
		    	Log.d("a",""+i);
		    	}
		    	Log.d("a",""+am.getStreamVolume(AudioManager.STREAM_RING));
			}
			else if(lux>0&&lux<=32)
			{
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		    	for(int i=am.getStreamMaxVolume(AudioManager.STREAM_RING);i<am.getStreamMaxVolume(AudioManager.STREAM_RING)/2;i++){
		    	Log.d("a",""+am.getStreamVolume(AudioManager.STREAM_RING));
		    	Toast.makeText(this, ""+am.getStreamVolume(AudioManager.STREAM_RING),  Toast.LENGTH_SHORT);
		    	}
		    	Log.d("a",""+am.getStreamVolume(AudioManager.STREAM_RING));
			}
			else if(lux>32)
			{
				for(int i =0 ;i<am.getStreamMaxVolume(AudioManager.STREAM_RING);i++){
					am.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
				}
				Log.d("a",""+am.getStreamVolume(AudioManager.STREAM_RING));
			}
		}
		
		
	}

}
