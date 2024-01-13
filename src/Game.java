import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Game extends JPanel {


    int n = (int)(Math.random() * 11) + 9;
    int desired = (int)(Math.random() * 60) + 10;
    int turns = (int)(Math.random() * (70 - 15)) + 15;
    PrettierButton[][] sredinskiGumbi = new PrettierButton[n][n];
    JLabel[] zgornjiLabli = new JLabel[5];

    char curr = ' ';
    char prev = ' ';
    int sum = 0;

    boolean gameFinished = false;

    Game(Difficulty diff, GameWindow.gameActionListener gameAL){

        setLayout(new BorderLayout());

        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new GridLayout(1, 5));
        add( BorderLayout.NORTH, gameInfo);
        n = (int)(Math.random() * (diff.maxSize - diff.minSize)) + diff.minSize;
        turns = (int)(Math.random() * (diff.maxTurns - diff.minTurns)) + diff.minTurns;
        desired = (int)(Math.random() * (diff.maxTarget - diff.minTarget)) + diff.minTarget;
        sredinskiGumbi = new PrettierButton[n][n];

        zgornjiLabli[0] = new JLabel("Prev: " + prev);
        zgornjiLabli[1] = new JLabel("Curr: " + curr);
        zgornjiLabli[2] = new JLabel("Sum: " + sum);
        zgornjiLabli[3] = new JLabel("Desired: " + desired);
        zgornjiLabli[4] = new JLabel("Turns: " + turns);


        for (int i = 0; i < 5; i++) {
            gameInfo.add(zgornjiLabli[i]);
        }

        JPanel sredina = new JPanel();
        sredina.setLayout(new GridLayout(n,n));
        add(BorderLayout.CENTER, sredina);

        Poslusalec1 slisimGumbke = new Poslusalec1();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sredinskiGumbi[i][j] = new PrettierButton((int)(Math.random() * 9 + 1) + "");
                sredinskiGumbi[i][j].addActionListener(gameAL);
                sredinskiGumbi[i][j].addActionListener(slisimGumbke);

                sredina.add(sredinskiGumbi[i][j]);
            }
        }
    }

    Game(String fileName, GameWindow.gameActionListener gameAL){
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));
            String[] controlStr = reader.readLine().split(" ");
            n = Integer.parseInt(controlStr[0]);
            prev = controlStr[1].charAt(0);
            prev = prev != '-'? prev : ' ';
            curr = controlStr[2].charAt(0);
            curr = curr != '-'? curr : ' ';
            turns = Integer.parseInt(controlStr[3]);
            desired = Integer.parseInt(controlStr[4]);
            sum = Integer.parseInt(controlStr[5]);
        }catch(Exception e){
            e.printStackTrace();
            return;
        }


        setLayout(new BorderLayout());

        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new GridLayout(1, 5));
        add( BorderLayout.NORTH, gameInfo);
        sredinskiGumbi = new PrettierButton[n][n];

        zgornjiLabli[0] = new JLabel("Prev: " + prev);
        zgornjiLabli[1] = new JLabel("Curr: " + curr);
        zgornjiLabli[2] = new JLabel("Sum: " + sum);
        zgornjiLabli[3] = new JLabel("Desired: " + desired);
        zgornjiLabli[4] = new JLabel("Turns: " + turns);


        for (int i = 0; i < 5; i++) {
            gameInfo.add(zgornjiLabli[i]);
        }

        JPanel sredina = new JPanel();
        sredina.setLayout(new GridLayout(n,n));
        add(BorderLayout.CENTER, sredina);

        Poslusalec1 slisimGumbke = new Poslusalec1();

        for (int i = 0; i < n; i++) {
            try {
                String line = reader.readLine();
                for (int j = 0; j < n; j++) {
                    sredinskiGumbi[i][j] = new PrettierButton(line.charAt(j) + "");
                    sredinskiGumbi[i][j].addActionListener(gameAL);
                    sredinskiGumbi[i][j].addActionListener(slisimGumbke);


                    sredina.add(sredinskiGumbi[i][j]);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        slisimGumbke.setEnabled();
    }

    void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
        writer.write(n + " " + (prev != ' '? prev : -1) + " " + (curr != ' '? curr : -1) + " " + turns + " " + desired + " " + sum + "\n");
        for (JButton[] jButtons : sredinskiGumbi) {
            for (JButton jButton : jButtons) {
                writer.write(jButton.getText());
            }
            writer.write("\n");
        }
        writer.close();
    }

    class Poslusalec1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (e.getSource() == sredinskiGumbi[i][j]) {
                        String tmp = sredinskiGumbi[i][j].getText();
                        prev = curr;
                        curr = tmp.charAt(0);
                        sum += Integer.parseInt(tmp);

                        turns--;

                        if (sum >= desired || turns == 0) {
                            gameFinished = true;
                        }

                        sredinskiGumbi[i][j].setText("X");

                        zgornjiLabli[0].setText("Prev: " + prev);
                        zgornjiLabli[1].setText("Curr: " + curr);
                        zgornjiLabli[2].setText("Sum: " + sum);
                        zgornjiLabli[4].setText("Turns: " + turns);
                        setEnabled();
                    }
                }
            }
        }

        void setEnabled() {
            int disable = n*n;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int c = j + 1, r = i + 1;
                    int current = Integer.parseInt(curr + ""), previus = 0;
                    if (prev != ' ') previus = Integer.parseInt(prev + "");

                    sredinskiGumbi[i][j].setEnabled(false);

                    char text = sredinskiGumbi[i][j].getText().charAt(0);
                    if (text != 'X') {
                        if (c % current == 0 || r % current == 0) sredinskiGumbi[i][j].setEnabled(true);
                        if (prev != ' ' && (c % previus == 0 || r % previus == 0))
                            sredinskiGumbi[i][j].setEnabled(true);

                    }
                }
            }
        }
    }
}
