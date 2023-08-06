package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.screens.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Breakout extends Game {
    public static Breakout INSTANCE;
    public SpriteBatch batch;
    public int screenWidth;
    public int screenHeight;

    public Breakout() {

        INSTANCE = this;
    }

    @Override
    public void create() {

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        setScreen(new MainMenuScreen());
    }


    public void manageExitTheGame() {

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }
}
