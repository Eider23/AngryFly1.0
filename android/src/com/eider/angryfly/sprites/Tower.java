package com.eider.angryfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tower {

    public static final int TOWER_WIDTH = 52;

    private static final int FLUCTUATION = 450;
    private static final int TOWER_GAP = 950;
    private static final int LOWEST_OPENING = -200;

    private Texture topTower;
    private Texture bottomTower;

    private Vector2 posTopTower;
    private Vector2 posBotTower;

    private Random rand;

    private Rectangle boundsTop;
    private Rectangle boundsBot;

    public  Tower(float y){
        topTower = new Texture("obstaculoinferior.PNG");
        bottomTower = new Texture("obstaculosuperior.png");

        rand = new Random();

        posBotTower = new Vector2(rand.nextInt(FLUCTUATION) - TOWER_GAP - LOWEST_OPENING,y );
        posTopTower = new Vector2(posBotTower.x + TOWER_GAP + topTower.getHeight() , y);

        boundsBot = new Rectangle(posTopTower.x, posTopTower.y, topTower.getWidth(), topTower.getHeight());
        boundsTop = new Rectangle(posBotTower.x, posBotTower.y, bottomTower.getWidth(), bottomTower.getHeight());

    }

    public void reposition(float y){
        posBotTower.set(rand.nextInt(FLUCTUATION) - TOWER_GAP - LOWEST_OPENING,y);
        posTopTower.set(posBotTower.x + TOWER_GAP + topTower.getHeight() , y);

        boundsBot.setPosition(posBotTower.x, posBotTower.y);
        boundsTop.setPosition(posTopTower.x, posTopTower.y);

    }

    public boolean collides(Rectangle player){
        return  player.overlaps(boundsTop) || player.overlaps(boundsBot);

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
