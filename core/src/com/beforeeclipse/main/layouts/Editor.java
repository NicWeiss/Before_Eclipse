package com.beforeeclipse.main.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beforeeclipse.main.Controller_Editor;
import com.beforeeclipse.main.Core;
import com.beforeeclipse.main.Layout;
import com.beforeeclipse.main.Objects;

import java.util.ArrayList;

/**
 * Created by nic on 11.10.19.
 */

public class Editor extends Layout {

    Texture background, space_block;
    float opacity;
    boolean hide = false;
    boolean touched = false;
    public static boolean pause = false;
    public static boolean pause_menu = false;
    public static ArrayList<Objects> map, ui, menu;
    public static Objects selected_oblect;
    float touch_x, touch_y, oldoffset_x, oldoffset_y, mouse_move_x, mouse_move_y, touch_dragged_x, touch_dragged_y;
    int selected_id = 0;

    public void init() {
        Gdx.app.log("Debug", "Layout Editor ");
        opacity = 1;
        background = new Texture("bg/editor_back.png");
        space_block = new Texture("unsort/space_block.png");
        map = new ArrayList<Objects>();
        ui = new ArrayList<Objects>();
        menu = new ArrayList<Objects>();
        Controller_Editor.init(this);
        Controller_Editor.load_map("1");
        Controller_Editor.load_ui("editor");
        Controller_Editor.select_object(selected_id);
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
        touched = true;
        touch_x = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        touch_x = (touch_x - Core.camera.viewportWidth / 2 + 1080 / 2);

        touch_y = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        touch_y = 1920 - (touch_y - Core.camera.viewportHeight / 2 + 1920 / 2);
        touch_dragged_x = touch_x;
        touch_dragged_y = touch_y;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int tmp_x, tmp_y;
        boolean skip = false;

        tmp_x = (int) (touch_dragged_x / 128);
        tmp_y = (int) (touch_dragged_y / 128);

        if (tmp_x<2)if(tmp_y == 14 || tmp_y == 15) skip = true;

        tmp_x = (int) ((touch_x - touch_dragged_x) * (-1) / 128);
        tmp_y = (int) ((touch_y - touch_dragged_y) * (-1) / 128);

        if (tmp_x != 0 && tmp_y != 0) skip =true;

        if (!pause) {
            if (tmp_x != 0)
                oldoffset_x = oldoffset_x + tmp_x;
            if (tmp_y != 0)
                oldoffset_y = oldoffset_y + tmp_y;
        }



        if (!pause && touched && !skip)
                if ((touch_dragged_y / 128) > 1)
                    Controller_Editor.add_map_element(
                            selected_id,
                            ((int) ((touch_dragged_x / 128) - oldoffset_x) * 128),
                            ((int) ((touch_dragged_y / 128) - oldoffset_y) * 128),
                            oldoffset_x * (128),
                            oldoffset_y * (128)
                    );
        touched = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int tmp_x, tmp_y;
        touch_dragged_x = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        touch_dragged_x = (touch_dragged_x - Core.camera.viewportWidth / 2 + 1080 / 2);

        touch_dragged_y = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        touch_dragged_y = 1920 - (touch_dragged_y - Core.camera.viewportHeight / 2 + 1920 / 2);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouse_move_x = (screenX * Core.camera.viewportWidth) / Gdx.graphics.getWidth();
        mouse_move_x = (mouse_move_x - Core.camera.viewportWidth / 2 + 1080 / 2);

        mouse_move_y = (screenY * Core.camera.viewportHeight) / Gdx.graphics.getHeight();
        mouse_move_y = 1920 - (mouse_move_y - Core.camera.viewportHeight / 2 + 1920 / 2);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Draw all objects
     *
     * @param batch
     */
    public void render(SpriteBatch batch) {

        if (!hide) {
            if (opacity + 0.03 < 0.99)
                opacity += 0.03;
        }
        if (hide) {
            if (opacity > 0.01)
                opacity -= 0.01;
        }
        batch.setColor(1, 1, 1, opacity);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float) 0x00 / 0xff, (float) 0x00 / 0xff, (float) 0x00 / 0xff, 1);
        float x_scale = 4080;
        float y_scale = 7520;
        float rotation = 0;
        batch.draw(background,
                -1800, -1800,
                x_scale / 2, y_scale / 2,
                x_scale, y_scale,
                1, 1,
                rotation,
                0, 0,
                background.getWidth(), background.getHeight(),
                false, false);


        //Draw Map
        if (map.size() > 0) {
            for (int i = 0; i < map.size(); i++) {
                map.get(i).process();
                map.get(i).draw(batch);
                if (!pause) if (touched)
                    map.get(i).do_touch(
                            (oldoffset_x + (int) (((touch_x - touch_dragged_x) * (-1)) / 128)) * 128,
                            (oldoffset_y + (int) (((touch_y - touch_dragged_y) * (-1)) / 128)) * 128);
            }
        }

        //space block
        batch.draw(space_block,
                (int) (touch_dragged_x / 128) * 128, (int) (touch_dragged_y / 128) * 128,
                x_scale / 2, y_scale / 2,
                128, 128,
                1, 1,
                rotation,
                0, 0,
                background.getWidth(), background.getHeight(),
                false, false);

        // Draw ui
        if (ui.size() > 0) {
            for (int i = 0; i < ui.size(); i++) {
                ui.get(i).process();
                ui.get(i).draw(batch);
                if (!pause)
                    ui.get(i).do_touch(touch_x, touch_y);
            }
            map.get(selected_id).draw(batch);
            selected_oblect.draw(batch);
        }

        // Draw menu
        if (pause_menu) if (menu.size() > 0) {
            for (int i = 0; i < menu.size(); i++) {
                menu.get(i).process();
                menu.get(i).draw(batch);
                if (touched)
                    menu.get(i).do_touch(touch_x, touch_y);
            }
        }


    }

}
