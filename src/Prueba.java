import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Prueba extends  JFrame {

String vantaja;
static int tiempo;
static Graphics g;
ImageIcon image= new ImageIcon("src/images/fondoApp.jpg");


    public static void main(String[] args) {
     //   pe();
        start();
        int contador=0;
        for (int i = 0; i <10 ; i++) {
            contador++;
            System.out.println(contador);
        }
    }
    public static void start() {
        JFrame f= new JFrame();

        f.setSize(358,350);
        f.setResizable(false);
        f.setVisible(true);

        Thread t = new Thread();
        for (int i = 10; i>=0 ; i--) {
            try {
                t.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            tiempo=i;
        }
       //g.drawImage(image.getImage(),0,0,358,350,f);
    }

    @Override
    public void paintComponents(Graphics g) {
        g.drawString("Tiempo: "+tiempo,305,18);
        g.drawImage(image.getImage(),0,0,358,350,this);
    }

    public static int pe(){
    Random ventaja= new Random();
    if(ventaja.nextInt(6)==5){
        System.out.println("hola");
    }
    System.out.println(ventaja);

    return 1;

    }

    /*
    Thread tt= new Thread();
    for (int t = 20; t>=0 ; t--) {
        tt.sleep(1000);
        tiemporestante=t;
    }
    */



}
