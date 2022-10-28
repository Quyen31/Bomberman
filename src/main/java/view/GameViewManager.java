package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import entity.*;
import entity.entityAnimation.Bomb;
import entity.entityAnimation.Brick;
import entity.entityMove.Bomberman;
import entity.entityMove.enemy.Balloom;
import entity.entityMove.enemy.Doll;
import entity.entityMove.enemy.Enemy;
import entity.entityMove.enemy.Oneal;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Music;
import java.util.ArrayList;
import java.util.List;

public class GameViewManager extends ShowView{
    private Stage menuLevelStage;
    private Stage menuStage;

    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    private char[][] LEVEL_MAP;
    private Bomberman bombermanRun;

    private List<Enemy> Enemy = new ArrayList<Enemy>();

    private Entity[][] ArrayEntity;
    private Item[][] ItemMap;
    private boolean isLeftKeyPressed;
    private boolean isRightPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isKeySpace;
    private AnimationTimer gameTimer;
    private List<Bomb> ArrayBomb = new ArrayList<Bomb>();
    private int checkSetBom = 0;
    private TranslateTransition transition = new TranslateTransition();
    private List<KeyEvent> ArrayKeyEvent = new ArrayList<KeyEvent>();

    private Music musicGame ;
    private Music musicWalk;

    private Music musicSetBomb;


    public GameViewManager(int GAME_WIDTH, int GAME_HEIGHT, char[][] LEVEL_MAP){
        super("Game Bomberman");
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT= GAME_HEIGHT;
        this.LEVEL_MAP = LEVEL_MAP;
        ArrayEntity = new Entity[GAME_HEIGHT][GAME_WIDTH];
        ItemMap = new Item[GAME_HEIGHT][GAME_WIDTH];
        gameBackground();
        setUpGame();
        createKeyListeners();
        transition.setDuration(Duration.seconds(0.001));
        transition.setNode(Pane);
        musicSetBomb = new Music(music_placed_bomb_url);
        musicSetBomb.Volume(3);
        musicGame = new Music(music_game_url);
        musicGame.Volume(-10);
        musicGame.musicLoop();
        musicWalk = new Music(music_walk_url);
        musicWalk.Volume(3);
    }


    public void gameBackground() {
        for(int i = 0; i <= GAME_WIDTH; i ++) {
            for (int j = 0; j <= GAME_HEIGHT; j ++) {
                Grass grass = new Grass(i * UNIT, j * UNIT, LEVEL_MAP, Pane);
                grass.update();
            }
        }
    }

    public void setUpGame(){
        for (int i = 0; i < GAME_HEIGHT; i++) {
            for (int j = 0; j < GAME_WIDTH; j++) {
                if (LEVEL_MAP[i][j] == '#'){
                    ArrayEntity[i][j] = new Wall(j * UNIT, i * UNIT, LEVEL_MAP, Pane);
                    ArrayEntity[i][j].update();
                }
                if (LEVEL_MAP[i][j] == 'b' || LEVEL_MAP[i][j] == 's' || LEVEL_MAP[i][j] == 'x' || LEVEL_MAP[i][j] == 'f') {
                    ItemMap[i][j] = new Item(j * UNIT, i * UNIT, LEVEL_MAP, Pane);
                    ItemMap[i][j].update();
                }

                if (LEVEL_MAP[i][j] == '*') {
                    ArrayEntity[i][j] = new Brick(j * UNIT, i * UNIT, LEVEL_MAP, Pane);
                    ArrayEntity[i][j].update();

                }
                if (LEVEL_MAP[i][j] == 'p') {
                    bombermanRun = new Bomberman(j * UNIT,i * UNIT, LEVEL_MAP, Pane, Stage, menuStage);
                }

                if (LEVEL_MAP[i][j] == '1') {
                    Enemy.add(new Balloom(j * UNIT, i* UNIT, LEVEL_MAP ,Pane));
                    Enemy.get(Enemy.size() - 1).update();
                }

                if (LEVEL_MAP[i][j] == '2') {
                    Enemy.add(new Oneal(j * UNIT, i* UNIT, LEVEL_MAP, Pane));
                    Enemy.get(Enemy.size() - 1).update();
                }

                if (LEVEL_MAP[i][j] == '3') {
                    Enemy.add(new Doll(j * UNIT, i* UNIT, LEVEL_MAP, Pane));
                    Enemy.get(Enemy.size() - 1).update();
                }
            }
        }
    }

    private void checkTrue (KeyCode event) {
        if (event == KeyCode.SPACE) {
            isKeySpace = true;
        } else if (event == KeyCode.LEFT) {
            isLeftKeyPressed = true;

        } else if (event == KeyCode.RIGHT) {
            isRightPressed = true;

        } else if (event == KeyCode.UP) {
            isUpKeyPressed = true;
        } else if (event == KeyCode.DOWN) {
            isDownKeyPressed = true;
        }
    }

    private void checkFalse (KeyCode event) {
        if (event == KeyCode.SPACE) {
            isKeySpace = false;
        }else if (event == KeyCode.LEFT) {
            isLeftKeyPressed = false;

        } else if (event == KeyCode.RIGHT) {
            isRightPressed = false;

        } else if (event == KeyCode.UP) {
            isUpKeyPressed = false;
        } else if (event == KeyCode.DOWN) {
            isDownKeyPressed = false;
        }
    }
    private void createKeyListeners() {
        Scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ((ArrayBomb.size() >= bombermanRun.NumberBom || LEVEL_MAP[bombermanRun.heartY / 48] [bombermanRun.heartX / 48] == 'n' )&& event.getCode() == KeyCode.SPACE) {
                    isKeySpace = false;
                } else {
                    if (ArrayKeyEvent.size() == 0) {
                        ArrayKeyEvent.add(event);
                    }
                    if (ArrayKeyEvent.size() == 1){
                        if(event.getCode() != ArrayKeyEvent.get(0).getCode()) {
                            ArrayKeyEvent.add(event);
                            checkFalse(ArrayKeyEvent.get(0).getCode());
                        }
                    }
                    if (ArrayKeyEvent.size() == 2){
                        if(event.getCode() != ArrayKeyEvent.get(0).getCode() && event.getCode() != ArrayKeyEvent.get(1).getCode()) {
                            ArrayKeyEvent.add(event);
                            checkFalse(ArrayKeyEvent.get(0).getCode());
                            checkFalse(ArrayKeyEvent.get(1).getCode());
                        }
                    }
                    checkTrue(event.getCode());
                }

            }
        });

        Scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (ArrayKeyEvent.size() == 1) {
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    }
                }
                if (ArrayKeyEvent.size() == 2) {
                    if (event.getCode() != ArrayKeyEvent.get(0).getCode()) {
                        checkTrue(ArrayKeyEvent.get(0).getCode());
                        ArrayKeyEvent.remove(1);
                    }
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    }
                }
                if (ArrayKeyEvent.size() == 3) {
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    } else if (event.getCode() == ArrayKeyEvent.get(1).getCode()) {
                        ArrayKeyEvent.remove(1);
                    } else {
                        ArrayKeyEvent.remove(2);
                        checkTrue(ArrayKeyEvent.get(1).getCode());
                    }
                }
                checkFalse(event.getCode());
            }
        });
    }

    private int K = 0;
    private int K1 = 0;
    private void moveMap() {
        if (bombermanRun.getToX() > SCREEN_WIDTH/2 + bombermanRun.SPEED_MAX * K && bombermanRun.getToX() <= GAME_WIDTH * UNIT - SCREEN_WIDTH/2) {
            transition.setToX( - bombermanRun.SPEED_MAX * K);
            K ++;
            transition.play();
        }
        if (bombermanRun.getToX() < SCREEN_WIDTH/2 + bombermanRun.SPEED_MAX * K && bombermanRun.getToX() >= SCREEN_WIDTH/2) {
            K --;
            transition.setToX( - bombermanRun.SPEED_MAX * K);
            transition.play();
        }
        if (bombermanRun.getToY() > SCREEN_HEIGHT/2 + bombermanRun.SPEED_MAX * K1 && bombermanRun.getToY() <= GAME_HEIGHT * UNIT - SCREEN_HEIGHT/2) {
            transition.setToY( - bombermanRun.SPEED_MAX * K1);
            K1 ++;
            transition.play();
        }
        if (bombermanRun.getToY() < SCREEN_HEIGHT/2 + bombermanRun.SPEED_MAX * K1 && bombermanRun.getToY() >= SCREEN_HEIGHT/2) {
            K1 --;
            transition.setToY( - bombermanRun.SPEED_MAX * K1);
            transition.play();
        }
    }

    // Vòng lặp game
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameEnd();
                moveEnemy();
                moveBomberman();
                moveMap();
                Bomb();
                removeArrayBom();
                nextLevel();
                EnemyEatBomber();
            }
        };
        gameTimer.start();
    }

    private AnimationTimer loopGameOver;
    private int timeGameOver = 0;
    private void gameEnd() {
        if (bombermanRun.gameOver == true) {
            bombermanRun.loopDie();
            loopGameOver = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    timeGameOver ++;
                    if (timeGameOver == 35) {
                        musicSetBomb.clip.stop();
                        musicGame.clip.stop();
                        musicWalk.clip.stop();
                        loopGameOver.stop();
                        Stage.close();
                        GameEnd gameEnd = new GameEnd();
                        gameEnd.gameEnd(menuStage, MENU_LEVEL.musicMenu, true);
                    }
                }
            };
            MENU_LEVEL.index = 0;
            loopGameOver.start();
            gameTimer.stop();
        }
    }

    private void nextLevel() {
        int a = bombermanRun.heartX / UNIT;
        int b = bombermanRun.heartY / UNIT;
        if (LEVEL_MAP[b][a] == 'x' && Enemy.size() == 0) {
            gameTimer.stop();
            musicSetBomb.clip.stop();
            musicGame.clip.stop();
            musicWalk.clip.stop();
            MENU_LEVEL menu_level = new MENU_LEVEL();
            menu_level.index ++;
            if (MENU_LEVEL.index > ARRAY_URL_LEVEL.length - 1) {
                Stage.close();
                MENU_LEVEL.index = 0;
                GameEnd gameEnd = new GameEnd();
                gameEnd.gameEnd(menuStage, MENU_LEVEL.musicMenu, false);
            } else
                menu_level.createNewGame(Stage, 1);
        }
    }

    private void EnemyEatBomber() {
        if (bombermanRun.gameOver == false) {
            for (int j = 0; j < Enemy.size(); j++) {
                for (int m = 0; m < 4; m++) {
                    if (bombermanRun.ArrayX[m] <= Enemy.get(j).ArrayX[1] && bombermanRun.ArrayX[m] >= Enemy.get(j).ArrayX[0] &&
                            bombermanRun.ArrayY[m] >= Enemy.get(j).ArrayY[0] && bombermanRun.ArrayY[m] <= Enemy.get(j).ArrayY[2]) {
                        bombermanRun.gameOver = true;
                        break;
                    }
                }
                if (bombermanRun.gameOver == true) {
                    break;
                }
            }
        }
    }

    private void dieEnemyOrBomber(Bomb bomb) {
        // Enemy die bị bom nổ
        for (int j = 0; j < Enemy.size(); j++) {

            for (int m = 0; m < 4; m++) {
                if (Enemy.get(j).ArrayX[m] < bomb.getWidthExplosiveMax() && Enemy.get(j).ArrayX[m] > bomb.getWidthExplosiveMin() &&
                        Enemy.get(j).ArrayY[m] > bomb.getToY() * UNIT && Enemy.get(j).ArrayY[m] < bomb.getToY() * UNIT + UNIT){
                    Enemy.get(j).die = true;
                    break;
                }
                if (Enemy.get(j).ArrayY[m] < bomb.getHightExplosiveMax() && Enemy.get(j).ArrayY[m] > bomb.getHightExplosiveMin() &&
                        Enemy.get(j).ArrayX[m] > bomb.getToX() * UNIT && Enemy.get(j).ArrayX[m] < bomb.getToX() * UNIT + UNIT) {
                    Enemy.get(j).die = true;
                    break;
                }
            }
        }
        for (int j = 0; j < Enemy.size(); j++) {

            if (Enemy.get(j).die == true) {
                Enemy.get(j).loopDie();
                Enemy.remove(j);
                j--;
            }
        }

        // Bom die Bomber
        if (bombermanRun.gameOver == false) {
            for (int m = 0; m < 4; m++) {
                if (bombermanRun.ArrayX[m] < bomb.getWidthExplosiveMax() && bombermanRun.ArrayX[m] > bomb.getWidthExplosiveMin() &&
                        bombermanRun.ArrayY[m] > bomb.getToY() * UNIT && bombermanRun.ArrayY[m] < bomb.getToY() * UNIT + UNIT) {
                    bombermanRun.gameOver = true;
                    break;
                }
                if (bombermanRun.ArrayY[m] < bomb.getHightExplosiveMax() && bombermanRun.ArrayY[m] > bomb.getHightExplosiveMin() &&
                        bombermanRun.ArrayX[m] > bomb.getToX() * UNIT && bombermanRun.ArrayX[m] < bomb.getToX() * UNIT + UNIT) {
                    bombermanRun.gameOver = true;
                    break;
                }
            }
        }

    }

    private void moveEnemy() {
        for ( int j = 0; j < Enemy.size(); j++) {
            Enemy.get(j).move(bombermanRun);
        }

    }
    public void Bomb () {
        if (isKeySpace == true) {
            checkSetBom = 1;
        }
        if (checkSetBom == 1 && isKeySpace == false ) {
            checkSetBom = 0;
            musicSetBomb.musicStart(15);
            Bomb.checkFire = bombermanRun.ExplosiveSize;
            ArrayBomb.add(new Bomb(bombermanRun.heartX / UNIT, bombermanRun.heartY / UNIT, Pane,
                    ArrayEntity, ItemMap, LEVEL_MAP, GAME_HEIGHT, GAME_WIDTH));
            ArrayBomb.get(ArrayBomb.size()-1).update();
            ArrayBomb.get(ArrayBomb.size()-1).Animation(130);

        }
    }

    private void removeArrayBom() {
        for (int i = 0; i < ArrayBomb.size(); i++) {
            if (ArrayBomb.get(i).isStopBom() == true) {
                dieEnemyOrBomber(ArrayBomb.get(i));
                LEVEL_MAP[ArrayBomb.get(i).getToY()][ArrayBomb.get(i).getToX()] = ' ';
                ArrayBomb.remove(i);
                i--;
            }
        }
    }

    private int checkMusic = 0;
    private void moveBomberman() {
        if (!isLeftKeyPressed && isRightPressed && !isDownKeyPressed && !isUpKeyPressed) {
            bombermanRun.Bomberman_Run(RIGHT, ItemMap, ArrayBomb);
        }
        if (isLeftKeyPressed && !isRightPressed && !isDownKeyPressed && !isUpKeyPressed) {
            bombermanRun.Bomberman_Run(LEFT, ItemMap, ArrayBomb);
        }
        if (!isLeftKeyPressed && !isRightPressed && !isDownKeyPressed && isUpKeyPressed) {
            bombermanRun.Bomberman_Run(UP, ItemMap, ArrayBomb);
        }
        if (!isLeftKeyPressed && !isRightPressed && isDownKeyPressed && !isUpKeyPressed) {
            bombermanRun.Bomberman_Run(DOWN, ItemMap, ArrayBomb);
        }
        if (isLeftKeyPressed || isRightPressed || isDownKeyPressed || isUpKeyPressed) {
            if (checkMusic == 20)
                checkMusic = 0;
            if (checkMusic == 0) {
                musicWalk.musicStart(15);
            }
            checkMusic ++;
        }
    }
    public void createNewGame(Stage menuLevelStage,Stage menuStage) {
        this.menuStage = menuStage;
        this.menuLevelStage = menuLevelStage;
        this.menuLevelStage.close();
        bombermanRun.update();
        createGameLoop();
        Stage.show();

    }
}
