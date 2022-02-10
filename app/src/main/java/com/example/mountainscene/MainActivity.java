package com.example.mountainscene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // I would like the display to always be horizontal
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // creating a mountainView that represents the surface view layout
        MountainView mountainView = findViewById(R.id.surfaceView);

        // Create the elements that will have listeners added to them, each seek bar
        // will assigned to an rgb value
        TextView elementName = findViewById(R.id.textView3);
        SeekBar r = findViewById(R.id.red_seekBar);
        SeekBar b = findViewById(R.id.blue_seekBar);
        SeekBar g = findViewById(R.id.green_seekBar);

        // create a controller that takes in the surfaceView, textView, and r,g,b seekbars
        Controller mountainController = new Controller(mountainView, elementName, r, b, g);

        // set the Listeners to the views and seekbars created above
        mountainView.setOnTouchListener(mountainController);
        r.setOnSeekBarChangeListener(mountainController);
        b.setOnSeekBarChangeListener(mountainController);
        g.setOnSeekBarChangeListener(mountainController);

    }
}