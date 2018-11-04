import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;

//Con esta clase pueden reproducir los sonidos que quieran, pero tengan en cuenta que NO SE REPRODUSEN EN SEGUNDO PLANO
//Descarguen el http://www.javazoom.net/javalayer/sources.html

public class Sound {

static String music;

    /*
    EJEMPLO:

    public static void firesound(){
    music= lo que quiran
    try {
            Reproductor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

    public static void Backgraundmusic(){
        music= "src/Sound/lfz-echoes-ncs-release.mp3";
        try {
            Reproductor();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void Reproductor() throws FileNotFoundException, IOException{
        try {
            FileInputStream direccion;


            direccion = new FileInputStream(music);

            Player player;

            BufferedInputStream bis= new BufferedInputStream(direccion);

            player = new Player(bis);
            player.play();


        }catch (JavaLayerException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    }

