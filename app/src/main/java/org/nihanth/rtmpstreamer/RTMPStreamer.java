package org.nihanth.rtmpstreamer;

import android.hardware.camera2.CameraCharacteristics;
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
    String cameraInUse = null;
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
//                Snackbar.make(view, "Switching Camera", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                switchCamera(view);
            }
        });

        final ImageView shutter_button_start = (ImageView) findViewById(R.id.shutter_button_start);
        final ImageView shutter_button_stop = (ImageView) findViewById(R.id.shutter_button_stop);
        shutter_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shutter_button_start.setVisibility(View.INVISIBLE);
                shutter_button_stop.setVisibility(View.VISIBLE);
            }
        });

        shutter_button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shutter_button_stop.setVisibility(View.INVISIBLE);
                shutter_button_start.setVisibility(View.VISIBLE);
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

    private void switchCamera(View view){
        try{
            Log.e(getString(R.string.app_name), "Swapping camera");
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String[] cameraIds = cameraManager.getCameraIdList();
            if(this.cameraInUse == null){
                for(String cameraId : cameraIds){
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                    int lensFacing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if(lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                        this.cameraInUse = cameraId;
                    }
                }
            }else {
                for(String cameraId : cameraIds){
                    if(!this.cameraInUse.equals(cameraId)){
                        this.cameraInUse = cameraId;
                        break;
                    }
                }
            }
        }catch(Exception ex){
            Log.e(getString(R.string.app_name), "Unable to swap camera");
        }

    }
}
