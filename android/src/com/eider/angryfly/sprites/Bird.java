package com.eider.angryfly.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int MOVEMENT = 250;

    private static final  int GRAVITY = 35;

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;
    private Animation birdAnimation;
    private Texture texture;

    private Rectangle bounds;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("RedBird.png");
        //texture = new Texture("birdAnimation.png");
        //birdAnimation = new Animation(new TextureRegion(texture), 2,1f);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt){
        //birdAnimation.update(dt);
        if (position.y > 0){
            velocity.add(GRAVITY,0, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x,MOVEMENT * dt,0);

        if (position.x < 0 ){
            position.x = 0 ;
        }
        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);

    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }
    public void  jump(){
        velocity.x = -500;
        flap.play(0.9f);

    }
    public void dispose(){
        bird.dispose();
        flap.dispose();
    }
}
