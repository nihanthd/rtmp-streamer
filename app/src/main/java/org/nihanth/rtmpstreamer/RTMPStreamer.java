package org.nihanth.rtmpstreamer;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class RTMPStreamer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtmpstreamer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] cameraIds = null;

        try {
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraIds = cameraManager.getCameraIdList();

        }catch(Exception ex){
            Snackbar.make(findViewById(R.id.parent), "Switching Camera raised an error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


        FloatingActionButton swb = (FloatingActionButton) findViewById(R.id.switch_camera);
        swb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Switching Camera", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView shutter_button_start = (ImageView) findViewById(R.id.shutter_button_start);
        shutter_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rtmpstreamer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
