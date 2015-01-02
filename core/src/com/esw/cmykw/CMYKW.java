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
	private GridSquare[][] gemArray;

	private BitmapFont debugMessage;
	private String inputDebug = "Input debug: ";
	private String timingDebug = "Timing debug: 0.0";
	private String fpsDebug = "FPS: ";
	private boolean first = true;

	private float deltaTime;
	private float debugClearClock;
	private float timingClock;

	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		debugMessage = new BitmapFont();

		boxTexture 		= new Texture(Gdx.files.internal("images/tronbox3.png"));
		cyanTexture 	= new Texture(Gdx.files.internal("images/cyan.png"));
		magentaTexture 	= new Texture(Gdx.files.internal("images/magenta.png"));
		yellowTexture	= new Texture(Gdx.files.internal("images/yellow.png"));
		blackTexture	= new Texture(Gdx.files.internal("images/black.png"));
		whiteTexture	= new Texture(Gdx.files.internal("images/white.png"));

		box = new Sprite(boxTexture);
		//box.setSize(box.getHeight()-(SCREEN_HEIGHT * 0.1f), box.getWidth()-(SCREEN_HEIGHT * 0.1f));
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		box.setOriginCenter();

		float gemsize = (box.getWidth() * 0.14f);
		Sprite[] colors = new Sprite[5];
		colors[0] = new Sprite(cyanTexture);
		colors[0].setSize(gemsize, gemsize);
		colors[1] = new Sprite(magentaTexture);
		colors[1].setSize(gemsize, gemsize);
		colors[2] = new Sprite(yellowTexture);
		colors[2].setSize(gemsize, gemsize);
		colors[3] = new Sprite(blackTexture);
		colors[3].setSize(gemsize, gemsize);
		colors[4] = new Sprite(whiteTexture);
		colors[4].setSize(gemsize, gemsize);

		Random rand = new Random();

		gemArray = new GridSquare[5][5];
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				int r = rand.nextInt(9);
				if(r < 5){
					gemArray[x][y] = new GridSquare(box, new Sprite(colors[r]), x, y);
				} else {
					gemArray[x][y] = new GridSquare(box, x, y);
				}
			}
		}

		boxCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		boxCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		boxCamera.update();

		worldCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		worldCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
		worldCamera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		boxTexture.dispose();
		cyanTexture.dispose();
		magentaTexture.dispose();
		yellowTexture.dispose();
		blackTexture.dispose();
		whiteTexture.dispose();
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

		//Reroll!
		if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
			dispose();
			create();
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
			rotateLeft(boxCamera, gemArray);
		} else if(rotatingRight) {
			rotateRight(boxCamera, gemArray);
		}

		if(!first && !rotatingLeft && !rotatingRight) {
			timingClock--;
			timingDebug = "Timing debug: " + timingClock;
			if(timingClock <= 0) {
				timingClock = 0;
				timingDebug = "Timing debug: " + timingClock + " : DROP"; //FIXME Drop code
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
		debugMessage.setColor(Color.YELLOW);
		debugMessage.draw(batch, fpsDebug + Gdx.graphics.getFramesPerSecond(), SCREEN_WIDTH-100, 20);
		batch.end();
		//END BATCH

		boxCamera.update();
		batch.setProjectionMatrix(boxCamera.combined);
		//BEGIN BATCH FOR BOX CAMERA
		batch.begin();
		box.draw(batch);
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				if(gemArray[x][y].isOccupied()){
					gemArray[x][y].getGem().draw(batch);
				}
			}
		}
		batch.end();
		//END BATCH
		first = false;

	}

	private void rotateLeft(OrthographicCamera camera, GridSquare[][] array) {
		rotatingLeft = true;
		camera.rotate(ROT_CON);
		this.rotation += ROT_CON;
		if(rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingLeft = false;
		}
		int n = array.length;
		for(int x = 0; x < n/2; x++) {
			for(int y = 0; y < n-x-1; y++) {
				GridSquare tmp = array[x][y];
				array[x][y] = array[y][n-x-1];
				array[y][n-x-1] = array[n-x-1][n-y-1];
				array[n-x-1][n-y-1] = array[n-y-1][x];
				array[n-y-1][x] = tmp;
			}
		}
	}

	private void rotateRight(OrthographicCamera camera, GridSquare[][] array) {
		rotatingRight = true;
		camera.rotate(-ROT_CON);
		rotation += ROT_CON;
		if(rotation >= 90 || rotation <= -90) {
			rotation = 0;
			rotatingRight = false;
		}
		int n = array.length;
		for(int x = 0; x < n/2; x++) {
			for(int y = 0; y < n-x-1; y++) {
				GridSquare tmp = array[x][y];
				array[x][y] = array[n-y-1][x];
				array[n-y-1][x] = array[n-x-1][n-y-1];
				array[n-x-1][n-y-1] = array[y][n-x-1];
				array[y][n-x-1] = tmp;
			}
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










