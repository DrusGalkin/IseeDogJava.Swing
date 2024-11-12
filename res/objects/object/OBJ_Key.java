package objects.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Key extends SuperObject{



    public OBJ_Key(){
        this.width = 350;
        this.height = 450;
        solidArea = new Rectangle(0, 0, 350, 450);

        name = "home";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hop1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
