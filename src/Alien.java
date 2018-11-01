import javax.swing.ImageIcon;
import java.util.Random;

public class Alien extends Sprite {
    public int health;
    private Bomb bomb;
    String alienImg;
    int type;
    int puntos;
    String ventaja;

    public Alien(int type,int x, int y) {
        Random ventajas= new Random();
        this.type=type;
        if(type==1){
            health=2;
            alienImg="src/images/alien.png";
            puntos= 10;
        }
        if(type==2){
            health=3;
            alienImg="src/images/NaveEnemiga2.png";
            puntos=20;
        }
        if(type==3){
            health=5;
            alienImg="src/images/NaveEnemiga3.png";
            puntos=50;
        }
        int randomNumero=ventajas.nextInt(10);
        if(randomNumero==2||randomNumero==1)
            ventaja="inmunidad";
        if(randomNumero==3||randomNumero==4||randomNumero==5 ||randomNumero==6||randomNumero==7||randomNumero==8||randomNumero==9)  //6
            ventaja="doubleDamage";
        if(randomNumero==10)
            ventaja="congelar";

        initAlien(x, y);
    }

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(alienImg);
        setImage(ii.getImage());
    }

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }

    public class Bomb extends Sprite {

        private final String bombImg = "src/images/bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombImg);
            setImage(ii.getImage());

        }

        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }
    }
}