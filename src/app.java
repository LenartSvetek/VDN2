import javax.swing.*;

public class app{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        new GameWindow();
    }
}