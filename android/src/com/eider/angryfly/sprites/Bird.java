package com.eider.angryfly.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int MOVEMENT = 205;

    private static final  int GRAVITY = -25;

    private Vector3 position;
    private Vector3 velocity;

    private Animation birdAnimation;
    private Texture texture;

    private Rectangle bounds;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("animation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 2,2f);

        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        bounds = new Rectangle(x, y, texture.getWidth() / 2, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if (position.y > 0){
            velocity.add(0,GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);

        if (position.y < 0 ){
            position.y = 0 ;
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

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void  jump(){
        velocity.y= 400;
        flap.play(0.9f);
    }
    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}
