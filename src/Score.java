

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Score {
    int score;
    String player;
    static String file = "src\\Leaderscore\\Tabla.txt";

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

    private static List<Score> Ranking() {
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
    public static String RankingString(){
        String devolver = "";
        List<Score> ranking = Ranking();
        for (int i = 0; i < ranking.size(); i++){
            devolver = devolver + ranking.get(i).Refractor() + " | ";
        }
        return devolver;
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
                    if (i < ranking.size()-1) {
                        writer.write(ranking.get(i).Refractor() + "\n");
                    }else {
                        writer.write(ranking.get(i).Refractor());
                    }
                    }
            } else {
                for (int i = 0; i < 10; i++) {
                    if (i < ranking.size()-1) {
                        writer.write(ranking.get(i).Refractor() + "\n");
                    }else {
                        writer.write(ranking.get(i).Refractor());
                    }
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static class PrintScore extends JFrame implements ActionListener {
        JTextField nombre;
        JLabel name;
        String devolver;

        public PrintScore(){
            JFrame score = new JFrame();

            score.setVisible(true);
            score.setResizable(false);
            score.setSize(450, 150);// uds ya sabe q hacer
            score.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            score.setLocationRelativeTo(null);

            JButton cuadro = new JButton();
            JPanel panel = new JPanel();

            JLabel texto = new JLabel("Ingrese su nombre:\t");
            name= new JLabel("Su nombre es:\t");
            nombre = new JTextField(30);
            nombre.addActionListener(this);

            cuadro.add(nombre);
            panel.add(texto);
            panel.add(cuadro);
            panel.add(name);
            score.add(panel);

            score.setVisible(true);

            paintComponents(getGraphics());
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==nombre){
                name.setText("Su nombre es:\t" + nombre.getText());
                devolver = nombre.getText();
            }
        }
        public String GetName(){
            return devolver;
        }
    }
}
