import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

public class Settings extends JFrame {
    Difficulty diff;
    Difficulty difficulties[];
    JComboBox<String> difficultiesCombo;

    JTextField minSizeField;
    JTextField maxSizeField;

    JTextField minTurnField;
    JTextField maxTurnField;

    JTextField minTargetField;
    JTextField maxTargetField;
    Settings(GameWindow.settingEventListener settingEL){
        setLayout(new GridLayout(5,1));

        difficulties = new Difficulty[4];
        difficulties[0] = new Difficulty("Easy", 5, 10, 20, 30, 20, 40);
        difficulties[1] = new Difficulty("Medium", 10, 20, 15, 70, 10, 70);
        difficulties[2] = new Difficulty("Hard", 15, 25, 20, 80, 30, 100);
        difficulties[3] = new Difficulty("Custom", 0, 0, 0, 0, 0, 0);
        diff = difficulties[1];

        JPanel diffPanel = new JPanel();
        JLabel difficultiesLabel = new JLabel("Težavnost(Ne pozabi klikniti enter na textfield): ");
        diffPanel.add(difficultiesLabel);
        String[] choices = {"Easy", "Medium", "Hard", "Custom"};
        difficultiesCombo = new JComboBox<String>(choices);
        difficultiesCombo.setSelectedIndex(1);
        difficultiesCombo.addActionListener(new difficultyComboAL());
        diffPanel.add(difficultiesCombo);
        add(diffPanel);

        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.LINE_AXIS));
        JLabel sizeLabel = new JLabel("Polje: ");
        sizePanel.add(sizeLabel);
        minSizeField = new JTextField(diff.minSize + "");
        minSizeField.setEnabled(false);
        sizePanel.add(minSizeField);
        sizePanel.add(new JLabel("-"));
        maxSizeField = new JTextField(diff.maxSize + "");
        maxSizeField.setEnabled(false);
        sizePanel.add(maxSizeField);
        add(sizePanel);

        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.LINE_AXIS));
        JLabel turnLabel = new JLabel("Turni: ");
        turnPanel.add(turnLabel);
        minTurnField = new JTextField(diff.minTurns + "");
        minTurnField.setEnabled(false);
        turnPanel.add(minTurnField);
        turnPanel.add(new JLabel("-"));
        maxTurnField = new JTextField(diff.maxTurns + "");
        maxTurnField.setEnabled(false);
        turnPanel.add(maxTurnField);
        add(turnPanel);

        JPanel targetPanel = new JPanel();
        targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.LINE_AXIS));
        JLabel targetLabel = new JLabel("Tarča: ");
        targetPanel.add(targetLabel);
        minTargetField = new JTextField(diff.maxTarget + "");
        minTargetField.setEnabled(false);
        targetPanel.add(minTargetField);
        targetPanel.add(new JLabel("-"));
        maxTargetField = new JTextField(diff.maxTarget + "");
        maxTargetField.setEnabled(false);
        targetPanel.add(maxTargetField);
        add(targetPanel);

        JButton done = new JButton("Done");
        done.addActionListener(settingEL);
        done.addActionListener(new doneBtnDiff());
        add(done);

        setSize(400, 200);
    }

    class doneBtnDiff implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int diffI = difficultiesCombo.getSelectedIndex();
            if(diffI == 3) {
                difficulties[3].minSize = Integer.parseInt(minSizeField.getText());
                difficulties[3].maxSize = Integer.parseInt(maxSizeField.getText());
                difficulties[3].minTurns = Integer.parseInt(minTurnField.getText());
                difficulties[3].maxTurns = Integer.parseInt(maxTurnField.getText());
                difficulties[3].minTarget = Integer.parseInt(minTargetField.getText());
                difficulties[3].maxTarget = Integer.parseInt(maxTargetField.getText());

                System.out.printf("hhj");
                diff = difficulties[3];
                return;
            };
        }
    }
    class difficultyComboAL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int diffI = difficultiesCombo.getSelectedIndex();
            if(diffI == 3) {
                diff = difficulties[3];

                minSizeField.setEnabled(true);
                maxSizeField.setEnabled(true);
                minTurnField.setEnabled(true);
                maxTurnField.setEnabled(true);
                minTargetField.setEnabled(true);
                maxTargetField.setEnabled(true);
                return;
            };
            minSizeField.setEnabled(false);
            maxSizeField.setEnabled(false);
            minTurnField.setEnabled(false);
            maxTurnField.setEnabled(false);
            minTargetField.setEnabled(false);
            maxTargetField.setEnabled(false);

            diff = difficulties[diffI];

            minSizeField.setText(diff.minSize + "");
            maxSizeField.setText(diff.maxSize + "");
            minTurnField.setText(diff.minTurns + "");
            maxTurnField.setText(diff.maxTurns + "");
            minTargetField.setText(diff.minTarget + "");
            maxTargetField.setText(diff.maxTarget + "");
        }
    }


}
