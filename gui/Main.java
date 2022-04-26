import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Main {
    static JTable table = new JTable();
    public static void main(String[] args) throws Exception
    {
        Controller controller = new Controller();
        final JFrame frame=new JFrame("Marauders Music Listening Analysis");
        frame.setSize(550,600);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(1,1));
        JTabbedPane tabs=new JTabbedPane();

        // ABOUT TAB
        JLabel aboutHeader=new JLabel("About", SwingConstants.CENTER);
        JLabel teamNumber=new JLabel("This project was created by: ", SwingConstants.CENTER);
        JLabel teamMember1=new JLabel("Team Member 1: Claire \n", SwingConstants.CENTER);
        JLabel teamMember2=new JLabel("Team Member 2: Emilia\n", SwingConstants.CENTER);
        JLabel teamMember3=new JLabel("Team Member 3: Sanjana\n", SwingConstants.CENTER);
        JLabel teamMember4=new JLabel("Team Member 4: Stella\n", SwingConstants.CENTER);
        JLabel productInfo1 = new JLabel("Welcome to the Marauders Music Listening Analysis application!" ,SwingConstants.CENTER);
        JLabel productInfo2 = new JLabel("This project creates listening statistics and analysis based on Lastfm data,", SwingConstants.CENTER);
        JLabel productInfo3 = new JLabel("an application that records music listening data from various applications such as Spotify, Apple Music, and YouTube. ", SwingConstants.CENTER);
        JLabel productInfo4 = new JLabel("The database was constructed from the last 5000 scrobbles of Lastfm user profiles ", SwingConstants.CENTER);
        JLabel productInfo5 = new JLabel("beetsnstuff, cirellisaurus, joneskimchi, jordono, nintendies, qwertywert42, and wvelyn.", SwingConstants.CENTER);
        JLabel productInfo6 = new JLabel("These users are members of the Album Listening Club at ASU.", SwingConstants.CENTER);

        //JTextArea productInfo=new JTextArea("  These users are members of the Album Listening Club at ASU.");
        //productInfo.setLineWrap(true);
        JPanel aboutPage=new JPanel();


 
        //BoxLayout boxLayout = new BoxLayout();
        aboutPage.setLayout(new GridLayout(0,1,0, 0));
        aboutPage.add(productInfo1);
        aboutPage.add(aboutHeader);
        aboutPage.add(teamNumber);
        aboutPage.add(teamMember1);
        aboutPage.add(teamMember2);
        aboutPage.add(teamMember3);
        aboutPage.add(teamMember4);
        aboutPage.add(productInfo2);
        aboutPage.add(productInfo3);
        aboutPage.add(productInfo4);
        aboutPage.add(productInfo5);
        aboutPage.add(productInfo6);
        JPanel aboutPageTemp=new JPanel(new GridLayout(1,1));
        
        aboutPageTemp.add(aboutPage, BorderLayout.CENTER);
        tabs.addTab("About",aboutPageTemp);

        frame.add(tabs, BorderLayout.CENTER);

        // Personal Data Analysis Tab
        GridLayout gridLayout = new GridLayout(4, 2);
        String[] personalDataAnalysisOptions = {"List of top 15 most listened artists for user", "List of top 15 most listened albums for user", "List of top 15 most listened songs for user","Listens on the tracks of an album for a user", "Top tracks from artist for user","Most listened to albums from artist"};
        JComboBox personalDataAnalysisOptionsComboBox = new JComboBox(personalDataAnalysisOptions);
        personalDataAnalysisOptionsComboBox.setSelectedIndex(0);
        JLabel inputNeeded = new JLabel("User Name");
        JTextField textInput = new JTextField(30);
        
        JLabel inputNeeded2 = new JLabel("Additional Input");
        inputNeeded2.setVisible(false);
        JTextField textInput2 = new JTextField(30);
        textInput2.setVisible(false);
        DefaultTableModel updatedTableModel = new DefaultTableModel(0,0);
        JTable updatedDataTable=new JTable(updatedTableModel);
        JLabel errorLabel = new JLabel("No data found! Check the input.");
        //textInput2.setEditable(false);
        personalDataAnalysisOptionsComboBox.addActionListener(new ActionListener()

            {
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    String inputOption = (String)cb.getSelectedItem();
                    if(inputOption.equals("List of top 15 most listened artists for user") ) 
                    {
                        textInput2.setVisible(false);
                        inputNeeded2.setVisible(false);
                    }
                    
                    if(inputOption.equals("List of top 15 most listened albums for user") ) 
                    {
                        textInput2.setVisible(false);
                        inputNeeded2.setVisible(false);
                    }
                    
                    if(inputOption.equals("List of top 15 most listened songs for user") ) 
                    {
                        textInput2.setVisible(false);
                        inputNeeded2.setVisible(false);
                    }
                    if(inputOption.equals("Listens on the tracks of an album for a user") ) 
                    {
                        textInput2.setVisible(true);
                        inputNeeded2.setVisible(true);
                        inputNeeded2.setText("Album Name");
                    }
                    
                    if(inputOption.equals("Top tracks from artist for user") ) 
                    {
                        textInput2.setVisible(true);
                        inputNeeded2.setVisible(true);
                        inputNeeded2.setText("Artist Name");
                    }
                    
                    if(inputOption.equals("Most listened to albums from artist") ) 
                    {
                        textInput2.setVisible(true);
                        inputNeeded2.setVisible(true);
                        inputNeeded2.setText("Artist Name");
                    }
                    
                }
            }
        );

        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    //JComboBox cb = (JComboBox)event.getSource();
                    String OptionName = (String)personalDataAnalysisOptionsComboBox.getSelectedItem();
                    if(OptionName.equals("List of top 15 most listened artists for user"))
                    {
                        String[] columnNames = controller.most_listened_artist_for_user_Columns(textInput.getText());
                        String[][] data = controller.most_listened_artist_for_user(textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);   
                    }

                    if(OptionName.equals("List of top 15 most listened albums for user"))
                    {
                        String[] columnNames = controller.most_listened_album_for_user_Columns(textInput.getText());
                        String[][] data = controller.most_listened_album_for_user(textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);   
                    }
                    
                    if(OptionName.equals("List of top 15 most listened songs for user"))
                    {
                        String[] columnNames = controller.most_listened_song_for_user_Columns(textInput.getText());
                        String[][] data = controller.most_listened_song_for_user(textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);   
                    }

                    if(OptionName.equals("Listens on the tracks of an album for a user"))
                    {
                        String[] columnNames = controller.album_track_listens_for_user_Columns(textInput2.getText(), textInput.getText());
                        String[][] data = controller.album_track_listens_for_user(textInput2.getText(), textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);                    
                    }

                    if(OptionName.equals("Top tracks from artist for user"))
                    {
                        String[] columnNames = controller.top_tracks_of_artist_for_user_Columns(textInput2.getText(), textInput.getText());
                        String[][] data = controller.top_tracks_of_artist_for_user(textInput2.getText(), textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);                    
                    }

                    if(OptionName.equals("Most listened to albums from artist"))
                    {
                        String[] columnNames = controller.top_albums_of_artist_for_user_Columns(textInput2.getText(), textInput.getText());
                        String[][] data = controller.top_albums_of_artist_for_user(textInput2.getText(), textInput.getText());
                        if(data.length==0)
                            errorLabel.setVisible(true);
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        updatedDataTable.setModel(newTableModel);                    
                    }
                }
            });

        JPanel personalDataPage=new JPanel();
        
        BoxLayout boxlayout2 = new BoxLayout(personalDataPage, BoxLayout.Y_AXIS);
        //aboutPage.setLayout(boxlayout2);
        personalDataPage.setLayout(boxlayout2);
        JScrollPane updatedDataPanel = new JScrollPane(updatedDataTable);
        updatedDataTable.setFillsViewportHeight(true);
        personalDataPage.add(personalDataAnalysisOptionsComboBox);
        JPanel tempInput1 = new JPanel();
        tempInput1.add(inputNeeded);
        tempInput1.add(textInput);
        personalDataPage.add(tempInput1);
        
        JPanel tempInput2 = new JPanel();
        tempInput2.add(inputNeeded2);
        tempInput2.add(textInput2);
        personalDataPage.add(tempInput2);
        JPanel tempInput3 = new JPanel();
        tempInput3.add(submitButton);
        personalDataPage.add(tempInput3);
        errorLabel.setVisible(false);
        personalDataPage.add(errorLabel);
        personalDataPage.add(updatedDataPanel);
        tabs.addTab("Personal Analysis",personalDataPage);

        // General Data Analysis Tab
        GridLayout gridLayoutDatabaseAnalysis = new GridLayout(4, 2);
        String[] dataAnalysisOptions = {"List of top listeners of this artist", "List of top listeners of this album", "List of top listeners of this song", "List of top 30 most listened artists", "List of top 30 most listened albums", "List of top 30 most listened songs", "List of top 30 most listened artists(by number of listeners)", "List of top 30 most listened albums(by number of listeners)", "List of top 30 most listened songs(by number of listeners)"};

        JComboBox dataAnalysisOptionsComboBox = new JComboBox(dataAnalysisOptions);
        dataAnalysisOptionsComboBox.setSelectedIndex(3);
        JLabel inputNeededDatabaseAnalysis = new JLabel("Additional Info");
        JTextField textInputDatabaseAnalysis = new JTextField(30);
        inputNeededDatabaseAnalysis.setVisible(false);
        textInputDatabaseAnalysis.setVisible(false);
        DefaultTableModel newTableModel = new DefaultTableModel(0,0);
        JTable newDataTable=new JTable(updatedTableModel);
        dataAnalysisOptionsComboBox.addActionListener(new ActionListener()

            {
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    String inputOption = (String)cb.getSelectedItem();
                    //updateLabel(petName);
                    if(inputOption.equals("List of top 30 most listened artists") || inputOption.equals("List of top 30 most listened albums") || inputOption.equals("List of top 30 most listened songs"))
                    {
                        inputNeededDatabaseAnalysis.setVisible(false);
                        textInputDatabaseAnalysis.setVisible(false);
                    }
                    if(inputOption.equals("List of top listeners of this artist") )
                    {
                        inputNeededDatabaseAnalysis.setVisible(true);
                        textInputDatabaseAnalysis.setVisible(true);
                        inputNeededDatabaseAnalysis.setText("Artist Name");
                    }
                    
                    if(inputOption.equals("List of top listeners of this album") )
                    {
                        inputNeededDatabaseAnalysis.setVisible(true);
                        textInputDatabaseAnalysis.setVisible(true);
                        inputNeededDatabaseAnalysis.setText("Album Name");
                    }
                    
                    if(inputOption.equals("List of top listeners of this song") )
                    {
                        inputNeededDatabaseAnalysis.setVisible(true);
                        textInputDatabaseAnalysis.setVisible(true);
                        inputNeededDatabaseAnalysis.setText("Song Name");
                    }
                    
                    if(inputOption.equals("List of top 30 most listened songs(by number of listeners)") || inputOption.equals("List of top 30 most listened artists(by number of listeners)") || inputOption.equals("List of top 30 most listened albums(by number of listeners)"))
                    {
                        inputNeededDatabaseAnalysis.setVisible(false);
                        textInputDatabaseAnalysis.setVisible(false);
                    }
                }
            }
        );

        JTextArea l2 = new JTextArea("");
        JButton submitButton2 = new JButton("Submit");
        submitButton2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    //JComboBox cb = (JComboBox)event.getSource();
                    String OptionName = (String)dataAnalysisOptionsComboBox.getSelectedItem();
                    if(OptionName.equals("List of top listeners of this artist"))
                    {
                        String[] columnNames = controller.who_scrobbled_artist_most_Columns(textInputDatabaseAnalysis.getText());
                        String[][] data = controller.who_scrobbled_artist_most(textInputDatabaseAnalysis.getText());
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel);   
                    }

                    if(OptionName.equals("List of top listeners of this album"))
                    {
                        String[] columnNames = controller.who_scrobbled_album_most_Columns(textInputDatabaseAnalysis.getText());
                        String[][] data = controller.who_scrobbled_album_most(textInputDatabaseAnalysis.getText());
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel);   
                    }

                    if(OptionName.equals("List of top listeners of this song"))
                    {
                        String[] columnNames = controller.who_scrobbled_song_most_Columns(textInputDatabaseAnalysis.getText());
                        String[][] data = controller.who_scrobbled_song_most(textInputDatabaseAnalysis.getText());
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened artists"))
                    {
                        String[] columnNames = controller.most_listened_artist_for_everyone_Columns();
                        String[][] data = controller.most_listened_artist_for_everyone();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened albums"))
                    {
                        String[] columnNames = controller.most_listened_album_for_everyone_Columns();
                        String[][] data = controller.most_listened_album_for_everyone();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened songs"))
                    {
                        String[] columnNames = controller.most_listened_song_for_everyone_Columns();
                        String[][] data = controller.most_listened_song_for_everyone();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened songs(by number of listeners)"))
                    {
                        String[] columnNames = controller.greatest_amount_of_listeners_song_Columns();
                        String[][] data = controller.greatest_amount_of_listeners_song();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened artists(by number of listeners)"))
                    {
                        String[] columnNames = controller.greatest_amount_of_listeners_artist_Columns();
                        String[][] data = controller.greatest_amount_of_listeners_artist();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                    if(OptionName.equals("List of top 30 most listened albums(by number of listeners)"))
                    {
                        String[] columnNames = controller.greatest_amount_of_listeners_album_Columns();
                        String[][] data = controller.greatest_amount_of_listeners_album();
                        DefaultTableModel newTableModel = new DefaultTableModel(data, columnNames);
                        newDataTable.setModel(newTableModel); 
                    }

                }
            });
        JPanel dataPage=new JPanel();
        JScrollPane newDataPanel = new JScrollPane(newDataTable);
        BoxLayout boxlayout3 = new BoxLayout(dataPage, BoxLayout.Y_AXIS);
        //aboutPage.setLayout(boxlayout2);
        dataPage.setLayout(boxlayout3);
        newDataTable.setFillsViewportHeight(true);
        dataPage.add(dataAnalysisOptionsComboBox);
        JPanel tempPanel = new JPanel();
        tempPanel.add(inputNeededDatabaseAnalysis);
        tempPanel.add(textInputDatabaseAnalysis);
        dataPage.add(tempPanel);
        //dataPage.add(textInputDatabaseAnalysis);
        JPanel tempInput4 = new JPanel();
        tempInput4.add(submitButton2);
        dataPage.add(tempInput4);
        dataPage.add(newDataPanel);
        tabs.addTab("Database Analysis",dataPage);
    }

    /** Listens to the combo box. */

}
