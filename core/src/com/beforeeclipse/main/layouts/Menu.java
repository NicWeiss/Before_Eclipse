package com.beforeeclipse.main.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beforeeclipse.main.Controller_Menu;
import com.beforeeclipse.main.Core;
import com.beforeeclipse.main.Layout;
import com.beforeeclipse.main.Objects;

import java.util.ArrayList;

/**
 * Created by nic on 10.10.19.
 */

public class Menu extends Layout {

    Texture background;
    public static ArrayList<Objects> objects;
    public static float opacity;
    int counter;
    public static float finger_x, finger_y;
    public static boolean hide = false;

    public void init() {
        background = new Texture("bg/Menu.png");
        opacity = 0;
        objects = new ArrayList<Objects>();
        Controller_Menu.init(this);
        Controller_Menu.create_buttons("new", 35, 1600);
        Controller_Menu.create_buttons("load", 35, 1250);
        Controller_Menu.create_buttons("info", 35, 900);
        Controller_Menu.create_buttons("settings", 35, 550);
        Controller_Menu.create_buttons("exit", 35, 200);
        Gdx.app.log("Debug", "Menu is Load");
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        finger_x = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        finger_x = (finger_x - Core.camera.viewportWidth / 2 + 1080 / 2);

        finger_y = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        finger_y = 1920 - (finger_y - Core.camera.viewportHeight / 2 + 1920 / 2);

        //Obj board = objects.get(0);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        finger_x = -1;
        finger_y = -1;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void render(SpriteBatch batch) {

        if(!hide){
            if (opacity + 0.03 < 0.99)
                opacity += 0.03;
        }
        if(hide){
            if (opacity > 0.01)
                opacity -= 0.01;
        }
        batch.setColor(1, 1, 1, opacity);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float) 0x00 / 0xff, (float) 0x00 / 0xff, (float) 0x00 / 0xff, 1);
        batch.draw(background, 0, 0);

        if (objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).process();
                objects.get(i).draw(batch);
                objects.get(i).do_touch(finger_x, finger_y);
            }
            //objects.get(1).draw(batch);
        }


    }
}
