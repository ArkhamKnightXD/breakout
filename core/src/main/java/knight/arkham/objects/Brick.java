package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

public class Brick extends GameObject {
    private boolean isDestroyed;
    private boolean setToDestroy;
    private final int brickPoints;
    private final Rectangle drawBounds;

    public Brick(int positionX, int positionY, World world, String spritePath, int brickPoints) {
        super(
            new Rectangle(
                515 + positionX,
                900 - positionY, 64, 20
            ), world, spritePath, "okay.wav"
        );
        this.brickPoints = brickPoints;
        drawBounds = getDrawBounds();
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update() {

        if (setToDestroy && !isDestroyed)
            destroyBrick();
    }

    private void destroyBrick() {

        actualWorld.destroyBody(body);
        isDestroyed = true;
    }

    @Override
    public void draw(Batch batch) {

        if (!isDestroyed)
            batch.draw(sprite, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
    }

    public void hitByTheBall() {
        setToDestroy = true;

        Hud.addScore(brickPoints);

        collisionSound.play(0.6f);
    }
}
