package com.mygame.geometrydash.screens;


import static com.mygame.geometrydash.screens.GameScreen.ALTURA_OBS;
import static com.mygame.geometrydash.screens.GameScreen.array3blocs;
import static com.mygame.geometrydash.screens.GameScreen.arrayBloque;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs1;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs2;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs3;
import static com.mygame.geometrydash.screens.GameScreen.arraySpike;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygame.geometrydash.MainGame;
import com.mygame.geometrydash.actors.Coin;
import com.mygame.geometrydash.actors.Obstaculo;
import com.mygame.geometrydash.actors.Obstaculo2;
import com.mygame.geometrydash.actors.Obstaculo3;
import com.mygame.geometrydash.actors.Obstaculo3Bloques;
import com.mygame.geometrydash.actors.ObstaculoBloque;
import com.mygame.geometrydash.actors.Player;
import com.mygame.geometrydash.actors.Spikes;


public class Controller extends BaseScreen  {

    /*CLASE PARA CONTROLAR LA CREACION DE OBSTACULOS, ELIMINARLOS Y/O PARARLOS CUANDO ACABE EL JUEGO*/

    public Controller(MainGame mainGame) {
        super(mainGame);
    }

    /*------GENERAR OBSTACULAS SEGUN LA POSICION----------*/

    public void addObstaculosShow(Stage stage, float x){

        TextureRegion ob1 = main.assetManagment.getObs1();
        TextureRegion ob2 = main.assetManagment.getObs2();
        TextureRegion ob3 = main.assetManagment.getObs3();
        TextureRegion bloq = main.assetManagment.getBloque();
        TextureRegion spike = main.assetManagment.getSpike();
        TextureRegion blocs_3 = main.assetManagment.getThreeBlocs();


        Obstaculo obs1 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(x,ALTURA_OBS));
        arrayObs1.add(obs1);
        stage.addActor(obs1);



        Obstaculo2 obs2  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1.getPosition()+1.4f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2);
        stage.addActor(obs2);


        Obstaculo3 obs3 = new Obstaculo3(main.gameScreen.world,ob3,new Vector2(obs2.getPosition()+1.2f,ALTURA_OBS+.5f));
        arrayObs3.add(obs3);
        stage.addActor(obs3);

        Spikes pincho1 = new Spikes(main.gameScreen.world,spike,obs3.getPosition()+1.5f,ALTURA_OBS+.02f);
        arraySpike.add(pincho1);
        stage.addActor(pincho1);


        ObstaculoBloque bloque = new ObstaculoBloque(main.gameScreen.world,bloq,new Vector2(pincho1.getPosition()+1.5f,ALTURA_OBS+.05f));
        arrayBloque.add(bloque);
        stage.addActor( bloque);



        Obstaculo2 obs2_2  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(bloque.getPosition()+2.2f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_2);
        stage.addActor(obs2_2);

        Obstaculo3 obs3_2 = new Obstaculo3(main.gameScreen.world,ob3,new Vector2(obs2_2.getPosition()+1.5f,ALTURA_OBS+.5f));
        arrayObs3.add(obs3_2);
        stage.addActor(obs3_2);

        Obstaculo2 obs2_3  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs3_2.getPosition()+1.8f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_3);
        stage.addActor(obs2_3);

        Obstaculo obs1_2 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs2_3.getPosition()+1.6f,ALTURA_OBS+.8f));
        arrayObs1.add(obs1_2);
        stage.addActor(obs1_2);

        Spikes pincho2 = new Spikes(main.gameScreen.world,spike,obs1_2.getPosition()+1f,ALTURA_OBS+.02f);
        arraySpike.add(pincho2);
        stage.addActor(pincho2);

        Obstaculo obs1_3 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs1_2.getPosition()+1.7f,ALTURA_OBS+1.2f));
        arrayObs1.add(obs1_3);
        stage.addActor(obs1_3);

        Spikes pincho3 = new Spikes(main.gameScreen.world,spike,obs1_3.getPosition()+1.6f,ALTURA_OBS+.02f);
        arraySpike.add(pincho3);
        stage.addActor(pincho3);

        Obstaculo obs1_4 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs1_3.getPosition()+1.7f,ALTURA_OBS+1.4f));
        arrayObs1.add(obs1_4);
        stage.addActor(obs1_4);

        Obstaculo2 obs2_4  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1_4.getPosition()+2.2f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_4);
        stage.addActor(obs2_4);

        Obstaculo3Bloques obs3_blocs = new Obstaculo3Bloques(main.gameScreen.world,blocs_3, new Vector2( obs2_4.getPosition()+2.2f, ALTURA_OBS+.6f));
        array3blocs.add(obs3_blocs);
        stage.addActor(obs3_blocs);

        Spikes pincho4 = new Spikes(main.gameScreen.world,spike,obs3_blocs.getPosition()+1.7f,ALTURA_OBS+.02f);
        arraySpike.add(pincho4);
        stage.addActor(pincho4);

        Spikes pincho5 = new Spikes(main.gameScreen.world,spike,pincho4.getPosition()+1.8f,ALTURA_OBS+.02f);
        arraySpike.add(pincho5);
        stage.addActor(pincho5);

    }

    public void endGame(Player player) {
            if(player.isOutOfScreen()){
                main.gameScreen.music_bg.stop();
                stop1();
                stop2();
                stop3();
                stopBloque();
                stopSpike();
                stopBloque3();
                main.gameScreen.stage.addAction(
                        Actions.sequence(
                                Actions.fadeOut(.4f),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        main.gameScreen.dead_sound.play();
                                        Actions.delay(.5f);
                                        main.setScreen(new GameOverScreen(main));
                                    }
                                })
                        )
                );
            }


    }



    /*-----ELIMINAR CUERPOS----------*/

   public  void removeOb1(World world){
        for (Obstaculo ob : arrayObs1) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();

                    arrayObs1.removeValue(ob,false);
                }
            }
        }
    }

    public  void stop1(){
        for (Obstaculo ob : arrayObs1) {
            ob.stopObs();
        }
    }
    public  void stop2(){
        for (Obstaculo2 ob : arrayObs2) {
            ob.stopObs();
        }
    }
    public  void stop3(){
        for (Obstaculo3 ob : arrayObs3) {
            ob.stopObs();
        }
    }
     public  void stopBloque() {
         for (ObstaculoBloque ob : arrayBloque) {
             ob.stopObs();
         }
     }
     public  void stopBloque3() {
         for (Obstaculo3Bloques ob : array3blocs) {
             ob.stopObs();
         }
     }
     public  void stopSpike(){
            for (Spikes ob : arraySpike) {
                ob.stopObs();
            }
        }

    public void removeOb2(World world) {
        for (Obstaculo2 ob : arrayObs2) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();

                    arrayObs2.removeValue(ob,false);
                }
            }
        }
    }

    public  void removeOb3(World world) {
        for (Obstaculo3 ob : arrayObs3) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();
                    arrayObs3.removeValue(ob,false);
                }
            }
        }
    }

    public  void removeBloque(World world) {
        for (ObstaculoBloque ob : arrayBloque) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();
                    arrayBloque.removeValue(ob,false);
                }
            }
        }
    }
    public  void removeBloque3(World world) {
        for (Obstaculo3Bloques ob : array3blocs) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();
                    array3blocs.removeValue(ob,false);
                }
            }
        }
    }

    public  void removeSpike(World world) {
        for (Spikes ob : arraySpike) {
            if(!world.isLocked()) {
                if(ob.isOutOfScreen()) {
                    ob.detach();
                    ob.remove();
                    arraySpike.removeValue(ob,false);
                }
            }
        }
    }

    public void addObstaculosBucle(Stage stage, float x) {


        TextureRegion ob1 = main.assetManagment.getObs1();
        TextureRegion ob2 = main.assetManagment.getObs2();
        TextureRegion ob3 = main.assetManagment.getObs3();
        TextureRegion bloc = main.assetManagment.getBloque();
        TextureRegion spike = main.assetManagment.getSpike();
        TextureRegion blocs_3 = main.assetManagment.getThreeBlocs();


        Obstaculo obs1 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(x,ALTURA_OBS));
        arrayObs1.add(obs1);
        stage.addActor(obs1);


        Obstaculo2 obs2  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1.getPosition()+1.2f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2);
        stage.addActor(obs2);


        ObstaculoBloque bloque = new ObstaculoBloque(main.gameScreen.world,bloc,new Vector2(obs2.getPosition()+2.2f,ALTURA_OBS+.06f));
        arrayBloque.add(bloque);
        stage.addActor(bloque);

        Obstaculo obs1_1 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(bloque.getPosition()+1.6f,ALTURA_OBS+1.2f));
        arrayObs1.add(obs1_1);
        stage.addActor(obs1_1);


        Obstaculo obs1_2 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs1_1.getPosition()+2f,ALTURA_OBS));
        arrayObs1.add(obs1_2);
        stage.addActor(obs1_2);


        Spikes pincho7 = new Spikes(main.gameScreen.world, spike,obs1_2.getPosition()-.45f,ALTURA_OBS+.02f);
        arraySpike.add(pincho7);
        stage.addActor(pincho7);

        Obstaculo2 obs2_2  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1_2.getPosition()+1.3f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2_2);
        stage.addActor(obs2_2);

        Obstaculo3 obs3 = new Obstaculo3(main.gameScreen.world,ob3,new Vector2(obs2_2.getPosition()+1.3f,ALTURA_OBS+.5f));
        arrayObs3.add(obs3);
        stage.addActor(obs3);

        ObstaculoBloque bloque2 = new ObstaculoBloque(main.gameScreen.world,bloc,new Vector2(obs3.getPosition()+2.5f,ALTURA_OBS+.05f));
        arrayBloque.add(bloque2);
       stage.addActor(bloque2);


        Obstaculo obs1_3 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(bloque2.getPosition()+1.8f,ALTURA_OBS+.5f));
        arrayObs1.add(obs1_3);
        stage.addActor(obs1_3);


        Spikes pincho1 = new Spikes(main.gameScreen.world, spike,obs1_3.getPosition()+.8f,ALTURA_OBS+.02f);
        arraySpike.add(pincho1);
        stage.addActor(pincho1);


        Obstaculo obs1_4 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs1_3.getPosition()+1.6f,ALTURA_OBS+.9f));
        arrayObs1.add(obs1_4);
        stage.addActor(obs1_4);

        Spikes pincho2 = new Spikes(main.gameScreen.world, spike,obs1_4.getPosition()+.8f,ALTURA_OBS+.02f);
        arraySpike.add(pincho2);
        stage.addActor(pincho2);


        Obstaculo obs1_5 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs1_4.getPosition()+1.5f,ALTURA_OBS+1.2f));
        arrayObs1.add(obs1_5);
        stage.addActor(obs1_5);

        Spikes pincho4 = new Spikes(main.gameScreen.world, spike,obs1_5.getPosition()+1.2f,ALTURA_OBS+.02f);
        arraySpike.add(pincho4);
        stage.addActor(pincho4);


        ObstaculoBloque bloque3 = new ObstaculoBloque(main.gameScreen.world,bloc,new Vector2(obs1_5.getPosition()+2.2f,ALTURA_OBS+1.6f));
        arrayBloque.add(bloque3);
        stage.addActor(bloque3);

        Spikes pincho3 = new Spikes(main.gameScreen.world, spike,bloque3.getPosition()+2f,ALTURA_OBS+.02f);
        arraySpike.add(pincho3);
        stage.addActor(pincho3);


        Spikes pincho5 = new Spikes(main.gameScreen.world, spike,bloque3.getPosition()+.2f,ALTURA_OBS+2.12f);
        arraySpike.add(pincho5);
        stage.addActor(pincho5);

        Obstaculo obs1_6 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(bloque3.getPosition()+3f,ALTURA_OBS+1.2f));
        arrayObs1.add(obs1_6);
       stage.addActor(obs1_6);

        Spikes pincho6 = new Spikes(main.gameScreen.world, spike,obs1_6.getPosition()+1.6f,ALTURA_OBS+.02f);
        arraySpike.add(pincho6);
        stage.addActor(pincho6);

        Obstaculo3Bloques obs3_blocs = new Obstaculo3Bloques(main.gameScreen.world,blocs_3, new Vector2( obs1_6.getPosition()+2.4f, ALTURA_OBS+.8f));
        array3blocs.add(obs3_blocs);
        stage.addActor(obs3_blocs);

        Spikes pincho8 = new Spikes(main.gameScreen.world, spike,obs3_blocs.getPosition()+.5f,ALTURA_OBS+1.15f);
        arraySpike.add(pincho8);
        stage.addActor(pincho8);

        int i = 2;

       while(GameScreen.scoreNum >=i){
           Spikes pincho11 = new Spikes(main.gameScreen.world, spike,obs3_blocs.getPosition()+2.9f,ALTURA_OBS+.02f);
           arraySpike.add(pincho11);
           stage.addActor(pincho11);
           i++;

           if (i >= 4) {
               Spikes pincho12 = new Spikes(main.gameScreen.world, spike,pincho11.getPosition()+1.7f,ALTURA_OBS+.02f);
               arraySpike.add(pincho12);
               stage.addActor(pincho12);


               if(i >= 6){

                   Obstaculo obs1_10 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(pincho12.getPosition()+.45f,ALTURA_OBS));
                   arrayObs1.add(obs1_10);
                   stage.addActor(obs1_10);

                   Obstaculo2 obs2_4  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1_10.getPosition()+1.4f,ALTURA_OBS+.25f));
                   arrayObs2.add(obs2_4);
                   stage.addActor(obs2_4);

                   if(i>=7){
                       Obstaculo obs1_11 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs2_4.getPosition()+1.6f,ALTURA_OBS));
                       arrayObs1.add(obs1_11);
                       stage.addActor(obs1_11);

                       Obstaculo2 obs2_5  = new Obstaculo2(main.gameScreen.world,ob2,new Vector2(obs1_11.getPosition()+1.8f,ALTURA_OBS+.25f));
                       arrayObs2.add(obs2_5);
                       stage.addActor(obs2_5);


                    if(i>=8){

                        Obstaculo3 obs3_4 = new Obstaculo3(main.gameScreen.world,ob3,new Vector2(obs2_4.getPosition()+1.7f,ALTURA_OBS+.5f));
                        arrayObs3.add(obs3_4);
                        stage.addActor(obs3_4);

                          Obstaculo3 obs3_5 = new Obstaculo3(main.gameScreen.world,ob3,new Vector2(obs3_4.getPosition()+1.8f,ALTURA_OBS+.5f));
                           arrayObs3.add(obs3_5);
                           stage.addActor(obs3_5);

                           if (i>=9){
                               Obstaculo obs1_12 = new Obstaculo(main.gameScreen.world,ob1,new Vector2(obs3_5.getPosition()+1.6f,ALTURA_OBS+1f));
                               arrayObs1.add(obs1_12);
                               stage.addActor(obs1_12);

                           }

                       }
                   }
               }


           }




        }

    }

}
