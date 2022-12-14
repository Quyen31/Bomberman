package Contruction;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

import java.io.File;

public interface Image_Game {
    String Grass = "src/main/resources/Resources/classic.png";
    String Grass1 = "src/main/resources/Resources/sprites.png";
    Image image1 = new Image(new File("src/main/resources/Resources/blue.png").toURI().toString(),256, 256, false, true);
    BackgroundImage backgroundImage1 = new BackgroundImage(image1, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
    String TEXT_LEVE1 = "src/main/resources/Resources/image_level/level1.png";
    String TEXT_LEVE2 = "src/main/resources/Resources/image_level/level2.png";

    String GAME_OVER = "src/main/resources/Resources/GAME_OVER.png";
    String GAME_WIN = "src/main/resources/Resources/GAME_WIN.png";
    Image image_classic = new Image(new File(Grass).toURI().toString());
    Image image_sprites = new Image(new File(Grass1).toURI().toString());
    Image image_text_level1 = new Image(new File(TEXT_LEVE1).toURI().toString());
    Image image_text_level2 = new Image(new File(TEXT_LEVE2).toURI().toString());

    Image[] image_text_level = {image_text_level1, image_text_level2};

    Image image_game_over = new Image(new File(GAME_OVER).toURI().toString());
    Image image_game_win = new Image(new File(GAME_WIN).toURI().toString());

    Rectangle2D[] bomberLeftRectangle = {
            new Rectangle2D(288 + 48 * 3 , 0, 48, 48),
            new Rectangle2D(288 + 48 * 4 , 0, 48, 48),
            new Rectangle2D(288 + 48 * 5 , 0, 48, 48)
    };
    Rectangle2D[] bomberRightRectangle = {
            new Rectangle2D(288 , 0, 48, 48),
            new Rectangle2D(288 + 48, 0, 48, 48),
            new Rectangle2D(288 + 48 * 2, 0, 48, 48)};
    Rectangle2D[] bomberUpRectangle = {
            new Rectangle2D(0 , 0, 48, 48),
            new Rectangle2D(48 , 0, 48, 48),
            new Rectangle2D(48 * 2 , 0, 48, 48)
    };
    Rectangle2D[] bomberDownRectangle = {
            new Rectangle2D(48 * 3 , 0, 48, 48),
            new Rectangle2D(48 * 4 , 0, 48, 48),
            new Rectangle2D(48 * 5 , 0, 48, 48)
    };

    Rectangle2D[] brickRectangle = {
            new Rectangle2D(432, 288, 48, 48),
            new Rectangle2D(480, 288, 48, 48),
            new Rectangle2D(528, 288, 48, 48)
    };

    Rectangle2D[] leftFireRectangle = {
            new Rectangle2D(144, 384, 48, 48),
            new Rectangle2D(192, 384, 48, 48),
            new Rectangle2D(240, 384, 48, 48),
    };

    Rectangle2D[] rightFireRectangle = {
            new Rectangle2D(144, 480, 48, 48),
            new Rectangle2D(192, 480, 48, 48),
            new Rectangle2D(240, 480, 48, 48),
    };

    Rectangle2D[] upFireRectangle = {
            new Rectangle2D(0, 432, 48, 48),
            new Rectangle2D(48, 432, 48, 48),
            new Rectangle2D(96, 432, 48, 48)
    };

    Rectangle2D[] downFireRectangle = {
            new Rectangle2D(0, 528, 48, 48),
            new Rectangle2D(48, 528, 48, 48),
            new Rectangle2D(96, 528, 48, 48)
    };

    Rectangle2D[] mindleFireRectangle = {
            new Rectangle2D(0, 384, 48, 48),
            new Rectangle2D(48, 384, 48, 48),
            new Rectangle2D(96, 384, 48, 48)
    };

    Rectangle2D[] bombRectangle = {
            new Rectangle2D(384, 192, 48, 48),
            new Rectangle2D(432, 192, 48, 48),
            new Rectangle2D(480, 192, 48, 48)
    };

    Rectangle2D[] FireRLRectangle = {
            new Rectangle2D(144, 432, 48, 48),
            new Rectangle2D(192, 432, 48, 48),
            new Rectangle2D(240, 432, 48, 48)
    };

    Rectangle2D[] FireUDRectnagle = {
            new Rectangle2D(0, 480, 48, 48),
            new Rectangle2D(48, 480, 48, 48),
            new Rectangle2D(96, 480, 48, 48)
    };
    Rectangle2D[] LeftBalloomRetangle = {
            new Rectangle2D(0, 48, 48, 48),
            new Rectangle2D(48, 48, 48, 48),
            new Rectangle2D(96, 48, 48, 48)

    };

    Rectangle2D[] RightBalloomRetangle = {
            new Rectangle2D(144, 48, 48, 48),
            new Rectangle2D(192, 48, 48, 48),
            new Rectangle2D(240, 48, 48, 48)
    };

    Rectangle2D[] LeftOnealRetangle = {
            new Rectangle2D(0, 96, 48, 48),
            new Rectangle2D(48, 96, 48, 48),
            new Rectangle2D(96, 96, 48, 48)
    };

    Rectangle2D[] RightOnealRetangle = {
            new Rectangle2D(144, 96, 48, 48),
            new Rectangle2D(192, 96, 48, 48),
            new Rectangle2D(240, 96, 48, 48)
    };

    Rectangle2D[] LeftDollRectangle = {
            new Rectangle2D(0, 3 * 48, 48, 48),
            new Rectangle2D(48, 3 * 48, 48, 48),
            new Rectangle2D(48 * 2, 3 * 48, 48, 48)
    };

    Rectangle2D[] RightDollRectangle = {
            new Rectangle2D(48 * 3, 3 * 48, 48, 48),
            new Rectangle2D(48 * 4, 3 * 48, 48, 48),
            new Rectangle2D(48 * 5, 3 * 48, 48, 48)
    };
    Rectangle2D[] BomberDie = {
            new Rectangle2D(576, 0, 48, 48),
            new Rectangle2D(624, 0, 48, 48),
            new Rectangle2D(672, 0, 48, 48)

    };
    Rectangle2D[] EnemyDie = {
            new Rectangle2D(528, 192, 48, 48),
            new Rectangle2D(576, 192, 48, 48),
            new Rectangle2D(624, 192, 48, 48)
    };

    Rectangle2D GrassRectangle = new Rectangle2D(288,0,48,48);

    Rectangle2D dieBalloom = new Rectangle2D(288, 48, 48, 48);

    Rectangle2D dieDoll = new Rectangle2D(48 * 6, 48 * 3, 48, 48);

    Rectangle2D dieOneal = new Rectangle2D(288, 96, 48, 48);
    Rectangle2D BombItem = new Rectangle2D(384, 384, 48, 48);
    Rectangle2D FlameItem = new Rectangle2D(432, 384, 48, 48);
    Rectangle2D SpeedItem = new Rectangle2D(480, 384, 48, 48);
    Rectangle2D Portal = new Rectangle2D(720, 0, 48, 48);
    Rectangle2D WallRectangle = new Rectangle2D(240,0,48,48);
    Rectangle2D BrickRectangle = new Rectangle2D(336,0,48,48);

    /**
     * delay l?? t???n s??? h??nh ???nh nh???p nh??y delay c??ng nh???u th?? nh???p nh??y c??ng ch???m
     * cout l?? bi???n ?????m m???i l???n bi???n ?????m t??ng ?????n m???t kho???ng nh???t ?????nh n??o ???? th?? s??? ?????i h??nh
     * Rectangle l?? b??? khung h??nh ch??? nh???t hi???n ???nh
     * image l?? ???nh l???n
     * imageView l?? hi???n ???nh ?????y ra v???i khung h??nh n??o tr??n 1 ???nh l???n
     * H??m n??y d??ng ????? ki???m so??t ????? nh???p nh??y
     * N?? ???????c k???t h???p v???i m???t v??ng loop (AnimationTime) v?? m???t bi???n count truy???n v??o
     * ????? t???o m???t ???nh ?????ng (VD Bom ... hay bomber ????? di chuy???n nh??? k???t h???p n?? v???i loop cu??? game)
     * count b??n ngo??i ph???i c?? gi???i h???n count < delay * rectangle2DS.length r???i ph???i quay l???i count = 0;
     */
    static void Animation(int delay, int count, int coordinatesX, int coordinatesY, Rectangle2D[] rectangle2DS, ImageView imageView, Image image) {
        imageView.setImage(image);
        for (int j = 0; j < rectangle2DS.length; j++) {
            if (delay * j <= count && count < delay * (j + 1)) {
                imageView.setViewport(rectangle2DS[j]);
            }
        }
        imageView.setLayoutX(coordinatesX);
        imageView.setLayoutY(coordinatesY);

    }

    /**
     *Di chuy???n ???nh sang m???t v??? tr?? kh??c
     */
    static void moveCoordinates(int nextX, int nextY, ImageView imageView) {
        imageView.setLayoutY(nextY);
        imageView.setLayoutX(nextX);
    }

    /**
     * Hi???n ???nh imageView t???i m???t khung ch??? nh???t rectangle
     * (m?? h??nh ???nh to th?? ch??? hi???n ph???n ???nh trong c??i khung ch??? nh???t ????)
     * ?????ng th???i add v??o AnchorPane
     */
    static void insertImage(int toX, int toY, ImageView imageView, Image image, Rectangle2D rectangle2D, AnchorPane pane) {
        imageView.setImage(image);
        imageView.setViewport(rectangle2D);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        pane.getChildren().add(imageView);
    }

    /**
     * Hi???n c??? m???t h??nh ???nh ch??? kh??ng c???t
     */
    static void insertImage(int toX, int toY, ImageView imageView, Image image, AnchorPane pane) {
        imageView.setImage(image);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        pane.getChildren().add(imageView);
    }
}
