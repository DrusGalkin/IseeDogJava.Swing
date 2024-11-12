package main;

import entity.Player;
import objects.object.SuperObject;
import tile.TileManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // Параметры Экрана
    final int originalTitleSize = 16; //16x16 пикселей
    final int scale = 3; // Маштабирование

    public final int tileSize = originalTitleSize * scale; // 48x48 пикселей
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize *  maxScreenCol; // 960px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    //Параметры Карты
    public final int maxWorldCol = 300;
    public final int maxWorldRow = 200;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //ПолноЭкранный режим
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    //FPS
    int FPS = 60;

    //Файлы
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //Игрок и объекты
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];

    //Состояния игры
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        gameState = titleState;

        tempScreen = new BufferedImage( screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        setFullScreen();
    }
    public void setFullScreen(){

        // Подгон экранa
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //Полный экран по ширине и высоте
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.0166666 СЕК
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1 UPDATE
            update();

            // 2 DRAW
            drawToTempScreen();
            drawToScreen();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime +=drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){

        if(gameState == playState){
            player.update();
        }
        if(gameState == pauseState){
            //ниче
        }
    }

    public void drawToTempScreen(){

        //debug
        long drawStart = 0;

        if(keyH.checkDrawTime == false){
            drawStart = System.nanoTime();
        }

        //title screen

        if(gameState == titleState){

            ui.draw(g2);

        }else{

            // tile
            tileM.draw(g2);
            //object
            for(int i = 0; i< obj.length; i++){
                if(obj[i] !=null){
                    obj[i].draw(g2, this);
                }
            }
            //player
            player.draw(g2);

            //UI

            ui.draw(g2);
        }

        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setColor(Color.WHITE);
            g2.drawString("Draw Tile: " + passed, 10, 400);
            System.out.println("Draw Tile: " + passed);
        }
    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
    }

    public void drawToScreen(){

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

}
