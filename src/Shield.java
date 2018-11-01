import javax.swing.*;

public class Shield extends  Sprite{

    boolean shieldActivity=false;
    int shields=4;
    int vidaRestante;
    String fondo="src/images/Shield.jpg";

  //  int posicionInicialEsudo_X=
    private int posicionInicialEsudo_Y=250;




    Shield(){
        initShield();
    }

    public void initShield(){

        ImageIcon escudo= new ImageIcon(fondo);
        setImage(escudo.getImage());
       // setX();
        setY(posicionInicialEsudo_Y);

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

}
