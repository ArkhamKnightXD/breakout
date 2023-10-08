package knight.arkham.objects.structures;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.AssetsHelper;

public abstract class Structure {
    protected final Rectangle actualBounds;
    protected final World actualWorld;
    private final Sound collisionSound;

    protected Structure(Rectangle bounds, World world) {
        actualBounds = bounds;
        actualWorld = world;
        collisionSound = AssetsHelper.loadSound("magic.wav");
        createBody();
    }

    protected abstract void createBody();

    public void hitByTheBall() {

        collisionSound.play();
    }

    public void dispose(){
        collisionSound.dispose();
    }
}
