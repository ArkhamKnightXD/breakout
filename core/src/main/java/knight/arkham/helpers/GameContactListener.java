package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.screens.GameScreen;

public class GameContactListener implements ContactListener {

    private final GameScreen gameScreen;

    public GameContactListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Gdx.app.log("fixture A:", fixtureA.getUserData().toString());
        Gdx.app.log("fixture B:", fixtureB.getUserData().toString());

        if (fixtureA.getUserData() == ContactType.BALL || fixtureB.getUserData() == ContactType.BALL) {

            if (fixtureA.getUserData() == ContactType.PLAYER || fixtureB.getUserData() == ContactType.PLAYER) {
                gameScreen.getBall().reverseVelocityY();
                gameScreen.getBall().incrementYVelocity();
            }

            else if (fixtureA.getUserData() == ContactType.BRICK || fixtureB.getUserData() == ContactType.BRICK){
                gameScreen.getBall().reverseVelocityY();
            }

            else
                gameScreen.getBall().reverseVelocityX();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
