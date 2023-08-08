package knight.arkham.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

public class Brick extends GameObject{
    private boolean isDestroyed;
    private boolean setToDestroy;
    private final Sound hitSound;
    public Brick(Rectangle bounds, World world, String spritePath) {
        super(bounds, world, spritePath);
        hitSound = AssetsHelper.loadSound("okay.wav");
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update(){

        if (setToDestroy && !isDestroyed)
            destroyBrick();
    }

    private void destroyBrick() {

        actualWorld.destroyBody(body);
        isDestroyed = true;
    }


    @Override
    public void draw(Batch batch){

        if (!isDestroyed)
            super.draw(batch);
    }

    public void hitByTheBall() {
        setToDestroy = true;

        Hud.addScore();

        hitSound.play(0.6f);
    }

    @Override
    public void dispose() {
        hitSound.dispose();
        super.dispose();
    }
}
