package com.mygame.geometrydash.actors;

import static com.mygame.geometrydash.extra.Utils.USER_BLOQUE;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstaculo  extends Actor {
    //ancho y alto de los obstaculos de un bloque

    //TODOS LOS OBSTACULOS SON KYNEMATIC PORQUE SE VAN A MOVER Y NO LES AFECTA LA FISICA
    public static final float OBS_WIDTH = .5f;
    public static final float OBS_HEIGHT = .5f;

    //velocidad en x de todos los obstaculos (se mueven hacia la izquierda)
    public static  float SPEED = -2.2f;

    private TextureRegion obstaculo;
    private Body bodyObs;
    private Fixture fixtureObs;

    private World world;

    public Obstaculo(World world, TextureRegion obstaculo, Vector2 position) {
        this.obstaculo = obstaculo;
        this.world = world;
        createBodyObst(position);
        createFixtureObs();

    }


    private void createBodyObst(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        bodyObs = world.createBody(def);

        bodyObs.setLinearVelocity(SPEED,0);

    }


    private void createFixtureObs() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(OBS_WIDTH/2,OBS_HEIGHT/2);
        this.fixtureObs = bodyObs.createFixture(box,9);
        this.fixtureObs.setUserData(USER_BLOQUE);
        box.dispose();


    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyObs.getPosition().x, bodyObs.getPosition().y);
        batch.draw(this.obstaculo,bodyObs.getPosition().x - OBS_WIDTH/2,bodyObs.getPosition().y - OBS_HEIGHT/2
        ,OBS_WIDTH,OBS_HEIGHT);

    }


    //metodo para destuir el fixture y el body
    public void detach(){
        bodyObs.destroyFixture(fixtureObs);
        world.destroyBody(bodyObs);

    }

    //metodo para parar los obstaculos una vez salga de la pantalla
    public void stopObs(){
        bodyObs.setLinearVelocity(0,0);

    }

    //metodo para obtener su posicion en x

    public float getPosition(){
        return this.bodyObs.getPosition().x;
    }

    //metodo para saber si se ha salido de la pantalla

    public boolean isOutOfScreen(){
        return this.bodyObs.getPosition().x <= -.8f;
    }
}
