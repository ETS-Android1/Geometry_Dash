package com.mygame.geometrydash.screens;

import static com.mygame.geometrydash.extra.Utils.HEIGHT_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WIDTH_SCREEN;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygame.geometrydash.MainGame;


public class HomeScreen extends BaseScreen{

    private final Image geometrydash_home;
    private final Image button_play;
    private Stage stage;
    public OrthographicCamera cam;
    public MainGame game;


    public HomeScreen(MainGame mainGame) {
        super(mainGame);
        this.game=mainGame;

        stage = new Stage(new StretchViewport(WIDTH_SCREEN,HEIGHT_SCREEN));
        cam = new OrthographicCamera(WIDTH_SCREEN,HEIGHT_SCREEN);
        cam.position.set(WIDTH_SCREEN/2f, HEIGHT_SCREEN/2f,0);


        Image background = new Image(mainGame.assetManagment.getBackground());
        background.setPosition( 0, 0);

        stage.addActor(background);


        this.geometrydash_home = new Image(mainGame.assetManagment.getPantallaInicio());
        this.geometrydash_home.setPosition(WIDTH_SCREEN/2-geometrydash_home.getWidth()/2,270);


        this.button_play = new Image(mainGame.assetManagment.getBotonPlay());
        this.button_play.setPosition(WIDTH_SCREEN/2-button_play.getWidth()/2,180);


        stage.addActor(this.geometrydash_home);
        stage.addActor(this.button_play);

        ScreenGame();
    }




    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }


    private void ScreenGame() {
        if(Gdx.input.justTouched()){
            this.geometrydash_home.addAction(Actions.fadeOut(.2f));
            this.button_play.addAction(Actions.sequence(
                    Actions.fadeOut(.2f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            button_play.remove();
                            game.setScreen(new GameScreen(game));
                        }
                    })
            ));
        }
    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}
