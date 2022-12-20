import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main extends JFrame {
    // Constructor --> alt + ins

    // Panel1: User registration
    JPanel subscriberPanel;

    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;


    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    public Main(){

        subscriberPanel = new JPanel();
        Border panel1Title = BorderFactory.createTitledBorder("Subscriber detail");
        subscriberPanel.setBorder(panel1Title);
        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(4, 2));

        // JLabel
        nameLBL = new JLabel("Name: ");
        lastLBL = new JLabel("Last name: ");
        mobileLBL = new JLabel("Mobile number: ");
        cityLBL = new JLabel("City: ");

        // JTextField
        subName = new JTextField();
        subLastName = new JTextField();
        subMobile = new JTextField();
        subCity = new JTextField();

        // Adding components to subscriberPanel... Our 1st panel
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);

        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);

        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);

        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);


        // Adding panels to JFrame
        setLayout(null); // Setting null layout for JFrame
        add(subscriberPanel);



    }

    public static void main(String[] args){
        Main main = new Main();
        main.setVisible(true);
        main.setBounds(100,10,1000,800);
    }
}