package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CMYKW extends ApplicationAdapter implements ApplicationListener,
InputProcessor {
	public int SCREEN_WIDTH = 0;
	public int SCREEN_HEIGHT = 0;
	
	SpriteBatch batch;
	
	Array gemArray;
	
	Sprite[][] spriteArray;
	Sprite box;

	public CMYKW(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}

	@Override
	public void create() {
		
		batch = new SpriteBatch();
		
		gemArray = new Array();
		gemArray.printArray("Start");
		gemArray.printPosArray();
		
		FileHandle[] colors = new FileHandle[5];
		colors[0] = Gdx.files.internal("images/cyan.png");
		colors[1] = Gdx.files.internal("images/magenta.png");
		colors[2] = Gdx.files.internal("images/yellow.png");
		colors[3] = Gdx.files.internal("images/black.png");
		colors[4] = Gdx.files.internal("images/white.png");
		
		Random rand = new Random();
		
		box = new Sprite(new Texture(Gdx.files.internal("images/tronbox6.png")));
		box.setCenter(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		float boxPosX = box.getBoundingRectangle().getX();
		float boxPosY = box.getBoundingRectangle().getY();
		
		spriteArray = new Sprite[5][5];
		
		float gemsize = 25f;
		
		Sprite gem00 = new Sprite(new Texture(colors[rand.nextInt(5)]));
		gem00.setSize(gemsize, gemsize);
		float x0 = boxPosX + additive(box, 1);
		float y0 = boxPosY + additive(box, 1);
		gem00.setCenter(x0, y0);
		spriteArray[0][0] = gem00;
		
		Sprite gem01 = new Sprite(new Texture(colors[rand.nextInt(5)]));
		gem01.setSize(gemsize, gemsize);
		float y1 = boxPosY + additive(box, 3);
		gem01.setCenter(x0, y1);
		spriteArray[0][1] = gem01;
		
		
		
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		handleInput();
		
		batch.begin();
		box.draw(batch);
		
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				if(!gemArray.getGem(x, y).isNull)
					gemArray.getGem(x, y).draw(batch);;
			}
		}
		batch.end();
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
			gemArray.rotateLeft();
			gemArray.printArray("Rotate Left");
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			gemArray.rotateRight();
			gemArray.printArray("Rotate Right");
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			//gemArray.drop();
			gemArray.printArray("Drop");
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










