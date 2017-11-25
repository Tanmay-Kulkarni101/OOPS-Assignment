/*The execution starts with the driver class which inokes the other classes.
 * The order of invokation is as follows:
 * 1. The Driver Class
 * 2. The Security Class which verifies the username and the password
 *
 * 3. The AdminControls Class which helps the adminstrator of the system perform the necessary functions.
 * OR
 * 3. For an ordinary user, the Main Screen Class loads, which displays all the options available:
 *		a. Insert new question
 *		b. Delete existing question
 *		c. Modify existing question
 *		d. Export questions to File
 ****************************************************************************************************************************
 */
/**
 *@author tanmay kulkarni
 *
 */


import javax.swing.*;

 public class Driver{
	public static void main(String args[]){
        Security.main();
	}
}