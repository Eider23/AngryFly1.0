package com.eider.angryfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tower {

    public static final int TOWER_WIDTH = 300;

    private static final int FLUCTUATION = 380;
    private static final int TOWER_GAP = 200;
    private static final int LOWEST_OPENING = 300;

    private Texture topTower;
    private Texture bottomTower;

    private Vector2 posTopTower;
    private Vector2 posBotTower;

    private Random rand;

    private Rectangle boundsTop;
    private Rectangle boundsBot;

    public  Tower(float x){
        topTower = new Texture("obstaculoSuperior.png");
        bottomTower = new Texture("obstaculoInferior.png");

        rand = new Random();

        posTopTower = new Vector2(x, rand.nextInt(FLUCTUATION) + TOWER_GAP + LOWEST_OPENING );
        posBotTower = new Vector2(x, posTopTower.y - TOWER_GAP - bottomTower.getHeight());


        boundsTop = new Rectangle(posTopTower.x, posTopTower.y, topTower.getWidth(), topTower.getHeight());
        boundsBot = new Rectangle(posBotTower.x, posBotTower.y, bottomTower.getWidth(), bottomTower.getHeight());

    }

    public void reposition(float x){
        posTopTower.set(x, rand.nextInt(FLUCTUATION) + TOWER_GAP + LOWEST_OPENING);
        posBotTower.set(x, posTopTower.y - TOWER_GAP - bottomTower.getHeight());

        boundsTop.setPosition(posTopTower.x, posTopTower.y);
        boundsBot.setPosition(posBotTower.x, posBotTower.y);

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

    public void dispose(){
        topTower.dispose();
        bottomTower.dispose();
    }
}
