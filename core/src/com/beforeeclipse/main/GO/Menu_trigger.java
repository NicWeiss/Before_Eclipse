package com.beforeeclipse.main.GO;

import com.beforeeclipse.main.Objects;
import com.beforeeclipse.main.layouts.Editor;

/**
 * Created by nic on 15.10.19.
 */

public class Menu_trigger extends Objects{

    public Menu_trigger() {
        x_scale = 300;
        y_scale = 300;
        type = TYPE_BUTTON;
        touched = false;
        opacity = (float) 0.7;
    }

    public void process() {
        if (touched) {
            Editor.pause = true;
            Editor.pause_menu =true;
        }
        touched = false;

    }
}
