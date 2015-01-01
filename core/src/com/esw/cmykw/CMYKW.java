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

	private Board box;
	private Gem gem;
	private GridSquare gemArray;

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

		box = new Board(boxTexture);
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		Rectangle bounds = box.getBoundingRectangle();
		float x = bounds.getX();
		float y = bounds.getY();
		
		x = x + additive(1);
		y = y + additive(3);
		
		gem = new Gem(cyanTexture, 60f);
		gem.setCenter(x, y);
		
		boxCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		boxCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		boxCamera.update();
		
		worldCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		worldCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		worldCamera.update();
	}
	
	private float additive(int times) {
		return ((box.getDimension() * 0.1f) * times);
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
			Gdx.app.exit(); //Kill dis
		}

		if(!rotatingLeft && !rotatingRight) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				inputDebug = "Input debug: left key pressed";
				rotatingLeft = true;
				debugClearClock = 0;
				timingClock = 20;
				timingDebug = "Timing debug: " + timingClock;
			} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				inputDebug = "Input debug: right key pressed";
				rotatingRight = true;
				debugClearClock = 0;
				timingClock = 20;
				timingDebug = "Timing debug: " + timingClock;
			}
		}
		timingClock--;
		timingDebug = "Timing debug: " + timingClock;
		
		//Times the rotation (sort of) //TODO fix timing
		if(timingClock <= 0) {
			timingClock = 0;
			timingDebug = "Timing debug: " + timingClock;
		}
		
		if(rotatingLeft) {
			rotateLeft(boxCamera);
		} else if(rotatingRight) {
			rotateRight(boxCamera);
		}
		
		if(!rotatingLeft && !rotatingRight) {
			if(timingClock == 0)
				timingDebug = "Timing debug: " + timingClock + " : DROP"; //TODO Drop code
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
		gem.draw(batch);
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










