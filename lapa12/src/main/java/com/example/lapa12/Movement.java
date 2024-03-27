package com.example.lapa12;

import com.example.lapa12.heros.Hilichurl;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Movement {
        boolean goUp, goDown, goRight, goLeft;
        AnimationTimer animationTimer;
        public void keyPress(Scene scene, Group root, Hilichurl character, double x, double y) throws FileNotFoundException {

            Node hero = new ImageView(new Image(new FileInputStream(character.getImagePath()),150,140, true, true));
            root.getChildren().add(hero);

            moveHeroTo(x, y, hero);

            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()){
                    case UP -> goUp =true;
                    case DOWN -> goDown=true;
                    case LEFT -> goLeft = true;
                    case RIGHT -> goRight = true;
                }
            });

            scene.setOnKeyReleased(keyEvent -> {
                switch (keyEvent.getCode()){
                    case UP -> goUp =false;
                    case DOWN -> goDown=false;
                    case LEFT -> goLeft = false;
                    case RIGHT -> goRight = false;
                }
            });
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    int dx=0, dy=0;
                    if (goUp) dy-=2;
                    if (goDown) dy+=2;
                    if (goRight) dx+=2;
                    if (goLeft) dx-=2;

                    moveHeroBy(dx, dy, hero, character);
                }
            };
            animationTimer.start();

        }
        private void moveHeroBy(int dx, int dy, Node hero, Hilichurl character){
            if (dx==0 && dy==0) return;

            double cx = hero.getBoundsInLocal().getWidth()/2;
            double cy = hero.getBoundsInLocal().getHeight()/2;

            double x = cx + hero.getLayoutX() + dx;
            double y = cy + hero.getLayoutY() + dy;

            character.setX(x);
            character.setY(y);

            moveHeroTo(x, y, hero);
        }

        private void moveHeroTo(double x, double y, Node hero){
            double cx = hero.getBoundsInLocal().getWidth()/2;
            double cy = hero.getBoundsInLocal().getHeight()/2;

            if (x-cx>=0 &&
                    x+cx<=Main.W &&
                    y-cy>=0 &&
                    y+cy<=Main.H){
                hero.relocate(x-cx, y-cy);
            }
        }
        public void stopTimer(){
            if (this.animationTimer!=null){
            animationTimer.stop();
            }
        }
    }

