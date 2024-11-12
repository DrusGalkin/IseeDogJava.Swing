package objects.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Stick extends SuperObject{

    public OBJ_Stick(){

        name = "stick";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ring.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
