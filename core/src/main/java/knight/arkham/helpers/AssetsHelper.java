package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsHelper {

    public static Sound loadSound(String filenamePath){
        return Gdx.audio.newSound(Gdx.files.internal("sounds/"+ filenamePath));
    }

    public static Skin loadUiSkin() {

        final Skin skin;
        //This method create an assetManager every time that is call. This could inefficient,
        // I should try to find a better way to load the uiSkin.
        AssetManager assetManager = new AssetManager();

        AssetDescriptor<Skin> uiSkin = new AssetDescriptor<>("images/ui/uiskin.json", Skin.class, new SkinLoader.SkinParameter("images/ui/uiskin.atlas"));

        assetManager.load(uiSkin);

        assetManager.finishLoading();

        skin = assetManager.get(uiSkin);
        return skin;
    }
}
