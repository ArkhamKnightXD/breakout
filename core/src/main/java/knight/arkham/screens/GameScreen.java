package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Breakout;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.helpers.GameDataHelper;
import knight.arkham.objects.*;
import knight.arkham.objects.structures.Ceiling;
import knight.arkham.objects.structures.Wall;
import knight.arkham.scenes.Hud;
import knight.arkham.scenes.PauseMenu;

import static knight.arkham.helpers.Constants.*;

public class GameScreen extends ScreenAdapter {
    private final Breakout game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final Hud hud;
    private final PauseMenu pauseMenu;
    private final World world;
    private final Player player;
    private final Ball ball;
    private final Ceiling ceiling;
    private final Wall leftWall;
    private final Wall rightWall;
    private final Array<Brick> bricks;
    private final Sound winSound;
    public static boolean isGamePaused;

    public GameScreen() {

        game = Breakout.INSTANCE;

        camera = game.camera;

        batch = new SpriteBatch();

        world = new World(new Vector2(0, 0), true);

        GameContactListener contactListener = new GameContactListener();

        world.setContactListener(contactListener);

        player = new Player(new Rectangle(950, 350, 64, 16), world);
        ball = new Ball(new Rectangle(950, 700, 20, 20), world);

        ceiling = new Ceiling(world);
        rightWall = new Wall(new Rectangle(1467, FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);
        leftWall = new Wall(new Rectangle(453, FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);

        winSound = AssetsHelper.loadSound("win.wav");

        bricks = createBricks();
        hud = new Hud();
        pauseMenu = new PauseMenu();

        isGamePaused = false;
    }

    private Array<Brick> createBricks() {
        int positionX;
        int positionY = 0;
        int brickPoints = 8;
        String spritePath;

        Array<Brick> temporalBricks = new Array<>();

        for (int i = 0; i < 8; i++) {

            positionX = 0;

            if (i % 2 == 0)
                spritePath = "images/light-blue-brick.png";

            else
                spritePath = "images/purple-brick.png";

            for (int j = 0; j < 15; j++) {

                temporalBricks.add(new Brick(positionX, positionY, world, spritePath, brickPoints));
                positionX += 64;
            }

            brickPoints--;
            positionY += 20;
        }

        return temporalBricks;
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update() {

        world.step(1 / 60f, 6, 2);

        player.update();
        ball.update();

        for (Brick brick : bricks)
            brick.update();
    }

    private void gameOver() {

        if (Player.score == 540) {

            winSound.play();
            GameDataHelper.saveHighScore();
            game.setScreen(new MainMenuScreen());
        } else if (Ball.livesQuantity < 0) {
            GameDataHelper.saveHighScore();
            game.setScreen(new MainMenuScreen());
        }
    }


    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 0);

        if (!isGamePaused) {
            update();
            draw();
        } else {

//            The act method is necessary if we want that the button react to the hover animation.
            pauseMenu.stage.act();
            pauseMenu.stage.draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isGamePaused = !isGamePaused;

        gameOver();
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        for (Brick brick : bricks)
            brick.draw(batch);

        player.draw(batch);
        ball.draw(batch);

        batch.end();

//            The drawing of the hud stage should be put outside our main spriteBatch.
        hud.stage.draw();
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        rightWall.dispose();
        leftWall.dispose();
        ceiling.dispose();
        player.dispose();
        ball.dispose();
        hud.dispose();
        pauseMenu.dispose();
        winSound.dispose();
        world.dispose();
        batch.dispose();

        for (Brick brick : bricks)
            brick.dispose();
    }
}
