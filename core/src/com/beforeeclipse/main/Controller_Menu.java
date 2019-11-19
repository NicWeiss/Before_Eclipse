package com.beforeeclipse.main;

import com.badlogic.gdx.Gdx;
import com.beforeeclipse.main.GO.Menu_Button;
import com.beforeeclipse.main.layouts.Menu;

/**
 * Created by nic on 10.10.19.
 */

public class Controller_Menu {

    private static GraphicLoader menuui_graphicLoader=null;
    private static Menu menu;

    public static void init(Menu _menu){
        Gdx.app.log("Debug","INIT Controller menu");
        menuui_graphicLoader = new GraphicLoader();
        menuui_graphicLoader.load_grapfSet("systemui");
        menu = _menu;
    }

    public static void create_buttons(String name, float x, float y ){
        Objects s;
        s = new Menu_Button();
        int id = menuui_graphicLoader.get_id_by_name(name);
        s.set_texture(menuui_graphicLoader.get_texture(id));
        s.set_services_type(menuui_graphicLoader.get_type(id));
        s.set_services_name(name);
        s.set_x(x);
        s.set_y(y);
        s.set_scale_x(1020);
        s.set_scale_y(340);
        s.set_opacity((float)0.5);
        s.this_layout = "main_menu";
        menu.objects.add(s);
    }
}
