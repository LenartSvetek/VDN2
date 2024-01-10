import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {


    int n = (int)(Math.random() * 11) + 9;
    int desired = (int)(Math.random() * 60) + 10;
    int turns = (int)(Math.random() * (70 - 15)) + 15;
    JButton sredinskiGumbi[][] = new JButton[n][n];
    JLabel zgornjiLabli[] = new JLabel[5];

    char curr = ' ';
    char prev = ' ';
    int sum = 0;
    Game(){

    }



    Game(Difficulty diff){

        setLayout(new BorderLayout());

        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new GridLayout(1, 5));
        add( BorderLayout.NORTH, gameInfo);
        n = (int)(Math.random() * (diff.maxSize - diff.minSize)) + diff.minSize;
        turns = (int)(Math.random() * (diff.maxTurns - diff.minTurns)) + diff.minTurns;
        desired = (int)(Math.random() * (diff.maxTarget - diff.minTarget)) + diff.minTarget;
        sredinskiGumbi = new JButton[n][n];

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
                sredinskiGumbi[i][j] = new JButton((int)(Math.random() * 9 + 1) + "");
                sredinskiGumbi[i][j].addActionListener(slisimGumbke);

                sredina.add(sredinskiGumbi[i][j]);
            }
        }
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

                        if (sum >= desired || turns == 0) System.exit(turns);

                        turns--;

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
