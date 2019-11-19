package com.beforeeclipse.main;

import com.badlogic.gdx.Gdx;
import com.beforeeclipse.main.GO.Map_element;
import com.beforeeclipse.main.layouts.Game;

/**
 * Created by nic on 11.10.19.
 */

public class Controller_Game{

        private static GraphicLoader map_graphicLoader=null;
        private static Game game;

        public static void init(Game _game){
            Gdx.app.log("Debug","INIT Controller GAME");
            game = _game;
        }

        public static void add_map_element(int id, float x, float y ){
            Objects s;
            s = new Map_element();
            s.set_texture(map_graphicLoader.get_texture(id));
            s.set_services_type(map_graphicLoader.get_type(id));
            s.set_services_name("map");
            s.set_x(x);
            s.set_y(y);
            s.set_opacity(1);
            game.map.add(s);
        }

        public static void load_map(String mapname){
            map_graphicLoader = new GraphicLoader();
            map_graphicLoader.load_grapfSet("map/"+mapname);
            for (int i=0; i<10; i++) {
                add_map_element(0, i*128, 384);
                for (int j=-2; j<3; j++) {
                    add_map_element(1, i*128,j* 128);
                }
            }

        }
}