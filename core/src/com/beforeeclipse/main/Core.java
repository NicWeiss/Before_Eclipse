package com.beforeeclipse.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.beforeeclipse.main.layouts.Logo;

import static com.badlogic.gdx.Application.LOG_INFO;

public class Core extends ApplicationAdapter {
	SpriteBatch batch;
	ExtendViewport viewport;
	public static OrthographicCamera camera;

	public static Layout layout;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(LOG_INFO);
		batch = new SpriteBatch();
        layout = null;
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(1080,1920,camera);
		viewport.apply();
		camera.position.set(1080/2,1920/2,0);
		change_layout(new Logo()); //THIS IS ROOM CHANGER!!!!!!!!!HERE!
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

	public static void change_layout(Layout new_layout){
		if(layout!=null) {
            layout.destruct();
            layout = null;
		}
		//Texture.clearAllTextures(Gdx.app);
		System.gc();
        layout = new_layout;
        layout.init();
		Gdx.input.setInputProcessor(layout);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render () {
		//viewport.apply();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        layout.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
