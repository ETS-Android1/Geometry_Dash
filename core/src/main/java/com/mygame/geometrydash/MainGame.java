package com.mygame.geometrydash;

import com.badlogic.gdx.Game;
import com.mygame.geometrydash.extra.AssetManagment;
import com.mygame.geometrydash.screens.GameScreen;
import com.mygame.geometrydash.screens.HomeScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
	public GameScreen gameScreen;
	public HomeScreen homeScreen;
	public AssetManagment assetManagment;

	@Override
	public void create() {
		this.assetManagment = new AssetManagment();
		this.homeScreen = new HomeScreen(this);
		this.gameScreen = new GameScreen(this);
		setScreen(this.gameScreen);
	}


}