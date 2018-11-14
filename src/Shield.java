import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Shield extends  Sprite{

    boolean shieldActivity=false;
    static List<Shield> shields;
    int vidaRestante;
    String fondo="src/images/Shield.png";

    private static final int SHIELD_INIT_X = 20;
    private static final int SHIELD_INIT_Y = 255;
  //  int posicionInicialEsudo_X=





    Shield(int x, int y){
        initShield(x,y);
    }

    public void initShield(int x, int y){

        ImageIcon escudo= new ImageIcon(fondo);
        setImage(escudo.getImage());
        setX(x);
        setY(y);

    }

    public void setShield(){
        shieldActivity=true;
        vidaRestante=100;
    }

    public boolean actualizacionShield(){
        if(vidaRestante==0){
            shieldActivity=false;
            return shieldActivity;
        }
        return shieldActivity;
    }

    static List<Shield> getShields(){
        shields= new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            shields.add(new Shield( SHIELD_INIT_X+ 80 * i, SHIELD_INIT_Y));
        }
        return shields;
    }

}
//setDying
