package com.radiantcreek;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameplayScreen implements Screen {

    //Object that draws all our sprite graphics: jpgs, pngs, etc.
    private SpriteBatch spriteBatch;

    //Object that draws shapes: rectanges, ovals, line, etc;
    private ShapeRenderer shapeRenderer;

    //Camera to view the virtual world
    private Camera camera;

    //Controls how the camera views the world
    //zoom in/out? Keep everything scaled?
    private Viewport viewport;

    private GameBoard gameBoard;


    /*
     * runs one time, at the very beginning
     * all setup should happen here
     * loading graphics
     * start values for variables
     */
    @Override
    public void show() {
        //OrthographicCamera is a 2D camera
        camera = new OrthographicCamera();

        //set the camera position to the middle of the world
        camera.position.set(1280/2, 720/2, 0);

        //required to save and update the camera changes above
        camera.update();

        //freeze my view to 1280x720, no matter the resolution of the window the camera 
        //will always show the same amount of world space
        viewport = new FitViewport(1280, 720, camera);

        //empty initialization of object that will draw graphics for us
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //I just know that this was the solution to an annoying problem I had
        shapeRenderer.setAutoShapeType(true);

        gameBoard = new GameBoard(this);

    }


    /*
     * this method runs as fast as it can (or as fast as it can for a set FPS)
     * repeatedly, constantly looped
     * things to include in this method:
     * process user input
     * AI
     * Draw all graphics
     */
    @Override
    public void render(float delta) {
        //get player input
        //process player input, AI
        //all drawings of shapes MUST go between begin/end
        shapeRenderer.begin();
        shapeRenderer.end();

        //all drawing of graphics MUST be between begin/end
        spriteBatch.begin();
        gameBoard.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
    
}
