package com.esw.cmykw;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.esw.Meta;


public class CMYKW extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;

	private SpriteBatch batch;
	private ShapeRenderer sr;

	private Texture boxTexture;
	private Texture cyanTexture;
	private Texture magentaTexture;
	private Texture yellowTexture;
	private Texture blackTexture;
	private Texture whiteTexture;
	private Texture circleTexture;

	private Board board;
	private Sprite circles;
	private Gem[] gemArray;
	private Gem gem;

	private BitmapFont debugMessage;
	private String message = "Debug: ";

	private float deltaTime;
	private float debugClock;
	private float period = 6f;
	private float angle = 0;
	private float radius = 5;

	Rectangle bounds;
	float gemX;
	float gemY;
	boolean trigger = true;

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

		boxTexture = new Texture(Gdx.files.internal("images/tronbox3.png")); //Path is /android/assets/...
		board = new Board(boxTexture, 500);
		//box.setSize(box.getHeight()-(SCREEN_HEIGHT * 0.2f), box.getWidth()-(SCREEN_HEIGHT * 0.2f)); //TODO SCALING DO NOT USE YET
		board.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		
		circleTexture = new Texture(Gdx.files.internal("images/circles.png"));
		circles = new Sprite(circleTexture);
		circles.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

		bounds = board.getBoundingRectangle();
		board.create(bounds);

		cyanTexture = new Texture(Gdx.files.internal("images/cyan.png"));
		magentaTexture = new Texture(Gdx.files.internal("images/magenta.png"));
		yellowTexture = new Texture(Gdx.files.internal("images/yellow.png"));
		blackTexture = new Texture(Gdx.files.internal("images/black.png"));
		whiteTexture = new Texture(Gdx.files.internal("images/white.png"));

		gem = new Gem(cyanTexture, 60f);
		float gemX;
		float gemY;
		
		gemX = bounds.getX();
		gemY = bounds.getY();
		
		gemX = gemX + (board.getWidth()*0.1f);
		gemY = gemY + (board.getHeight()*0.1f);
		gem.setCenter(gemX, gemY);

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
		debugClock += deltaTime;

		//Exit the application if the escape key is pressed
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit(); //Kill dis
		}
		
		
		if(!board.isRotating()) { //Block input if the box is rotating
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				message = "Debug: left key pressed";
				board.rotatingLeft(true);
				debugClock = 0;
			} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				message = "Debug: right key pressed";
				board.rotatingRight(true);
				debugClock = 0;
			}
		}
		
		gem.setOrigin(board.getWidth(), board.getHeight());
		if(board.isRotatingLeft()) { //Continue rotation (animate)
			board.rotateLeft();
			gem.rotate(10f);
		} else if(board.isRotatingRight()) { //Continue rotation (animate)
			board.rotateRight();
			gem.rotate(-10f);
		}

		if(debugClock >= 2) { //About 2 seconds
			message = "Debug: "; //Resets the debug message
			debugClock = 0; //Resets the debug clock
		}
		
		angle += 1f;
		float x = (float)((bounds.getX()+board.getWidth()/2) + Math.sin(angle)*280);
		float y = (float)((bounds.getY()+board.getHeight()/2) + Math.cos(angle)*280);
		gem.setCenter(x, y);
		Meta.println(""+ angle);

		//BEGIN BATCH
		batch.begin();
		board.draw(batch);
		circles.draw(batch);
		debugMessage.draw(batch, message, 10, 40);
		gem.draw(batch);
		batch.end();
		//END BATCH

		//BEGIN SHAPERENDERREREHRUYEIUY ///////PLS FOR DEER JESUS' SAKE ONLY USE FOR DEBUG!
		//		sr.begin(ShapeType.Filled);
		//		sr.setColor(Color.RED);
		//		sr.circle(circleX, circleY, 10);
		//		sr.end();

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










