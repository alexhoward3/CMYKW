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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.esw.Meta;


public class CMYKW extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;

	private OrthographicCamera boxCamera;
	private OrthographicCamera worldCamera;
	private boolean rotatingRight, rotatingLeft;
	private float rotation = 0;
	private float ROT_CON = 10f;
	
	private SpriteBatch batch;

	private Texture boxTexture;
	private Texture cyanTexture;
	private Texture magentaTexture;
	private Texture yellowTexture;
	private Texture blackTexture;
	private Texture whiteTexture;

	private Sprite box;
	private Sprite gem;
	private Sprite[][] gemArray;

	private BitmapFont debugMessage;
	private String inputDebug = "Input debug: ";
	private String timingDebug = "Timing debug: 0.0";

	private float deltaTime;
	private float debugClearClock;
	private float timingClock;

	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create () {
		Meta.newline();

		batch = new SpriteBatch();

		debugMessage = new BitmapFont();
		debugMessage.setColor(Color.GREEN);

		boxTexture 		= new Texture(Gdx.files.internal("images/tronbox3.png"));
		cyanTexture 	= new Texture(Gdx.files.internal("images/cyan.png"));
		magentaTexture 	= new Texture(Gdx.files.internal("images/magenta.png"));
		yellowTexture	= new Texture(Gdx.files.internal("images/yellow.png"));
		blackTexture	= new Texture(Gdx.files.internal("images/black.png"));
		whiteTexture	= new Texture(Gdx.files.internal("images/white.png"));

		box = new Sprite(boxTexture);
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		Rectangle bounds = box.getBoundingRectangle();
		float xB = bounds.getX();
		float yB = bounds.getY();
		
		xB = xB + additive(1);
		yB = yB + additive(3);
		
		gem = new Sprite(cyanTexture);
		gem.setSize(60f, 60f);
		gem.setCenter(xB, yB);
		
		Sprite[] colors = new Sprite[5];
		colors[0] = new Sprite(cyanTexture);
		colors[0].setSize(70f, 70f);
		colors[1] = new Sprite(magentaTexture);
		colors[1].setSize(70f, 70f);
		colors[2] = new Sprite(yellowTexture);
		colors[2].setSize(70f, 70f);
		colors[3] = new Sprite(blackTexture);
		colors[3].setSize(70f, 70f);
		colors[4] = new Sprite(whiteTexture);
		colors[4].setSize(70f, 70f);
		
		Random rand = new Random();
		
		gemArray = new Sprite[5][5];
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				int r = rand.nextInt(5);
				gemArray[x][y] = new Sprite(colors[r]);
				gemArray[x][y].setOrigin(gemArray[x][y].getWidth()/2, gemArray[x][y].getHeight()/2);
			}
		}
		positions();
		
		boxCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		boxCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		boxCamera.update();
		
		worldCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		worldCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		worldCamera.update();
	}
	
	//Sets all of the positions for the gems
	public void positions() {
		for(int x = 0; x < 5; x++) {
			float xpos = box.getBoundingRectangle().getX();
			switch(x) {
			case 0:
				xpos = xpos + additive(1);
				break;
			case 1:
				xpos = xpos + additive(3);
				break;
			case 2:
				xpos = xpos + additive(5);
				break;
			case 3:
				xpos = xpos + additive(7);
				break;
			case 4:
				xpos = xpos + additive(9);
				break;
			}
			for(int y = 0; y < 5; y++) {
				float ypos = box.getBoundingRectangle().getY();
				switch(y) {
				case 0:
					ypos = ypos + additive(1);
					break;
				case 1:
					ypos = ypos + additive(3);
					break;
				case 2:
					ypos = ypos + additive(5);
					break;
				case 3:
					ypos = ypos + additive(7);
					break;
				case 4:
					ypos = ypos + additive(9);
				}
				gemArray[x][y].setCenter(xpos, ypos);
			}
		}
	}
	
	private float additive(int times) {
		return ((box.getWidth() * 0.1f) * times);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1f); //Set background to BLACK
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		debugClearClock += deltaTime;
		
		//Exit the application if the escape key is pressed
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			inputDebug = "Bye bye!";
			Gdx.app.exit(); //Kill dis
		}

		if(!rotatingLeft && !rotatingRight) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				inputDebug = "Input debug: left key pressed";
				rotatingLeft = true;
				debugClearClock = 0;
				timingClock = 50;
				timingDebug = "Timing debug: " + timingClock;
			} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				inputDebug = "Input debug: right key pressed";
				rotatingRight = true;
				debugClearClock = 0;
				timingClock = 50;
				timingDebug = "Timing debug: " + timingClock;
			}
		}
		
		if(rotatingLeft) {
			rotateLeft(boxCamera);
		} else if(rotatingRight) {
			rotateRight(boxCamera);
		}
		
		if(!rotatingLeft && !rotatingRight) {
			timingClock--;
			timingDebug = "Timing debug: " + timingClock;
			if(timingClock <= 0) {
				timingClock = 0;
				timingDebug = "Timing debug: " + timingClock + " : DROP"; //TODO Drop code
			}
		}

		if(debugClearClock > 1) {
			inputDebug = "Input debug: ";
			debugClearClock = 0;
		}
		
		worldCamera.update();
		batch.setProjectionMatrix(worldCamera.combined);
		//BEGIN BATCH FOR WORLD CAMERA
		batch.begin();
		debugMessage.setColor(Color.GREEN);
		debugMessage.draw(batch, inputDebug, 10, 20);
		debugMessage.setColor(Color.RED);
		debugMessage.draw(batch, timingDebug, 10, 40);
		batch.end();
		//END BATCH

		boxCamera.update();
		batch.setProjectionMatrix(boxCamera.combined);
		//BEGIN BATCH FOR BOX CAMERA
		batch.begin();
		box.draw(batch);
		//gem.draw(batch); //Testing sprite
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				gemArray[x][y].draw(batch);
			}
		}
		batch.end();
		//END BATCH


	}
	
	private void rotateLeft(OrthographicCamera camera) {
		rotatingLeft = true;
		camera.rotate(ROT_CON);
		this.rotation += ROT_CON;
		if(rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingLeft = false;
		}
	}

	private void rotateRight(OrthographicCamera camera) {
		rotatingRight = true;
		camera.rotate(-ROT_CON);
		rotation += ROT_CON;
		if(rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingRight = false;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
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










