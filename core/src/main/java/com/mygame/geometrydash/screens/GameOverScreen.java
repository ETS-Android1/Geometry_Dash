package com.mygame.geometrydash.screens;

import static com.mygame.geometrydash.extra.Utils.HEIGHT_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WIDTH_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WORLD_HEIGHT;
import static com.mygame.geometrydash.extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygame.geometrydash.MainGame;
import com.mygame.geometrydash.extra.AssetManagment;

public class GameOverScreen extends BaseScreen {

    Image background;
    AssetManagment assets;
    private Stage stage;
    public OrthographicCamera cam;
    private Sound inicio;
    private static float FRAME_TIME=1/4f; //tiempo de la animacion (el 4 es porque tengo 4 imagenes)
    Animation<TextureRegion> animation;
    private SpriteBatch spriteBatch;
    private float deltaTime = 0f;


    public GameOverScreen(MainGame mainGame) {
        super(mainGame);
        assets = new AssetManagment();
        this.stage = new Stage(new StretchViewport(WIDTH_SCREEN,HEIGHT_SCREEN));
        this.cam = new OrthographicCamera(WIDTH_SCREEN,HEIGHT_SCREEN);
        this.cam.position.set(WIDTH_SCREEN/2f, HEIGHT_SCREEN/2f,0); //coloco la camara en mitad de la pantalla

        inicio = mainGame.assetManagment.getLevelInicio();

    }

    @Override
    public void show() {
        //cargo las imagenes de la animaccion y las introduzco en el array e indico la duracion de la animacion(frame_time)
        animation = new Animation<TextureRegion>(FRAME_TIME, assets.textureAtltas.findRegions("gameover"));
        //animation.setFrameDuration(FRAME_TIME);
        spriteBatch = new SpriteBatch();

        drawBackgorund();

    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }


    @Override
    public void render(float delta) {
        cam.update();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();

        this.stage.act();
        this.stage.draw();

        deltaTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = animation.getKeyFrame(deltaTime,true);

        //posiciom y tamaño de la animacion
        spriteBatch.draw(frame,(WIDTH_SCREEN/2.6f),HEIGHT_SCREEN/2.4f,frame.getRegionWidth()/1.1f, frame.getRegionHeight()/1.1f);

        spriteBatch.end();

        if(Gdx.input.justTouched()){
            inicio.play();
            this.stage.addAction(
                    Actions.sequence(
                            Actions.delay(.6f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    background.remove();
                                    main.setScreen(new HomeScreen(main));

                                }
                            })

                    )
            );
        }
    }

    private void drawBackgorund() {
        background = new Image(main.assetManagment.getGameOverBackground());
        background.setSize(WIDTH_SCREEN,HEIGHT_SCREEN);
        this.stage.addActor(background);
    }

    @Override
    public void hide() {
    }


}
