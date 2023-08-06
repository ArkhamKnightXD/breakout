package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.screens.GameScreen;
import knight.arkham.screens.MainMenuScreen;

public class Breakout extends Game {
    public static Breakout INSTANCE;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public int screenWidth;
    public int screenHeight;
    public Viewport viewport;
    public Box2DDebugRenderer debugRenderer;

    public Breakout() {

        INSTANCE = this;
    }

    @Override
    public void create() {

        camera = new OrthographicCamera();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        viewport = new FitViewport(screenWidth / 32f, screenHeight / 32f, camera);

        camera.position.set(screenWidth / 32f, screenHeight / 32f, 0);

        batch = new SpriteBatch();

        debugRenderer = new Box2DDebugRenderer();

        setScreen(new GameScreen());
    }


    public void manageExitTheGame() {

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }
}
