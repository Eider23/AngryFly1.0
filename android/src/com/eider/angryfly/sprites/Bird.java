package com.eider.angryfly.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int MOVEMENT = 100;

    private static final  int GRAVITY = 30;

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("RedBird.png");
    }

    public void update(float dt){
        if (position.y > 0){
            velocity.add(GRAVITY,0, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x,MOVEMENT * dt,0);

        if (position.x < 0 ){
            position.x = 0 ;
        }
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }
    public void  jump(){
        velocity.x = -500;

    }
}
