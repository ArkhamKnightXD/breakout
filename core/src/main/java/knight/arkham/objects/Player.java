package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;

public class Player extends GameObject {
    private float velocityX;
    public int score;

    public Player(Rectangle rectangle, World world) {
        super(
            new Box2DBody(rectangle, 10, world, ContactType.PLAYER),
            "images/players.png", 10
        );
        score = 0;
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velocityX = 1.5f;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            velocityX = -1.5f;

        body.setLinearVelocity(velocityX * actualSpeed, 0);

        velocityX = 0;
    }
}
