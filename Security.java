/* This file contains the necessary implementation related to the creation and implementation of a login window.
 * The most important class is the Security class which renders the Login window and also verifies the input given by the user.
 * If the user is the adminstrator it goes to the screen associated with the adminstrative functions.
 * If the user is an ordinary user, it goes to the Main Screen which performs all the functions associated with a question bank
 * manager.
 **********************************************************************************************************************************
 */
/**
 *
 * @author tanmay
 *
 */
 class Security{

    public Security() {
        initComponents();
    }

    @SuppressWarnings("unchecked")                        
    private void initComponents() {
        //This fuction intitialises the components required for the creation of the Graphical User Interface.
        headingLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameInputField = new javax.swing.JTextField(15);
        passwordInputField = new javax.swing.JPasswordField(15);
        submitButton = new javax.swing.JButton();
        disp=new javax.swing.JFrame();
        disp.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headingLabel.setFont(new java.awt.Font("Ubuntu", 1, 35)); // NOI18N
        headingLabel.setText("Login");

        usernameLabel.setText("Username:");

        passwordLabel.setText("Password:");

        usernameInputField.setText("Enter your username here");
        usernameInputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputFieldActionPerformed(evt);
            }
        });

        passwordInputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordInputFieldActionPerformed(evt);
            }
        });

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(disp.getContentPane());
        disp.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameInputField)
                    .addComponent(passwordInputField))
                .addGap(95, 95, 95))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(headingLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(submitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        disp.pack();
    }                      

    private void usernameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        
    }                                                  

    private void passwordInputFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                   
       
    }                                                  

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        //Verify the input given by the user.                                           
        username=usernameInputField.getText().trim();
        password=passwordInputField.getText();
        if(validate()){//In case of the admin go to the Admin Controls 
            if(username.equals("admin")){
                disp.setVisible(false);
                AdminControls.main(disp);
            }
            else{//Otherwise show the MainScreen
                disp.setVisible(false);
                MainScreen.main(disp);
            }
        }
        else{
            javax.swing.JFrame failedLoginMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(failedLoginMessage,"Invalid username or password, please try again");
        }
    }                                            
    private boolean validate(){
        //Validate the credentials given by the user.
        DataHandler dh=new DataHandler();
        if(dh.verifyUser(username,password)){
            dh.terminateConnection();
            return true;
        }
        else{
            dh.terminateConnection();
            return false;
        }
    }
   
    public static void main() {
        /* Set the Nimbus look and feel */
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Security.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Security.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Security.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Security.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the Login Screen */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Security().show();
            }
        });
    }
    public void show(){
    	disp.setVisible(true);
    }
                         
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPasswordField passwordInputField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField usernameInputField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JFrame disp;
    String username,password;
                      
}
