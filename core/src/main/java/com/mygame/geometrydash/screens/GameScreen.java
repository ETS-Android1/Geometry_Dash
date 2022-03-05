package com.mygame.geometrydash.screens;
import static com.mygame.geometrydash.extra.Utils.EXPLOTION;
import static com.mygame.geometrydash.extra.Utils.HEIGHT_SCREEN;

import static com.mygame.geometrydash.extra.Utils.SUELO;
import static com.mygame.geometrydash.extra.Utils.USER_BLOQUE;
import static com.mygame.geometrydash.extra.Utils.USER_PINCHO;
import static com.mygame.geometrydash.extra.Utils.USER_PLAYER;
import static com.mygame.geometrydash.extra.Utils.WIDTH_SCREEN;
import static com.mygame.geometrydash.extra.Utils.WORLD_HEIGHT;
import static com.mygame.geometrydash.extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
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

    //altura media donde coloco los obstaculos en y (como son imagenes recortadas a algunos les tengo que sumar
    //un poco, dependiendo del obstaculo)
    public static final float ALTURA_OBS =1.72f;

    public Music music_bg; //musica de fondo
    public Sound dead_sound; //sonido cuando muere pos salir de la pantalla
    public Sound explosion_sound; //sonido cuando cuando colisiona

    private final float SPAWN_TIME = 19f; //cada cuanto tiempo va a tardar el render en generar obstaculos

    private float time=0f;//donde voy a almacenar el tiempo que pase




    /*ARRAYS DE CADA TIPO DE OBSTACULO PARA MÁS ADELANTE ELIMINARLOS*/
    public static  Array<Obstaculo> arrayObs1;
    public static Array<Obstaculo2> arrayObs2;
    public static Array<Obstaculo3> arrayObs3;
    public static Array<ObstaculoBloque> arrayBloque;
    public static Array<Spikes> arraySpike;
    public static Array<Obstaculo3Bloques> array3blocs;


    private OrthographicCamera ortCamera; //camara para el mundo

    private OrthographicCamera fontCamera; //camara para proyectar la puntuacion

    private BitmapFont score; // donde voy a almacenar la fuenta de letra para la puntuacion

    public static int scoreNum; //variable para puntuacion


    Animation<TextureRegion> animation;
    private SpriteBatch spriteBatch;
    private float deltaTime=0f;

    public GameScreen(MainGame mainGame) {
        super(mainGame);

        this.world = new World(new Vector2(0, -12), true);
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        //fitviewport de adapta a la pantalla, añadiendo barras negras en los laterales de la pantalla si es necesario

        this.stage = new Stage(fitViewport);

        this.world.setContactListener(this); //manejar colisiones

        arrayObs1 = new Array<>();
        arrayObs2 = new Array<>();
        arrayObs3 = new Array<>();
        arrayBloque = new Array<>();
        arraySpike = new Array<>();
        array3blocs = new Array<>();


        spriteBatch = new SpriteBatch();
        scoreNum=0;

        this.music_bg = this.main.assetManagment.getBGMUSIC();
        this.dead_sound=this.main.assetManagment.getDeathSound();
        this.explosion_sound=this.main.assetManagment.getExplosionSound();

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

        controller.addObstaculosShow(this.stage,this.world,9f);

        animation = main.assetManagment.getAnimationExplotion();

        spriteBatch = new SpriteBatch();

        this.music_bg.setLooping(true);
        this.music_bg.play();


    }


    private void addFloor() {
        BodyDef bd = new BodyDef();

        bd.position.x = 0;
        bd.position.y = 1.5f;
        bd.type = BodyDef.BodyType.StaticBody; //no le va a afectar la fisica y no se va a mover

        Body oBody = world.createBody(bd);

        EdgeShape shape = new EdgeShape(); //el suelo es una linea
        //va a ocupar toda la pantalla en x
        shape.set(0,0,WORLD_WIDTH,0);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;

        //para poder ponerle al fixture el userData
        Fixture suelo = oBody.createFixture(fixture);
        suelo.setUserData(SUELO);
        shape.dispose();

    }
    private void addBackground() {
        this.background = new Image(main.assetManagment.getBackground());
        this.background.setPosition(0, 0);
        this.background.setSize(WORLD_WIDTH, WORLD_HEIGHT); //para que ocupe toda la pantalla
        this.stage.addActor(this.background);
    }


    private void addPlayer() {
        TextureRegion player = main.assetManagment.getPlayer();
        this.player= new Player(this.world,player,new Vector2(1.4f,2.3f));
        this.stage.addActor(this.player);

    }

    public void setUpScore(){

        this.score = this.main.assetManagment.getFont(); //obtengo la fuente con assetManager
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
                    //creo todos los obstaculos en la clase Controller para que esta actividad no quede tan grande
                    controller.addObstaculosBucle(this.stage,this.world, 10f); //se van a generar fuera de la pnatalla(10)
                    Obstaculo.SPEED -= .12f; //cada "vuelta" aumento la velocidad de los obstáculos
                    scoreNum++; //cada "vuelta" del render aumento la puntuacion
                    int i = 3;
                    while (scoreNum >= i) {
                        i++;
                        if (i >= 4) {
                            //al aumentar la velocidad, cada vez es más dificil hacer los saltos porque no le da tiempo al personaje,
                            //asique aumento la gravedad para que pese más
                            this.world.setGravity(new Vector2(0, -13.4f));

                            if (i >= 6) {
                                //también aumento el salto del personaje, porque al pesar más llega más justo a los obstaculos y necesita saltar más alto
                                Player.JUMP_SPEED = 4f;
                                this.world.setGravity(new Vector2(0, -13.9f));

                                if(i >= 8){
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

        //metodo donde voy a crear los obstaculos
        addObstaculesRender(delta);
        this.stage.getBatch().setProjectionMatrix(ortCamera.combined);
        this.stage.act();

        this.world.step(delta, 6, 2);
        this.stage.draw();
        this.ortCamera.update();


        //METODO PARA LANZAR EL GAMEOVERSCREEN SI EL PERSONAJE SALE DE LA PANTALLA
        controller.endGame(this.player, this.stage);


        /*METODO PARA ELIMINAR EL FIXTURE Y EL BODY Y NO SE QUEDE EN MEMORA CUANDO SALEN DE LA PANTALLA*/
        controller.removeOb1(this.world);
        controller.removeOb2(this.world);
        controller.removeOb3(this.world);
        controller.removeBloque(this.world);
        controller.removeSpike(this.world);
        controller.removeBloque3(this.world);




        this.stage.getBatch().setProjectionMatrix(this.fontCamera.combined);
        this.stage.getBatch().begin();
        //POSICION DONDE VA A ESTAR EL SCORE
        this.score.draw(this.stage.getBatch(),""+scoreNum,WIDTH_SCREEN/2, 450);
        this.stage.getBatch().end();



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

    //metodo para comprobar quien ha chocado
    public boolean areColider(Contact contact, Object objA, Object objB){
        return (contact.getFixtureA().getUserData().equals(objA) && contact.getFixtureB().getUserData().equals(objB)) ||
                (contact.getFixtureA().getUserData().equals(objB) && contact.getFixtureB().getUserData().equals(objA));
    }

    @Override
    public void beginContact(Contact contact) {
        //cuando el personaje choque con el pincho, lanza el gameover
        if(areColider(contact,USER_PLAYER, USER_PINCHO)){

            controller.endGameConctact(this.player,this.stage);
            //si el personaje esta encima de un obstaculo o el suelo, puede saltar
        }else if(areColider(contact,USER_PLAYER,USER_BLOQUE) || areColider(contact,USER_PLAYER,SUELO)){
            player.canJump = true;
        }

    }

    @Override
    public void endContact(Contact contact) {
        //una vez perdido el contatco con el suelo o un obstaculo, es decir, está en el aire, no puede saltar
        if(areColider(contact,USER_PLAYER,USER_BLOQUE) || areColider(contact,USER_PLAYER,SUELO)){
            player.canJump = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
