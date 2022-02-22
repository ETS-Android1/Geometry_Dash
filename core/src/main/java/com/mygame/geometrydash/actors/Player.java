package com.mygame.geometrydash.actors;

import static com.mygame.geometrydash.extra.Utils.USER_PLAYER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor{

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DEAD = 1;
    private static final float JUMP_SPEED = 3.9f;

    public int state;

    private TextureRegion player;
    private boolean jumping, alive;
    private Vector2 position;
    private World world;
    private Body body;
    private Fixture fixture;
    private float stateTime;

    public Player(World world, TextureRegion player, Vector2 position){
        this.world = world;
        this.player = player;
        this.position = new Vector2(position);
        state = STATE_NORMAL;
        stateTime = 0f;
        createPlayer();
    }

    private void createPlayer() {

        BodyDef bd = new BodyDef();

        bd.position.set(position);
        bd.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bd);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f,0.2f);

        this.fixture = this.body.createFixture(shape,9);

        this.body.setFixedRotation(true);
        this.body.setUserData(USER_PLAYER);
        this.body.setBullet(true);
        this.fixture.setFriction(0f);
        shape.dispose();

    }

    @Override
    public void act(float delta) {
        boolean jump = Gdx.input.justTouched();
        if(jump && state == STATE_NORMAL){
           this.body.setLinearVelocity(0,JUMP_SPEED);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x-0.2f, body.getPosition().y-0.2f);
        batch.draw(this.player,getX(),getY(),.4f,.4f);


        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void detach(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
    }

    /*public boolean canJump() {
        if (this.body.getPosition().y <= 0) {
            return false;
        }
        return true;
    }*/
}
