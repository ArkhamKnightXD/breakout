package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Ceiling extends GameObject {

    public Ceiling(World world) {
        super(new Rectangle(950, 952, 1024, 16), world, "images/wall.png");
    }

    @Override
    protected Body createBody() {
       return Box2DHelper.createBody(
           new Box2DBody(actualBounds, 0, actualWorld, this)
       );
    }
}
