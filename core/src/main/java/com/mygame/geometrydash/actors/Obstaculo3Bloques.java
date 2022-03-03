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

public class Obstaculo3Bloques extends Actor{

    private static final float OBS_WIDTH = 1.5f;
    private static final float OBS_HEIGHT = .2f;
    private TextureRegion obstaculo;
    private Body bodyObs;
    private Fixture fixtureObs;

    private World world;

    public Obstaculo3Bloques(World world, TextureRegion obstaculo, Vector2 position) {
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
        bodyObs.setUserData(USER_BLOQUE);
        bodyObs.setLinearVelocity(Obstaculo.SPEED,0);
    }


    private void createFixtureObs() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(OBS_WIDTH/2,OBS_HEIGHT/2);
        this.fixtureObs = bodyObs.createFixture(box,9);

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
    public void detach(){
        bodyObs.destroyFixture(fixtureObs);
        world.destroyBody(bodyObs);

    }

    public void stopObs(){
        this.bodyObs.setLinearVelocity(0,0);

    }

    public float getPosition(){
        return this.bodyObs.getPosition().x;
    }

    public boolean isOutOfScreen(){
        return this.bodyObs.getPosition().x <= -1.6f;
    }
}

