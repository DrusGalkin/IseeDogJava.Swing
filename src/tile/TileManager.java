package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[50]; // Количество плиток
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
            //Трава
            setup(1, "grass5", false);

            //Растительность
            setup(5, "bush", false);
            setup(4, "bush2", true);

            //Поля
            setup(3, "wheat", true);
            setup(2, "wheatFull", true);

            setup(14, "sand", false);
            setup(12, "sand_right", false);
            setup(13, "sand_left", false);
            setup(15, "sand_top", false);
            setup(16, "sand_connect4", false);
            setup(17, "sand_connect3", false);
            setup(18, "sand_connect1", false);
            setup(19, "sand_connect2", false);
            setup(20, "sand_start", false);

            //Забор
            setup(6, "fence_right_bottom", false);
            setup(7, "fence", false);
            setup(8, "fence_connect2", false);
            setup(9, "fence_left_bottom", false);
            setup(10, "fence_left", false);
            setup(11, "fence_right", false);


    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            String line;
            while((line = br.readLine()) != null){
                String numbers[] = line.split(",");

                for(col = 0; col < gp.maxWorldCol; col++) {
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    }
                }
                row++;
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        // Заполнение травой
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            //Отрисовка Камеры Игрока
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //СтопКамера
            if(gp.player.screenX > gp.player.worldX){
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY){
                screenY = worldY;
            }
            int rightOFFset = gp.screenWidth - gp.player.screenX;
            if(rightOFFset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOFFset = gp.screenHeight - gp.player.screenY;
            if(bottomOFFset > gp.worldWidth - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            //Отрисовка видимой области
            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                            worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.screenX > gp.player.worldX ||
                       gp.player.screenY > gp.player.worldY ||
                       rightOFFset > gp.worldWidth - gp.player.worldX ||
                       bottomOFFset > gp.worldWidth - gp.player.worldY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }


            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
