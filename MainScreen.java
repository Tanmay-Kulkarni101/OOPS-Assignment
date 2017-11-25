/* This file contains all the Classes dealing with the main user interface related classes.
 * The most important class amongst these is the main screen which renders the GUI and also handles the inteeractions with the
 * user. It implements all the funcitonality namely, the insertion,modification,deletion and export of questions, with the help
 * of prompts  generated with the help of creation of new dialog boxes to take the required user input.
 *******************************************************************************************************************************
 */
/**
 *
 * @author tanmay
 *
 */
import java.util.*;
import java.io.*;

public class MainScreen {


    public MainScreen() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")                       
    private void initComponents() {
        //Initializes the components of the Graphical User Interface.
        titleLabel = new javax.swing.JLabel();
        insertQuestionButton = new javax.swing.JButton();
        deleteQuestionButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        modifyButtonLabel = new javax.swing.JButton();
        generateTestLabel = new javax.swing.JLabel();
        modifyQuestionLabel = new javax.swing.JLabel();
        insertQuestionLabel = new javax.swing.JLabel();
        deleteQuestionLabel = new javax.swing.JLabel();
        addSubjectButton = new javax.swing.JButton();
        addSubjectLabel = new javax.swing.JLabel();
        display=new javax.swing.JFrame();
        
        display.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent e){
                MainScreen.previousFrame.setVisible(true);
                display.dispose();
            }
        });

        titleLabel.setFont(new java.awt.Font("Ubuntu", 1, 35)); // NOI18N
        titleLabel.setText("Enter the Action to be Performed");

        insertQuestionButton.setText("Insert");
        insertQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertQuestionButtonActionPerformed(evt);
            }
        });

        deleteQuestionButton.setText("Delete");
        deleteQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteQuestionButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        modifyButtonLabel.setText("Modify");
        modifyButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonLabelActionPerformed(evt);
            }
        });

        generateTestLabel.setText("Generate Test");

        modifyQuestionLabel.setText("Modify Question");

        insertQuestionLabel.setText("Insert Question");

        deleteQuestionLabel.setText("Delete Question");

        addSubjectButton.setText("Add Subject");
        addSubjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectButtonActionPerformed(evt);
            }
        });

        addSubjectLabel.setText("Add Subjects");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(display.getContentPane());
        display.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(deleteQuestionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(insertQuestionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteQuestionButton)
                            .addComponent(insertQuestionButton))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modifyQuestionLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(generateTestLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportButton)
                            .addComponent(modifyButtonLabel))
                        .addGap(157, 157, 157))))
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSubjectButton)
                    .addComponent(addSubjectLabel))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(generateTestLabel)
                    .addComponent(insertQuestionLabel)
                    .addComponent(insertQuestionButton))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyButtonLabel)
                    .addComponent(deleteQuestionButton)
                    .addComponent(modifyQuestionLabel)
                    .addComponent(deleteQuestionLabel))
                .addGap(25, 25, 25)
                .addComponent(addSubjectLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addSubjectButton)
                .addGap(18, 18, 18))
        );

        display.pack();
    }

    private ArrayList<Integer> subjectSelection(){
        //This function creates a dialog that asks the user to choose the subjects with respect to which the operations will be made.
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        javax.swing.JLabel subjectLabel=new javax.swing.JLabel("Enter the subjects in which you whish to add subjects");
        javax.swing.JCheckBox subjectList[]=new javax.swing.JCheckBox[Subjects.numOfSubjects];

        javax.swing.JPanel inputPanel=new javax.swing.JPanel();
        inputPanel.add(subjectLabel);

        for(int i=0;i<Subjects.numOfSubjects;i++){
            subjectList[i]=new javax.swing.JCheckBox(Subjects.convertToString(i));
            inputPanel.add(subjectList[i]);
        }
        
        int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the name of the subject",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result==javax.swing.JOptionPane.OK_OPTION){
            for(int i=0;i<Subjects.numOfSubjects;i++){
                if(subjectList[i].isSelected()){
                    indexes.add(i);
                }
            }
            return indexes;
        }
        else{
            return null;
        }
    }

    private int typeSelection(){
        //This window allows the user to choose the type of subject with which the user wishes to perform the operation.
        int index=-1;
        javax.swing.JLabel typesLabel=new javax.swing.JLabel("Enter the type of question");
        javax.swing.JRadioButton typesList[]=new javax.swing.JRadioButton[Types.NUMBEROFTYPES];
        javax.swing.ButtonGroup group=new javax.swing.ButtonGroup();
        javax.swing.JPanel typesPanel=new javax.swing.JPanel();
        typesPanel.setLayout(new java.awt.GridLayout(Types.NUMBEROFTYPES+1,1));

        typesPanel.add(typesLabel);
        for(int i=0;i<Types.NUMBEROFTYPES;i++){
            if(i==Types.FILLINTHEBLANKS){
                typesList[i]=new javax.swing.JRadioButton("Fill in the blanks.");
            }
            else if(i==Types.MCQ){
                typesList[i]=new javax.swing.JRadioButton("Multiple Choice Question.");
            }
            else{
                typesList[i]=new javax.swing.JRadioButton("True or False");
            }
            group.add(typesList[i]);
            typesPanel.add(typesList[i]);
        }

        int result=javax.swing.JOptionPane.showConfirmDialog(null,typesPanel,"Enter the type of Question",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result==javax.swing.JOptionPane.OK_OPTION){
            for(int i=0;i<Types.NUMBEROFTYPES;i++){
                if(typesList[i].isSelected()){
                    index=i;
                    break;
                }
            }
        }
        return index;
    }
    private void inputForm(int type,int subject){
        //This creates a form in which the user inputs the data for insertion into the database.
        if(type==Types.FILLINTHEBLANKS){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(15);
            javax.swing.JLabel answerLabel=new javax.swing.JLabel("Answer");
            javax.swing.JTextField answerInput=new javax.swing.JTextField(15);
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(answerLabel);
            inputPanel.add(answerInput);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the blanks",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(result==javax.swing.JOptionPane.OK_OPTION){
                if(questionInputField.getText().equals("")||answerInput.getText().equals("")){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                    DataHandler dh=new DataHandler();
                    DataBundle db=new DataBundle(subject,questionInputField.getText(),answerInput.getText());
                    dh.insertValue(type,db);
                    dh.terminateConnection();
                }
            }
        }
        else if(type==Types.MCQ){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(15);
            javax.swing.JLabel option1Label=new javax.swing.JLabel("Option1");
            javax.swing.JTextField option1Input=new javax.swing.JTextField(15);
            javax.swing.JLabel option2Label=new javax.swing.JLabel("Option2");
            javax.swing.JTextField option2Input=new javax.swing.JTextField(15);
            javax.swing.JLabel option3Label=new javax.swing.JLabel("Option3");
            javax.swing.JTextField option3Input=new javax.swing.JTextField(15);
            javax.swing.JLabel option4Label=new javax.swing.JLabel("Option4"); 
            javax.swing.JTextField option4Input=new javax.swing.JTextField(15);
            javax.swing.JLabel answerLabel=new javax.swing.JLabel("Answer between 0 and 3");
            javax.swing.JTextField answerInput=new javax.swing.JTextField(15);
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            int num=-1;
            inputPanel.setLayout(new java.awt.GridLayout(12,1));

            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(option1Label);
            inputPanel.add(option1Input);
            inputPanel.add(option2Label);
            inputPanel.add(option2Input);
            inputPanel.add(option3Label);
            inputPanel.add(option3Input);
            inputPanel.add(option4Label);
            inputPanel.add(option4Input);
            inputPanel.add(answerLabel);
            inputPanel.add(answerInput);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the MCQ",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(result==javax.swing.JOptionPane.OK_OPTION){
                try{
                    num=Integer.parseInt(answerInput.getText());
                }
                catch(NumberFormatException nfe){
                    num=-1;
                }
                if(questionInputField.getText().equals("")||option1Input.getText().equals("")||option2Input.getText().equals("")||option3Input.getText().equals("")||option4Input.getText().equals("")||answerInput.getText().equals("")||num<0||num>3){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                    DataHandler dh=new DataHandler();
                    DataBundle db=new DataBundle(subject,questionInputField.getText(),option1Input.getText(),option2Input.getText(),option3Input.getText(),option4Input.getText(),num);
                    dh.insertValue(type,db);
                    dh.terminateConnection();
                }
            }
        }
        else if(type==Types.TRUEORFALSE){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(15);
            javax.swing.JRadioButton True=new javax.swing.JRadioButton("True");
            javax.swing.JRadioButton False=new javax.swing.JRadioButton("False");
            javax.swing.ButtonGroup group=new javax.swing.ButtonGroup();
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            inputPanel.setLayout(new java.awt.GridLayout(4,1));
            boolean inputNotPresent=true;
            boolean answer=false;
            group.add(False);
            group.add(True);
            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(False);
            inputPanel.add(True);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the true false question",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(False.isSelected()){
                inputNotPresent=false;
                answer=false;
            }
            else if(True.isSelected()){
                inputNotPresent=false;
                answer=true;
            }
            if(result==javax.swing.JOptionPane.OK_OPTION){
                if(questionInputField.getText().equals("")||inputNotPresent){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                     DataHandler dh=new DataHandler();
                     DataBundle db=new DataBundle(subject,questionInputField.getText(),answer);
                     dh.insertValue(type,db);
                     dh.terminateConnection();
                }
            }
        }
    }

    private void inputForm(int type,int subject,NumberedDataBundle ndb){
        //This creates a form in which the user can edit the contents of already existing questions.
        if(type==Types.FILLINTHEBLANKS){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(ndb.fb.question,15);
            javax.swing.JLabel answerLabel=new javax.swing.JLabel("Answer");
            javax.swing.JTextField answerInput=new javax.swing.JTextField(ndb.fb.answer,15);
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(answerLabel);
            inputPanel.add(answerInput);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the blanks",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(result==javax.swing.JOptionPane.OK_OPTION){
                if(questionInputField.getText().equals("")||answerInput.getText().equals("")){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                    DataHandler dh=new DataHandler();
                    DataBundle db=new DataBundle(subject,questionInputField.getText(),answerInput.getText());
                    dh.modifyValue(type,db,ndb.serialnum);
                    dh.terminateConnection();
                }
            }
        }
        else if(type==1){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(ndb.mcq.question,15);
            javax.swing.JLabel option1Label=new javax.swing.JLabel("Option1");
            javax.swing.JTextField option1Input=new javax.swing.JTextField(ndb.mcq.option1,15);
            javax.swing.JLabel option2Label=new javax.swing.JLabel("Option2");
            javax.swing.JTextField option2Input=new javax.swing.JTextField(ndb.mcq.option2,15);
            javax.swing.JLabel option3Label=new javax.swing.JLabel("Option3");
            javax.swing.JTextField option3Input=new javax.swing.JTextField(ndb.mcq.option3,15);
            javax.swing.JLabel option4Label=new javax.swing.JLabel("Option4"); 
            javax.swing.JTextField option4Input=new javax.swing.JTextField(ndb.mcq.option4,15);
            javax.swing.JLabel answerLabel=new javax.swing.JLabel("Answer between 0 and 3");
            javax.swing.JTextField answerInput=new javax.swing.JTextField(Integer.toString(ndb.mcq.answer),15);
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            int num=-1;
            inputPanel.setLayout(new java.awt.GridLayout(12,1));

            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(option1Label);
            inputPanel.add(option1Input);
            inputPanel.add(option2Label);
            inputPanel.add(option2Input);
            inputPanel.add(option3Label);
            inputPanel.add(option3Input);
            inputPanel.add(option4Label);
            inputPanel.add(option4Input);
            inputPanel.add(answerLabel);
            inputPanel.add(answerInput);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the MCQ",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(result==javax.swing.JOptionPane.OK_OPTION){
                try{
                    num=Integer.parseInt(answerInput.getText());
                }
                catch(NumberFormatException nfe){
                    num=-1;
                }
                if(questionInputField.getText().equals("")||option1Input.getText().equals("")||option2Input.getText().equals("")||option3Input.getText().equals("")||option4Input.getText().equals("")||answerInput.getText().equals("")||num<0||num>3){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                    DataHandler dh=new DataHandler();
                    DataBundle db=new DataBundle(subject,questionInputField.getText(),option1Input.getText(),option2Input.getText(),option3Input.getText(),option4Input.getText(),num);
                    dh.modifyValue(type,db,ndb.serialnum);
                    dh.terminateConnection();
                }
            }
        }
        else if(type==2){
            javax.swing.JLabel questionLabel =new javax.swing.JLabel("Question");
            javax.swing.JTextField questionInputField=new javax.swing.JTextField(ndb.tof.question,15);
            javax.swing.JRadioButton True=new javax.swing.JRadioButton("True");
            javax.swing.JRadioButton False=new javax.swing.JRadioButton("False");
            if(ndb.tof.answer){
                True.setSelected(true);
            }
            else{
                False.setSelected(true);
            }
            javax.swing.ButtonGroup group=new javax.swing.ButtonGroup();
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            inputPanel.setLayout(new java.awt.GridLayout(4,1));
            boolean inputNotPresent=true;
            boolean answer=false;
            group.add(False);
            group.add(True);
            inputPanel.add(questionLabel);
            inputPanel.add(questionInputField);
            inputPanel.add(False);
            inputPanel.add(True);

            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the data for the true false question",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(False.isSelected()){
                inputNotPresent=false;
                answer=false;
            }
            else if(True.isSelected()){
                inputNotPresent=false;
                answer=true;
            }
            if(result==javax.swing.JOptionPane.OK_OPTION){
                if(questionInputField.getText().equals("")||inputNotPresent){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                     DataHandler dh=new DataHandler();
                     DataBundle db=new DataBundle(subject,questionInputField.getText(),answer);
                     dh.modifyValue(type,db,ndb.serialnum);
                     dh.terminateConnection();
                }
            }
        }
    }

    private ArrayList<NumberedDataBundle> displayForm(int subject){
        //This function displays the data which is to be selected for addtional operations.
        DataHandler dh=new DataHandler();
        ArrayList<NumberedDataBundle> data=new ArrayList<NumberedDataBundle>();
        ArrayList<NumberedDataBundle> selectedData=new ArrayList<NumberedDataBundle>();
        for(int i=0;i<Types.NUMBEROFTYPES;i++){
            data.addAll(dh.displayRecords(subject,i,DataHandler.PRINTALL));
        }
        dh.terminateConnection();

        if(data.isEmpty()){
            javax.swing.JFrame warningInputMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(warningInputMessage,"No records found");
            return selectedData;
        }
        else{
            javax.swing.JCheckBox questionList[]=new javax.swing.JCheckBox[data.size()];
            javax.swing.JLabel promptLabel=new javax.swing.JLabel("Enter the questions to be deleted");
            javax.swing.JPanel inputPanel=new javax.swing.JPanel();
            inputPanel.add(promptLabel);
            inputPanel.setLayout(new java.awt.GridLayout(1+data.size(),1));
            for(int i=0;i<data.size();i++){
                if(data.get(i).type==Types.FILLINTHEBLANKS){
                    questionList[i]=new javax.swing.JCheckBox(data.get(i).fb.question);
                }
                else if(data.get(i).type==Types.MCQ){
                    questionList[i]=new javax.swing.JCheckBox(data.get(i).mcq.question);
                }
                else if(data.get(i).type==Types.TRUEORFALSE){
                    questionList[i]=new javax.swing.JCheckBox(data.get(i).tof.question);
                }
                inputPanel.add(questionList[i]);
            }
            int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the question to be deleted",javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(result==javax.swing.JOptionPane.OK_OPTION){
                for(int i=0;i<data.size();i++){
                    if(questionList[i].isSelected()){
                        selectedData.add(data.get(i));
                    }
                }
            }
            return selectedData;
        }     
    }

    private void insertQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        //This function performs the actions required to add new data to the database.
        ArrayList<Integer> indexes=new ArrayList<Integer>();   
        int index=-1;   
        //This asks the user for the subject into which the question is to be inserted.                                             
        indexes=subjectSelection();
        if(indexes==null){
            //Do nothing if the cancel button is pressed.
        }
        else if(indexes.isEmpty()){
            javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
        }
        else{
            for(int i=0;i<indexes.size();i++){
                //This asks the user for the type of question which is to be inserted.
                index=typeSelection();
                if(index==-1){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                }
                else{
                    inputForm(index,indexes.get(i));
                }
            }
        }
    }                                                    

    private void deleteQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {     
        //This function deletes the questions specified by the user.                                                
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        ArrayList<NumberedDataBundle> data=new ArrayList<NumberedDataBundle>();  
        //This asks the user for the subject into which the question is to be inserted.                                             
        indexes=subjectSelection();
        if(indexes==null){
            //Do nothing if the cancel button is pressed.
        }
        else if(indexes.isEmpty()){
            javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
        }
        else{
            for(int i=0;i<indexes.size();i++){
                //Taking the data for the indexes chosen by the user, from the form displayed on screen.
                data=(displayForm(indexes.get(i)));
                if(data.isEmpty()){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"No options selected");
                }
                else{
                    DataHandler dh=new DataHandler();
                    for(int j=0;j<data.size();j++){
                        dh.deleteValue(data.get(i).type,data.get(i).serialnum);
                    }
                    dh.terminateConnection();
                }
            }
        }
    }                                                    

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        //This function exports the data to a file location specified by the user.                                           
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        ArrayList<NumberedDataBundle> data=new ArrayList<NumberedDataBundle>(); 
        DataHandler dh=new DataHandler(); 
        int num=-1; 
        int fileFormat=MainScreen.TEXT_FILE;
        javax.swing.JLabel pathLabel=new javax.swing.JLabel("Enter the path of the file");
        javax.swing.JTextField pathInput=new javax.swing.JTextField(15);
        javax.swing.JPanel pathPanel=new javax.swing.JPanel();
        String path=MainScreen.DEFAULT_ADDRESS;
        java.io.File inputFile=new File(path);
        pathPanel.setLayout(new java.awt.GridLayout(5,1));

        pathPanel.add(pathLabel);
        pathPanel.add(pathInput);

        //Checking for the existance of the directory
        int result1=javax.swing.JOptionPane.showConfirmDialog(null,pathPanel,"Enter the details required to export the file",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result1==javax.swing.JOptionPane.OK_OPTION){
            String inputPath=pathInput.getText();
            java.io.File checkFile=new java.io.File(inputPath);
            if(!checkFile.isDirectory()){
                checkFile.getParentFile();
            }
            if(checkFile.exists()){
                inputFile=new java.io.File(inputPath);
            }
            else{
                javax.swing.JFrame fileNotFoundMessage=new javax.swing.JFrame();
                javax.swing.JOptionPane.showMessageDialog(fileNotFoundMessage,"File not found in path, storing in default file location ./exports");
            }
        }

        java.io.File questions=new java.io.File(inputFile,"questions");
        java.io.File answers=new java.io.File(inputFile,"answers");
        java.io.FileWriter fw1=null;
        java.io.FileWriter fw2=null;
        try{
            fw1=new java.io.FileWriter(questions);
            fw2=new java.io.FileWriter(answers);
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return;
        }
        if(fw1==null||fw2==null){
            return;
        }

        //Writing the prompts to the files.
        java.io.BufferedWriter buffWriter1=new java.io.BufferedWriter(fw1);
        java.io.BufferedWriter buffWriter2=new java.io.BufferedWriter(fw2);
        String str1="The Solution Bank";
        String str2="The Question Bank";
        try{
            buffWriter1.write("***************************************************************************************************");
            buffWriter1.newLine();
            buffWriter1.write(str2,0,str2.length());
            buffWriter1.newLine();
            buffWriter1.write("***************************************************************************************************");
            buffWriter1.newLine();
            buffWriter1.newLine();
           
            buffWriter2.write("***************************************************************************************************");
            buffWriter2.newLine();
            buffWriter2.write(str1,0,str1.length());
            buffWriter2.newLine();
            buffWriter2.write("***************************************************************************************************");
            buffWriter2.newLine();
            buffWriter2.newLine();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //Choosing the subjects selected by the user.
        indexes=subjectSelection();
        if(indexes==null){
            //On pressing the cancel option do,nothing.
        }
        else if(indexes.isEmpty()){
            javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
        }
        else{

            for(int i=0;i<indexes.size();i++){
                javax.swing.JLabel numberLabel=new javax.swing.JLabel("How many questions in subject "+Subjects.convertToString(indexes.get(i)));
                javax.swing.JTextField numInput=new javax.swing.JTextField(15);
                javax.swing.JPanel inputPanel=new javax.swing.JPanel();
                data=new ArrayList<NumberedDataBundle>();

                ArrayList<javax.swing.JLabel> displayElement=new ArrayList<javax.swing.JLabel>();

                inputPanel.add(numberLabel);
                inputPanel.add(numInput);

                //Ask the user for the number of questions to be exported.
                int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the number of questions to be exported",javax.swing.JOptionPane.OK_CANCEL_OPTION);
                if(result==javax.swing.JOptionPane.OK_OPTION){
                    try{
                        num=Integer.parseInt(numInput.getText());
                        if(num<=0){
                            throw new ArithmeticException();
                        }
                    }
                    catch(Exception e){
                        javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                        javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
                    }
                    System.out.println("The number entered by the user is"+num);

                    for(int j=0;j<Types.NUMBEROFTYPES;j++){
                        data.addAll(dh.displayRecords(indexes.get(i),j,num));
                    }
                    System.out.println("The number of records obtained is "+data.size());
                    if(data.isEmpty()){
                        javax.swing.JFrame noRecordsMessage=new javax.swing.JFrame();
                        javax.swing.JOptionPane.showMessageDialog(noRecordsMessage,"No records found for the subject");    
                    }
                    else{
                        if(data.size()<num){
                            for(int k=0;k<data.size();k++){
                                displayElement.addAll(data.get(k).convertToLabels());
                                writeToTextFile(data.get(k),buffWriter1,buffWriter2,k+1);
                            }
                        }
                        else{
                            ArrayList<Integer> shuffledIndexes=new ArrayList<Integer>();
                            for(int k=0;k<data.size();k++){
                                shuffledIndexes.add(k);
                            }
                            Collections.shuffle(shuffledIndexes);
                            for(int k=0;k<data.size();k++){
                                System.out.print(shuffledIndexes.get(k));
                            }
                            System.out.println("");
                            for(int k=0;k<num;k++){
                                displayElement.addAll(data.get(shuffledIndexes.get(k)).convertToLabels());
                                writeToTextFile(data.get(shuffledIndexes.get(k)),buffWriter1,buffWriter2,k+1);
                            }
                        }
                        System.out.println("Display the data to the user containing "+ displayElement.size()+" records");
                        javax.swing.JPanel resultsPanel=new javax.swing.JPanel();
                        resultsPanel.setLayout(new java.awt.GridLayout(displayElement.size(),1));
                        for(int m=0;m<displayElement.size();m++){
                            resultsPanel.add(displayElement.get(m));
                        }
                        javax.swing.JOptionPane.showConfirmDialog(null,resultsPanel,"These contents will be exported",javax.swing.JOptionPane.OK_CANCEL_OPTION);
                    }
                }
            }
        }
        try{
            buffWriter2.close();
            buffWriter1.close();
            fw1.close();
            fw2.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        dh.terminateConnection();
    } 

    private void writeToTextFile(NumberedDataBundle nbd,java.io.BufferedWriter buffWriter1,java.io.BufferedWriter buffWriter2,int questionNumber){
            //This function writes the data within the data bundle into a text file that has been previously specified.
            try{
                if(nbd.type==Types.FILLINTHEBLANKS){
                    buffWriter1.write(questionNumber+". "+nbd.fb.question+"______________________________________________");
                    System.out.println(nbd.fb.question);
                    buffWriter1.newLine();
                    buffWriter1.newLine();

                    buffWriter2.write(questionNumber+". "+nbd.fb.answer);
                    System.out.println(nbd.fb.answer);
                    buffWriter2.newLine();
                    buffWriter2.newLine();
                }
                else if(nbd.type==Types.MCQ){
                    buffWriter1.write(questionNumber+". "+nbd.mcq.question);
                    System.out.println(nbd.mcq.question);
                    buffWriter1.newLine();
                    buffWriter1.write("a."+" "+nbd.mcq.option1);
                    System.out.println(nbd.mcq.option1);
                    buffWriter1.newLine();
                    buffWriter1.write("b."+" "+nbd.mcq.option2);
                    System.out.println(nbd.mcq.option2);
                    buffWriter1.newLine();
                    buffWriter1.write("c."+" "+nbd.mcq.option3);
                    System.out.println(nbd.mcq.option3);
                    buffWriter1.newLine();
                    buffWriter1.write("d."+" "+nbd.mcq.option4);
                    System.out.println(nbd.mcq.option4);
                    buffWriter1.newLine();
                    buffWriter1.newLine();

                    buffWriter2.write(questionNumber+". "+Integer.toString(nbd.mcq.answer));
                    System.out.println(Integer.toString(nbd.mcq.answer));
                    buffWriter2.newLine();
                    buffWriter2.newLine();
                }
                else if(nbd.type==Types.TRUEORFALSE){
                    buffWriter1.write("Is the following statement true or false");
                    buffWriter1.newLine();
                    buffWriter1.write(questionNumber+". "+nbd.tof.question);
                    System.out.println(nbd.tof.question);
                    buffWriter1.newLine();
                    buffWriter1.write("a. True");
                    buffWriter1.newLine();
                    buffWriter1.write("b. False");
                    buffWriter1.newLine();
                    buffWriter1.newLine();

                    buffWriter2.write(questionNumber+". "+Boolean.toString(nbd.tof.answer));
                    System.out.println(Boolean.toString(nbd.tof.answer));
                    buffWriter2.newLine();
                    buffWriter2.newLine();
                }
            }
            catch(Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
    }                                           
    
    private void modifyButtonLabelActionPerformed(java.awt.event.ActionEvent evt) {
        //This function modifies the data already present within the database.                                            
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        ArrayList<NumberedDataBundle> data=new ArrayList<NumberedDataBundle>();                                               
        indexes=subjectSelection();
        if(indexes==null){
            //If cancel option is pressed, do nothing
        }
        else if(indexes.isEmpty()){
            javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
        }
        else{
            for(int i=0;i<indexes.size();i++){
                //Obtain the questions which have to be modified.
                data=(displayForm(indexes.get(i)));
                if(data.isEmpty()){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"No options selected");
                }
                else{
                    for(int j=0;j<data.size();j++){
                        inputForm(data.get(j).type,data.get(j).subject,data.get(j));
                    }
                }
            }
        }
    }                                                 

    private void addSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        //Adds a subject to the database.                                               
        javax.swing.JLabel subjectInputLabel=new javax.swing.JLabel("Subject:");
        javax.swing.JTextField subjectInput=new javax.swing.JTextField("Enter the subject",10);
        javax.swing.JPanel inputPanel=new javax.swing.JPanel();

        inputPanel.add(subjectInputLabel);
        inputPanel.add(subjectInput);

        int result=javax.swing.JOptionPane.showConfirmDialog(null,inputPanel,"Enter the name of the subject",javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if(result==javax.swing.JOptionPane.OK_OPTION){
            if(subjectInput.getText().equals("") || subjectInput.getText().equals("Enter the subject")){
                javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Invalid input");
            }
            else{
                boolean status=Subjects.addSubject(subjectInput.getText());
                if(!status){
                    javax.swing.JFrame failedInputMessage=new javax.swing.JFrame();
                    javax.swing.JOptionPane.showMessageDialog(failedInputMessage,"Subject already exists");
                }
            }
        }

    }                                                

    
    public static void main(javax.swing.JFrame previousFrame) {
        /* Set the Nimbus look and feel */
        MainScreen.previousFrame=previousFrame;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the GUI */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().show();
            }
        });
    }
     public void show(){
        display.setVisible(true);
    }
                        
    private javax.swing.JButton addSubjectButton;
    private javax.swing.JLabel addSubjectLabel;
    private javax.swing.JButton deleteQuestionButton;
    private javax.swing.JLabel deleteQuestionLabel;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel generateTestLabel;
    private javax.swing.JButton insertQuestionButton;
    private javax.swing.JLabel insertQuestionLabel;
    private javax.swing.JButton modifyButtonLabel;
    private javax.swing.JLabel modifyQuestionLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JFrame display;
    static javax.swing.JFrame previousFrame;
    private static final String DEFAULT_ADDRESS="./exports";
    private static final int TEXT_FILE=0;                 
}
