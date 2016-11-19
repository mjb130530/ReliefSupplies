/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package WorkFromHere;

import javax.swing.JFrame;
import java.awt.BorderLayout;


/**
 *
 * @author Matthew
 */
public class CreatedTabbedPane extends JFrame{
    
    public CreatedTabbedPane(){
        //initComponents();
        createAndShowGUI();
    }
    
//    private void initComponents() {
//        
//    }
    
    
    //----------------------------------------------
    
//    public TabbedPaneDemo() {
//        super(new GridLayout(1, 1));
//        
//        JTabbedPane tabbedPane = new JTabbedPane();
//        
//        JPanel panelCreate = new BasicCreatePanel();
//        tabbedPane.addTab("Create", panelCreate);
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//        
//        JPanel panelRetrieve = new BasicRetrievePanel();
//        tabbedPane.addTab("Retrieve", panelRetrieve);
//        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
//        
//        JComponent panel4 = makeTextPanel("Not implemented yet");
//        //JPanel panelUpdate = new BasicUpdatePanel();
//        panel4.setPreferredSize(new Dimension(410, 50));
//        tabbedPane.addTab("Update", panel4);
//        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//        
//        JPanel panelDelete = new BasicDeletePanel();
//        tabbedPane.addTab("Delete", panelDelete);
//        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
//        
//        //Add the tabbed pane to this panel.
//        add(tabbedPane);
//        
//        //The following line enables to use scrolling tabs.
//        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//    }
    
//    protected JComponent makeTextPanel(String text) {
//        JPanel panel = new JPanel(false);
//        JLabel filler = new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1, 1));
//        panel.add(filler);
//        return panel;
//    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JAMDasCutD Disaster Relief");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(new java.awt.Point(200, 200));
                
        //Add content to the window.
        //TabbedPaneDemo tabbedPaneDemo = new TabbedPaneDemo();
        frame.add(new TabbedPaneDemo(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatedTabbedPane().setVisible(false);
            }
        });
    }
}
