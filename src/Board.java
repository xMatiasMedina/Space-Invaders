
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Commons {

    private int nivel=1;
    boolean alive= true;

    private Dimension d;

    //
    private Ovni ovni;
    //
    private ArrayList<Alien> aliens;
    private Player player;
    private Shot shot;
    private ArrayList<Shield> shields;
    Score score;
    String playerN;

    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 5;

    private int direction = -1;
    private int deaths = 0;
    private int puntos;
    int contador=0;

    ImageIcon fondo= new ImageIcon("src/images/fondoApp.jpg");

    //Ventajas:
        //Shield
        ImageIcon inmunidadImg= new ImageIcon("src/images/playerInmunidad.png");
        ImageIcon inmunidadpost= new ImageIcon("src/images/ShieldPost.png");
        //DobleD
        ImageIcon dobleDpost= new ImageIcon("src/images/DobleD.png");

    private boolean ingame = true;
    private final String explImg = "src/images/ExplosionNueva.png";
    private String message = "Game Over";
    private Thread animator;


    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        gameInit();
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    public void gameInit() {

        Nivel nivelactual = new Nivel(nivel,ALIEN_INIT_Y,ALIEN_INIT_X);
        aliens=nivelactual.aliensdelnivel;

        player = new Player();
        shot = new Shot();
        ovni= new Ovni();

        shields= (ArrayList<Shield>) Shield.getShields();
        for (Shield i: shields) {
            i.setShield();
        }

        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }
    public void drawFondo(Graphics g){

        g.drawImage(fondo.getImage(),0,0,getWidth(),getHeight(),this);
    }

    public void drawAliens(Graphics g) {

        Iterator it = aliens.iterator();

        for (Alien alien: aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

      if (player.isVisible()) {
            player.playerImg="src/images/player.png";
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }


        if (player.isDying()) {

            player.die();
            ingame = false;
        }
    }
    void drawShield(Graphics g,Shield i){
        if(i.shieldActivity){
            // shield.fondo="src/images/playerShield.png";
            g.drawImage(i.getImage(),i.x,i.y,this);
        }
    }
    void drawInmunidad(Graphics g){
        if(player.inmunidad) {
            g.drawImage(inmunidadpost.getImage(),165,295,this);//:y:309, antes de todo
            g.drawImage(inmunidadImg.getImage(), player.getX(), player.getY(), this);
        }
    }
    void drawDobleDd(Graphics g){
        if(player.doubleDamage){
            g.drawImage(dobleDpost.getImage(),145,295,this);//y:300
        }
    }

    public void drawShot(Graphics g) {

        if (shot.isVisible() && shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void drawUfo(Graphics g){
        if(ovni.isVisible()){
            ovni.ovnif="S";
            g.drawImage(ovni.getImage(),ovni.getX(),ovni.getY(),this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.red);

        if (ingame) {
            drawFondo(g);
            drawAliens(g);
            g.drawString("Score: "+puntos,150,18);
            g.drawString("Vida: "+player.health,5,18);
            g.drawString("Nivel: "+nivel,305,18);

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawPlayer(g);
            //
            if(ovni.ufo){drawUfo(g);}
            //PowerUps
            for (Shield i: shields) {
                if(i.shieldActivity){drawShield(g,i);}
            }
            if(player.inmunidad){g.drawString("Inmunidad: "+player.tiemporestante,5,72); drawInmunidad(g);}
            if(player.doubleDamage){g.drawString("Damage x2: "+player.tiemporestante,5, 90); drawDobleDd(g);}
            if(player.congelar){g.drawString("Congelar: "+ player.tiemporestante, 5, 108);}
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    private void RedoArea(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Graphics g = this.getGraphics();
        g.clearRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
    }
    public void gameOver() throws InterruptedException {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        this.RedoArea();

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Game over", (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);

        this.RedoArea();

        g.drawString("Score: " + puntos, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);

        this.RedoArea();





        /**JFrame frame = new JFrame();
         JLabel label = new JLabel("Introduzca su nombre: ");
         JTextField field = new JTextField();**/

    }

    public void animationCycle() throws InterruptedException {
        //Ventajas: DobleDan & Inmunidad
        if (player.tiemporestante == 0) {
            player.actualizacionInmunidad();
        }
        if (player.tiemporestante == 0) {
            player.actualizacionDouble();
        }
        if (player.tiemporestante == 0) {
            player.actualizacionCongelar();
        }

        //Fin de Juego o nivel
        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            nivel++;
            if (nivel > 5) {
                ingame = false;
                message = "Game won!";
            }

            deaths = 0;
            gameInit();


        }

        // player
        player.act();
        if(ovni.tiemporestante==1){
            ovni.setUFO();
        }


        // shot


        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();


            for (Alien alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {

                    //
                    if(ovni.ufo&& ovni.tiemporestante==0){
                        int ovniX=ovni.getX();
                        int ovniY=ovni.getY();
                        if(shotX>=(ovniX)&& shotX<=(ovniX+ALIEN_WIDTH) && shotY>=(ovniY) & shotY<=(ovniY + ALIEN_HEIGHT)){
                            puntos += ovni.puntos;
                            ovni.ufo=false;
                        }
                    }
                    //
                    if (shotX >= (alienX)
                            && shotX <= (alienX + ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + ALIEN_HEIGHT)) {
                        alien.health -= player.damage;
                        contador++;//VERRRRRRRRRRRRRRRRRRRRRRRR
                        System.out.println(contador);
                        if (alien.health <= 0) {
                            puntos += alien.puntos;
                            ImageIcon ii = new ImageIcon(explImg);
                            alien.setImage(ii.getImage());
                            alien.setDying(true);
                            //
                            if(!ovni.ufo) {
                                ovni.tiemporestante = 30;
                                ovni.Timeris();
                            }
                            //
                            deaths++;
                            if (contador >= 4) {
                                if ("inmunidad".equals(alien.ventaja)) {
                                    if (!player.doubleDamage && !player.congelar && player.tiemporestante == 0) {
                                        player.setInmunidad(true, 5);
                                        System.out.println("inmunidad");
                                        contador = 0;
                                    }
                                }
                                if ("doubleDamage".equals(alien.ventaja)) {
                                    if (!player.inmunidad && !player.congelar && player.tiemporestante == 0) {
                                        player.setDamage(true, 2, 5);// CAMBIAR
                                        System.out.println("dobleD");
                                        contador = 0;

                                    }
                                }

                                if ("congelar".equals(alien.ventaja)) {
                                    if (!player.inmunidad && !player.doubleDamage && player.tiemporestante == 0) {
                                        player.setCongelar(true, 5);
                                        System.out.println("Congelar");
                                        contador = 0;
                                    }
                                }

                            }
                        }
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }


        // aliens

        //********************************************************************************
        if (!player.congelar) {
            //*******************************************************************************

            for (Alien alien : aliens) {

                int x = alien.getX();

                if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {

                    direction = -1;
                    Iterator i1 = aliens.iterator();

                    while (i1.hasNext()) {

                        if(ovni.ufo){
                            ovni.act(direction);
                        }

                        Alien a2 = (Alien) i1.next();
                        a2.setY(a2.getY() + GO_DOWN);
                    }
                }

                if (x <= BORDER_LEFT && direction != 1) {

                    direction = 1;

                    Iterator i2 = aliens.iterator();

                    while (i2.hasNext()) {
                        if(ovni.ufo){
                            ovni.act(-direction);
                        }

                        Alien a = (Alien) i2.next();
                        a.setY(a.getY() + GO_DOWN);
                    }
                }
            }

            Iterator it = aliens.iterator();

            while (it.hasNext()) {

                Alien alien = (Alien) it.next();

                if (alien.isVisible()) {

                    int y = alien.getY();

                    if (y > GROUND - ALIEN_HEIGHT) {
                        ingame = false;
                        message = "Invasion!";
                    }

                    alien.act(direction);
                }
            }

        //*******************************************************************************
        }
        //*******************************************************************************
        // bombs

        if(!player.congelar) {
            Random generator = new Random();

            for (Alien alien : aliens) {

                int shot = generator.nextInt(15);
                Alien.Bomb b = alien.getBomb();

                if (shot == CHANCE && alien.isVisible() && b.isDestroyed()) {

                    b.setDestroyed(false);
                    b.setX(alien.getX());
                    b.setY(alien.getY());
                }


                int bombX = b.getX();
                int bombY = b.getY();
                int playerX = player.getX();
                int playerY = player.getY();


                //******************************************************************************

                for (Shield shield: shields) {
                    int shieldY = shield.getY();
                    int shieldX= shield.getX();
                    if (shield.shieldActivity && !b.isDestroyed()) {
                        if (bombX >= (shieldX)
                                && bombX <= (shieldX + SHIELD_WIDTH)
                                && bombY >= (shieldY)
                                && bombY <= (playerY + SHIELD_HEIGHT)) {
                            shield.vidaRestante -= 2;
                            if (shield.vidaRestante == 0) {
                                shield.actualizacionShield();
                            }
                            b.setDestroyed(true);
                        }
                    }
                }

                //******************************************************************************

                if (player.isVisible() && !b.isDestroyed()) {

                    if (bombX >= (playerX)
                            && bombX <= (playerX + PLAYER_WIDTH)
                            && bombY >= (playerY)
                            && bombY <= (playerY + PLAYER_HEIGHT)) {
                        if (player.inmunidad) {
                        }//player.tiemporestante--;
                        else {
                            player.health--;
                        }
                        if (player.health == 0) {
                            ImageIcon ii = new ImageIcon(explImg);
                            alive = false;
                            player.setImage(ii.getImage());
                            player.setDying(true);
                           // Sound.DeathSound();--------------------------------------
                        }
                        b.setDestroyed(true);
                    }
                }


                if (!b.isDestroyed()) {

                    b.setY(b.getY() + 1);

                    if (b.getY() >= GROUND - BOMB_HEIGHT) {
                        b.setDestroyed(true);
                    }
                }
            }
            //******************************************************************
        }
        //******************************************************
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();

            try {
                animationCycle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }

        try {
            gameOver();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Sound.Backgraundmusic();// Demostracion----------------------------------------------
        Score.PrintScore pScore = new Score.PrintScore();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playerN = pScore.GetName();
        score = new Score(playerN, this.puntos);
        score.SendToFile();
        Score.print();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
}