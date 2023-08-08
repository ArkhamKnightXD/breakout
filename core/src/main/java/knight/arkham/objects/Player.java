package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Player extends GameObject {
    public static int score;
    private final float speed;
    private float velocityX;
    private final Sound hitSound;

    public Player(Rectangle bounds, World world) {
        super(bounds, world, "images/players.png");
        speed = 10;
        score = 0;
        hitSound = AssetsHelper.loadSound("drop.wav");
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 10, actualWorld, this)
        );
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velocityX = 1.5f;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            velocityX = -1.5f;

        body.setLinearVelocity(velocityX * speed, 0);

        velocityX = 0;
    }

    public void hitTheBall() {

        hitSound.play(0.6f);
    }

    @Override
    public void dispose() {
        hitSound.dispose();
        super.dispose();
    }
}
