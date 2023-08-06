package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Ball extends GameObject {
    private final Vector2 velocity;

    public Ball(Rectangle bounds, World world) {
        super(bounds, world, "images/white.png", 6);

        velocity = new Vector2(getRandomDirection(), -1);
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0.1f, actualWorld, this)
        );
    }

    private float getRandomDirection(){

        return (Math.random() < 0.5) ? 1 : -1;
    }

    private void resetBallPosition(){
        velocity.set(getRandomDirection(), -1);

        body.setTransform(950/ PIXELS_PER_METER,750/ PIXELS_PER_METER,0);
    }

    public void update(){

        body.setLinearVelocity(velocity.x * actualSpeed, velocity.y * actualSpeed);

        if (getPixelPosition().y < 250)
            resetBallPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.R))
            resetBallPosition();
    }

    public void reverseVelocityX(){
        velocity.x *= -1;
    }

    public void reverseVelocityY(){velocity.y *= -1;}
}
