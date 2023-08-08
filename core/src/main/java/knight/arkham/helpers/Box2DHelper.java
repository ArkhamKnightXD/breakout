package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Brick;
import knight.arkham.objects.structures.Ceiling;
import knight.arkham.objects.Player;

import static knight.arkham.helpers.Constants.*;

public class Box2DHelper {

    public static Body createBody(Box2DBody box2DBody) {

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(box2DBody.bounds.width / 2 / PIXELS_PER_METER, box2DBody.bounds.height / 2 / PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixtureDef.density = box2DBody.density;

        //For fixed levels collisions it is not necessary to define a maskBit for my objects.
        // Because the collisions with each one of the objects is very specific.
        if (box2DBody.userData instanceof Player)
            fixtureDef.filter.categoryBits = PLAYER_BIT;

        else if (box2DBody.userData instanceof Brick)
            fixtureDef.filter.categoryBits = BRICK_BIT;

        else if (box2DBody.userData instanceof Ceiling)
            fixtureDef.filter.categoryBits = CEILING_BIT;

        else
            fixtureDef.filter.categoryBits = WALL_BIT;

        Body body = createBox2DBodyByType(box2DBody);

        body.createFixture(fixtureDef).setUserData(box2DBody.userData);

        shape.dispose();

        return body;
    }

    public static Body createBallBody(Box2DBody box2DBody) {

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape circleShape = new CircleShape();

        circleShape.setRadius(8 / PIXELS_PER_METER);

        fixtureDef.shape = circleShape;

        fixtureDef.density = box2DBody.density;

        fixtureDef.filter.categoryBits = BALL_BIT;

        Body body = createBox2DBodyByType(box2DBody);

        body.createFixture(fixtureDef).setUserData(box2DBody.userData);

        circleShape.dispose();

        return body;
    }


    private static Body createBox2DBodyByType(Box2DBody box2DBody) {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = box2DBody.bodyType;

        bodyDef.position.set(box2DBody.bounds.x / PIXELS_PER_METER, box2DBody.bounds.y / PIXELS_PER_METER);

        bodyDef.fixedRotation = true;

        return box2DBody.world.createBody(bodyDef);
    }
}
