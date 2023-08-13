package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.screens.MainMenuScreen;

public class Breakout extends Game {
    public static Breakout INSTANCE;
    public OrthographicCamera camera;
    public Viewport viewport;
    public int screenWidth;
    public int screenHeight;
    public AssetDescriptor<Skin> uiSkin;

    public Breakout() {

        INSTANCE = this;
    }

    @Override
    public void create() {

        camera = new OrthographicCamera();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        viewport = new FitViewport(screenWidth / 32f, screenHeight / 32f, camera);

        camera.position.set(screenWidth / 32f, screenHeight / 32f, 0);

        uiSkin = new AssetDescriptor<>("images/ui/uiskin.json", Skin.class, new SkinLoader.SkinParameter("images/ui/uiskin.atlas"));

        setScreen(new MainMenuScreen());
    }
}
