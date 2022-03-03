package com.mygame.geometrydash.actors;



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

public class Spikes  extends Actor {



    private TextureRegion obstaculo;
    private Body bodyObs;
    private Fixture fixtureObs;
    private World world;

    public Spikes(World world, TextureRegion obstaculo,float x, float y) {
        this.obstaculo = obstaculo;
        this.world = world;
        createBodyObst(x,y);


    }

    private void createBodyObst(float x, float y) {
        BodyDef def = new BodyDef();
        def.position.set(new Vector2(x,y-.04f));
        def.type = BodyDef.BodyType.KinematicBody;
        bodyObs = world.createBody(def);


        bodyObs.setLinearVelocity(Obstaculo.SPEED,0);


        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.16f,-0.16f);
        vertices[1] = new Vector2(0.16f,-0.16f);
        vertices[2] = new Vector2(0,0.12f);
        box.set(vertices);

        this.fixtureObs = bodyObs.createFixture(box,9);
        this.fixtureObs.setUserData(Utils.USER_PINCHO);
        box.dispose();

        setPosition(x, y);

        setSize(.58f,.6f);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyObs.getPosition().x-.28f, bodyObs.getPosition().y-.3f);
        batch.draw(this.obstaculo,getX(),getY(),getWidth(),getHeight()-.06f );
    }
    public void detach(){
        bodyObs.destroyFixture(fixtureObs);
        world.destroyBody(bodyObs);

    }

    public float getPosition(){
        return this.bodyObs.getPosition().x;
    }

    public void stopObs(){
        this.bodyObs.setLinearVelocity(0,0);

    }

    public boolean isOutOfScreen(){
        return this.bodyObs.getPosition().x <= -.8f;
    }
}
