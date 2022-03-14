/**
 * Handan Ozkurt
 * 350238515
 * Jan 23, 2022
 * Final Project 
 * A Java mortgage calculator app/program; designed with the main purpose of 
 * providing the user with the accurate monthly payment amount 
 * based on the information given by the user.
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class mortgageCalculator implements  ActionListener{ //class implements the action listener interface for functionalities
    //declaring the class variables for java swing components used in this program
    JPanel textPanel, panelForTextFields, completionPanel;
    JLabel titleLabel, loanLabel, yearsLabel, rateLabel, rate2Label, payLabel, iconLabel;
    JTextField loanField, yearsField, rateField, payField;
    JButton clearButton,loginButton;
    JSeparator lineSeparator, sepTwo, sepThree;
    JRadioButton b1, b2;
    JSpinner spinSpinner;
    SpinnerNumberModel mod;
    
   //class variables with private modifier and return type double for the infos that can include decimals.
    private double loan, years, rate, monthly;
    
    //private method to clear all JTextFields, by setting text fields to null
    private void clearFields(){
        loanField.setText(null);
        yearsField.setText(null);
        rateField.setText(null); 
        payField.setText(null);
    }
   
    //private method to enable buttons,text fields and the spinner when the user checks the "ON" box.
    public void enable() {
    	loanField.setEnabled(true);
    	yearsField.setEnabled(true);
    	rateField.setEnabled(true);
    	payField.setEnabled(true);
    	
    	b1.setEnabled(false); //ON button disable
    	b2.setEnabled(true); //OFF button enable
    	   	
    	clearButton.setEnabled(true);
    	loginButton.setEnabled(true);
    	
    	spinSpinner.setEnabled(true);  	
    }
    //private method to disable the following components when the "OFF" button is checked
    public void disable() {
        loanField.setEnabled(false);
    	yearsField.setEnabled(false);
    	rateField.setEnabled(false);
    	payField.setEnabled(false);
    	
    	b1.setEnabled(true); //ON button enable
    	b2.setEnabled(false); //OFF button disable
    	
    	clearButton.setEnabled(false);
    	loginButton.setEnabled(false);  
    	
    	spinSpinner.setEnabled(false); 
    }
    
    //public method to create a panel containing everything inside the the frame
    public JPanel createContentPane (){
        //We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);//layout of the panel is set to null
      
        //We create a title label with the following text, font, location, size and alignment
        titleLabel = new JLabel("Mortgage Calculator");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleLabel.setLocation(210,0);
        titleLabel.setSize(180, 30);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);//adding the label to totalGUI panel.
        
        //We create a button group for multiple exclusion scope for JRadioButtons
        ButtonGroup g = new ButtonGroup();
        
        //We create a JRadioButton- ON
        b1 = new JRadioButton();
        b1.setText("ON");
        b1.setLocation(545, 17);
        b1.setSize(110, 20);
        g.add(b1);//adding this button to the button group
        totalGUI.add(b1);
        b1.setEnabled(false); //ON button disabled
        b1.addActionListener(new java.awt.event.ActionListener() {//adding an action listener to enable the ON button
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
     
        //We create a J RadioButton-OFF
        b2 = new JRadioButton("OFF");
        b2.setLocation(545, 47);
        b2.setSize(110, 20);
        g.add(b2);//adding this button to the same button group
        totalGUI.add(b2);
        b2.addActionListener(new java.awt.event.ActionListener() {//adding an action listener to enable the OFF button
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        
        // Text Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(10, 35);
        textPanel.setSize(180, 190);
        totalGUI.add(textPanel);
        
        // Loan Label
        loanLabel = new JLabel("Loan Amount $");
        loanLabel.setLocation(0, 10);
        loanLabel.setSize(100, 30);
        loanLabel.setHorizontalAlignment(4);
        textPanel.add(loanLabel);

        // Login Label
        yearsLabel = new JLabel("# of Years");
        yearsLabel.setLocation(-5, 50);
        yearsLabel.setSize(100, 30);
        yearsLabel.setHorizontalAlignment(4);
        textPanel.add(yearsLabel);        
         
        // Interest Rate Label
        rateLabel = new JLabel("Interest Rate");
        rateLabel.setLocation(0,100);
        rateLabel.setSize(100, 30);
        rateLabel.setHorizontalAlignment(4);
        textPanel.add(rateLabel);
        
        // Interest Rate Example Label
        rate2Label = new JLabel("ex.(5.0 for 5%)");
        rate2Label.setLocation(0,120);
        rate2Label.setSize(100,30);
        rate2Label.setHorizontalAlignment(4);
        textPanel.add(rate2Label);
        
        // Total Monthly Pay Label
        payLabel = new JLabel("Monthly Payment $");
        payLabel.setLocation(0, 162);
        payLabel.setSize(150,30);
        payLabel.setHorizontalAlignment(4);
        textPanel.add(payLabel);
        
        // First Horizontal J-Separator to separate payLabel from previous labels
        lineSeparator=new JSeparator();
        lineSeparator.setOrientation(SwingConstants.HORIZONTAL);
        lineSeparator.setLocation(0, 145);
        lineSeparator.setSize(300, 300);
        textPanel.add(lineSeparator);

        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(250, 40);
        panelForTextFields.setSize(100, 180);
        totalGUI.add(panelForTextFields);

        // Username Textfield
        loanField = new JTextField(8);
        loanField.setLocation(0, 0);
        loanField.setSize(100, 30);
        panelForTextFields.add(loanField);
        loanField.addActionListener(this);
   
         // Login Textfield
         yearsField = new JTextField(8);
         yearsField.setLocation(0, 40);
         yearsField.setSize(100, 30);
         panelForTextFields.add(yearsField);
         yearsField.addActionListener(this);
        
         // Spinner set-up 
         mod = new SpinnerNumberModel(1, 0, 10, 1);  //intial value is 1, min is 0, max is 10 and iterates by 1 number
         spinSpinner = new JSpinner(mod);//adding the numbers from the SpinnerNumberModel inside the spinner
         spinSpinner.setLocation(37, 70);
         spinSpinner.setSize(59, 20);
         panelForTextFields.add(spinSpinner);
         spinSpinner.addChangeListener(new javax.swing.event.ChangeListener() { //action listener to display the chosen number inside the yeras text field.
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                catchvalueFromSpinner(evt);
            }
        });
       
        // Rate Textfield
        rateField = new JTextField(8);
        rateField.setLocation(0, 98);
        rateField.setSize(100, 30);
        panelForTextFields.add(rateField);
        rateField.addActionListener(this);
       
        // Second Horizontal J-Separator to separate payField from the previous text fields
        sepTwo=new JSeparator();
        sepTwo.setOrientation(SwingConstants.HORIZONTAL);
        sepTwo.setLocation(0, 140);
        sepTwo.setSize(300, 300);
        panelForTextFields.add(sepTwo);
      
        // Pay TextField   
        payField = new JTextField(8);
        payField.setEditable(false);
        payField.setLocation(0,159);
        payField.setSize(100,30);
        panelForTextFields.add(payField);
        payField.addActionListener(this);

        // Completion Panel to contain the completion JLabels
        completionPanel = new JPanel();
        completionPanel.setLayout(null);
        completionPanel.setLocation(40, 35);
        completionPanel.setSize(170, 180);
        totalGUI.add(completionPanel);

        // Icon Label that contains the image from the specified directory
        iconLabel=new JLabel("         ");
        Image img=new ImageIcon(this.getClass().getResource("iconicicon.png")).getImage();//getting the image from a directory into this class using a new Image Icon
        iconLabel.setIcon(new ImageIcon(img)); //passing the img object
        iconLabel.setBounds(200, -50, 460, 380);
        iconLabel.setHorizontalAlignment(4);
        totalGUI.add(iconLabel);
         
        // Clear Button for clearing all Text-Fields
        clearButton = new JButton("CLEAR");
        clearButton.setLocation(15, 268);
        clearButton.setSize(150, 30);
        clearButton.addActionListener(new java.awt.event.ActionListener() {//action listener to call the clear method
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                 clearAllActionPerformed(evt);
             }
         });
        totalGUI.add(clearButton);

        totalGUI.setOpaque(true); //setting the opaqueness of the totalGUI panel
          
        // First vertical J-Separator to seperate payLabel from payField and loginButton from clearButton.
        sepThree=new JSeparator();
        sepThree.setOrientation(SwingConstants.VERTICAL);
        sepThree.setLocation(210, 200);
        sepThree.setSize(900, 620);
        totalGUI.add(sepThree);
    
        // Button for Logging in
        loginButton=new JButton();
        loginButton.setText("Calculate");
        loginButton.setLocation(240, 268);
        loginButton.setSize(150, 30);
        loginButton.addActionListener(this);//action listener to calculate the monthly payment
        totalGUI.add(loginButton);
         
        totalGUI.setOpaque(true); 
         
        return totalGUI; //returning the totalGUI panel
    }
   
    // action listener to clear all text fields
    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {                                         
        clearFields();
    } 

    @Override  // overridong a method same as declared in parent class with action listener to calculate the final value
    public void actionPerformed(ActionEvent e) {
    	loan = (Double.parseDouble(loanField.getText())); //getting text from the loanField
    	years = (Double.parseDouble(yearsField.getText())); //getting text from the yearsField
    	rate = (Double.parseDouble(rateField.getText())); //getting text from the rateField
    	double mRate = (rate)/1200.0; //monthly interest rate is the annual rate over 12
    	double months = (years)*12.0; //time amount is # of years times 12
    	double denom = (Math.pow((1+mRate),months)-1); //denominator calculation from the mortgage calculation formula
    	monthly = (mRate + mRate/denom)*loan; //calculation of the actual final monthly payment 
    	DecimalFormat x = new DecimalFormat("#.##"); //formatting the decimal places
    	monthly = Double.valueOf(x.format(monthly)); //applying the format to the final monthly amount
    	payField.setText("");
    	payField.setText(monthly +""); //final pay text field is set to the monthly payment amount      
        
    }
    
    // action listener for RadioButton b1-ON
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        enable(); //calling the enable method
    }
    
    // action listener for RadioButton b2-OFF
    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        disable(); //calling the diable method
    }  
    
    //change listener for the spinner by creating a spinner object
    private void catchvalueFromSpinner(javax.swing.event.ChangeEvent evt) {  
    	String spinner="Changed";
        Integer selectedVal = (Integer) spinSpinner.getValue(); //getting the value choosed fromt he spinner, and setting it to an integer object
        spinner=selectedVal.toString(); //changing integer value to a String
        yearsField.setText(spinner); //setting the changed spinner String value inside the years text field
    } 
    
    //private class method to display the GUI
    private static void createAndShowGUI() {
        // JFrame with the title and the demo
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Mortgage Calculator");

        mortgageCalculator demo = new mortgageCalculator();
        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//frame closes on exit
        frame.setSize(630, 345);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);//frame cannot be resized
      
   }
    
    //main method to contain the swing utilities to show the GUI 
    public static void main(String[] args) {
    	//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}





