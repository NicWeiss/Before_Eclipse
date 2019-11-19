package com.beforeeclipse.main.GO;

import com.badlogic.gdx.Gdx;
import com.beforeeclipse.main.Core;
import com.beforeeclipse.main.Objects;
import com.beforeeclipse.main.layouts.Editor;
import com.beforeeclipse.main.layouts.Game;
import com.beforeeclipse.main.layouts.Menu;

/**
 * Created by nic on 10.10.19.
 */

public class Menu_Button extends Objects {

    String selector = "NULL";

    public Menu_Button() {
        type = TYPE_BUTTON;
        touched = false;
        counter = 0;
    }

    public void process() {


        if (this_layout == "editor_menu") {
            if (touched) {
                if (services_name == "close") {
                    counter = 5;
                }

                if (services_name == "exit") {
                    Gdx.app.log("Debug", "Change Layout ");
                    Menu.hide = false;
                    Core.change_layout(new Menu());
                }
            }
            if (Editor.pause) {
                if (counter != 0){
                    counter --;
                    if (counter == 0){
                        Editor.pause = false;
                        Editor.pause_menu = false;
                    }
                }
            }
        }

        if (this_layout == "main_menu") {
            if (touched) {
                opacity = 1;
                if (services_name == "new") {
                    Menu.hide = true;
                    selector = "new";
                }
                if (services_name == "load") {
                    Menu.hide = true;
                    selector = "load";
                    Gdx.app.log("Debug", "SELECT LOAD GAME");
                }
                if (services_name == "info") Gdx.app.log("Debug", "SELECT GAME INFO");
                if (services_name == "settings") Gdx.app.log("Debug", "SELECT SETTINGS");
                if (services_name == "exit") {
                    Gdx.app.log("Debug", "EXIT GAME");
                    System.exit(0);
                }
            }
            if (Menu.hide) {
                if (opacity - 0.03 > 0.01)
                    opacity -= 0.03;
                if (opacity - 0.03 < 0.01 && selector == "new") {
                    Gdx.app.log("Debug", "Change Layout ");
                    Core.change_layout(new Game());
                }
                if (opacity - 0.03 < 0.01 && selector == "load") {
                    Gdx.app.log("Debug", "Change Layout ");
                    Core.change_layout(new Editor());
                }

            }
        }

        touched = false;
    }


}
