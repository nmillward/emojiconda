package com.nickmillward.snake.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by nmillward on 10/7/16.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getSimpleName();
    public static final Assets instance = new Assets();

    public SnakeAssets snakeAssets;
    public FoodAssets foodAssets;

    private AssetManager assetManager;

    public Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        snakeAssets = new SnakeAssets(atlas);
        foodAssets = new FoodAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class SnakeAssets {

        public final AtlasRegion ios_cry_tears;
        public final AtlasRegion ios_grin;
        public final AtlasRegion ios_happy;
        public final AtlasRegion ios_happy_tears;
        public final AtlasRegion ios_heart;
        public final AtlasRegion ios_kiss;
        public final AtlasRegion ios_shades;
        public final AtlasRegion ios_shock;
        public final AtlasRegion ios_smirk;
        public final AtlasRegion ios_tongue;
        public final AtlasRegion ios_tongue_tease;
        public final AtlasRegion ios_tongue_wink;

        public SnakeAssets(TextureAtlas atlas) {
            ios_cry_tears = atlas.findRegion(Constants.IOS_CRY_TEARS);
            ios_grin = atlas.findRegion(Constants.IOS_GRIN);
            ios_happy = atlas.findRegion(Constants.IOS_HAPPY);
            ios_happy_tears = atlas.findRegion(Constants.IOS_HAPPY_CRY);
            ios_heart= atlas.findRegion(Constants.IOS_HEART);
            ios_kiss = atlas.findRegion(Constants.IOS_KISS);
            ios_shades = atlas.findRegion(Constants.IOS_SHADES);
            ios_shock = atlas.findRegion(Constants.IOS_SHOCK);
            ios_smirk = atlas.findRegion(Constants.IOS_SMIRK);
            ios_tongue = atlas.findRegion(Constants.IOS_TONGUE_HAPPY);
            ios_tongue_tease = atlas.findRegion(Constants.IOS_TONGUE_TEASE);
            ios_tongue_wink = atlas.findRegion(Constants.IOS_TONGUE_WINK);
        }

    }

    public class FoodAssets {

        public final AtlasRegion ios_beef;
        public final AtlasRegion ios_burger;
        public final AtlasRegion ios_cake;
        public final AtlasRegion ios_candybar;
        public final AtlasRegion ios_cookie;
        public final AtlasRegion ios_donut;
        public final AtlasRegion ios_drumstick;
        public final AtlasRegion ios_icecream;
        public final AtlasRegion ios_lollipop;
        public final AtlasRegion ios_pizza;

        public FoodAssets(TextureAtlas atlas) {
            ios_beef = atlas.findRegion(Constants.IOS_BEEF);
            ios_burger = atlas.findRegion(Constants.IOS_BURGER);
            ios_cake = atlas.findRegion(Constants.IOS_CAKE);
            ios_candybar = atlas.findRegion(Constants.IOS_CANDYBAR);
            ios_cookie = atlas.findRegion(Constants.IOS_COOKIE);
            ios_donut = atlas.findRegion(Constants.IOS_DONUT);
            ios_drumstick = atlas.findRegion(Constants.IOS_DRUMSTICK);
            ios_icecream = atlas.findRegion(Constants.IOS_ICECREAM);
            ios_lollipop = atlas.findRegion(Constants.IOS_LOLLIPOP);
            ios_pizza = atlas.findRegion(Constants.IOS_PIZZA);
        }

    }
}
