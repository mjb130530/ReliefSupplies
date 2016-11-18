/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrameCards;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Matthew
 */
public class differentApproach implements ItemListener{
  //-----------------------
    
    JPanel cards;
    final static String INSERTDONOR = "Add a Donor";
    final static String INSERTEMPLOYEE = "Add an employee";
    final static String INSERTSUPPLY = "Add a supply";
    final static String INSERTLOCATION = "Add a location";
    
//    public InsertCards() {
//        initComponents();
//    }
    
    //public void addComponentToPane(Container pane){
    //public void addComponentToPane(Container pane){  
        private void initComponents(){
            Container pane = new Container();
//        JPanel cards;
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { INSERTDONOR, INSERTEMPLOYEE, INSERTSUPPLY, INSERTLOCATION };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        InsertDonor insertDonor = new InsertDonor();
        InsertEmployee insertEmployee = new InsertEmployee();
        InsertSupply insertSupply = new InsertSupply();
        InsertLocation insertLocation = new InsertLocation();
        
        //-------
        //DonorCard donorCard = new DonorCard();
        //------------------
                
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(insertDonor, INSERTDONOR);
        cards.add(insertEmployee, INSERTEMPLOYEE);
        cards.add(insertSupply, INSERTSUPPLY);
        cards.add(insertLocation, INSERTLOCATION);
        //cards.add(donorCard, INSERTDONOR);//This is for the attempt at a dynamic dropdownbox
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
        
        
    }
    
    @Override    
    public void itemStateChanged(ItemEvent evt) {
        CardLayout c1 = (CardLayout)(cards.getLayout());
        c1.show(cards, (String)evt.getItem());
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JPanel jPanel1 = new JPanel();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        InsertCards demo = new InsertCards();
        demo.addComponentToPane(jPanel1);
        
        //Display the window.
        //jPanel1.pack();
        jPanel1.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
//        try {
//            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        /* Turn off metal's use of bold fonts */
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

//    @Override
//    public void addLayoutComponent(String name, Component comp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void removeLayoutComponent(Component comp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Dimension preferredLayoutSize(Container parent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Dimension minimumLayoutSize(Container parent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void layoutContainer(Container parent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}

