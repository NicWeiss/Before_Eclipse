package com.beforeeclipse.main.GO;

import com.beforeeclipse.main.Objects;

/**
 * Created by nic on 11.10.19.
 */

public class Map_element extends Objects {
    float save_x, save_y;

    public Map_element() {
        x_scale = 128;
        y_scale = 128;
        type = TYPE_BLOCK;
        touched = false;
        opacity = 1;
    }

    public void do_touch( float f_x , float f_y) {
        offset_x = f_x;
        offset_y =  f_y;
        //Gdx.app.log("Debug","opacity - " + opacity);

    }
}
