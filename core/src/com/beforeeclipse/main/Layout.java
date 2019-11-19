package com.beforeeclipse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nic on 10.10.19.
 */

public abstract class Layout implements InputProcessor {
    public void render(SpriteBatch batch){

    }
    public void init(){

    }
    public  void destruct(){

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Gdx.app.log("Debug","Layout finalize");
    }

}
