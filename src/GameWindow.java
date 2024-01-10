import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Set;

public class GameWindow extends JFrame {

    JMenuBar menuBar = new JMenuBar();
    JMenu menuDatoteke = new JMenu();
    JMenuItem menuDtiems[] = new JMenuItem[2];
    JMenu menuIgra;
    JMenuItem menuIItems[] = new JMenuItem[3];

    Game game;
    Settings settings;
    Difficulty diff;
    GameWindow() {
        menuDatoteke = new JMenu("Datoteke");

        menuDtiems[0] = new JMenuItem("Shrani");
        menuDtiems[1] = new JMenuItem("Odpri");

        menuDatoteke.add(menuDtiems[0]);
        menuDatoteke.add(menuDtiems[1]);

        menuBar.add(menuDatoteke);

        menuIgra = new JMenu("Igra");

        itemIgra eventListener = new itemIgra();

        menuIItems[0] = new JMenuItem("Ponovna igra");
        menuIItems[0].addActionListener(eventListener);
        menuIItems[1] = new JMenuItem("Spremeni nastavitve");
        menuIItems[1].addActionListener(eventListener);
        menuIItems[2] = new JMenuItem("Zaključi igro");
        menuIItems[2].addActionListener(eventListener);

        menuIgra.add(menuIItems[0]);
        menuIgra.add(menuIItems[1]);
        menuIgra.add(menuIItems[2]);

        menuBar.add(menuIgra);
        setJMenuBar(menuBar);

        JLabel levo = new JLabel("     ");
        JLabel desno = new JLabel("     ");
        JLabel spodaj = new JLabel("     ");
        add(BorderLayout.WEST, levo);
        add(BorderLayout.EAST, desno);
        add(BorderLayout.SOUTH, spodaj);

        settings = new Settings(new settingEventListener());
        diff = settings.diff;
        game = new Game(diff);
        add(BorderLayout.CENTER, game);



        setSize(700, 700);
        this.setVisible(true);
    }
    class itemIgra implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuIItems[0]) {
                remove(game);
                game = new Game(diff);
                add(BorderLayout.CENTER, game);
                setVisible(true);

            } else if(e.getSource() == menuIItems[1]) {
                settings.setVisible(true);
            }
            else if(e.getSource() == menuIItems[2]) {

                System.exit(0);
            }
        }
    }

    class settingEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            diff = settings.diff;
            System.out.println(diff.name + " " + diff.minSize + " " + diff.maxTurns);
            settings.setVisible(false);

            remove(game);
            game = new Game(diff);
            add(BorderLayout.CENTER, game);
            setVisible(true);
        }
    }
}