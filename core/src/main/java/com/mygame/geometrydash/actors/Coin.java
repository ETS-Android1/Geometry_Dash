package com.mygame.geometrydash.actors;

import static com.mygame.geometrydash.extra.Utils.USER_COIN;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Coin extends Actor {
    private TextureRegion coin;
    public static final float OBS_WIDTH = .5f;
    public static final float OBS_HEIGHT = .5f;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_DEAD = 1;

    private int state;
    private Sound coinSound;
    private Vector2 position;

    private World world;

    private float stateTime;

    private Body body;

    private Fixture fixture;

    public Coin(World world,TextureRegion coin,Sound sound, Vector2 position) {
        this.coin = coin;
        this.position      = position;
        this.world         = world;
        this.coinSound = sound;

        state = STATE_NORMAL;
        createBody();
        createFixture();

    }


    public void createBody(){

        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(position);

        bodyDef.type = BodyDef.BodyType.KinematicBody;


        this.body = this.world.createBody(bodyDef);


    }


    public void createFixture(){

        CircleShape circle = new CircleShape();

        circle.setRadius(0.25f);

        //createFixture
        this.fixture = this.body.createFixture(circle,8);
        this.fixture.setUserData(USER_COIN);
        this.fixture.setSensor(true);
        //dispose
        circle.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(body.getPosition().x, body.getPosition().y);
        batch.draw(this.coin,body.getPosition().x - OBS_WIDTH/2,body.getPosition().y - OBS_HEIGHT/2
                ,OBS_WIDTH,OBS_HEIGHT);

    }

    public void stopObs(){
        this.body.setLinearVelocity(0,0);

    }

    public void detach(){

        //(body) destroyFixture
        this.body.destroyFixture(this.fixture);
        //(world) destroyBody
        this.world.destroyBody(this.body);

    }

}
