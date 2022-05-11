package com.eider.angryfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.eider.angryfly.sprites.Bird;
import com.eider.angryfly.sprites.Tower;

public class PlayState extends State{

    private static final int TOWER_SPACING = 125;
    private static final int TOWER_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tower> towers;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(320,50);
        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth() /2.4), (float) (Gdx.graphics.getHeight()/2.7));
        bg = new Texture("fondo.png");
        towers = new Array<Tower>();

        for (int i = 1 ; i <= TOWER_COUNT ; i++){
            towers.add(new Tower(i *(TOWER_SPACING + Tower.TOWER_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        camera.position.y = bird.getPosition().y + 80;

        for (Tower tower: towers){
            if (camera.position.y - (camera.viewportWidth/2) >tower.getPosTopTower().y +tower.getTopTower().getWidth() ){
                tower.reposition(tower.getPosTopTower().y + ((Tower.TOWER_WIDTH + TOWER_SPACING) * TOWER_COUNT));
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth / 2),camera.position.y - (camera.viewportHeight / 2));
        spriteBatch.draw(bird.getBird(), bird.getPosition().x,bird.getPosition().y);
        for (Tower tower : towers){
            spriteBatch.draw(tower.getTopTower(), tower.getPosTopTower().x, tower.getPosTopTower().y);
            spriteBatch.draw(tower.getBottomTower(), tower.getPosBotTower().x, tower.getPosBotTower().y);
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
