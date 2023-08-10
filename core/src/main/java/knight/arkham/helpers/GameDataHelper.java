package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import knight.arkham.objects.Player;

public class GameDataHelper {

    public static void saveHighScore(){

        Preferences preferences = Gdx.app.getPreferences("breakout-data");

        if (Player.score < loadHighScore())
            return;

        preferences.putInteger("playerScore", Player.score);

        preferences.flush();
    }

    public static int loadHighScore(){

        Preferences preferences = Gdx.app.getPreferences("breakout-data");

        return preferences.getInteger("playerScore");
    }
}
