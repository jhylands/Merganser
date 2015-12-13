package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
	
	final MyGdxGame game;
	Button play, howtoplay, quit;
	
	public MainMenuScreen(final MyGdxGame gameInst) {
		game = gameInst;
		play = new Button(game.play, 225, 100, 50, 50);
		howtoplay = new Button(game.how, 210, 50, 100, 50);
		quit = new Button(game.quit, 225, 0, 50, 50);
	}
	
	public void update(){
		if (play.isPressed()){
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
//		if (howtoplay.isPressed()){
//			this.dispose();
//		}
		if (quit.isPressed()){
			Gdx.app.exit();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f,0f,0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		play.draw();
		howtoplay.draw();
		quit.draw();
		
		update();
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
