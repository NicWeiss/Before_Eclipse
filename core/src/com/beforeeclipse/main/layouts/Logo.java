package com.beforeeclipse.main.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.beforeeclipse.main.Core;
import com.beforeeclipse.main.Layout;

/**
 * Created by nic on 10.10.19.
 */

public class Logo extends Layout{
    Texture background;
    float opacity;
    int counter;
    ShapeRenderer shapeRenderer;

    public void init(){
        background = new Texture("bg/Logo.png");
        opacity=1;
        counter = 300;
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
        next_room();
        return false;
    }

    private void next_room(){
        Core.change_layout(new Menu());
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float)0x00/0xff , (float)0x00/0xff, (float)0x00/0xff, 1);


        batch.draw(background, 0,0);
        counter--;
        if(counter<0)
            opacity-=0.01;
        batch.setColor(1,1,1, opacity);

        if (opacity<0) {
            batch.setColor(1, 1, 1, 1);
            next_room();
        }
    }

}