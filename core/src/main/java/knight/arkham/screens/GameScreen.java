package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Breakout;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.Ball;
import knight.arkham.objects.Brick;
import knight.arkham.objects.Player;
import knight.arkham.objects.Wall;

import static knight.arkham.helpers.Constants.*;

public class GameScreen extends ScreenAdapter {

    private final Breakout game;
    private final Player player;
    private final Ball ball;
    private final Wall leftWall;
    private final Wall rightWall;
    private final Array<Brick> bricks;
    private final OrthographicCamera camera;
    private final World world;
    private final Sound winSound;
    private boolean isDebug;

    public GameScreen() {

        game = Breakout.INSTANCE;

        camera = game.camera;

        world = new World(new Vector2(0, 0), true);

        GameContactListener contactListener = new GameContactListener(this);

        world.setContactListener(contactListener);

        player = new Player(new Rectangle(700, 350, 64, 16), world);

        ball = new Ball(new Rectangle(1000,600, 16, 16), this);

        rightWall = new Wall(new Rectangle(1470,FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);
        leftWall = new Wall(new Rectangle(450,FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);

        winSound = AssetsHelper.loadSound("win.wav");

        bricks = createBricks();
    }

    private Array<Brick> createBricks() {
        int positionX;
        int positionY = 0;
        String spritePath;

        Array<Brick> temporalBricks = new Array<>();

        for (int i = 0; i < 8; i++){

            positionX = 0;

            if (i % 2 == 0)
                spritePath = "images/blue-brick.png";

            else
                spritePath = "images/purple-brick.png";

            for (int j = 0; j < 15; j++) {

                Brick actualBrick = new Brick(
                    new Rectangle(
                        515 + positionX,950 - positionY, 64, 20
                    ), world, spritePath
                );

                temporalBricks.add(actualBrick);
                positionX += 64;
            }

            positionY += 20;
        }

        return temporalBricks;
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update(){

        world.step(1 / 60f, 6, 2);

        player.update();
        ball.update();

        setGameOverScreen();

        game.manageExitTheGame();

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isDebug = !isDebug;
    }

    private void setGameOverScreen() {

        if (player.score > 10){

            winSound.play();
            game.setScreen(new MainMenuScreen());
        }
    }


    @Override
    public void render(float delta) {

        update();

        draw();

    }

    private void draw() {

        ScreenUtils.clear(0,0,0,0);

        if (!isDebug){
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();

            for (Brick brick : bricks)
                brick.draw(game.batch);

            player.draw(game.batch);
            ball.draw(game.batch);

            game.batch.end();
        }

        else
            game.debugRenderer.render(world, camera.combined);
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        rightWall.dispose();
        leftWall.dispose();
        player.dispose();
        ball.dispose();
    }

    public Ball getBall() {return ball;}

    public Player getPlayer() {return player;}

    public World getWorld() {return world;}
}
