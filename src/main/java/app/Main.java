package app;

import controller.MainMenuController;
import model.Clinic;
import view.MainMenuView;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Clinic clinic = new Clinic();
            MainMenuView mainMenuView = new MainMenuView();
            new MainMenuController(clinic, mainMenuView);
            mainMenuView.setVisible(true);
        });
    }
}
