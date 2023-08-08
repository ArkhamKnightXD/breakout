package knight.arkham.objects.structures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Wall extends Structure {

    public Wall(Rectangle bounds, World world) {
        super(bounds, world);
    }

    @Override
    protected void createBody() {
        Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }
}
