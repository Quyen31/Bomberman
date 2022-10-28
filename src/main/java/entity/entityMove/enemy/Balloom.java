package entity.entityMove.enemy;

import Contruction.Image_Game;
import entity.entityMove.Bomberman;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Balloom extends Enemy {
    public Balloom(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
    }

    @Override
    public void update() {
        imageView = new ImageView();
        imageView.setFitWidth(44);
        imageView.setFitHeight(44);
        random = new Random();
        gamePane.getChildren().add(imageView);
    }

    @Override
    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
    }

    @Override
    public void move (Bomberman bombermanRun) {
        forCoordinates();
        heart();
        if ( count > 15) {
            count = 0;
        }
        if (checkRandom == false) {
            randomDirection = Random();
        }
        directionEnemy(randomDirection);
        count ++;
    }

    private void directionEnemy(int randomDirection) {
        if (randomDirection == 1 ) {
            if (checkMap(toX + speed, toY)) {
                checkRandom = true;
                toX = toX + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightBalloomRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 2) {
            if (checkMap(toX - speed, toY)) {
                checkRandom = true;
                toX = toX - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftBalloomRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 3) {
            if (checkMap(toX, toY - speed)) {
                checkRandom = true;
                toY = toY - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftBalloomRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 4) {
            if (checkMap(toX, toY + speed)) {
                checkRandom = true;
                toY = toY + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightBalloomRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        if (count <= 0) {
            imageView.setViewport(dieBalloom);
        }
        Image_Game.Animation(10 ,count, toX, toY, EnemyDie, imageView, image_sprites);
        count ++;
    }

    @Override
    public void loopDie() {
        loopDie = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeDie >= 30)
                    die();
                timeDie ++;
                if (timeDie >= 60){
                    loopDie.stop();
                    remove();
                }
            }
        };
        count = -10;
        loopDie.start();
    }

}
