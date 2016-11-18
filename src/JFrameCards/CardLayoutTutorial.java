/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrameCards;

/**
 *
 * @author Matthew
 */
import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



public class CardLayoutTutorial {

    JFrame frame = new JFrame("CardLayout demo");

    JPanel panelCont = new JPanel();
    
    SpaceFiller spaceFiller = new SpaceFiller();
    InsertLocationPanel panelLocation = new InsertLocationPanel();
    InsertEmployeePanel panelEmployee = new InsertEmployeePanel();
    JPanel panelDonor = new JPanel();
    JPanel panelSupplies = new JPanel();

    private String donorString= "Insert Donor";
    private String employeeString = "Insert Employee";
    private String supplyString = "Insert Supply";
    private String locationString = "Insert Location";
    
    String comboBoxItems[] = {donorString, employeeString, supplyString,  locationString };
    JComboBox jComboBox1= new JComboBox(comboBoxItems);
    JComboBox jComboBox2= new JComboBox(comboBoxItems);
    JComboBox jComboBox3= new JComboBox(comboBoxItems);
    JComboBox jComboBox4= new JComboBox(comboBoxItems);
    
    JButton jbA1 = new JButton("Location");
    JButton jbA2 = new JButton("Employee");
    JButton jbA3 = new JButton("Donor");
    JButton jbA4 = new JButton("Supply");

    CardLayout cl = new CardLayout();

    public CardLayoutTutorial() {

	panelCont.setLayout(cl);

        panelLocation.setLayout(new GridLayout(2,4));
	panelLocation.add(jComboBox1);
        panelLocation.add(jbA1);
        panelLocation.add(jbA2);
        panelLocation.add(jbA3);
        panelLocation.add(jbA4);
	panelEmployee.add(jComboBox2);
	panelDonor.add(jComboBox3);
	panelSupplies.add(jComboBox4);

	panelCont.add(panelLocation, "1");
	panelCont.add(panelEmployee, "2");
	panelCont.add(panelDonor, "3");
	panelCont.add(panelSupplies, "4");

	cl.show(panelCont, "1");

        jbA1.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent arg0){
              cl.show(panelCont, "1");
          }
        });
        
        jbA2.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent arg0){
              cl.show(panelCont, "2");
          }
        });
        
        jbA3.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent arg0){
              cl.show(panelCont, "3");
          }
        });
        
        jbA4.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent arg0){
              cl.show(panelCont, "4");
          }
        });
        
	jComboBox1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String goTo = jComboBox1.getSelectedItem().toString();
			if(goTo.equals(donorString)){
				cl.show(panelCont, "1");
			}
			else if (goTo.equals(employeeString)){
				cl.show(panelCont, "2");
			}
			else if (goTo.equals(supplyString)){
				cl.show(panelCont, "3");
			}
			else if(goTo.equals(locationString)){
				cl.show(panelCont, "4");
			}
		}
	});

	jComboBox2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String goTo = jComboBox1.getSelectedItem().toString();
			if(goTo.equals(donorString)){
				cl.show(panelCont, "1");
			}
			else if (goTo.equals(employeeString)){
				cl.show(panelCont, "2");
			}
			else if (goTo.equals(supplyString)){
				cl.show(panelCont, "3");
			}
			else if(goTo.equals(locationString)){
				cl.show(panelCont, "4");
			}
		}
	});
        
        jComboBox3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String goTo = jComboBox1.getSelectedItem().toString();
			if(goTo.equals(donorString)){
				cl.show(panelCont, "1");
			}
			else if (goTo.equals(employeeString)){
				cl.show(panelCont, "2");
			}
			else if (goTo.equals(supplyString)){
				cl.show(panelCont, "3");
			}
			else if(goTo.equals(locationString)){
				cl.show(panelCont, "4");
			}
		}
	});
        
        jComboBox4.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String goTo = jComboBox1.getSelectedItem().toString();
			if(goTo.equals(donorString)){
				cl.show(panelCont, "1");
			}
			else if (goTo.equals(employeeString)){
				cl.show(panelCont, "2");
			}
			else if (goTo.equals(supplyString)){
				cl.show(panelCont, "3");
			}
			else if(goTo.equals(locationString)){
				cl.show(panelCont, "4");
			}
		}
	});

        frame.setLayout(new GridLayout(2,1));
        frame.add(panelCont);
        frame.add(spaceFiller);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CardLayoutTutorial();
            }
        });
    }
}
