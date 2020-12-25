import javax.swing.*;
import java.util.Scanner;

public class Run {

    public static void main(String[] args) {

        System.out.println("\n--------------------------------------------------------------");
        System.out.println("-------------------------| StegTools |------------------------");
        System.out.println("---------------------------| v0.6 |---------------------------");
        System.out.println("--------------------------------------------------------------");

        setUpGUI();

        /*
        System.out.println("Menu: ");
        System.out.println("0 : exit");
        System.out.println("1 : start GUI application");
        System.out.println("2 : console testing");

        Scanner sc = new Scanner(System.in);
        int option = 0;
        try {
            System.out.print("\nInput: ");
            option = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }

        while (option != 0) {

            // GUI
            if (option == 1) {
                setUpGUI();
                break;
            }
            // Console testing
            else if (option == 2) {
                ConsoleTesting.testingMenu();
            }

            try {
                System.out.println("Menu: ");
                System.out.println("0 : exit");
                System.out.println("1 : start GUI application");
                System.out.println("2 : console testing");
                System.out.print("\nInput: ");
                option = sc.nextInt();

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }*/

    }

    private static void setUpGUI() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            UIManager.getCrossPlatformLookAndFeelClassName();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        MainFrame mainFrame = new MainFrame("StegTools");
        mainFrame.setVisible(true);
    }
}

