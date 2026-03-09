package andresrg.gestornotas;

import andresrg.ui.LoginFrame;

public class App {

    public static void main(String[] args) {

        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {    
        }
   
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}
