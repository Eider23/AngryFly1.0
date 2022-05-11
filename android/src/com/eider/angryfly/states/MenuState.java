package com.eider.angryfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("fondo.png");
        playButton = new Texture("botonplay.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();

        spriteBatch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );

        spriteBatch.draw(playButton, (Gdx.graphics.getWidth() / 2) - (playButton.getHeight() / 2), (Gdx.graphics.getHeight() / 2 - (playButton.getHeight() / 2)) );

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }

}
