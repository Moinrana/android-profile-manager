package profile.manager.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileManager1Activity extends Activity {
    public Button startBtn,stopBtn;
    public Intent in;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startBtn=(Button) findViewById(R.id.button1);
        stopBtn=(Button) findViewById(R.id.button2);
        in=new Intent(this,MyService.class);
    }
    public void startClicked(View v)
    {
    	startService(in);
    }
    public void stopClicked(View v)
    {
    	stopService(in);
    }
}