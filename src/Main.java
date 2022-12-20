import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {
    // Panel #1 --> subscriberPanel: User registration
    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;
    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;


    // Panel #2 --> cyclePanel
    // FLD means field.
    JPanel cyclePanel;
    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;


    public Main(){
        // Constructor --> alt + ins

        //              ----------> PANEL #1 <----------
        subscriberPanel = new JPanel();
        Border subscriberBorder = BorderFactory.createTitledBorder("Subscriber details");
        subscriberPanel.setBorder(subscriberBorder);
        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(4, 2));

        // JLabel
        nameLBL = new JLabel("Name: ");
        lastLBL = new JLabel("Last name: ");
        mobileLBL = new JLabel("Mobile number: ");
        cityLBL = new JLabel("City: ");

        // JTextField
        subName = new JTextField(); subName.setOpaque(false);
        subLastName = new JTextField(); subLastName.setOpaque(false);
        subMobile = new JTextField(); subMobile.setOpaque(false);
        subCity = new JTextField(); subCity.setOpaque(false);

        // Adding components to subscriberPanel... Our 1st panel
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);

        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);

        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);

        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);


        //              ----------> PANEL #2 <----------
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15,230,300,500);
        cyclePanel.setLayout(new GridLayout(14, 1));

        Border cycleBorder = BorderFactory.createTitledBorder("Cycle details");
        cyclePanel.setBorder(cycleBorder);

        // Components of cyclePanel
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: " + df.format(currentDate));

        // Start cycle date
        startCycleLBL = new JLabel("Start cycle date (DD/MM/YYYY)");
        startCycleFLD = new JTextField();

        // End cycle date
        endCycleLBL = new JLabel("End cycle date (DD/MM/YYYY)");
        endCycleFLD = new JTextField();

        // Number of TVs
        numberTVLBL = new JLabel("Number of TVs: ");
        numberTVFLD = new JTextField();

        // Adding components to cyclePanel... Our 2nd panel
        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);

        // Make opacity for fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);


        // Adding panels to JFrame
        setLayout(null); // Setting null layout for JFrame
        add(subscriberPanel);
        add(cyclePanel);








    }

    public static void main(String[] args){
        Main main = new Main();
        main.setVisible(true);
        main.setBounds(100,10,1000,800);
    }
}