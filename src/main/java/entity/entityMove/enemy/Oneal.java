package entity.entityMove.enemy;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import entity.entityMove.Bomberman;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Oneal extends Enemy {
    private boolean checkChase = false;

    public Oneal(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
    }

    @Override
    public void update() {
        heart();
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
    public void move(Bomberman bomberman) {
        if ( count > 15) {
            count = 0;
        }
        if (checkRandom == false) {
            randomDirection = Random();
        }
        if(enemyChase( bomberman) != 0) {
            randomDirection = enemyChase(bomberman);
            if (checkChase == false) {
                toX = (int) ((toX + 22) / UNIT) * UNIT;
                toY = (int) ((toY + 22) / UNIT) * UNIT;
                Image_Game.moveCoordinates(toX, toY, imageView);
                checkChase = true;
            }

        } else
            checkChase = false;
        directionEnemy(randomDirection);
        forCoordinates();
        heart();
        count ++;
    }
    private void directionEnemy(int randomDirection ) {
        if (randomDirection == 1 ) {
            if (checkMap(toX + speed, toY)) {
                checkRandom = true;
                toX = toX + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightOnealRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 2) {
            if (checkMap(toX - speed, toY)) {
                checkRandom = true;
                toX = toX - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftOnealRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 3) {
            if (checkMap(toX, toY - speed)) {
                checkRandom = true;
                toY = toY - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftOnealRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 4) {
            if (checkMap(toX, toY + speed)) {
                checkRandom = true;
                toY = toY + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightOnealRetangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
    }


    // enemyChase ??u???i theo ng?????i trong qu?? tr??nh ??u???i theo n???u g???p bom s??? ch???y
    // N???u kho???ng c??ch gi??? ng?????i v?? enemy c?? v???t c???n (t?????ng, bom, g???ch) th?? kh??ng ??u???i theo
    private int enemyChase (Bomberman bomberman) {

        int a = heartX / UNIT;
        int b = heartY / UNIT;
        if ( a == (int)bomberman.heartX / UNIT ) { // Khi Enemy2 c??ng tr???c X v???i Bomber
            if ( b < (int)bomberman.heartY / UNIT) { // N???u Enemy2 ??? tr??n Bomber
                for (int i = b; i < (int)bomberman.heartY / UNIT; i ++) { // X??t xem kho???ng c??ch gi???u ch??ng c?? v???t c???n kh??ng
                    if(Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[i][a]) || LEVEL_MAP[i][a] == 'n')
                        return 0;
                }
                return 4;
            }
            if (b > (int) bomberman.heartY / UNIT) { // N???u Enemy2 ??? d?????i Bomber
                for (int i = (int)bomberman.heartY / UNIT; i < b; i ++) { // X??t xem kho???ng c??ch gi???u ch??ng c?? v???t c???n kh??ng
                    if( Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[i][a]) || LEVEL_MAP[i][a] == 'n')
                        return 0; // n???u c?? th?? randum ????? ch???y
                }
                return 3; // N???u kh??ng th?? ??u???i theo
            }
        }
        if ( b == (int)bomberman.heartY / UNIT) {
            if ( a < (int)bomberman.heartX / UNIT) {
                for (int i = a; i < (int) bomberman.heartX / UNIT; i ++) {
                    if( Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[b][i]) || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 1;
            }
            if (a > (int)bomberman.heartX / UNIT) {

                for (int i = (int)bomberman.heartX / UNIT; i < a; i ++) {
                    if( Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[b][i]) || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 2;
            }
        }
        return 0;
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        if (count <= 0) {
            imageView.setViewport(dieOneal);

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
