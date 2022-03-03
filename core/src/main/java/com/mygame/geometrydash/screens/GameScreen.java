package com.mygame.geometrydash.screens;
import static com.mygame.geometrydash.extra.Utils.HEIGHT_SCREEN;

import static com.mygame.geometrydash.extra.Utils.USER_PINCHO;
import static com.mygame.geometrydash.extra.Utils.USER_PLAYER;
import static com.mygame.geometrydash.extra.Utils.WIDTH_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WORLD_HEIGHT;
import static com.mygame.geometrydash.extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygame.geometrydash.MainGame;
import com.mygame.geometrydash.actors.Obstaculo;
import com.mygame.geometrydash.actors.Obstaculo2;
import com.mygame.geometrydash.actors.Obstaculo3;
import com.mygame.geometrydash.actors.Obstaculo3Bloques;
import com.mygame.geometrydash.actors.ObstaculoBloque;
import com.mygame.geometrydash.actors.Player;
import com.mygame.geometrydash.actors.Spikes;

import controllers.Controller;


public class GameScreen extends BaseScreen implements ContactListener {
    Controller controller = new Controller(main);
    public Stage stage;
    private Image background;
    public World world;
    public Player player;

    public static final float ALTURA_OBS =1.72f;
    public Music music_bg;
    public Sound dead_sound;
    private final float SPAWN_TIME = 15f;
    private float time=0f;

    public static  Array<Obstaculo> arrayObs1;
    public static Array<Obstaculo2> arrayObs2;
    public static Array<Obstaculo3> arrayObs3;
    public static Array<ObstaculoBloque> arrayBloque;
    public static Array<Spikes> arraySpike;
    public static Array<Obstaculo3Bloques> array3blocs;


    private OrthographicCamera ortCamera;

    private OrthographicCamera fontCamera;
    private BitmapFont score;
    public static int scoreNum;

    public GameScreen(MainGame mainGame) {
        super(mainGame);

        this.world = new World(new Vector2(0, -12), true);
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage = new Stage(fitViewport);

        //this.world.setContactListener(this);

        arrayObs1 = new Array<>();
        arrayObs2 = new Array<>();
        arrayObs3 = new Array<>();
        arrayBloque = new Array<>();
        arraySpike = new Array<>();
        array3blocs = new Array<>();


        scoreNum=0;
        this.music_bg = this.main.assetManagment.getBGMUSIC();
        this.dead_sound=this.main.assetManagment.getDeathSound();
        this.ortCamera = (OrthographicCamera) this.stage.getCamera();

        setUpScore();

    }

    @Override
    public void show() {

        addBackground();
        addFloor();
        addPlayer();

        /*--CREO OBSTACULOS EN EL SHOW PARA QUE EL USUARIO NO TENGA QUE ESPERAR TANTO
         * --TIEMPO PARA QUE APARREZCAN DESDE EL RENDER, PORQUE ALTERO SU VALOR---*/

        controller.addObstaculosShow(this.stage,this.world,8.4f);

        this.music_bg.setLooping(true);
        this.music_bg.play();


    }


    private void addFloor() {
        BodyDef bd = new BodyDef();

        bd.position.x = 0;
        bd.position.y = 1.5f;
        bd.type = BodyDef.BodyType.StaticBody;

        Body oBody = world.createBody(bd);

        EdgeShape shape = new EdgeShape();
        shape.set(0,0,WORLD_WIDTH,0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;

        oBody.createFixture(fixture);

        shape.dispose();

    }
    private void addBackground() {
        this.background = new Image(main.assetManagment.getBackground());
        this.background.setPosition(0, 0);
        this.background.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage.addActor(this.background);
    }


    private void addPlayer() {
        TextureRegion player = main.assetManagment.getPlayer();
        this.player= new Player(this.world,player,new Vector2(1.4f,2.3f));
        this.stage.addActor(this.player);

    }

    public void setUpScore(){
        scoreNum=0;
        this.score = this.main.assetManagment.getFont();
        this.score.getData().scale(.6f);

        this.fontCamera = new OrthographicCamera();
        this.fontCamera.setToOrtho(false, WIDTH_SCREEN, HEIGHT_SCREEN);
        this.fontCamera.update();
    }


    private void addObstaculesRender(float delta) {
        if(player.state == Player.STATE_NORMAL){
                this.time += delta;
                if (this.time >= SPAWN_TIME) {
                    this.time -= SPAWN_TIME;
                    controller.addObstaculosBucle(this.stage,this.world, 9.6f);
                    Obstaculo.SPEED -= .12f; //cada "vuelta" aumento la velocidad de los obstáculos
                    scoreNum++; //cada "vuelta" del render aumento la puntuacion
                    int i = 4;
                    while (scoreNum >= i) {
                        i++;
                        if (i >= 5) {
                            //al aumentar la velocidad, cada vez es más dificil hacer los saltos porque no le da tiempo al personaje,
                            //asique aumento la gravedad para que pese más
                            this.world.setGravity(new Vector2(0, -13.4f));

                            if (i >= 7) {
                                //también aumento el salto del personaje, porque al pesar más llega más justo a los obstaculos y necesita saltar más alto
                                Player.JUMP_SPEED = 4f;
                                this.world.setGravity(new Vector2(0, -13.9f));

                                if(i >= 9){
                                    Player.JUMP_SPEED = 4.1f;
                                    this.world.setGravity(new Vector2(0, -14.2f));
                                }
                            }
                        }
                    }

                }
        }
    }


    @Override
    public void render(float delta) {

        //update(delta);
        addObstaculesRender(delta);
        this.stage.getBatch().setProjectionMatrix(ortCamera.combined);
        this.stage.act();

        this.world.step(delta, 6, 2);
        this.stage.draw();
        this.ortCamera.update();

        controller.endGame(this.player, this.stage);
        //this.debugRenderer.render(this.world, this.ortCamera.combined);

        /*METODO PARA ELIMINAR EL FIXTURE Y LE BODY Y NO SE QUEDE EN MEMORA CUANDO SALEN DE LA PANTALLA*/

        controller.removeOb1(this.world);
        controller.removeOb2(this.world);
        controller.removeOb3(this.world);
        controller.removeBloque(this.world);
        controller.removeSpike(this.world);
        controller.removeBloque3(this.world);


        this.stage.getBatch().setProjectionMatrix(this.fontCamera.combined);
        this.stage.getBatch().begin();
        this.score.draw(this.stage.getBatch(),""+scoreNum,WIDTH_SCREEN/2, 450);
        this.stage.getBatch().end();



    }

    private void update(float delta) {
        float dt = 0f;
        dt+=delta/1.2f;
        addObstaculesRender(dt);

    }


    @Override
    public void hide() {
        this.player.detach();
        this.player.remove();

        this.music_bg.stop();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }

    @Override
    public void pause() {
    }

    public boolean areColider(Contact contact, Object objA, Object objB){
        return (contact.getFixtureA().getUserData().equals(objA) && contact.getFixtureB().getUserData().equals(objB)) ||
                (contact.getFixtureA().getUserData().equals(objB) && contact.getFixtureB().getUserData().equals(objA));
    }

    @Override
    public void beginContact(Contact contact) {
        if(areColider(contact, USER_PLAYER, USER_PINCHO)){
            controller.endGame(this.player,this.stage);
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
