import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;

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


    // Panel #2 --> cyclePanel --> FLD means field.
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

    // Panel #3 --> packagesPanel --> CHKBX means checkbox
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;
    JPanel packagesPanel;

    // Panel #4 --> detailsPanel
    JTextArea channelsAreaSport;
    JTextArea channelsAreaMovie;
    JTextArea channelsAreaDocumentary;
    JPanel detailsPanel;

    // Panel #5 --> Check and Payments
    JLabel installFeeLBL;
    JPanel feePanel;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;

    // Panel #6 --> Table (Data of subscription)

    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    // Panel #7 --> Action Panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7ActionPanel;

    // Classes and Objects

    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice = 0;
    int totalPrice;

    // Saving
    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;



    public Main(){
        // Constructor --> alt + ins

        //               ---------->    PANEL #1: Subscriber    <----------
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


        //               ---------->    PANEL #2: Cycle   <----------
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

        //          ---------->    PANEL #3: Channel's packages    <----------
        // Ps: CHKBX means 'CheckBox'

        packagesPanel = new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5,1));

        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packBorder);

        JLabel packagesLBL = new JLabel("Please select your package: ");
        sportsCHKBX = new JCheckBox("Sports package");
        moviesCHKBX = new JCheckBox("Movies package");
        docCHKBX = new JCheckBox("Documentary package");

        JButton subscriberBTN = new JButton("Subscribe");

        packagesPanel.add(packagesLBL);
        packagesPanel.add(sportsCHKBX);
        packagesPanel.add(moviesCHKBX);
        packagesPanel.add(docCHKBX);
        packagesPanel.add(subscriberBTN);

        // Checkbox Item Listener

        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportsCHKBX.isSelected()){
                    DisplaySportsChannels();

                } else {
                    channelsAreaSport.setText("");

                }

            }
        });

        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (moviesCHKBX.isSelected()){
                    DisplayMoviesChannels();
                } else {
                    channelsAreaMovie.setText("");

                }
            }
        });

        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (docCHKBX.isSelected()){
                    DisplayDocumentaryChannels();
                } else {
                    channelsAreaDocumentary.setText("");

                }
            }
        });

        subscriberBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GetSubscriberData();
                }  catch (Exception ee){

                }
            }
        });

        //          ---------->    PANEL #4: Packages details    <----------

        detailsPanel = new JPanel();
        detailsPanel.setBounds(330,230,300,500);
        detailsPanel.setLayout(new GridLayout(3,1));

        Border p4Border = BorderFactory.createTitledBorder("Available channels");
        detailsPanel.setBorder(p4Border);

        channelsAreaSport = new JTextArea(5,1);
        channelsAreaSport.setEditable(false);
        channelsAreaSport.setOpaque(false);
        channelsAreaSport.setLineWrap(true);

        channelsAreaMovie = new JTextArea(5,1);
        channelsAreaMovie.setEditable(false);
        channelsAreaMovie.setOpaque(false);
        channelsAreaMovie.setLineWrap(true);

        channelsAreaDocumentary = new JTextArea(5,1);
        channelsAreaDocumentary.setEditable(false);
        channelsAreaDocumentary.setOpaque(false);
        channelsAreaDocumentary.setLineWrap(true);

        detailsPanel.add(channelsAreaDocumentary);
        detailsPanel.add(channelsAreaMovie);
        detailsPanel.add(channelsAreaSport);


        //          ---------->    PANEL #5: Payment    <----------
        feePanel = new JPanel();
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        Border blackline5 = BorderFactory.createTitledBorder("Fee & Check");
        feePanel.setBorder(blackline5);

        installFeeLBL = new JLabel("Installation fee: ");
        packageFeeLBL = new JLabel("Packages Fee: ");
        totalFeeLBL = new JLabel("Total amount to pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);

        //          ---------->    PANEL #6: Table panel    <----------
        p6Panel = new JPanel();
        p6Panel.setBounds(645,230,515,500);
        p6Panel.setLayout(new GridLayout(3,1));

        Border border6 = BorderFactory.createTitledBorder("Our customer");
        p6Panel.setBorder(border6);

        // Table:
        // 1st: Table model

        tableModel = new DefaultTableModel();

        // 2nd: Columns
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        // 3rd: Add scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);


        //     ---------->    PANEL #7: Action Panel    <----------

        p7ActionPanel = new JPanel();
        p7ActionPanel.setBounds(860,15,300,200);

        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        p7ActionPanel.setBorder(border7);
        p7ActionPanel.setLayout(new GridLayout(4,1));

        saveBTN = new JButton("Save Subscription");
        newBTN = new JButton("New Subscription");
        loadBTN = new JButton("Load Subscription");

        p7ActionPanel.add(newBTN);
        p7ActionPanel.add(saveBTN);
        p7ActionPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription> k = LoadDataFromDisk();
            }
        });

        // Adding panels to JFrame
        setLayout(null);                // Setting null layout for JFrame
        add(subscriberPanel);           // Panel #1
        add(cyclePanel);                // Panel #2
        add(packagesPanel);             // Panel #3
        add(detailsPanel);              // Panel #4
        add(feePanel);                  // Panel #5
        add(p6Panel);                   // Panel #6
        add(p7ActionPanel);             // Panel #7
    }




                        /********** METHODS **********/


    private ArrayList<Subscription> LoadDataFromDisk() {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("d:\\myfile.dat");

        try{
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Subscription sub: s){
            DisplaySubscriptionInTable(sub);
        }

        return s;

    }

    private void DisplaySubscriptionInTable(Subscription sub) {
        // Displaying Data from disk into table
        tableModel.addRow(new Object[]{
                sub.getSubscriber().getfName(),
                sub.getSubscriber().getlName(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });

    }

    private void NewSubscription() {
        // All the fields are empty
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");
        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total amount to pay: ");

        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);
        sportsCHKBX.setSelected(false);
    }
    private void SaveSubscriptionToDisk() {
        listToSave.add(subscription);
        file = new File("d:\\myfile.dat");

        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // Saving the list of subscriptions
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void GetSubscriberData() throws ParseException {
        Date currentDate = new Date();

        //Subscriber Data:
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                Integer.parseInt(subMobile.getText()));

        // Cycle
        Date startcycle = df.parse(startCycleFLD.getText());
        Date endcycle = df.parse(endCycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startcycle),
                df.format(endcycle)
        );

        // Subscription
        subscription = new Subscription(
                Integer.parseInt(numberTVFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: " + subscription.getTotalFee() + " $");
        showPrice();
    }

    private void showPrice() {

        if (docCHKBX.isSelected())
            packagesSelectedPrice += DisplayDocumentaryChannels();

        else if(moviesCHKBX.isSelected()){
            packagesSelectedPrice += DisplayMoviesChannels();
        }else if (sportsCHKBX.isSelected()){
            packagesSelectedPrice += DisplayMoviesChannels();
        }

        packageFeeLBL.setText("Packages Fee: " + packagesSelectedPrice + " $");
        totalPrice = subscription.getTotalFee() + packagesSelectedPrice;

        totalFeeLBL.setText("Total Amount to Pay: "+ totalPrice+ " $");
    }

    private Subscriber getSubscriber() {
        return subscriber;
    }

    private int DisplayDocumentaryChannels() {
        DocumentaryChannel m1 = new DocumentaryChannel("NAT GEO", "SP", "DOC", 3);
        DocumentaryChannel m2 = new DocumentaryChannel("PBS America", "EN", "DOC", 2);
        DocumentaryChannel m3 = new DocumentaryChannel("Al Jazeera Documentary", "IN", "DOC",3);
        DocumentaryChannel m4 = new DocumentaryChannel("Canal D", "USA", "EN", 4);
        DocumentaryChannel m5 = new DocumentaryChannel("Discovery Historia", "AR", "DOC", 5);
        DocumentaryChannel m6 = new DocumentaryChannel("World Documentary", "GR", "DOC", 1);

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1); documentaryChannels.add(m2); documentaryChannels.add(m3);
        documentaryChannels.add(m4); documentaryChannels.add(m5); documentaryChannels.add(m6);

        String docChannelString = "";
        int DocumentaryPackagePrice = 0;

        for (int i = 0; i<documentaryChannels.size(); i++){
            docChannelString += "    "
                    + documentaryChannels.get(i).getChannelName() + "    "
                    + documentaryChannels.get(i).getLanguage()
                    + "    " + documentaryChannels.get(i).getPrice()
                    + "\n";

            DocumentaryPackagePrice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaDocumentary.setText(docChannelString);

        return DocumentaryPackagePrice;
    }

    private int DisplayMoviesChannels() {
        MovieChannel m1 = new MovieChannel("MBC Bundle", "EN", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema One", "EN", "MOV",5);
        MovieChannel m3 = new MovieChannel("Cinema Pro", "RU", "MOV",6);
        MovieChannel m4 = new MovieChannel("Cinema 1", "AR", "MOV",2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV",4);
        MovieChannel m6 = new MovieChannel("Film4", "FR", "MOV",2);

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1); movieList.add(m2); movieList.add(m3);
        movieList.add(m4); movieList.add(m5); movieList.add(m6);

        String movChannelString = "";
        int moviePackagePrice =0;

        for (int i = 0; i < movieList.size(); i++) {
            movChannelString += "    "
                    + movieList.get(i).getChannelName() + "    "
                    + movieList.get(i).getLanguage()
                    + "    " + movieList.get(i).getPrice()
                    + "\n";
            moviePackagePrice += movieList.get(i).getPrice();
        }
        channelsAreaMovie.setText(movChannelString);
        return moviePackagePrice;
    }

    private int DisplaySportsChannels() {
        SportsChannel s1 = new SportsChannel("AFN Sports", "EN", "SPRT",5);
        SportsChannel s2 = new SportsChannel("beIN Sports", "FR", "SPRT",3);
        SportsChannel s3 = new SportsChannel("Eleven Sports", "EN", "SPRT",8);
        SportsChannel s4 = new SportsChannel("NBA TV", "EN", "SPRT",6);
        SportsChannel s5 = new SportsChannel("NFL Network", "AR", "SPRT",3);
        SportsChannel s6 = new SportsChannel("The Ski Channel", "USA", "SPRT",1);

        ArrayList<SportsChannel> sportList = new ArrayList<>();
        sportList.add(s1); sportList.add(s2); sportList.add(s3);
        sportList.add(s4); sportList.add(s5); sportList.add(s6);

        String spChannelString = "";
        int sportPackagePrice = 0;

        for (int i = 0; i < sportList.size(); i++) {
            spChannelString += "    "
                    + sportList.get(i).getChannelName() + "    "
                    + sportList.get(i).getLanguage()
                    + "    " + sportList.get(i).getPrice()
                    + "\n";
            sportPackagePrice += sportList.get(i).getPrice();
        }
        channelsAreaSport.setText(spChannelString);
        return sportPackagePrice;
    }

    public static void main(String[] args) {
        Main mainScreen = new Main();
        mainScreen.setVisible(true);
        mainScreen.setBounds(20,10,1200,800);
    }
}