package com.mygame.geometrydash.extra;

import static com.mygame.geometrydash.extra.Utils.ATLAS_MAP;
import static com.mygame.geometrydash.extra.Utils.BACKGROUND;
import static com.mygame.geometrydash.extra.Utils.BGMUSIC;

import static com.mygame.geometrydash.extra.Utils.COIN;
import static com.mygame.geometrydash.extra.Utils.DEATH_SOUND;
import static com.mygame.geometrydash.extra.Utils.FONT_FNT;
import static com.mygame.geometrydash.extra.Utils.FONT_PNG;
import static com.mygame.geometrydash.extra.Utils.INICIO_SOUND;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO1;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO2;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO3;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO_3BLOQUEs;
import static com.mygame.geometrydash.extra.Utils.OBSTACULO_BLOQUE;
import static com.mygame.geometrydash.extra.Utils.PANTALLA_INICIO;
import static com.mygame.geometrydash.extra.Utils.PINCHO;
import static com.mygame.geometrydash.extra.Utils.PLAYER;
import static com.mygame.geometrydash.extra.Utils.PLAY_INICIO;
import static com.mygame.geometrydash.extra.Utils.gameover_background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManagment {
    public  AssetManager assetManager;
    public TextureAtlas textureAtltas;



    public AssetManagment(){
        this.assetManager = new AssetManager();

        assetManager.load(ATLAS_MAP, TextureAtlas.class);
        assetManager.load(BGMUSIC, Music.class);

        assetManager.load(DEATH_SOUND, Sound.class);
        assetManager.load(INICIO_SOUND, Sound.class);

        assetManager.finishLoading();

        textureAtltas = assetManager.get(ATLAS_MAP);



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

    public TextureRegion getThreeBlocs(){return this.textureAtltas.findRegion(OBSTACULO_3BLOQUEs);}

    public Music getBGMUSIC(){return this.assetManager.get(BGMUSIC);}



    public Sound getDeathSound(){return this.assetManager.get(DEATH_SOUND);}

    public Sound getLevelInicio(){return this.assetManager.get(INICIO_SOUND);}

    public TextureRegion getSpike(){return this.textureAtltas.findRegion(PINCHO);}


    public TextureRegion getCoin(){return this.textureAtltas.findRegion(COIN);}
    public TextureRegion getGameOverBackground(){return this.textureAtltas.findRegion(gameover_background);}





    public BitmapFont getFont(){
        return new BitmapFont(Gdx.files.internal(FONT_FNT), Gdx.files.internal(FONT_PNG), false);
    }


}
