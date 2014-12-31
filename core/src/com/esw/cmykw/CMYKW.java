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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.esw.Meta;


public class CMYKW extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;

	private SpriteBatch batch;
	private ShapeRenderer sr;

	private Texture boxTexture;
	private Box box;
	private Texture cyanTexture;
	private Texture magentaTexture;
	private Texture yellowTexture;
	private Texture blackTexture;
	private Texture whiteTexture;
	//private Box[] gemArray; //TAG: WORKING CLASS CODE

	private BitmapFont debugMessage;
	private String message = "Debug: ";

	private float deltaTime;
	private float debugClock;

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

		boxTexture = new Texture(Gdx.files.internal("images/tronbox2.png")); //Path is /android/assets/...
		box = new Box(boxTexture);
		//box.setSize(box.getHeight()-(SCREEN_HEIGHT * 0.2f), box.getWidth()-(SCREEN_HEIGHT * 0.2f)); //SCALING DO NOT USE YET
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

		cyanTexture = new Texture(Gdx.files.internal("images/cyan.png"));
		magentaTexture = new Texture(Gdx.files.internal("images/magenta.png"));
		yellowTexture = new Texture(Gdx.files.internal("images/yellow.png"));
		blackTexture = new Texture(Gdx.files.internal("images/black.png"));
		whiteTexture = new Texture(Gdx.files.internal("images/white.png"));
		//TODO Create an array of colors for the textures
		

		//TAG: WORKING CLASS CODE DO NOT DELETE YET
		{
			//		gemArray = new Box[5];
			//		
			//		//TODO Place random colors from color array into the gem array
			//		float gemsize = 80f;
			//		gemArray[0] = new Box(cyanTexture, gemsize, gemsize);
			//		gemArray[1] = new Box(magentaTexture, gemsize, gemsize);
			//		gemArray[2] = new Box(yellowTexture, gemsize, gemsize);
			//		gemArray[3] = new Box(whiteTexture, gemsize, gemsize);
			//		gemArray[4] = new Box(blackTexture, gemsize, gemsize);

			//		bounds = box.getBoundingRectangle(); //Gets the bounding rectangle of the box object
			//		gemX = bounds.getX(); //Gets the x of the rectangle at the lower left corner
			//		gemY = bounds.getY(); //Gets the y of the rectangle at the lower left corner


			//TODO (Alex) Create gem positioning algorithm
			//		gemX = gemX + (box.getWidth() * 0.10f);
			//		gemY = gemY + (box.getHeight() * 0.10f);
			//		
			//		gemArray[0].setCenter(gemX, gemY);
			//		
			//		gemX = gemX + ((box.getWidth() * 0.10f) + (box.getWidth() * 0.10f));
			//		gemY = gemY + ((box.getHeight() * 0.10f) + (box.getHeight() * 0.10f));
			//		
			//		gemArray[1].setCenter(gemX, gemY);
			//		
			//		gemX = gemX + ((box.getWidth() * 0.10f) + (box.getWidth() * 0.10f));
			//		gemY = gemY + ((box.getHeight() * 0.10f) + (box.getHeight() * 0.10f));
			//		
			//		gemArray[2].setCenter(gemX, gemY);
			//		
			//		gemX = gemX + ((box.getWidth() * 0.10f) + (box.getWidth() * 0.10f));
			//		gemY = gemY + ((box.getHeight() * 0.10f) + (box.getHeight() * 0.10f));
			//		
			//		gemArray[3].setCenter(gemX, gemY);
			//		
			//		gemX = gemX + ((box.getWidth() * 0.10f) + (box.getWidth() * 0.10f));
			//		gemY = gemY + ((box.getHeight() * 0.10f) + (box.getHeight() * 0.10f));
			//		
			//		gemArray[4].setCenter(gemX, gemY);
			//		
			//		Meta.println("" + box.getOriginX() + " " + box.getOriginY());
			//		gemArray[0].setOrigin(box.getOriginX(), box.getOriginY());
			//		gemArray[1].setOrigin(box.getOriginX(), box.getOriginY());
			//		gemArray[4].setOrigin(-box.getOriginX(), -box.getOriginY());
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

		if(!box.isRotating()) { //Block input if the box is rotating
			if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				message = "Debug: left key pressed";
				//Meta.println("Rotating Left"); //CONSOLE DEBUG
				box.rotateLeft = true;
				//				for(int i = 0; i < gemArray.length; i++) { //TAG: WORKING CLASS CODE
				//					gemArray[i].isRotatingLeft = true;
				//				}
				debugClock = 0;
			} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				message = "Debug: right key pressed";
				//Meta.println("Rotating Right"); //CONSOLE DEBUG
				box.rotateRight = true;
				//				for(int i = 0; i < gemArray.length; i++) { //TAG: WORKING CLASS CODE
				//					gemArray[i].isRotatingRight = true;
				//				}
				debugClock = 0;
			}
		}

		if(box.rotateLeft) { //Continue rotation (animate)
			box.rotateLeft(10f); //This number must be -10f because reasons!
			//			for(int i = 0; i < gemArray.length; i++) { //TAG: WORKING CLASS CODE
			//				gemArray[i].rotateLeft(10f);
			//			}
		} else if(box.rotateRight) { //Continue rotation (animate)
			box.rotateRight(-10f); //This number must be 10f because reasons!
			//			for(int i = 0; i < gemArray.length; i++) { //TAG: WORKING CLASS CODE
			//				gemArray[i].rotateRight(-10f);
			//			}
		}

		if(debugClock >= 2) { //About 2 seconds
			message = "Debug: "; //Resets the debug message
			debugClock = 0; //Resets the debug clock
		}

		//BEGIN BATCH
		batch.begin();
		box.draw(batch);
		////TODO (Alex) THIS CODE IS TERRIBLE! PLEASE FIX MEEEEE!!!
		//		gemArray[0].draw(batch); //TAG: WORKING CLASS CODE
		//		gemArray[1].draw(batch);
		//		gemArray[2].draw(batch);
		//		gemArray[3].draw(batch);
		//		gemArray[4].draw(batch);
		debugMessage.draw(batch, message, 10, 40);
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










