package knight.arkham.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.Breakout;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.Ball;
import knight.arkham.objects.Player;
import knight.arkham.objects.Wall;

import static knight.arkham.helpers.Constants.*;

public class GameScreen extends ScreenAdapter {

    private final Breakout game;
    private final Player player;
    private final Ball ball;
    private final Wall leftWall;
    private final Wall rightWall;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final World world;
    private final TextureRegion[] scoreNumbers;
    private final Sound winSound;

    public GameScreen() {

        game = Breakout.INSTANCE;

        world = new World(new Vector2(0, 0), true);

        GameContactListener contactListener = new GameContactListener(this);

        world.setContactListener(contactListener);

        player = new Player(new Rectangle(700, 350, 64, 16), world);

        ball = new Ball(new Rectangle(1000,600, 16, 16), this);

        rightWall = new Wall(new Rectangle(1470,FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);
        leftWall = new Wall(new Rectangle(450,FULL_SCREEN_HEIGHT, 50, FULL_SCREEN_HEIGHT), world);

        camera = new OrthographicCamera();

        viewport = new FitViewport(BOX2D_FULL_SCREEN_WIDTH, BOX2D_FULL_SCREEN_HEIGHT, camera);

        camera.position.set(BOX2D_FULL_SCREEN_WIDTH, BOX2D_FULL_SCREEN_HEIGHT, 0);

        scoreNumbers = loadTextureSprite();

        winSound = AssetsHelper.loadSound("win.wav");
    }

    private TextureRegion[] loadTextureSprite(){

        Texture textureToSplit = new Texture("images/numbers.png");

        return TextureRegion.split(textureToSplit, textureToSplit.getWidth() / 10, textureToSplit.getHeight())[0];
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void update(){

        world.step(1 / 60f, 6, 2);

        player.update();
        ball.update();

        setGameOverScreen();

        game.manageExitTheGame();
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

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        rightWall.draw(game.batch);

        drawScoreNumbers(game.batch, player.score, 500);

        leftWall.draw(game.batch);

        player.draw(game.batch);
        ball.draw(game.batch);

        game.batch.end();
    }

    private void drawScoreNumbers(SpriteBatch batch, int scoreNumber, float positionX){

        final float width = 48;
        final float height = 64;

        if (scoreNumber < 10)
            batch.draw(scoreNumbers[scoreNumber], positionX/PIXELS_PER_METER, 900/PIXELS_PER_METER,
                width/PIXELS_PER_METER , height/PIXELS_PER_METER);

        else {

            batch.draw(scoreNumbers[Integer.parseInt(("" + scoreNumber).substring(0, 1))], positionX/PIXELS_PER_METER,
                900/PIXELS_PER_METER , width/PIXELS_PER_METER , height/PIXELS_PER_METER);

            batch.draw(scoreNumbers[Integer.parseInt(("" + scoreNumber).substring(1, 2))], positionX/PIXELS_PER_METER +20/PIXELS_PER_METER,
                900/PIXELS_PER_METER, width/PIXELS_PER_METER, height/PIXELS_PER_METER);
        }
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
