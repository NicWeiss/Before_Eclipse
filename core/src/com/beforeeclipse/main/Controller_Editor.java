package com.beforeeclipse.main;

import com.badlogic.gdx.Gdx;
import com.beforeeclipse.main.GO.Map_element;
import com.beforeeclipse.main.GO.Menu_trigger;
import com.beforeeclipse.main.GO.Menu_Button;
import com.beforeeclipse.main.GO.Overlay;
import com.beforeeclipse.main.layouts.Editor;

/**
 * Created by nic on 11.10.19.
 */

public class Controller_Editor {
    private static GraphicLoader map_graphicLoader=null;
    private static GraphicLoader ui_graphicLoader=null;
    private static Editor editor;

    public static void init(Editor _editor){
        Gdx.app.log("Debug","INIT Controller EDITOR");
        editor = _editor;
    }

    public static void add_map_element(int id, float x, float y, float offset_x , float offset_y){
        Objects s;
        s = new Map_element();
        s.set_texture(map_graphicLoader.get_texture(id));
        s.set_services_type(map_graphicLoader.get_type(id));
        s.set_services_name("map");
        s.set_x(x);
        s.set_y(y);
        s.set_offset_x(offset_x);
        s.set_offset_y(offset_y);
        s.set_opacity(1);
        editor.map.add(s);
    }

    public static void add_menu_trigger(int id, float x, float y){
        Objects s;
        s = new Menu_trigger();
        s.set_texture(ui_graphicLoader.get_texture(id));
        s.set_services_type(ui_graphicLoader.get_type(id));
        s.set_services_name("menu");
        s.set_x(x);
        s.set_y(y);
        editor.ui.add(s);
    }

    public static void add_menu_item(String name, float x, float y, int scale_x, int scale_y){
        Objects s;
        s = new Menu_Button();
        int id = ui_graphicLoader.get_id_by_name(name);
        s.set_texture(ui_graphicLoader.get_texture(id));
        s.set_services_type(ui_graphicLoader.get_type(id));
        s.set_services_name(name);
        s.set_x(x);
        s.set_y(y);
        s.set_scale_x(scale_x);
        s.set_scale_y(scale_y);
        s.set_opacity(1);
        s.this_layout = "editor_menu";
        editor.menu.add(s);
    }

    public static void add_static_ui(String name, float x, float y, int scale_x, int scale_y){
        Objects s;
        s = new Overlay();
        int id = ui_graphicLoader.get_id_by_name(name);
        s.set_texture(ui_graphicLoader.get_texture(id));
        s.set_services_type(ui_graphicLoader.get_type(id));
        s.set_services_name(name);
        s.set_x(x);
        s.set_y(y);
        s.set_scale_x(scale_x);
        s.set_scale_y(scale_y);
        s.set_opacity(1);
        s.this_layout = "editor_menu";
        editor.ui.add(s);
    }

    public static void load_map(String mapname){
        map_graphicLoader = new GraphicLoader();
        map_graphicLoader.load_grapfSet("map/"+mapname);
        for (int i=0; i<10; i++) {
            add_map_element(0, i*128, 384, 0, 0);
            for (int j=-2; j<3; j++) {
                add_map_element(1, i*128,j* 128 ,0 , 0);
            }
        }

    }

    public static void load_ui(String mapname) {
        ui_graphicLoader = new GraphicLoader();
        ui_graphicLoader.load_grapfSet("ui/" + mapname);
        add_menu_trigger(0, -40, 1760);
        add_static_ui("overlay", 0, (1920-Gdx.graphics.getHeight())/2, 1080, 500);
        add_menu_item("background", 0, -200, 1080, 2420);
        add_menu_item("open", 40, 1600, 1000, 300);
        add_menu_item("save", 40, 1200, 1000, 300);
        add_menu_item("exit", 40, 800, 1000, 300);
        add_menu_item("close", 450, 200, 200, 200);
    }

    public static void select_object(int id){
        Objects s;
        s = new Overlay();
        s.set_texture(map_graphicLoader.get_texture(id));
        s.set_services_type(map_graphicLoader.get_type(id));
        s.set_services_name("selected_object");
        s.set_x(0);
        s.set_y((1920-Gdx.graphics.getHeight())/2);
        s.set_offset_x(20);
        s.set_offset_y(20);
        s.set_scale_x(230);
        s.set_scale_y(230);
        s.set_opacity(1);
        Editor.selected_oblect = s;
    }
}
