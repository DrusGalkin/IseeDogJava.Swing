package main;

import objects.object.OBJ_Key;
import objects.object.OBJ_Ring;
import objects.object.OBJ_Stick;
import objects.object.OBJ_Toy;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 158 * gp.tileSize;
        gp.obj[0].worldY = 73 * gp.tileSize;

        gp.obj[1] = new OBJ_Ring();
        gp.obj[1].worldX = 92 * gp.tileSize;
        gp.obj[1].worldY = 89 * gp.tileSize;

        gp.obj[2] = new OBJ_Toy();
        gp.obj[2].worldX = 279 * gp.tileSize;
        gp.obj[2].worldY = 87 * gp.tileSize;

        gp.obj[3] = new OBJ_Stick();
        gp.obj[3].worldX = 251 * gp.tileSize;
        gp.obj[3].worldY = 28 * gp.tileSize;

        gp.obj[4] = new OBJ_Toy();
        gp.obj[4].worldX = 114 * gp.tileSize;
        gp.obj[4].worldY = 160 * gp.tileSize;

        gp.obj[5] = new OBJ_Ring();
        gp.obj[5].worldX = 249 * gp.tileSize;
        gp.obj[5].worldY = 162 * gp.tileSize;


//
//        gp.obj[3] = new OBJ_Ring();
//        gp.obj[3].worldX = 22 * gp.tileSize;
//        gp.obj[3].worldY = 20 * gp.tileSize;
//
//        gp.obj[4] = new OBJ_Ring();
//        gp.obj[4].worldX = 22 * gp.tileSize;
//        gp.obj[4].worldY = 20 * gp.tileSize;
//
//        gp.obj[5] = new OBJ_Ring();
//        gp.obj[5].worldX = 22 * gp.tileSize;
//        gp.obj[5].worldY = 20 * gp.tileSize;

    }


}
