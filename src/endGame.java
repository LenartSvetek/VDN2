import javax.swing.*;

public class endGame extends JPanel {
    /**
     * To je panel v katerem se prikazujejo rezultati igre
     *
     * @param sum Pridobljene tocke
     * @param turns Stevilo korakov ki jih je ostalo
     * @param target Zazeljeno stevilo tock
     */
    endGame(int sum, int turns, int target){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel message = new JLabel(sum >= target? "Bravo zmagali ste" : "Več sreče prihodnjič!");
        add(message);

        JLabel turnLabel = new JLabel("Turns: " + turns);
        add(turnLabel);

        JLabel sumLabel = new JLabel("Točke: " + sum);
        add(sumLabel);

        JLabel targetLabel = new JLabel("Zahtevane točke: " + target);
        add(targetLabel);
    }
}
