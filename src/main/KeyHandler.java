package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upLeftPressed, upRightPressed, downLeftPressed, downRightPressed; // Добавлены переменные для диагональных направлений

    //debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //title state
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }

            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum=0;
                }

            }
            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }

        //play state
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

        // Проверки для диагональных направлений
        if (code == KeyEvent.VK_W && code == KeyEvent.VK_A) {
            upLeftPressed = true;
        }
        if (code == KeyEvent.VK_W && code == KeyEvent.VK_D) {
            upRightPressed = true;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_A) {
            downLeftPressed = true;
        }
        if (code == KeyEvent.VK_S && code == KeyEvent.VK_D) {
            downRightPressed = true;
        }
        //debug
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }

        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }

        // Сброс флагов для диагональных направлений
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_A) {
            upLeftPressed = false;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_D) {
            upRightPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_A) {
            downLeftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_D) {
            downRightPressed = false;
        }
    }
}
