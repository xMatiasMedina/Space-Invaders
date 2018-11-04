
import java.util.ArrayList;


public class Nivel{
    public static ArrayList<Alien> aliensdelnivel;
    private static int ALIEN_INIT_Y;
    private static int ALIEN_INIT_X;


    Nivel(int nivel, int ALIEN_INIT_Y, int ALIEN_INIT_X) {
        Nivel.ALIEN_INIT_Y = ALIEN_INIT_Y;
        Nivel.ALIEN_INIT_X = ALIEN_INIT_X;
        aliensdelnivel = new ArrayList<>();
        setNivel(nivel);
    }

    public void setNivel(int nivel) {
        switch(nivel){
            case 1:
                Nivel1();
                break;
            case 2:
                Nivel2();
                break;
            case 3:
                Nivel3();
                break;
            case 4:
                Nivel4();
                break;
            case 5:
                Nivel5();
                break;
        }
    }
    public static void Nivel1(){
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 6; j++) {
                    Alien naveEnemiga1 = new Alien(1,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                    aliensdelnivel.add(naveEnemiga1);
                }
        }

    public static void Nivel2(){
        int i = 0;
        int j = 0;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 6; j++) {
                Alien naveEnemiga1 = new Alien(1,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                aliensdelnivel.add(naveEnemiga1);
            }
        }
        for (i=2 ;i < 3; i++) {
            for (j=0; j < 6; j++) {
                Alien naveEnemiga2 = new Alien(2,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                aliensdelnivel.add(naveEnemiga2);
            }
        }
    }
    public static void Nivel3(){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++) {
                Alien naveEnemiga2 = new Alien(2,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                aliensdelnivel.add(naveEnemiga2);
            }
    }
    public static void Nivel4(){
        int i = 0;
        int j = 0;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 6; j++) {
                Alien naveEnemiga2 = new Alien(2,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                aliensdelnivel.add(naveEnemiga2);
            }
        }
        for (i=2 ;i < 3; i++) {
            for (j=0; j < 6; j++) {
                Alien naveEnemiga3 = new Alien(3,ALIEN_INIT_X + 22 * j, ALIEN_INIT_Y + 22 * i);
                aliensdelnivel.add(naveEnemiga3);
            }
        }
    }
    public static void Nivel5(){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++) {
                Alien naveEnemiga3 = new Alien(3,ALIEN_INIT_X + 22 * j, ALIEN_INIT_Y + 22 * i);
                aliensdelnivel.add(naveEnemiga3);
            }
    }
}