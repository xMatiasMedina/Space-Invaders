import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Ovni extends Sprite {

    int health;
    boolean ufo=false;
    String ovnif=("src/images/UFO.png");
    private final int START_Y = 30;
    private final int START_X = 250;
    int tiemporestante;
    int puntos= (int) (Math.random()*250) + 50;


    public Ovni(){initOvni();}
    public void initOvni(){

        setX(START_X);
        setY(START_Y);
        ImageIcon ovni= new ImageIcon("src/images/ufo.png");
        setImage(ovni.getImage());
    }

    public void setUFO(){
        ufo=true;
        health=1;
        setX(START_X);
        setY(START_Y);
    }

    public void act(int direction) {

        this.x += direction;
    }

    public void Timeris(){
        java.util.Timer timer= new Timer();
        TimerTask task= new TimerTask() {
            @Override
            public void run() {
                if(!(tiemporestante<=0)) {
                    tiemporestante-=1;
                }
            }
        };
        timer.scheduleAtFixedRate(task,500,1000);
    }

}
