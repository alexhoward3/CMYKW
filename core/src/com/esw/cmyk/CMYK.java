package com.esw.cmyk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.esw.Meta;

public class CMYK extends ApplicationAdapter {
	public static int SCREEN_WIDTH = 1600; //TODO (Alex) Fix these constants!
	public static int SCREEN_HEIGHT = 900;
	
	private SpriteBatch batch;
	private Texture texture;
	private Sprite box;
	private Camera camera;
	private BitmapFont debugMessage;
	private String message = "Debug: ";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("images/testbox.png")); //Path is /android/assets/...
		debugMessage = new BitmapFont();
		debugMessage.setColor(Color.GREEN);
		
		box = new Sprite(texture);
		box.setCenter(SCREEN_WIDTH/2,  SCREEN_HEIGHT/2);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //Set background to BLACK
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			message = "Debug: left key pressed";
			box.rotate90(false);
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			message = "Debug: right key pressed";
			box.rotate90(true);
		}
		
		//BEGIN DRAWING
		batch.begin();
		box.draw(batch); //Draw the sprite
		debugMessage.draw(batch, message, 10, 40);
		batch.end();
		//END DRAWING
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
}
