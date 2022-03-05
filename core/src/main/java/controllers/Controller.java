package controllers;


import static com.mygame.geometrydash.screens.GameScreen.ALTURA_OBS;
import static com.mygame.geometrydash.screens.GameScreen.array3blocs;
import static com.mygame.geometrydash.screens.GameScreen.arrayBloque;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs1;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs2;
import static com.mygame.geometrydash.screens.GameScreen.arrayObs3;
import static com.mygame.geometrydash.screens.GameScreen.arraySpike;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygame.geometrydash.MainGame;
import com.mygame.geometrydash.actors.Obstaculo;
import com.mygame.geometrydash.actors.Obstaculo2;
import com.mygame.geometrydash.actors.Obstaculo3;
import com.mygame.geometrydash.actors.Obstaculo3Bloques;
import com.mygame.geometrydash.actors.ObstaculoBloque;
import com.mygame.geometrydash.actors.Player;
import com.mygame.geometrydash.actors.Spikes;
import com.mygame.geometrydash.screens.BaseScreen;
import com.mygame.geometrydash.screens.GameScreen;


public class Controller extends BaseScreen {

    /*CLASE PARA CONTROLAR LA CREACION DE OBSTACULOS, ELIMINARLOS Y/O PARARLOS CUANDO ACABE EL JUEGO*/

    public Controller(MainGame mainGame) {
        super(mainGame);

    }


    /*------GENERAR OBSTACULAS EN EL METODO SHOW (como la variable de spawn la he puesto a 19f, tarda mucho en spawnear desde el render, mientras  pongo
        obstaculos en el show)----------*/

    public void addObstaculosShow(Stage stage,World world, float x){

        //obtengo todas las texturas de los obstaculos

        TextureRegion ob1 = main.assetManagment.getObs1();
        TextureRegion ob2 = main.assetManagment.getObs2();
        TextureRegion ob3 = main.assetManagment.getObs3();
        TextureRegion bloq = main.assetManagment.getBloque();
        TextureRegion spike = main.assetManagment.getSpike();
        TextureRegion blocs_3 = main.assetManagment.getThreeBlocs();


        //dependiendo de la posicion del obstaculo anterior en x, voy añadiendo más con el metodo getPositoin()
        //a la altura por defecto le voy sumando mas o menos dependiendo del obstaculo porque son imagenes recortadas  (o porque está en el aire)

        Obstaculo obs1 = new Obstaculo(world,ob1,new Vector2(x+1f,ALTURA_OBS));
        arrayObs1.add(obs1);
        stage.addActor(obs1);


        Obstaculo2 obs2  = new Obstaculo2(world,ob2,new Vector2(obs1.getPosition()+1.6f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2);
        stage.addActor(obs2);


        Obstaculo3 obs3 = new Obstaculo3(world,ob3,new Vector2(obs2.getPosition()+1.4f,ALTURA_OBS+.5f));
        arrayObs3.add(obs3);
        stage.addActor(obs3);

        Obstaculo3Bloques obs3_blocs2 = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs3.getPosition()+2.2f, ALTURA_OBS+1.35f));
        array3blocs.add(obs3_blocs2);
        stage.addActor(obs3_blocs2);

        Spikes pincho9 = new Spikes(world,spike,obs3_blocs2.getPosition()+.5f,ALTURA_OBS+1.66f);
        arraySpike.add(pincho9);
        stage.addActor(pincho9);

        Spikes pincho1 = new Spikes(world,spike,obs3_blocs2.getPosition()+1.35f,ALTURA_OBS);
        arraySpike.add(pincho1);
        stage.addActor(pincho1);


        ObstaculoBloque bloque = new ObstaculoBloque(world,bloq,new Vector2(pincho1.getPosition()+1.25f,ALTURA_OBS+.05f));
        arrayBloque.add(bloque);
        stage.addActor( bloque);


        Obstaculo2 obs2_2  = new Obstaculo2(world,ob2,new Vector2(bloque.getPosition()+2.2f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_2);
        stage.addActor(obs2_2);

        Obstaculo3 obs3_2 = new Obstaculo3(world,ob3,new Vector2(obs2_2.getPosition()+1.6f,ALTURA_OBS+.5f));
        arrayObs3.add(obs3_2);
        stage.addActor(obs3_2);

        Obstaculo2 obs2_3  = new Obstaculo2(world,ob2,new Vector2(obs3_2.getPosition()+1.85f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_3);
        stage.addActor(obs2_3);

        Obstaculo obs1_2 = new Obstaculo(world,ob1,new Vector2(obs2_3.getPosition()+1.65f,ALTURA_OBS+.8f));
        arrayObs1.add(obs1_2);
        stage.addActor(obs1_2);

        Spikes pincho2 = new Spikes(world,spike,obs1_2.getPosition()+1f,ALTURA_OBS);
        arraySpike.add(pincho2);
        stage.addActor(pincho2);

        Obstaculo obs1_3 = new Obstaculo(world,ob1,new Vector2(obs1_2.getPosition()+1.75f,ALTURA_OBS+1.08f));
        arrayObs1.add(obs1_3);
        stage.addActor(obs1_3);

        Spikes pincho3 = new Spikes(world,spike,obs1_3.getPosition()+1.6f,ALTURA_OBS);
        arraySpike.add(pincho3);
        stage.addActor(pincho3);

        Obstaculo obs1_4 = new Obstaculo(world,ob1,new Vector2(obs1_3.getPosition()+1.65f,ALTURA_OBS+1.4f));
        arrayObs1.add(obs1_4);
        stage.addActor(obs1_4);

        Obstaculo2 obs2_4  = new Obstaculo2(world,ob2,new Vector2(obs1_4.getPosition()+2.2f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_4);
        stage.addActor(obs2_4);

        Obstaculo3Bloques obs3_blocs = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs2_4.getPosition()+2.3f, ALTURA_OBS+.6f));
        array3blocs.add(obs3_blocs);
        stage.addActor(obs3_blocs);

        Spikes pincho4 = new Spikes(world,spike,obs3_blocs.getPosition()+1.85f,ALTURA_OBS);
        arraySpike.add(pincho4);
        stage.addActor(pincho4);

        Obstaculo obs1_5= new Obstaculo(world,ob1,new Vector2(pincho4.getPosition()+.5f,ALTURA_OBS));
        arrayObs1.add(obs1_5);
        stage.addActor(obs1_5);

        Obstaculo2 obs2_5  = new Obstaculo2(world,ob2,new Vector2(obs1_5.getPosition()+1.6f,ALTURA_OBS+0.25f));
        arrayObs2.add(obs2_5);
        stage.addActor(obs2_5);


        Spikes pincho6 = new Spikes(world,spike,obs2_5.getPosition()+1.2f,ALTURA_OBS);
        arraySpike.add(pincho6);
        stage.addActor(pincho6);

        Obstaculo3Bloques obs3_blocs3 = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs2_5.getPosition()+2.2f, ALTURA_OBS+.8f));
        array3blocs.add(obs3_blocs3);
        stage.addActor(obs3_blocs3);

        Spikes pincho5 = new Spikes(world,spike,obs3_blocs3.getPosition()+.5f,ALTURA_OBS+1.11f);
        arraySpike.add(pincho5);
        stage.addActor(pincho5);

        Spikes pincho7 = new Spikes(world,spike,pincho5.getPosition()+2.6f,ALTURA_OBS);
        arraySpike.add(pincho7);
        stage.addActor(pincho7);

        ObstaculoBloque bloque2 = new ObstaculoBloque(world,bloq,new Vector2(pincho7.getPosition()+1.3f,ALTURA_OBS+.05f));
        arrayBloque.add(bloque2);
        stage.addActor( bloque2);

        Spikes pincho8 = new Spikes(world,spike,bloque2.getPosition()+.9f,ALTURA_OBS+.54f);
        arraySpike.add(pincho8);
        stage.addActor(pincho8);


    }

    //se acaba el juego si el personaje sale de la pantalla
    public void endGame(Player player, Stage stage) {
            if(player.isOutOfScreen()){
                player.hurt();
                main.gameScreen.music_bg.stop(); //paro la musica


                //paro los obstaculos
                stop1();
                stop2();
                stop3();
                stopBloque();
                stopSpike();
                stopBloque3();
                stage.addAction(
                        Actions.sequence(
                                Actions.fadeOut(.4f),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        main.gameScreen.dead_sound.play(); //suena el sonido cuando se muere
                                        Actions.delay(.3f);
                                        main.setScreen(main.gameOverScreen); //lanzo el gameover
                                    }
                                })
                        )
                );
            }


    }

    //se termina el juego si choca con un pincho
    public void endGameConctact(Player player, Stage stage) {
            player.hurt();
            main.gameScreen.music_bg.stop();
            main.gameScreen.dead_sound.play();
            stop1();
            stop2();
            stop3();
            stopBloque();
            stopSpike();
            stopBloque3();
            stage.addAction(
                    Actions.sequence(
                            Actions.fadeOut(.4f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    Actions.delay(.5f);
                                    main.setScreen(main.gameOverScreen);
                                    //lanzo el gameover
                                }
                            })
                    )
            );

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

    //PARAR OBSTACULOS
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


        /*METODO DONDE APARECEN LOS OBSTACULOS DESDE EL RENDER*/

    public void addObstaculosBucle(Stage stage,World world, float x) {


        TextureRegion ob1 = main.assetManagment.getObs1();
        TextureRegion ob2 = main.assetManagment.getObs2();
        TextureRegion ob3 = main.assetManagment.getObs3();
        TextureRegion bloc = main.assetManagment.getBloque();
        TextureRegion spike = main.assetManagment.getSpike();
        TextureRegion blocs_3 = main.assetManagment.getThreeBlocs();


        Obstaculo obs1 = new Obstaculo(world,ob1,new Vector2(x,ALTURA_OBS));
        arrayObs1.add(obs1);
        stage.addActor(obs1);


        Obstaculo2 obs2  = new Obstaculo2(world,ob2,new Vector2(obs1.getPosition()+1.2f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2);
        stage.addActor(obs2);


        ObstaculoBloque bloque = new ObstaculoBloque(world,bloc,new Vector2(obs2.getPosition()+2.2f,ALTURA_OBS+.06f));
        arrayBloque.add(bloque);
        stage.addActor(bloque);

        Obstaculo obs1_1 = new Obstaculo(world,ob1,new Vector2(bloque.getPosition()+1.6f,ALTURA_OBS+1.2f));
        arrayObs1.add(obs1_1);
        stage.addActor(obs1_1);


        Obstaculo obs1_2 = new Obstaculo(world,ob1,new Vector2(obs1_1.getPosition()+2f,ALTURA_OBS));
        arrayObs1.add(obs1_2);
        stage.addActor(obs1_2);


        Spikes pincho7 = new Spikes(world, spike,obs1_2.getPosition()-.45f,ALTURA_OBS);
        arraySpike.add(pincho7);
        stage.addActor(pincho7);

        Obstaculo2 obs2_2  = new Obstaculo2(world,ob2,new Vector2(obs1_2.getPosition()+1.4f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2_2);
        stage.addActor(obs2_2);

        Obstaculo obs1_12 = new Obstaculo(world,ob1,new Vector2(obs2_2.getPosition()+1.9f,ALTURA_OBS));
        arrayObs1.add(obs1_12);
        stage.addActor(obs1_12);

        Obstaculo3Bloques obs3_blocs2 = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs1_12.getPosition()+2.05f, ALTURA_OBS+.55f));
        array3blocs.add(obs3_blocs2);
        stage.addActor(obs3_blocs2);

        Obstaculo2 obs2_3  = new Obstaculo2(world,ob2,new Vector2(obs3_blocs2.getPosition()+2f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2_3);
        stage.addActor(obs2_3);

        Obstaculo3 obs3 = new Obstaculo3(world,ob3,new Vector2(obs2_3.getPosition()+1.7f,ALTURA_OBS+.48f));
        arrayObs3.add(obs3);
        stage.addActor(obs3);

        Obstaculo obs1_13 = new Obstaculo(world,ob1,new Vector2(obs3.getPosition()+1.8f,ALTURA_OBS+1.1f));
        arrayObs1.add(obs1_13);
        stage.addActor(obs1_13);


        Spikes pincho8 = new Spikes(world, spike,obs1_13.getPosition()+1f,ALTURA_OBS);
        arraySpike.add(pincho8);
        stage.addActor(pincho8);

        Obstaculo obs1_3 = new Obstaculo(world,ob1,new Vector2(obs1_13.getPosition()+1.7f,ALTURA_OBS+1.35f));
        arrayObs1.add(obs1_3);
        stage.addActor(obs1_3);

        Obstaculo3Bloques obs3_blocs = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs1_3.getPosition()+2.55f, ALTURA_OBS+.7f));
        array3blocs.add(obs3_blocs);
        stage.addActor(obs3_blocs);

        Spikes pincho9 = new Spikes(world, spike,obs3_blocs.getPosition()+.5f,ALTURA_OBS+1.01f);
        arraySpike.add(pincho9);
        stage.addActor(pincho9);

        ObstaculoBloque bloque2 = new ObstaculoBloque(world,bloc,new Vector2(obs3_blocs.getPosition()+2.5f,ALTURA_OBS+.05f));
        arrayBloque.add(bloque2);
         stage.addActor(bloque2);

        Obstaculo obs1_4 = new Obstaculo(world,ob1,new Vector2(bloque2.getPosition()+2.25f,ALTURA_OBS+.35f));
        arrayObs1.add(obs1_4);
        stage.addActor(obs1_4);

        Obstaculo obs1_5 = new Obstaculo(world,ob1,new Vector2(obs1_4.getPosition()+1.75f,ALTURA_OBS+.8f));
        arrayObs1.add(obs1_5);
        stage.addActor(obs1_5);

        Spikes pincho6 = new Spikes(world, spike,obs1_5.getPosition(),ALTURA_OBS+1.25f);
        arraySpike.add(pincho6);
        stage.addActor(pincho6);

        Obstaculo obs1_7 = new Obstaculo(world,ob1,new Vector2(pincho6.getPosition()+1.9f,ALTURA_OBS));
        arrayObs1.add(obs1_7);
        stage.addActor(obs1_7);


        Obstaculo2 obs2_6  = new Obstaculo2(world,ob2,new Vector2(obs1_7.getPosition()+1.6f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2_6);
        stage.addActor(obs2_6);

        Obstaculo3 obs3_1 = new Obstaculo3(world,ob3,new Vector2(obs2_6.getPosition()+1.6f,ALTURA_OBS+.48f));
        arrayObs3.add(obs3_1);
        stage.addActor(obs3_1);


        Obstaculo2 obs2_7  = new Obstaculo2(world,ob2,new Vector2(obs3_1.getPosition()+1.85f,ALTURA_OBS+.25f));
        arrayObs2.add(obs2_7);
        stage.addActor(obs2_7);


        Obstaculo3Bloques obs3_blocs3 = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs2_7.getPosition()+2.2f, ALTURA_OBS+.85f));
        array3blocs.add(obs3_blocs3);
        stage.addActor(obs3_blocs3);


        Spikes pincho10 = new Spikes(world, spike,obs3_blocs3.getPosition()+.5f,ALTURA_OBS+1.16f);
        arraySpike.add(pincho10);
        stage.addActor(pincho10);

        Spikes pincho13 = new Spikes(world, spike,obs2_7.getPosition()+1.6f,ALTURA_OBS+.02f);
        arraySpike.add(pincho13);
        stage.addActor(pincho13);


        int i = 2;

        //VOY AÑADIENDO OBSTACULOS CONFORME AUMENTA LA PUNTUACION, PORQUE AL AUMENTAR LA VELOCIDAD EN CADA VUELTA, HAY MAS ESPACIO ENTRE UN BUCLE Y OTRO (aumento la velocidad en gamescreen)
       while(GameScreen.scoreNum >=i){
           Spikes pincho11 = new Spikes(world, spike,obs3_blocs3.getPosition()+2.8f,ALTURA_OBS);
           arraySpike.add(pincho11);
           stage.addActor(pincho11);
           i++;
            //APARECEN MAS OBSTACULOS A PARTIR DE LA 3º VUELTA
           if (i >= 3) {
               Spikes pincho12 = new Spikes(world, spike,pincho11.getPosition()+1.9f,ALTURA_OBS);
               arraySpike.add(pincho12);
               stage.addActor(pincho12);

               if(i>= 4){

                   Obstaculo obs1_10 = new Obstaculo(world,ob1,new Vector2(pincho12.getPosition()+1.7f,ALTURA_OBS));
                   arrayObs1.add(obs1_10);
                   stage.addActor(obs1_10);


                   if(i >= 5){

                       Obstaculo3Bloques obs3_blocs4 = new Obstaculo3Bloques(world,blocs_3, new Vector2( obs1_10.getPosition()+1.6f, ALTURA_OBS+.7f));
                       array3blocs.add(obs3_blocs4);
                       stage.addActor(obs3_blocs4);

                       Obstaculo2 obs2_4  = new Obstaculo2(world,ob2,new Vector2(obs3_blocs4.getPosition()+1.4f,ALTURA_OBS+.25f));
                       arrayObs2.add(obs2_4);
                       stage.addActor(obs2_4);

                       if(i>=7){
                           Obstaculo obs1_11 = new Obstaculo(world,ob1,new Vector2(obs2_4.getPosition()+1.6f,ALTURA_OBS));
                           arrayObs1.add(obs1_11);
                           stage.addActor(obs1_11);

                           Obstaculo2 obs2_5  = new Obstaculo2(world,ob2,new Vector2(obs1_11.getPosition()+1.6f,ALTURA_OBS+.25f));
                           arrayObs2.add(obs2_5);
                           stage.addActor(obs2_5);


                           if(i>=8){

                               Obstaculo3 obs3_4 = new Obstaculo3(world,ob3,new Vector2(obs2_4.getPosition()+1.7f,ALTURA_OBS+.5f));
                               arrayObs3.add(obs3_4);
                               stage.addActor(obs3_4);

                               Obstaculo3 obs3_5 = new Obstaculo3(world,ob3,new Vector2(obs3_4.getPosition()+1.8f,ALTURA_OBS+.5f));
                               arrayObs3.add(obs3_5);
                               stage.addActor(obs3_5);



                           }
                       }
                   }
               }


           }


       }

   }

}

