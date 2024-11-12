package objects.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Toy extends SuperObject{

    public OBJ_Toy(){
        this.width = 48;
        this.height = 48;

        name = "toy_sheep";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ring.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
