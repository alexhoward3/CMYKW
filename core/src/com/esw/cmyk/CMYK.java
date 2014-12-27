package com.esw.cmyk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esw.Meta;

public class CMYK extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public static int SCREEN_WIDTH = 1600; //TODO (Alex) Fix these constants!
	public static int SCREEN_HEIGHT = 900;

	private SpriteBatch batch;
	private Texture texture;
	private Box box;
	private BitmapFont debugMessage;
	private String message = "Debug: ";
	private float deltaTime;
	private float debugClock;

	@Override
	public void create () {
		Meta.println("CMYKW");
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("images/testbox.png")); //Path is /android/assets/...
		debugMessage = new BitmapFont();
		debugMessage.setColor(Color.GREEN);
		box = new Box(texture);
		
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
		deltaTime = Gdx.graphics.getDeltaTime();
		debugClock += deltaTime;
		
		if(!box.isRotatingRight && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			message = "Debug: left key pressed";
			Meta.println("Rotating Left");
			box.isRotatingRight = true;
			debugClock = 0;
		} else if(!box.isRotatingLeft && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			message = "Debug: right key pressed";
			Meta.println("Rotating Right");
			box.isRotatingLeft = true;
			debugClock = 0;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		if(box.isRotatingLeft) {
			box.rotateLeft(-10f); //This number must be -10f!
		} else if(box.isRotatingRight) {
			box.rotateRight(10f); //This number must be 10f!
		}
		
		if(debugClock >= 2) { //About 2 seconds
			message = "Debug:"; //Resets the debug message
			debugClock = 0; //Resets the debug clock
		}
		
		batch.begin();
		box.draw(batch);
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}










