package com.eider.angryfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tower {

    public static final int TOWER_WIDTH = 52;

    private static final int FLUCTUATION = 150;
    private static final int TOWER_GAP = 950;
    private static final int LOWEST_OPENING = -200;

    private Texture topTower;
    private Texture bottomTower;

    private Vector2 posTopTower;
    private Vector2 posBotTower;

    private Random rand;

    public  Tower(float y){
        topTower = new Texture("obstaculoinferior.PNG");
        bottomTower = new Texture("obstaculosuperior.png");

        rand = new Random();

        posBotTower = new Vector2(rand.nextInt(FLUCTUATION) - TOWER_GAP - LOWEST_OPENING,y );
        posTopTower = new Vector2(posBotTower.x + TOWER_GAP + topTower.getHeight() , y);

    }

    public void reposition(float y){
        posBotTower.set(rand.nextInt(FLUCTUATION) - TOWER_GAP - LOWEST_OPENING,y);
        posTopTower.set(posBotTower.x + TOWER_GAP + topTower.getHeight() , y);

    }


    public Texture getTopTower() {
        return bottomTower;
    }

    public void setTopTower(Texture topTower) {
        this.bottomTower = bottomTower;
    }

    public Texture getBottomTower() {
        return topTower;
    }

    public void setBottomTower(Texture bottomTower) {
        this.topTower = topTower;
    }

    public Vector2 getPosTopTower() {
        return posBotTower;
    }

    public void setPosTopTower(Vector2 posTopTower) {
        this.posBotTower = posBotTower;
    }

    public Vector2 getPosBotTower() {
        return posTopTower;
    }

    public void setPosBotTower(Vector2 posBotTower) {
        this.posTopTower = posTopTower;
    }

}
