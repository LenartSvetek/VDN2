import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameWindow extends JFrame {

    JMenuBar menuBar = new JMenuBar();
    JMenu menuDatoteke;
    JMenuItem[] menuDtiems = new JMenuItem[2];
    JMenu menuIgra;
    JMenuItem[] menuIItems = new JMenuItem[3];

    Game game;
    Settings settings;
    Difficulty diff;
    endGame end;
    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuDatoteke = new JMenu("Datoteke");

        datotekaMenuActionListener datAL = new datotekaMenuActionListener();
        menuDtiems[0] = new JMenuItem("Odpri");
        menuDtiems[0].addActionListener(datAL);
        menuDtiems[1] = new JMenuItem("Shrani");
        menuDtiems[1].addActionListener(datAL);

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
        game = new Game(diff, new gameActionListener());
        add(BorderLayout.CENTER, game);

        setSize(700, 700);
        this.setVisible(true);
    }
    class itemIgra implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuIItems[0]) {
                if(end != null){
                    remove(end);
                    end = null;
                }else {
                    remove(game);
                    game = null;
                }
                game = new Game(diff, new gameActionListener());
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

    class datotekaMenuActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuDtiems[0]){
                if(end != null){
                    remove(end);
                    end = null;
                }else {
                    remove(game);
                    game = null;
                }
                game = new Game("save.txt", new gameActionListener());
                add(BorderLayout.CENTER, game);
                setVisible(true);
            }
            else if(e.getSource() == menuDtiems[1]){
                try {
                    game.saveToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class settingEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            diff = settings.diff;

            settings.setVisible(false);

            if(end != null){
                remove(end);
                end = null;
            }else {
                remove(game);
                game = null;
            }

            game = new Game(diff, new gameActionListener());
            add(BorderLayout.CENTER, game);
            setVisible(true);
        }
    }

    class gameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.gameFinished){
                remove(game);
                end = new endGame(game.sum, game.turns, game.desired);
                add(BorderLayout.CENTER, end);
                game = null;
            }
        }
    }
}