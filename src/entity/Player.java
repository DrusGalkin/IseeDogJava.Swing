package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasItems = 0;
    int stopCounter = 0;

    public Player (GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //Хитбокс
        solidArea = new Rectangle();
        //Размеры хитбокса
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        //Позиция Игрока
        worldX = gp.tileSize * 147;
        worldY = gp.tileSize * 79;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/players/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/players/up-2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/players/up-3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/players/down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/players/down-2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/players/down-3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/players/left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/players/left-2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/players/left-3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/players/right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/players/right-2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/players/right-3.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
            } else if (keyH.downPressed == true){
                direction = "down";
            }if(keyH.leftPressed == true){
                direction = "left";
            } else if (keyH.rightPressed == true){
                direction = "right";
            } if(keyH.leftPressed == true && keyH.upPressed == true){
                direction = "up-left";
            } else if (keyH.rightPressed == true && keyH.upPressed == true){
                direction = "up-right";
            } if(keyH.leftPressed == true && keyH.downPressed == true){
                direction = "down-left";
            } else if (keyH.rightPressed == true && keyH.downPressed == true){
                direction = "down-right";
            }

            //Проверка на колизию игрока
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //Проверка на коллизию объекта
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "up-right":
                        worldX += speed;
                        worldY -= speed;
                        break;
                    case "up-left":
                        worldX -= speed;
                        worldY -= speed;
                        break;
                    case "down-right":
                        worldX += speed;
                        worldY += speed;
                        break;
                    case "down-left":
                        worldX -= speed;
                        worldY += speed;
                        break;
                }
            }



                // Счетчик анимации
            spriteCounter++;
            if(spriteCounter>5){ // Скорость анимации
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            else{
                stopCounter++;

                if(stopCounter == 30){
                    spriteNum = 1;
                    stopCounter = 0;
                }
            }
        }
    }
    public void pickUpObject(int i){

        if(i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){

                case "ring":
                    hasItems++;
                    gp.obj[i] = null;
                    gp.ui.showMassage("Что-то очень знакомое..");
                    break;
                case "toy_sheep":
                    hasItems++;
                    gp.obj[i] = null;
                    gp.ui.showMassage("Когда-то он был новый..");
                    break;
                case "stick":
                    hasItems++;
                    gp.obj[i] = null;
                    gp.ui.showMassage("Как же было весело!!");
                    break;
                case "home":
                    if(hasItems == 5){
                        gp.obj[i] = null;
                        hasItems = 0;
                        gp.ui.gameFinished = true;
//                        gp.stopMusic();]
                        gp.ui.massageOn2 = true;
                    } else {
                        gp.ui.showMassage("Собери все 5 предметов.");
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                } else if(spriteNum == 2){
                    image = up2;
                } else if(spriteNum == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                } else if(spriteNum == 2){
                    image = down2;
                } else if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                } else if(spriteNum == 2){
                    image = left2;
                } else if(spriteNum == 3){
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                } else if(spriteNum == 2){
                    image = right2;
                } else if(spriteNum == 3){
                    image = right3;
                }
                break;
            case "down-left":
                if(spriteNum == 1){
                    image = left1;
                } else if(spriteNum == 2){
                    image = left2;
                } else if(spriteNum == 3){
                    image = left3;
                }
                break;
            case "down-right":
                if(spriteNum == 1){
                    image = right1;
                } else if(spriteNum == 2){
                    image = right2;
                } else if(spriteNum == 3){
                    image = right3;
                }
                break;
            case "up-left":
                if(spriteNum == 1){
                    image = left1;
                } else if(spriteNum == 2){
                    image = left2;
                } else if(spriteNum == 3){
                    image = left3;
                }
                break;

            case "up-right":
                if(spriteNum == 1){
                    image = right1;
                } else if(spriteNum == 2){
                    image = right2;
                } else if(spriteNum == 3){
                    image = right3;
                }
                break;
            case "break": image = down1; break;
        }

        int x = screenX;
        int y = screenY;

        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }
        int rightOFFset = gp.screenWidth - screenX;
        if(rightOFFset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOFFset = gp.screenHeight - screenY;
        if(bottomOFFset > gp.worldWidth - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }


        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
