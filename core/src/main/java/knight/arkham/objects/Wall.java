package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Wall extends GameObject {

    public Wall(Rectangle bounds, World world) {
        super(bounds, world, "images/wall.png");
    }

    @Override
    protected Body createBody() {
       return Box2DHelper.createBody(
           new Box2DBody(actualBounds, 0, actualWorld, this)
       );
    }
}
