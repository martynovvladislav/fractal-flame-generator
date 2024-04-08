package edu.fractalFlame;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.createDirectory();
        controller.initializeUI();
    }
}
