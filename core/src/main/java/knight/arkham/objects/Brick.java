package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;

public class Brick extends GameObject{

    private final World world;
    private boolean isDestroyed;
    private boolean setToDestroy;
    public Brick(Rectangle rectangle, World world, String spritePath) {
        super(
            new Box2DBody(rectangle, 0, world, ContactType.BRICK),
            spritePath, 0
        );
        this.world = world;
    }

    public void update(){

        if (setToDestroy)
            destroyBrick();
    }

    public void draw(Batch batch){

        if (!isDestroyed)
            super.draw(batch);
    }

    private void destroyBrick() {

        world.destroyBody(body);
        isDestroyed = true;
    }

    public void hitByBall() {
        setToDestroy = true;
    }
}
