package com.mygame.geometrydash.screens;

import static com.mygame.geometrydash.extra.Utils.HEIGHT_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WIDTH_SCREEN;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygame.geometrydash.MainGame;

public class HomeScreen extends BaseScreen{

    Image geometrydash_home,button_play, background;

    private Stage stage;
    public OrthographicCamera cam;
    private Sound inicio;

    public HomeScreen(MainGame mainGame) {
        super(mainGame);

        this.stage = new Stage(new StretchViewport(WIDTH_SCREEN,HEIGHT_SCREEN));
        this.cam = new OrthographicCamera(WIDTH_SCREEN,HEIGHT_SCREEN);
        this.cam.position.set(WIDTH_SCREEN/2f, HEIGHT_SCREEN/2f,0); //coloco la camara en la mitad de la pantalla

        inicio = mainGame.assetManagment.getLevelInicio();


    }

    @Override
    public void show() {
        drawBackground();
    }

    private void drawBackground() {
        background = new Image(main.assetManagment.getBackground());
        background.setPosition( 0, 0);

        //las medidas con strecthViewport se ponen en pixeles
        geometrydash_home = new Image(main.assetManagment.getPantallaInicio());
        geometrydash_home.setPosition(WIDTH_SCREEN/2-geometrydash_home.getWidth()/2,280);


        button_play = new Image(main.assetManagment.getBotonPlay());
        button_play.setPosition(WIDTH_SCREEN/2-button_play.getWidth()/2f,180);


        this.stage.addActor(background);

        this.stage.addActor(geometrydash_home);
        this.stage.addActor(button_play);
    }


    @Override
    public void dispose() {
        this.stage.dispose();
    }


    @Override
    public void render(float delta) {
        cam.update();
        this.stage.act();
        this.stage.draw();

        //cuando el usuraio toca la pantalla:
        if (Gdx.input.justTouched()) {
            inicio.play(); //suena el sonido de iniciar nivel

            this.stage.addAction(
                    Actions.sequence(
                            Actions.delay(.5f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    //elimino las fotos
                                    background.remove();
                                    geometrydash_home.remove();
                                    button_play.remove();
                                    //lanzo el gamescreen
                                    main.setScreen(new GameScreen(main));
                                }
                            })

                    )
            );
        }

    }

    @Override
    public void hide() {

    }

}
