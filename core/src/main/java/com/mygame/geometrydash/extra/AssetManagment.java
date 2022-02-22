package com.mygame.geometrydash.extra;

import static com.mygame.geometrydash.extra.Utils.ATLAS_MAP;
import static com.mygame.geometrydash.extra.Utils.BACKGROUND;
import static com.mygame.geometrydash.extra.Utils.BGMUSIC;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO1;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO2;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO3;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO_BLOQUE;
import static com.mygame.geometrydash.extra.Utils.PANTALLA_INICIO;
import static com.mygame.geometrydash.extra.Utils.PLAYER;
import static com.mygame.geometrydash.extra.Utils.PLAY_INICIO;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.geometrydash.actors.Obstaculo2;

public class AssetManagment {
    private AssetManager assetManager;
    private TextureAtlas textureAtltas;


    private static final GlyphLayout glyphLayout = new GlyphLayout();
    public static BitmapFont font;

    public AssetManagment(){
        this.assetManager = new AssetManager();

        assetManager.load(ATLAS_MAP, TextureAtlas.class);
        assetManager.load(BGMUSIC, Music.class);
        assetManager.finishLoading();
        textureAtltas = assetManager.get(ATLAS_MAP);

        font = new BitmapFont();
        font.getData().scale(5f);

    }

    public TextureRegion getBackground (){
        return this.textureAtltas.findRegion(BACKGROUND);
    }

    public TextureRegion getPlayer (){ return this.textureAtltas.findRegion(PLAYER); }

    public TextureRegion getPantallaInicio(){ return  this.textureAtltas.findRegion(PANTALLA_INICIO); }

    public TextureRegion getBotonPlay (){ return this.textureAtltas.findRegion(PLAY_INICIO); }

    public TextureRegion getObs1(){return this.textureAtltas.findRegion(OBSTACULO1);}

    public TextureRegion getObs2(){return this.textureAtltas.findRegion(OBSTACULO2);}

    public TextureRegion getObs3(){return this.textureAtltas.findRegion(OBSTACULO3);}

    public TextureRegion getBloque(){return this.textureAtltas.findRegion(OBSTACULO_BLOQUE);}

    public Music getBGMUSIC(){return this.assetManager.get(BGMUSIC);}






    public static float getTextWidth(String text){
        glyphLayout.setText(font,text);
        return glyphLayout.width;
    }
}
