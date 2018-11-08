package src;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Score {
    int score;
    String player;
    static String file = "Leaderscore\\Tabla.txt";

    public Score(String jugador, int puntaje) {
        player = jugador;
        score = puntaje;
    }

    public int GetPoints() {
        return score;
    }

    public String Refractor() {
        return player + ": " + score;
    }

    public void SendToFile() {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) == null) {
                writer.append("\n" + this.Refractor());
            }
            writer.write("\n" + this.Refractor());
            writer.close();

        } catch (IOException e) {
        }

    }

    public static List<Score> Ranking() {
        try {
            Score inter;
            Score inter2;
            List<Score> ranking = new LinkedList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Object[] atr = line.split(": ");
                ranking.add(new Score((String) atr[0], Integer.parseInt((String) atr[1])));
            }
            for (int i = 0; i < ranking.size() - 1; i++) {
                for (int j = i + 1; j < ranking.size(); j++) {
                    if (ranking.get(i).GetPoints() < ranking.get(j).GetPoints()) {
                        inter = ranking.get(i);
                        inter2 = ranking.get(j);
                        ranking.remove(j);
                        ranking.remove(i);
                        ranking.add(i, inter2);
                        ranking.add(j, inter);
                    }
                }
            }
            return ranking;
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void print() {
        try {
            List<Score> ranking = Ranking();
            FileWriter writer = new FileWriter(file, true);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            File temp = new File(file);
            if (temp.exists()) {
                RandomAccessFile raf = new RandomAccessFile(temp, "rw");
                raf.setLength(0);
            }
            if (ranking.size() < 10) {
                for (int i = 0; i < ranking.size(); i++) {
                    writer.write(ranking.get(i).Refractor() + "\n");
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    writer.write(ranking.get(i).Refractor() + "\n");
                }
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
