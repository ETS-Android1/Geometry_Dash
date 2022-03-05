package com.mygame.geometrydash.actors;


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
import com.mygame.geometrydash.extra.Utils;

public class Player extends Actor{

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DEAD = 1;
    public static  float JUMP_SPEED = 3.95f; //impulso vertical del personaje

    public int state;

    private TextureRegion player;
    private Vector2 position;
    private World world;
    private Body body;
    private Fixture fixture;
    public boolean canJump=false;

    public Player(World world, TextureRegion player, Vector2 position){
        this.world = world;
        this.player = player;
        this.position = new Vector2(position);
        state = STATE_NORMAL;

        createPlayer();
    }

    private void createPlayer() {
        BodyDef bd = new BodyDef();
        bd.position.set(position);
        bd.type = BodyDef.BodyType.DynamicBody; //dinamico porque le va a afectar la fisica
        this.body = this.world.createBody(bd);
        //this.body.setBullet(true);

        this.body.setFixedRotation(true); //para que no pueda girar el cuerpo fisico y se quede estatico

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f,0.25f);//el personaje medirá, contando desde la mitad del cuadrado, .2 de alto y ancho

        this.fixture = this.body.createFixture(shape,9);
        this.fixture.setUserData(Utils.USER_PLAYER); //identificador

        this.fixture.setFriction(0f); //para que cuando salte encima de un obstaculo no frene por la friccion
        shape.dispose();

    }

    @Override
    public void act(float delta) {
        boolean jump = Gdx.input.justTouched();

        //comprobar si el personaje puede saltar o no (cuando esté en el aire no podrá hacerlo)
        if (jump && canJump && state == STATE_NORMAL) {
            this.body.setLinearVelocity(0, JUMP_SPEED);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x-0.25f, body.getPosition().y-0.25f);
        batch.draw(this.player,getX(),getY(),.42f,.42f);

    }

    //eliminar el fixture y el body
    public void detach(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
    }

    public void hurt(){
        this.state = STATE_DEAD;

    }

    //para saber si se ha salido de la pantalla
    public boolean isOutOfScreen(){
        return this.body.getPosition().x <=-.6f;
    }

}
