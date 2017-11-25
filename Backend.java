/* This file contains the classes mainly associated with the data storage operations. The most important class amonggst these is 
 * the DataHandler class that manages the connection with the sqlite database.
 * NOTE: If a connection is opened by creating an instance of data handler, the terminate connection must be explicitly invoked to 
 *       close the connection.
 *********************************************************************************************************************************
*/
/**
 *
 * @author tanmay
 */
import java.sql.*;
import java.util.*;
class Subjects{
	//It stores the subjects and their respective indexes in a hash table
	static int numOfSubjects;
	private static Hashtable<Integer,String> subjectMapping;
	static{
		subjectMapping=new Hashtable<Integer,String>();
		DataHandler dh=new DataHandler();
		ArrayList<String> subjectList=dh.loadSubjects();
		for(int i=0;i<subjectList.size();i++){
			subjectMapping.put(i,subjectList.get(i));
		}

		numOfSubjects=subjectList.size();
		dh.terminateConnection();
		
	}
	static String convertToString(int subject){
		//It returns the subject code into the String form of the subject
		return subjectMapping.get(subject);
	}
	static boolean addSubject(String subject){
		//It adds the subject to the hash table and the database.
		if(subjectMapping.containsValue(subject)){
			return false;
		}
		subjectMapping.put(numOfSubjects,subject);
		numOfSubjects++;
		DataHandler dh=new DataHandler();
		dh.addSubjectToDataBase(subject);
		dh.terminateConnection();
		return true;
	}
}
class Types{
	//It stores the differnt types available to the user.
	final static int FILLINTHEBLANKS=0;
	final static int MCQ=1;
	final static int TRUEORFALSE=2;
	final static int NUMBEROFTYPES=3;
	static String convertToString(int type){
		//Converts the index to string to be used into the database.
		if(type==0){
			return "fillblanks";
		}
		else if(type==1){
			return "mcq";
		}
		else if(type==2){
			return "truefalse";
		}
		return "type not defined";
	}
}
class FillInTheBlanks{
	//It stores all the data related to a fill in the blanks question.
	String question;
	String answer;
	FillInTheBlanks(String question,String answer){
		this.question=question;
		this.answer=answer;
	}
}
class MultipleChoiceQuestion{
	//It stores all the data related to a fill in the multiple choice question.
	String question;
	String option1;
	String option2;
	String option3;
	String option4;
	int answer;
	MultipleChoiceQuestion(String question,String option1,String option2,String option3,String option4,int answer){
		this.question=question;
		this.option1=option1;
		this.option2=option2;
		this.option3=option3;
		this.option4=option4;
		this.answer=answer;
	}
}
class TrueOrFalse{
	//It stores all the data related to a true or false question.
	String question;
	boolean answer;
	TrueOrFalse(String question,boolean answer){
		this.question=question;
		this.answer=answer;
	}
}
class DataBundle{
	//It bundles together all the types of questions into a single object.
	int subject;
	FillInTheBlanks fb;
	MultipleChoiceQuestion mcq;
	TrueOrFalse tof;

	//Overloaded constructors to handle different types of objects.
	DataBundle(int subject,String question,String answer){
		this.subject=subject;
		fb=new FillInTheBlanks(question,answer);
	}
	DataBundle(int subject,String question,String option1,String option2,String option3,String option4,int answer){
		this.subject=subject;
		mcq=new MultipleChoiceQuestion(question,option1,option2,option3,option4,answer);
	}
	DataBundle(int subject,String question,boolean answer){
		this.subject=subject;
		tof=new TrueOrFalse(question,answer);
	}

	String assignValues(int type){
		//It is used to assign the values,when we wish to update the database.
		String assignments="";
		if(type==Types.FILLINTHEBLANKS){
			assignments+="question=";
			assignments+="\""+fb.question+"\"";
			assignments+=",";
			assignments+="answer=";
			assignments+="\""+fb.answer+"\"";
		}
		else if(type==Types.MCQ){
			assignments+="question=";
			assignments+="\""+mcq.question+"\"";
			assignments+=",";
			assignments+="option1=";
			assignments+="\""+mcq.option1+"\"";
			assignments+=",";
			assignments+="option2=";
			assignments+="\""+mcq.option2+"\"";
			assignments+=",";
			assignments+="option3=";
			assignments+="\""+mcq.option3+"\"";
			assignments+=",";
			assignments+="option4=";
			assignments+="\""+mcq.option4+"\"";
			assignments+=",";
			assignments+="answer=";
			assignments+=mcq.answer;
		}
		else if(type==Types.TRUEORFALSE){
			assignments+="question=";
			assignments+="\""+tof.question+"\"";
			assignments+=",";
			assignments+="answer=";
			assignments+=(tof.answer?1:0);
		}
		return assignments;
	}
	
}
class NumberedDataBundle extends DataBundle{
	//It is a bundle of data with addional characterisitics such as the serial number within the database and the type of the question stored.
	int serialnum;
	int type;
	NumberedDataBundle(int type,int serialnum,int subject,String question,String answer){
		super(subject,question,answer);
		this.serialnum=serialnum;
		this.type=type;
	}
	NumberedDataBundle(int type,int serialnum,int subject,String question,String option1,String option2,String option3,String option4,int answer){
		super(subject,question,option1,option2,option3,option4,answer);
		this.serialnum=serialnum;
		this.type=type;
	}
	NumberedDataBundle(int type,int serialnum,int subject,String question,boolean answer){
		super(subject,question,answer);
		this.serialnum=serialnum;
		this.type=type;
	}
	ArrayList<javax.swing.JLabel> convertToLabels(){
		//Converts the data into label form to be displayed when the data is to be exported
		ArrayList<javax.swing.JLabel> displayData=new ArrayList<javax.swing.JLabel>();
		if(type==Types.FILLINTHEBLANKS){
			displayData.add(new javax.swing.JLabel(fb.question+"_________________"));
			displayData.add(new javax.swing.JLabel(fb.answer));
			displayData.add(new javax.swing.JLabel(" "));
		}
		else if(type==Types.MCQ){
			displayData.add(new javax.swing.JLabel(mcq.question));
			displayData.add(new javax.swing.JLabel("a."+mcq.option1));
			displayData.add(new javax.swing.JLabel("b."+mcq.option2));
			displayData.add(new javax.swing.JLabel("c."+mcq.option3));
			displayData.add(new javax.swing.JLabel("d."+mcq.option4));
			displayData.add(new javax.swing.JLabel("Answer is option:"+Integer.toString(mcq.answer)));
			displayData.add(new javax.swing.JLabel(" "));
		}
		else{
			displayData.add(new javax.swing.JLabel(tof.question));
			displayData.add(new javax.swing.JLabel(Boolean.toString(tof.answer)));
			displayData.add(new javax.swing.JLabel(" "));
		}
		return displayData;
	}
}
class DataHandler{
	//This class is resposible for all the operatins related to the database.
	Connection con;
	Statement stmt1,stmt2;
	static final int PRINTALL=-1;
	DataHandler(){
	//It connects to the database and terminates execution if the database is not found.
		try{
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:DataBase/test.db");
			con.setAutoCommit(false);
		}
		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
		System.out.println("Database connected successfully!");
	}
	ArrayList<String> loadSubjects(){
		//This is invoked when the subjects are taken from the database when the program is loaded.
		ResultSet rs=null;
		ArrayList<String> subjectList=new ArrayList<String>();
		try{
			stmt1=con.createStatement();
			rs=stmt1.executeQuery("SELECT * FROM SUBJECTS");

			while(rs.next()){
				subjectList.add(rs.getString("subjectName"));
			}

			stmt1.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	return subjectList;
	}


	void addSubjectToDataBase(String subject){
		//It adds a subject to the database table subjects
		String query="INSERT INTO SUBJECTS VALUES (\""+ subject+"\")";
		System.out.println("Sending "+query);
		try{
			stmt1=con.createStatement();
			stmt1.executeUpdate(query);
			stmt1.close();
			con.commit();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	Boolean checkUserPresent(String username){
		//It checks if the user sent to the database is present in the database.
		ResultSet res=null;
		int size=0;
		try{
			stmt1=con.createStatement();
			res=stmt1.executeQuery("SELECT COUNT(*) AS total FROM USERINFO WHERE USERNAME IS "+"\""+username+"\"");
			size=res.getInt("total");
			stmt1.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
		if(size==0){
			return false;
		}
		else{
			return true;
		}
	}
	void insertUser(String username,String password){
		//It adds a user to the database, to the table userinfo
		String query="";
		query+="INSERT INTO USERINFO (USERNAME,PASSWORD) VALUES (";
		query+="\""+username+"\"";
		query+=",";
		query+="\""+password+"\"";
		query+=");";
		try{
			stmt1=con.createStatement();
			stmt1.executeUpdate(query);
			stmt1.close();
			con.commit();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	void insertValue(int type,DataBundle db){
		/*
		Adds a question to the database depending on the type of question.
			1. Fill in the blanks
			2. Multiple Choice Question
			3. True or false question
		in their respective formats.
		*/
		String query="";
		int size=0;
		ResultSet rs=null;
		try{
			stmt1=con.createStatement();
			if(type==Types.FILLINTHEBLANKS){
				query="INSERT INTO FILLBLANKS (QUESTION,ANSWER,SUBJECT) VALUES (";
				query+="\""+db.fb.question+"\"";
				query+=",";
				query+="\""+db.fb.answer+"\"";
				query+=",";
				query+="\""+Subjects.convertToString(db.subject)+"\"";
			}
			if(type==Types.MCQ){
				query="INSERT INTO MCQ (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER,SUBJECT) VALUES (";
				query+="\""+db.mcq.question+"\"";
				query+=",";
				query+="\""+db.mcq.option1+"\"";
				query+=",";
				query+="\""+db.mcq.option2+"\"";
				query+=",";
				query+="\""+db.mcq.option3+"\"";
				query+=",";
				query+="\""+db.mcq.option4+"\"";
				query+=",";
				query+=db.mcq.answer;
				query+=",";
				query+="\""+Subjects.convertToString(db.subject)+"\"";
			}
			else if(type==Types.TRUEORFALSE){
				query="INSERT INTO TRUEFALSE (QUESTION,ANSWER,SUBJECT) VALUES (\"";
				query+=db.tof.question;
				query+="\",";
				query+=db.tof.answer?1:0;
				query+=",\"";
				query+=Subjects.convertToString(db.subject);
				query+="\"";
			}
			query+=");";
			System.out.println("Sending "+query);
			stmt1.executeUpdate(query);
			stmt1.close();
			con.commit();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}

	void deleteValue(int type,int serialnum){
		//Deletes the entry based on the serial number
		try{
			stmt1=con.createStatement();
			String query="";
			query+="DELETE FROM ";
			query+=Types.convertToString(type);
			query+=" WHERE SERIALNUM="+serialnum+";";
			System.out.println("Sending the query: "+query);
			stmt1.executeUpdate(query);
			con.commit();
			stmt1.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	boolean deleteUser(String username){
		//It deletes user based on the username and returns true if the username is found.
		try{
			stmt1=con.createStatement();
			String query="";
			int countInitial=0,countFinal=0;
			ResultSet rs=null;
			rs=stmt1.executeQuery("SELECT COUNT(*) AS total FROM USERINFO");
			countInitial=rs.getInt("total");
			query+="DELETE FROM USERINFO WHERE USERNAME=";
			query+="\""+username+"\"";
			stmt1.executeUpdate(query);
			con.commit();
			rs=stmt1.executeQuery("SELECT COUNT(*) AS total FROM USERINFO");
			countFinal=rs.getInt("total");
			stmt1.close();
			if(countInitial>countFinal || countInitial==0){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
         	return false;
		}
	}
	void modifyValue(int type,DataBundle db,int serialnum){
		//Modifies the data stored on the database based on the seerial number.
		try{
			stmt1=con.createStatement();
			String query="";
			query+="UPDATE ";
			query+=Types.convertToString(type);
			query+=" SET ";
			query+=db.assignValues(type);
			query+=" WHERE SERIALNUM="+serialnum+";";
			System.out.println("Sending the query: "+query);
			stmt1.executeUpdate(query);
			con.commit();
			stmt1.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	ArrayList<NumberedDataBundle> displayRecords(int subject,int type,int number){
		//It returns an arraylist of the records stored within the database in the form of an arraylist.
		try{
			stmt1=con.createStatement();
			stmt2=con.createStatement();
			ResultSet rs1=null,rs2=null;
			int size=0;
			if(type==Types.FILLINTHEBLANKS){
				rs1=stmt1.executeQuery("SELECT * FROM FILLBLANKS WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
				rs2=stmt2.executeQuery("SELECT COUNT(*) AS total FROM FILLBLANKS WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
			}
			else if(type==Types.MCQ){
				rs1=stmt1.executeQuery("SELECT * FROM MCQ WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
				rs2=stmt2.executeQuery("SELECT COUNT(*) AS total FROM MCQ WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
			}
			else if(type==Types.TRUEORFALSE){
				rs1=stmt1.executeQuery("SELECT * FROM TRUEFALSE WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
				rs2=stmt2.executeQuery("SELECT COUNT(*) AS total FROM TRUEFALSE WHERE SUBJECT IS "+"\""+Subjects.convertToString(subject)+"\"");
			}
			if(rs1==null||rs2==null){
				System.out.println("An error occured,the result set is not initialized");
				System.exit(0);
			}
			int count=0;
			size=rs2.getInt("total");
			ArrayList<NumberedDataBundle> values=new ArrayList<NumberedDataBundle>();
			//If all the records have to be stored
			if(number>size||number==DataHandler.PRINTALL){
				while(rs1.next()){
					if(type==Types.FILLINTHEBLANKS){
						values.add(new NumberedDataBundle(Types.FILLINTHEBLANKS,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getString("answer")));
					}
					else if(type==Types.MCQ){
						values.add(new NumberedDataBundle(Types.MCQ,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getString("option1"),rs1.getString("option2"),rs1.getString("option3"),rs1.getString("option4"),rs1.getInt("answer")));
					}
					else if(type==Types.TRUEORFALSE){
						values.add(new NumberedDataBundle(Types.TRUEORFALSE,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getInt("answer")==1?true:false));
					}
				}
			}
			else{//For a given number of records specified by the user
				while(rs1.next() && count<size){
					if(type==Types.FILLINTHEBLANKS){
						values.add(new NumberedDataBundle(Types.FILLINTHEBLANKS,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getString("answer")));
					}
					else if(type==Types.MCQ){
						values.add(new NumberedDataBundle(Types.MCQ,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getString("option1"),rs1.getString("option2"),rs1.getString("option3"),rs1.getString("option4"),rs1.getInt("answer")));
					}
					else if(type==Types.TRUEORFALSE){
						values.add(new NumberedDataBundle(Types.TRUEORFALSE,rs1.getInt("serialnum"),subject,rs1.getString("question"),rs1.getInt("answer")==1?true:false));
					}
				}
			}
			stmt1.close();
			stmt2.close();
			return values;
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
         	return null;
		}
	}
	public void terminateConnection(){
		//It closes the connection to the database. It is essential if a connection is made at the time of construction, it has to be closed.
		try{
			con.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	public void finalize(){
		//If any connections are left open,they are closed when the object is cleaned by the garbage collector.
		try{
			con.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
		}
	}
	public boolean verifyUser(String username,String password){
		//This function verifies the credentials of the user by looking up the values from the database and returns true if the person exitsts.
		try{
			ResultSet rs1=null,rs2=null;
			stmt1=con.createStatement();
			stmt2=con.createStatement();
			rs1=stmt1.executeQuery("SELECT * FROM USERINFO WHERE USERNAME="+"\""+username+"\"");
			rs2=stmt2.executeQuery("SELECT COUNT(*) AS total FROM USERINFO WHERE USERNAME="+"\""+username+"\"");
			if(rs2.getInt("total")==0){
				return false;
			}
			else{
				if(password.equals(rs1.getString("password"))){
					return true;
				}
				else{
					return false;
				}
			}
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         	System.exit(0);
         	return false;
		}
	}
}
