package com.esw.cmykw;

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

public class CMYKW extends ApplicationAdapter implements ApplicationListener,
InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;
	
	boolean rotatingLeft, rotatingRight;
	float rotation;
	final float ROT_CON = 10f;
	
	OrthographicCamera worldCamera;
	OrthographicCamera gameCamera;
	
	BitmapFont debugMessage;

	SpriteBatch batch;
	Sprite box;
	Sprite cards;
	Grid grid;
	
	boolean first = true;
	String inputDebug = "Input debug: ";
	String timingDebug = "Timing debug: 0.0";
	String fpsDebug = "FPS: ";
	
	float deltaTime;
	float debugClearClock;
	float timingClock;

	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create() {
		
		worldCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		worldCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		worldCamera.update();
		
		gameCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		gameCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		gameCamera.update();
		
		batch = new SpriteBatch();
		
		debugMessage = new BitmapFont();

		box = new Sprite(new Texture(Gdx.files.internal("images/positionbox.png")));
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		
		cards = new Sprite(new Texture(Gdx.files.internal("images/cards.png")));
		cards.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		
		grid = new Grid(box);
		
		grid.printGrid("Start");
		
		debugClearClock = 0.0f;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		debugClearClock += deltaTime;
		
		if(rotatingLeft && !rotatingRight) {
			rotatingLeft = true;
			rotateCameraLeft();
		} else if(rotatingRight && !rotatingLeft) {
			rotatingRight = true;
			rotateCameraRight();
		}
		
		if (!rotatingLeft && !rotatingRight) {
			timingClock--;
			timingDebug = "Timing debug: " + timingClock;
			if (timingClock <= 0) {
				timingClock = 0;
				timingDebug = "Timing debug: " + timingClock + " : DROP";
				grid.drop();
			}
		}
		
		if(debugClearClock > 1) {
			inputDebug = "Input debug: ";
			debugClearClock = 0.0f;
		}
		
		//WORLD CAMERA
		worldCamera.update();
		batch.setProjectionMatrix(worldCamera.combined);
		batch.begin();
		debugMessage.setColor(Color.GREEN);
		debugMessage.draw(batch, inputDebug, 10, 20);
		debugMessage.setColor(Color.RED);
		debugMessage.draw(batch, timingDebug, 10, 40);
		debugMessage.setColor(Color.YELLOW);
		debugMessage.draw(batch, fpsDebug + Gdx.graphics.getFramesPerSecond(), SCREEN_WIDTH - 100, 20);
		batch.end();
		
		//GAME CAMERA
		gameCamera.update();
		batch.setProjectionMatrix(gameCamera.combined);
		
		batch.begin();
		box.draw(batch);
		grid.draw(batch);
		cards.draw(batch);
		batch.end();
		
		if(!rotatingLeft && !rotatingRight) 
			handleInput();
	}

	public float additive(Sprite box, int times) {
		return ((box.getWidth() * 0.1f) * times);
	}

	private void handleInput() {
		// Exit the application if the escape key is pressed
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit(); // Kill dis
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			grid.rotateLeft();
			rotatingLeft = true;
			grid.printGrid("Rot L");
			debugClearClock = 0;
			timingClock = 50;
			timingDebug = "Timing debug: " + timingClock;
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			grid.rotateRight();
			rotatingRight = true;
			grid.printGrid("Rot R");
			debugClearClock = 0;
			timingClock = 50;
			timingDebug = "Timing debug: " + timingClock;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			grid.drop();
			grid.printGrid("Drops");
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			grid.headNorth();
			grid.printGrid("North");
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			grid.headWest();
			grid.printGrid("West");
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			grid.headSouth();
			grid.printGrid("South");
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			grid.headEast();
			grid.printGrid("East");
		}
	}
	
	private void rotateCameraLeft() {
		gameCamera.rotate(ROT_CON);
		this.rotation += ROT_CON;
		if (rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingLeft = false;
		}
	}
	
	private void rotateCameraRight() {
		gameCamera.rotate(-ROT_CON);
		rotation += ROT_CON;
		if (rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingRight = false;
		}
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














































