package com.beforeeclipse.main.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beforeeclipse.main.Controller_Game;
import com.beforeeclipse.main.Core;
import com.beforeeclipse.main.Layout;
import com.beforeeclipse.main.Objects;

import java.util.ArrayList;

/**
 * Created by nic on 10.10.19.
 */

public class Game extends Layout {

    Texture background;
    float opacity;
    boolean hide = false;
    public static float finger_x, finger_y;
    public static ArrayList<Objects> map;
    boolean touched = false;
    float touch_x_start, touch_y_start, oldoffset_x, oldoffset_y;

    public void init(){
        Gdx.app.log("Debug","Layout GAME ");
        opacity=1;
        background = new Texture("bg/m1back.png");
        map = new ArrayList<Objects>();
        Controller_Game.init(this);
        Controller_Game.load_map("1");
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
        touch_x_start = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        touch_x_start = (touch_x_start - Core.camera.viewportWidth / 2 + 1080 / 2);

        touch_y_start = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        touch_y_start = 1920 - (touch_y_start - Core.camera.viewportHeight / 2 + 1920 / 2);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touched = false;
        oldoffset_x = oldoffset_x+(touch_x_start-finger_x)*(-1);
        oldoffset_y = oldoffset_y+(touch_y_start-finger_y)*(-1);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        finger_x = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        finger_x = (finger_x - Core.camera.viewportWidth / 2 + 1080 / 2);

        finger_y = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        finger_y = 1920 - (finger_y - Core.camera.viewportHeight / 2 + 1920 / 2);
        touched = true;

        //Obj board = objects.get(0);
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
        //batch.draw(background, 0, 0);
        float x_scale =2080;
        float y_scale =3520;
        float rotation = 0;
        batch.draw(background,
                -800, -800,
                x_scale/2,y_scale/2,
                x_scale,y_scale,
                1,1,
                rotation,
                0,0,
                background.getWidth(),background.getHeight(),
                false,false);

        if (map.size() > 0) {
            for (int i = 0; i < map.size(); i++) {
                map.get(i).process();
                map.get(i).draw(batch);
                if(touched)map.get(i).do_touch(oldoffset_x+(touch_x_start-finger_x)*(-1), oldoffset_y+(touch_y_start-finger_y)*(-1));
            }
        }


    }

}
