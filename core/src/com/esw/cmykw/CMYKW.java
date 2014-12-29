package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.esw.Meta;


public class CMYKW extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;

	private SpriteBatch batch;
	private ShapeRenderer sr;
	
	private Texture boxTexture;
	private Box box;
	private Texture gemTexture;
	private Box gem;
	
	private BitmapFont debugMessage;
	private String message = "Debug: ";
	
	private float deltaTime;
	private float debugClock;
	
	
	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create () {
		Meta.newline();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		
		debugMessage = new BitmapFont();
		debugMessage.setColor(Color.GREEN);
		
		boxTexture = new Texture(Gdx.files.internal("images/tronbox.png")); //Path is /android/assets/...
		box = new Box(boxTexture);
		//box.setSize(box.getHeight()-(SCREEN_HEIGHT * 0.2f), box.getWidth()-(SCREEN_HEIGHT * 0.2f)); //SCALING
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		
		Meta.println("Box X: " + box.getHeight());
		Meta.println("Box Y: " + box.getWidth());
		Meta.println("Box X * 0.2 = " + (box.getHeight()*0.2f));
		Meta.println("Box Y * 0.2 = " + (box.getWidth()*0.2f));
		
		gemTexture = new Texture(Gdx.files.internal("images/cyan.png"));
		gem = new Box(gemTexture);
		float[] boxvert = box.getVertices();
		for(int i = 0; i < boxvert.length; i++) {
			Meta.println("" + boxvert[i]);
		}
		
	}
	
	@SuppressWarnings("unused")
	private int rand(int bound) {
		return (int)(Math.random() * bound);
	}
	
	
	@SuppressWarnings("unused")
	private int rand(int top, int bottom) {
		return (int)(Math.random() * top + bottom);
	}

	@Override
	public void dispose() {
		batch.dispose();
		boxTexture.dispose();
		gemTexture.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1f); //Set background to BLACK
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		debugClock += deltaTime;

		//Exit the application if the escape key is pressed
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit(); //Kill dis
		}

		if(!box.isRotating()) { //Block input if the box is rotating
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				Meta.println("Debug: left key pressed");
				message = "Debug: left key pressed";
				//Meta.println("Rotating Left"); //CONSOLE DEBUG
				box.isRotatingLeft = true;
				debugClock = 0;
			} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				Meta.println("Debug: right key pressed");
				message = "Debug: right key pressed";
				//Meta.println("Rotating Right"); //CONSOLE DEBUG
				box.isRotatingRight = true;
				debugClock = 0;
			}
		}

		if(box.isRotatingLeft) { //Continue rotation (animate)
			box.rotateLeft(10f); //This number must be -10f because reasons!
		} else if(box.isRotatingRight) { //Continue rotation (animate)
			box.rotateRight(-10f); //This number must be 10f because reasons!
		}

		if(debugClock >= 2) { //About 2 seconds
			message = "Debug:"; //Resets the debug message
			debugClock = 0; //Resets the debug clock
		}
		
		//BEGIN BATCH
		batch.begin();
		box.draw(batch);
		debugMessage.draw(batch, message, 10, 40);
		batch.end();
		//END BATCH
		
		//CALC CIRCLE POSITION BASED ON BOX //TODO (Alex) Circle testing!!!
		
		
		//BEGIN SHAPERENDERREREHRUYEIUY
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.circle(100, 100, 10);
		sr.end();
		
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










