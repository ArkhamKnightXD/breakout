package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Ball extends GameObject {
    public static int livesQuantity;
    private final float speed;
    private final Vector2 velocity;

    public Ball(Rectangle bounds, World world) {
        super(bounds, world, "images/ball.png", "fall.wav");
        speed = 6;
        velocity = new Vector2(getRandomDirection(), -1);
        livesQuantity = 2;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBallBody(
            new Box2DBody(actualBounds, 0.1f, actualWorld, this)
        );
    }

    private float getRandomDirection(){

        return (Math.random() < 0.5) ? 1 : -1;
    }

    private void resetBallPosition(){
        velocity.set(getRandomDirection(), -1);

        body.setTransform(950/ PIXELS_PER_METER,700/ PIXELS_PER_METER,0);
    }

    public void update(){

        body.setLinearVelocity(velocity.x * speed, velocity.y * speed);

        if (livesQuantity > -1 && getPixelPosition().y < 200){

            //Maybe there is a way to avoid using this if.
            if (livesQuantity > 0){
                collisionSound.play(0.5f);
                resetBallPosition();
            }

            Hud.takeAvailableBalls();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R))
            resetBallPosition();
    }

    private Vector2 getPixelPosition() {
        return new Vector2(body.getPosition().x * PIXELS_PER_METER, body.getPosition().y * PIXELS_PER_METER);
    }

    public void reverseVelocityX(){
        velocity.x *= -1;
    }

    public void reverseVelocityY(){velocity.y *= -1;}
}
