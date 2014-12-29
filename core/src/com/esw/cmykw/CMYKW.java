package com.esw.cmykw;

import java.util.Random;

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


public class CMYKW extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;

	private SpriteBatch batch;
	private Texture texture;
	private Box box;
	private BitmapFont debugMessage;
	private String message = "Debug: ";
	private float deltaTime;
	private float debugClock;
	private Box[] gemArray = new Box[100];

	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create () {
		Meta.println("CMYKW");
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("images/testbox.png")); //Path is /android/assets/...
		debugMessage = new BitmapFont();
		debugMessage.setColor(Color.GREEN);
		box = new Box(texture);
		
		Texture cyanT = new Texture(Gdx.files.internal("images/cyan.png"));
		Texture magentaT = new Texture(Gdx.files.internal("images/magenta.png"));
		Texture yellowT = new Texture(Gdx.files.internal("images/yellow.png"));
		Texture blackT = new Texture(Gdx.files.internal("images/black.png"));
		Texture whiteT = new Texture(Gdx.files.internal("images/white.png"));

		Box cyan = new Box(cyanT, "cyan");
		Box magenta = new Box(magentaT, "magenta");
		Box yellow = new Box(yellowT, "yellow");
		Box black = new Box(blackT, "black");
		Box white = new Box(whiteT, "white");
		Box[] colors = new Box[5];
		colors[0] = cyan;
		colors[1] = magenta;
		colors[2] = yellow;
		colors[3] = black;
		colors[4] = white;
		
		for(int i = 0, rand = rand(5); i < gemArray.length; i++, rand = rand(5)) {
			gemArray[i] = colors[rand];
		}
		
		for(int i = 0; i < gemArray.length; i++) {
			Meta.println(gemArray[i].color());
		}

		box.setSize(box.getWidth(), box.getHeight()); //TODO (Alex) set box size appropriate to screen dimensions
		box.setCenter(SCREEN_WIDTH/2,  SCREEN_HEIGHT/2);
		
		
		for(int i = 0, randX = rand(SCREEN_WIDTH-20, 20), randY = rand(SCREEN_HEIGHT-20, 20); i < gemArray.length; i++, 
				randX = rand(SCREEN_WIDTH-20, 20), randY = rand(SCREEN_HEIGHT-20, 20)) {
			Meta.println("X: " + randX);
			Meta.println("Y: " + randY);
			Meta.newline();
			gemArray[i].setCenter(randX, randY);
		}
		
	}
	
	private int rand(int bound) {
		return (int)(Math.random() * bound);
	}
	
	private int rand(int top, int bottom) {
		return (int)(Math.random() * top + bottom);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
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

		batch.begin();
		box.draw(batch);
		debugMessage.draw(batch, message, 10, 40);
		for(int i = 0; i < gemArray.length; i++) {
			gemArray[i].draw(batch);
		}
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










