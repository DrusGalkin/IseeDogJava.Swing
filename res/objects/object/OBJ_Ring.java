package objects.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Ring extends SuperObject {

    public OBJ_Ring(){


        name = "ring";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ring.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
