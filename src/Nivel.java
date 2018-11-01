import java.util.ArrayList;


public class Nivel {
    int nivel;
    ArrayList<Alien> aliensdelnivel;

    Nivel(int nivel, int ALIEN_INIT_Y, int ALIEN_INIT_X) {

        aliensdelnivel= new ArrayList<>();
        this.nivel = nivel;
        if (this.nivel == 1) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 6; j++) {
                    Alien naveEnemiga1 = new Alien(1,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                    aliensdelnivel.add(naveEnemiga1);
                }
        }
        if (this.nivel == 2) {
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
        if(nivel==3){
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 6; j++) {
                    Alien naveEnemiga2 = new Alien(2,ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                    aliensdelnivel.add(naveEnemiga2);
                }
        }
        if(nivel==4){
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
        if(nivel==5){
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 6; j++) {
                    Alien naveEnemiga3 = new Alien(3,ALIEN_INIT_X + 22 * j, ALIEN_INIT_Y + 22 * i);
                    aliensdelnivel.add(naveEnemiga3);
                }
        }
    }
}