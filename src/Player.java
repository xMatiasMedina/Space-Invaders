import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    int health=3;
    int damage=1;


    private final int START_Y = 280;
    private final int START_X = 270;

    public  String playerImg = "src/images/player.png";
    private int width;

    //Ventajas:

    //Shield
    boolean inmunidad = false;
    //Double Damage
    boolean doubleDamage=false;
    //Congelar
    boolean congelar=false;
    //LosTres
    int tiemporestante;



    public Player() { initPlayer(); }


    public void setDamage(boolean doubleDamage,int damage, int tiempo) {
        this.doubleDamage=doubleDamage;

        this.damage = damage;
        tiemporestante=tiempo;

        Timeri();
    }
    public void setInmunidad(boolean inmunidad,int tiempo) {
        this.inmunidad = inmunidad;
        tiemporestante=tiempo;
        Timeri();
    }

    public void setCongelar(boolean congelar, int tiempo){
        this.congelar=congelar;
        tiemporestante=tiempo;
        Timeri();
    }

    public void Timeri(){
        Timer timer= new Timer();
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


    public boolean actualizacionDouble() {
        if (tiemporestante == 0) {
            doubleDamage = false;
            damage = 1;
            return doubleDamage;
        }
        System.out.println("TiempoRestante"+tiemporestante);
        return doubleDamage;
    }
    public boolean actualizacionInmunidad(){
        if(tiemporestante==0){
            inmunidad=false;
            return inmunidad;
        }
        return inmunidad;
    }
    public boolean actualizacionCongelar(){
        if(tiemporestante==0){
            congelar=false;
            return congelar;
        }
        return congelar;
    }
    //*************************************************************************************************
    private void initPlayer() {

        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void act() {

        x += dx;

        if (x <= 2) {
            x = 2;
        }

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 3;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
}