package WorkFromHere;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import JAMDasCutD.BasicCreatePanel;
import JAMDasCutD.BasicRetrievePanel;
//import JAMDasCutD.BasicUpdatePanel;
import JAMDasCutD.BasicDeletePanel;

public class TabbedPaneDemo extends JPanel {
    
//    public TabbedPaneDemo(){
//        createAndShowGUI();
//    }
    
    
//    private void createTabbed(){
    public TabbedPaneDemo() {
        super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel panelCreate = new BasicCreatePanel();
        tabbedPane.addTab("Create", panelCreate);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JPanel panelRetrieve = new BasicRetrievePanel();
        tabbedPane.addTab("Retrieve", panelRetrieve);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent panel4 = makeTextPanel("Not implemented yet");
        //JPanel panelUpdate = new BasicUpdatePanel();
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Update", panel4);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        JPanel panelDelete = new BasicDeletePanel();
        tabbedPane.addTab("Delete", panelDelete);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
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
        frame.add(new TabbedPaneDemo(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TabbedPaneDemo().setVisible(true);
//            }
//        });
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                //Turn off metal's use of bold fonts
//		UIManager.put("swing.boldMetal", Boolean.FALSE);
//		new TabbedPaneDemo().setVisible(true);
//            }
//        });
    }
}