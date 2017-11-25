
/* This file contains the Classes required to provide the functionality associated with the adminstrator.
 *The primary class associated with this is the AdminControls Class. It allows the adminstrator to add new users to 
 * the application. The program also has the ability to check for repetion in the usernames, which it expresses with the help
 * of a prompt. The class also contains the functionality to delete users who can access the database.
 ******************************************************************************************************************************
 */
/**
 *
 * @author tanmay
 *
 */
public class AdminControls {

    
    public AdminControls() {
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
                            
    private void initComponents() {
        //This function initializes the components associated with the Graphical user interface.
        headingLabel = new javax.swing.JLabel();
        addUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        addUserLabel = new javax.swing.JLabel();
        deleteUserLabel = new javax.swing.JLabel();
        disp=new javax.swing.JFrame();
        disp.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent e){
                AdminControls.previousFrame.setVisible(true);
                disp.dispose();
            }
        });

        headingLabel.setFont(new java.awt.Font("Ubuntu", 1, 35)); // NOI18N
        headingLabel.setText("Admin Controls");

        addUserButton.setText("Add");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        addUserLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        addUserLabel.setText("Add User");

        deleteUserLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        deleteUserLabel.setText("Delete User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(disp.getContentPane());
        disp.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteUserLabel)
                            .addComponent(addUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteUserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        disp.pack();
    }                      

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //This function adds a user to the database.                                              
        javax.swing.JLabel userNameLabel=new javax.swing.JLabel("User Name");
        javax.swing.JLabel passwordLabel=new javax.swing.JLabel("Password");
        javax.swing.JTextField userInput=new javax.swing.JTextField("Enter username",10);
        javax.swing.JPasswordField passInput=new javax.swing.JPasswordField(10);

        javax.swing.JPanel inputPanel=new javax.swing.JPanel();
        inputPanel.add(userNameLabel);
        inputPanel.add(userInput);
        inputPanel.add(passwordLabel);
        inputPanel.add(passInput);

        int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the username and password",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result==javax.swing.JOptionPane.OK_OPTION){
            if(userInput.getText().equals("") || userInput.getText().equals("Enter username") || passInput.getText().equals("")){
                javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
            }
            else{
                String username=userInput.getText();
                String password=passInput.getText();
                DataHandler dh=new DataHandler();
                //It checks if the user is already present within the database.
                if(dh.checkUserPresent(username)){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"User already present");
                }
                else{//Addition of the user to the database.
                    dh.insertUser(username,password);
                }
                dh.terminateConnection();
            }
        }
    }                                             

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        // It deletes the user specified by the adminstrator.                                              
        javax.swing.JLabel userNameLabel=new javax.swing.JLabel("User Name");
        javax.swing.JTextField userInput=new javax.swing.JTextField("Enter username",10);
        javax.swing.JPanel inputPanel=new javax.swing.JPanel();

        inputPanel.add(userNameLabel);
        inputPanel.add(userInput);

        int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the username to be deleted",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result==javax.swing.JOptionPane.OK_OPTION){
            if(userInput.getText().equals("") || userInput.getText().equals("Enter username")||userInput.getText().equals("admin")){
                javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
            }
            else{
                boolean status;
                String username=userInput.getText();
                DataHandler dh=new DataHandler();
                status=dh.deleteUser(username);
                dh.terminateConnection();
                if(status){//If the deletion is successful.
                    javax.swing.JFrame successOutputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(successOutputMessage,"Deletion is Successful");
                }
                else{//Else if the user is not found
                    javax.swing.JFrame failedOutputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedOutputMessage,"User is not present in the database.");
                }
            }
        }
    }                                                

   
    public static void main(javax.swing.JFrame previousFrame) {
        /* Set the Nimbus look and feel */
       
        AdminControls.previousFrame=previousFrame;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the Graphical Interface associated with the adminstrative functions.
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminControls().show();
            }
        });
    }
     public void show(){
            disp.setVisible(true);
    }
                        
    private javax.swing.JButton addUserButton;
    private javax.swing.JLabel addUserLabel;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JLabel deleteUserLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JFrame disp;
    static javax.swing.JFrame previousFrame;
                   
}
