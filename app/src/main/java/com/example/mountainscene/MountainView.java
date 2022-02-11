// @author Denver Backus

package com.example.mountainscene;

import static android.graphics.Color.alpha;
import static android.graphics.Color.argb;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

public class MountainView extends SurfaceView {

    // declare an arrayList of elements
    private ArrayList<CustomElement> elements;

    // create a selected element, so that it can display that one element name
    private CustomElement selectedElement;

    // created custom starting colors for a few elements, can all be changed in the arraylist
    // imported an argb function to convert rgb values into a single integer

    /**
     External Citation
     Date: 9 February 2022
     Problem: Was having trouble creating the colors in android, I was hoping to find
     something that would accept integers

     Resource:
     https://developer.android.com/reference/android/graphics/Color
     Solution: I found a method in android sdk that accomplished this.
     */
    int skyColor = argb(255, 137, 207, 240);
    int trunkColor = argb(255, 150, 75, 0);
    int bushColor = argb(255, 0, 100, 0);
    int houseColor = argb(255, 210, 180, 140);
    int windowColor = argb(255, 173, 216, 230);
    int doorColor = argb(255, 101, 67, 33);

    public MountainView(Context context, AttributeSet attr){

        super(context, attr);

        // created an array list to store the elements, and assign each element a specific Name
        // color and location that can all be accessed.
        elements = new ArrayList<CustomElement>();

        setWillNotDraw(false);

        // add new elements to the array list in order to get access to their locations and colors.
        elements.add(new CustomRect("Sky", skyColor, 0, 0, 3000, 3000));
        elements.add(new CustomCircle("Bush", bushColor, 600, 900, 75 ));
        elements.add(new CustomRect("Grass", Color.GREEN, 0,900, 3000,3000));
        elements.add(new CustomCircle("Sun", Color.YELLOW, 0,0,400));
        elements.add(new CustomRect("Trunk", trunkColor, 400, 450, 500, 900));
        elements.add(new CustomCircle("Tree", bushColor, 450, 350, 250));
        elements.add(new CustomCircle("Pond", Color.BLUE, 1000, 1100, 200));
        elements.add(new CustomRect("House", houseColor, 1500, 200, 3000, 900));
        elements.add(new CustomRect("Chimney", Color.RED, 1750, 0, 1850,200));
        elements.add(new CustomRect("Window", windowColor, 1600, 400, 2000, 700));
        elements.add(new CustomRect("Door", doorColor, 2200, 400, 2450, 900));


    }

    @Override
    public void onDraw(Canvas canvas){

        // need to draw each element in the array, in the order they are in the array
        // for each loop to draw elements
        for(CustomElement e : elements){
            e.drawMe(canvas);
        }
    }

    // creating a for each loop to check if the point that the user clicks fall into
    // the array element. It stops at the last element that includes the click event,
    // so that the graphics can overlap, but it will select the top element
    public CustomElement getClickedObject(int x, int y){

        selectedElement = null;

        for(CustomElement e : elements){

            if(e.containsPoint(x,y)){

                selectedElement = e;
            }
        }

        return selectedElement;
    }

    // once an element has been selected the int from the red seekBar needs to be changed
    // while keeping the others selected
    public void updateSelectedR(int i){

        if(selectedElement != null){
            int r = i;
            int a = alpha(selectedElement.getColor());
            int g = green(selectedElement.getColor());
            int b = blue(selectedElement.getColor());

            selectedElement.setColor(argb(a, r, g, b));
        }
    }

    // once an element has been selected the int from the green seekBar needs to be changed
    // while keeping the others selected
    public void updateSelectedG(int i){

        if(selectedElement != null){
            int r = red(selectedElement.getColor());
            int a = alpha(selectedElement.getColor());
            int g = i;
            int b = blue(selectedElement.getColor());

            selectedElement.setColor(argb(a, r, g, b));
        }
    }

    // once an element has been selected the int from the blue seekBar needs to be changed
    // while keeping the others selected
    public void updateSelectedB(int i){

        if(selectedElement != null){
            int r = red(selectedElement.getColor());
            int a = alpha(selectedElement.getColor());
            int g = green(selectedElement.getColor());
            int b = i;

            selectedElement.setColor(argb(a, r, g, b));
        }
    }

}
