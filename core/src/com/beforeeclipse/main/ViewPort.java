package com.beforeeclipse.main;

/**
 * Created by nic on 10.10.19.
 */

import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class ViewPort extends ScalingViewport {
    public ViewPort(Scaling scaling, float worldWidth, float worldHeight) {
        super(scaling, worldWidth, worldHeight);



    }
}
