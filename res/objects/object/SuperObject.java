package objects.object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public int width = 48;
    public int height = 48;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    //Хит Объекта
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX =0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //Отрисовка объекта
        if (worldX + width > gp.player.worldX - gp.player.screenX &&
                worldX < gp.player.worldX + gp.player.screenX &&
                worldY + height > gp.player.worldY - gp.player.screenY &&
                worldY < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, width, height, null);
        }
    }

}
