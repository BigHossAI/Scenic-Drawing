package com.example.mountainscene;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Controller implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

    // create a mountain View object that this class can use
    private MountainView mountainView;

    // the text view will need to contain the element name
    private TextView elementName;

    // create three seekbars, one for each rgb value
    private SeekBar rSeek;
    private SeekBar bSeek;
    private SeekBar gSeek;

    // ctor that takes the drawing area, element name area, and the seekbars
    public Controller(MountainView mountainView, TextView elementName, SeekBar r, SeekBar b, SeekBar g){

        this.mountainView = mountainView;
        this.elementName = elementName;

        this.rSeek = r;
        this.bSeek = b;
        this.gSeek = g;

    }

    // on touch methods to be implemented
    // I want to check to make sure that an object is selected, if no element is selected,
    // there should be no changes

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // get the x,y coordinates of the touch event
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();

        // send the x, y coordinates to get the object that is clicked, make sure it isn't null
        // and then set the text view to print the name of the element, and then lastly, set
        // seek bars to the color values associated with the elements color

        CustomElement element = mountainView.getClickedObject(x,y);

        if(element != null) {
            elementName.setText(element.getName());
            int color = element.getColor();

            // import method to set the specific color value when an object is clicked
            rSeek.setProgress(red(color));
            gSeek.setProgress(green(color));
            bSeek.setProgress(blue(color));


        }

        else{
            elementName.setText("");
            rSeek.setProgress(0);
            gSeek.setProgress(0);
            bSeek.setProgress(0);
        }

        mountainView.invalidate();
        return false;

    }

    // once a seekbar has been changed, we need to check to see which seek bar has been
    // changed, and use that seekbars value to change the corresponding rgb value
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        if(seekBar.getId() == R.id.red_seekBar){
            mountainView.updateSelectedR(i);
        }
        else if(seekBar.getId() == R.id.green_seekBar){
            mountainView.updateSelectedG(i);
        }
        else if(seekBar.getId() == R.id.blue_seekBar){
            mountainView.updateSelectedB(i);
        }

        mountainView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
