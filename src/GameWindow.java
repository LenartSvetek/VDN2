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

    /**
     * Glavno okno v katerem je igra in menuBaarr
     */
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

    /**
     * To je action listener ki poslusa za evente na menuTab Igra
     * In potem na podlagi kateri gumb je bil kliknen reagira
     *
     */
    class itemIgra implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuIItems[0]) { // ponovna igra
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

            } else if(e.getSource() == menuIItems[1]) { // nastavitve
                settings.setVisible(true);
            }
            else if(e.getSource() == menuIItems[2]) { // zakljucek igre

                System.exit(0);
            }
        }
    }

    /**
     * To je action listener ki poslusa za evente na menuTab Datoteka
     * In potem na podlagi kateri gumb je bil kliknen reagira
     */
    class datotekaMenuActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuDtiems[0]){ // odpre shranjeno igro
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
            else if(e.getSource() == menuDtiems[1]){ // shrani igro
                try {
                    game.saveToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    /**
     * To je poslusalec ki se aktivira ko je v nastavitvah kliknen gumb done
     * In inicializira novo igro, na podlagi izbrane tezavnosti
     */
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

    /**
     * poslusalec ki ga poslem game objektu, katera poslusa za spremembo game.finished in reagira
     * in ustvari endGame objekt
     */
    class gameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.gameFinished){
                remove(game);
                end = new endGame(game.sum, game.turns, game.desired); // now we are in the endgame
                add(BorderLayout.CENTER, end);
                game = null;
            }
        }
    }
}