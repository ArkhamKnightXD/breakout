package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Ball extends GameObject {
    private final Vector2 velocity;
    private final GameScreen gameScreen;

    public Ball(Rectangle rectangle, GameScreen gameScreen) {

//  Le doy poca densidad, pues si le doy 1 esta empujará a mi player y enemy de lugar
        super(
            new Box2DBody(rectangle, 0.1f, gameScreen.getWorld(), ContactType.BALL),
            "images/white.png", 6
        );

        this.gameScreen = gameScreen;
        velocity = new Vector2(getRandomDirection(), getRandomDirection());
    }

    private float getRandomDirection(){

        return (Math.random() < 0.5) ? 1 : -1;
    }

    private void resetBallPosition(){
        velocity.set(getRandomDirection(), getRandomDirection());

        body.setTransform(1000/ PIXELS_PER_METER,600/ PIXELS_PER_METER,0);
    }

    public void update(){

        body.setLinearVelocity(velocity.x * actualSpeed, velocity.y * actualSpeed);

        if (getPixelPosition().x > 1450){
            gameScreen.getPlayer().score += 1;
            resetBallPosition();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R))
            resetBallPosition();
    }

    public void reverseVelocityX(){
        velocity.x *= -1;
    }

    public void reverseVelocityY(){
        velocity.y *= -1;
    }

    public void incrementXVelocity(){
        velocity.x *= 1.1f;
    }

    public void incrementYVelocity(){
        velocity.y *= 1.1f;
    }
}
