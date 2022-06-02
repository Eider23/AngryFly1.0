package com.eider.angryfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.eider.angryfly.sprites.Bird;
import com.eider.angryfly.sprites.Tower;

public class PlayState extends State{

    private static final int TOWER_SPACING = 250;
    private static final int TOWER_COUNT = 6;
    private static final int GROUND_Y_OFFSET = 670;

    private Bird bird;
    private Texture bg;

    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    //private Vector2 groundPos3;

    private Array<Tower> towers;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(100,640);
        camera.setToOrtho(false, (float) (Gdx.graphics.getWidth() /2.4), (float) (Gdx.graphics.getHeight()/2.7));
        bg = new Texture("fondo.png");
        ground = new Texture("suelo.png");
        groundPos1 = new Vector2(camera.position.y + camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.y + camera.viewportWidth / 2) - ground.getWidth(),GROUND_Y_OFFSET);
        //groundPos3 = new Vector2(((camera.position.y + camera.viewportWidth / 2) - ground.getWidth())+ ground.getWidth(), GROUND_Y_OFFSET);

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
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < towers.size; i++){
            Tower tower = towers.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tower.getPosTopTower().x + tower.getTopTower().getWidth()){
                tower.reposition(tower.getPosTopTower().x + ((Tower.TOWER_WIDTH + TOWER_SPACING) * TOWER_COUNT));
            }
            if (tower.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));

            }
        }

        if (bird.getPosition().x >= ground.getWidth() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth / 2), camera.position.y - (camera.viewportHeight / 2));
        spriteBatch.draw(bird.getBird(), bird.getPosition().x,bird.getPosition().y);

        for (Tower tower : towers){
            spriteBatch.draw(tower.getTopTower(), tower.getPosTopTower().x, tower.getPosTopTower().y);
            spriteBatch.draw(tower.getBottomTower(), tower.getPosBotTower().x, tower.getPosBotTower().y);
        }
        //spriteBatch.draw(ground, groundPos1.y, groundPos1.x);
        //spriteBatch.draw(ground, groundPos2.y, groundPos2.x);
        //spriteBatch.draw(ground, groundPos3.y, groundPos3.x);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        ground.dispose();
        bird.dispose();
        for (Tower tower: towers)
            tower.dispose();
        System.out.println("PLAY STATE DISPOSED");
    }

    private void updateGround(){
        if (camera.position.y -(camera.viewportHeight / 2) > groundPos1.y + ground.getHeight())
            groundPos1.add(0, ground.getHeight() * 2);

        if (camera.position.y -(camera.viewportHeight / 2) > groundPos2.y + ground.getHeight())
            groundPos2.add(0, ground.getHeight() * 2);

        //if (camera.position.x -(camera.viewportWidth / 2) > groundPos3.x + ground.getWidth())
            //groundPos3.add(0, ground.getWidth() * 2);
    }
}
