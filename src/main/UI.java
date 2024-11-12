package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font BetterVCR;
    Font BetterVCR1;
    Font BetterVCR3;
    BufferedImage shadowImage;
    BufferedImage shadowImage2;

    public boolean massageOn = false;
    public boolean massageOn2 = false;
    public boolean massageOn3 = false;
    public boolean massageOn4 = false;
    public boolean massageOn5 = false;
    public boolean massageOn6 = false;
    public boolean massageOn7 = false;

    public String massage = "";
    int massageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;

    int massageLength = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            BetterVCR = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/BetterVCR.ttf"));
            BetterVCR = BetterVCR.deriveFont(Font.PLAIN, 25);

            BetterVCR1 = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/BetterVCR1.ttf"));
            BetterVCR1 = BetterVCR1.deriveFont(Font.PLAIN, 25);

            BetterVCR3 = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/BetterVCR1.ttf"));
            BetterVCR3 = BetterVCR1.deriveFont(Font.PLAIN, 80);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            BetterVCR = new Font("Arial", Font.PLAIN, 25);
        }

    }

    public void showMassage(String text){

        massage = text;
        massageOn = true;

    }
    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(BetterVCR);
        g2.setColor(Color.white);


        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            //ниче, глянуть че там будте
            TextMassage();
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScale();
        }

    }

    public void drawTitleScreen(){



        //шрифт
        g2.setFont(g2.getFont().deriveFont(BetterVCR.BOLD,95f));

        //лого
        String text = "MyLittleIsee";
        g2.setColor(Color.gray);
        int x = centerX(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        //тень
        g2.setColor(Color.white);
        g2.drawString(text, x-5, y-5);

        //шабакааа
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //меню
        if(commandNum == 0){
            g2.setColor(Color.yellow);

        }
        g2.setFont(g2.getFont().deriveFont(BetterVCR.BOLD,38f));
        String text1 = "New Game";
        x = centerX(text1);
        y += gp.tileSize*4;
        g2.drawString(text1, x, y);

        g2.setColor(Color.white);
        if(commandNum == 1){
            g2.setColor(Color.yellow);
        }

        text = "Exit";
        x = centerX(text);
        y += gp.tileSize * 1.5;
        g2.drawString(text, x, y);



    }

    public void TextMassage(){
        if(gameFinished == true){

            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(BetterVCR);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            if(massageOn2 == true){

                text = "Она прожила короткую, но счастливую жизнь";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
                g2.drawString(text, x, y);

                massageCounter++;

                if(massageCounter > 200){

                    massageCounter = 0;
                    massageOn2 = false;
                    massageOn3 = true;
                }
            }

            if(massageOn3 == true){

                text = "Это небольшая игра была о маленькой собачке Айси";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
                g2.drawString(text, x, y);

                massageCounter++;

                if(massageCounter > 200){
                    massageCounter = 0;
                    massageOn3 = false;
                    massageOn4 = true;
                }
            }

            if(massageOn4 == true){

                text = "Она любила жить и ловить ";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
                g2.drawString(text, x, y);

                text = "бабочек на полях";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = (int) (gp.screenHeight/2 + (gp.tileSize/1.5));
                g2.drawString(text, x, y);

                massageCounter++;

                if(massageCounter > 200){
                    massageCounter = 0;
                    massageOn4 = false;
                    massageOn5 = true;
                }
            }

            if(massageOn5 == true){

                text = "Однажды ее сбила машина,";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
                g2.drawString(text, x, y);
                text = "но она никогда не сдавалась.";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = (int) (gp.screenHeight/2 + (gp.tileSize/1.5)) ;
                g2.drawString(text, x, y);

                massageCounter++;

                if(massageCounter > 200){
                    massageCounter = 0;
                    massageOn5 = false;
                    massageOn6 = true;
                }
            }

            if(massageOn6 == true){

                text = "Радовалась жизни и ее семье";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
                g2.drawString(text, x, y);
                text = "до самого последнего дня.";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = (int) (gp.screenHeight/2 + (gp.tileSize/1.5)) ;
                g2.drawString(text, x, y);

                massageCounter++;

                if(massageCounter > 200){
                    massageCounter = 0;
                    massageOn6 = false;
                    massageOn7 = true;
                }
            }

            if(massageOn7 == true){

                text = "Айси, ты навсегда останешься в наших сердцах!";
                textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 - 200;
                g2.drawString(text, x, y);

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File("res/end/Isee.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (image != null) {
                    Image scaledImage = image.getScaledInstance(320, 380, Image.SCALE_SMOOTH);

                    BufferedImage scaledBufferedImage = new BufferedImage(320, 380, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = scaledBufferedImage.createGraphics();
                    g2d.drawImage(scaledImage, 0, 0, null);
                    g2d.dispose();

                    x = gp.screenWidth/2 - 320/2;
                    y = gp.screenHeight/2 - 130;
                    g2.drawImage(scaledBufferedImage, x, y, null);
                }

                massageCounter++;

                if(massageCounter > 200){
                    massageCounter = 0;
                    gp.gameThread = null;
                    gp.gameState = gp.titleState;
                }
            }
        }        else{
            g2.setFont(BetterVCR);
            g2.setColor(Color.white);

            shadowImage = new BufferedImage(
                    g2.getFontMetrics(BetterVCR1).stringWidth("Найдено x " + gp.player.hasItems) + 10,
                    g2.getFontMetrics(BetterVCR).getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D shadowGraphics = shadowImage.createGraphics();
            shadowGraphics.setFont(BetterVCR1);
            shadowGraphics.setColor(Color.black);
            shadowGraphics.drawString("Найдено x "+ gp.player.hasItems, 0, g2.getFontMetrics(BetterVCR1).getAscent());

            shadowImage2 = new BufferedImage(
                    g2.getFontMetrics(BetterVCR1).stringWidth(massage) + 10,
                    g2.getFontMetrics(BetterVCR).getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D shadowGraphics2 = shadowImage2.createGraphics();

            shadowGraphics2.setFont(BetterVCR1);
            shadowGraphics2.setColor(Color.black);
            shadowGraphics2.drawString(massage, 0, g2.getFontMetrics(BetterVCR1).getAscent());

            shadowGraphics.dispose();
            shadowGraphics2.dispose();



                    g2.drawImage(shadowImage, 16, 528, null);
                    g2.drawString("Найдено x "+ gp.player.hasItems, 15, 550);



            //Сообщение
            if(massageOn == true){

                int x = gp.screenWidth/2 - massageLength/2;
                int y = gp.screenHeight/2 - (gp.tileSize*3);

                massageLength = (int)g2.getFontMetrics().getStringBounds(massage,g2).getWidth();
                g2.drawImage(shadowImage2, x-1, y-23, null);
                g2.drawString(massage, x,y);

                massageCounter++;

                if(massageCounter > 120){
                    massageCounter = 0;
                    massageOn = false;
                }
            }
        }
    }

    public void drawPauseScale(){
        String text = "Пауза";

        int x = centerX(text);
        int y = gp.screenHeight/2;

        g2.setColor(new Color(0, 0, 0, 200)); // Альфа-канал 150 для 60% прозрачности
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }

    public int centerX(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }


}
