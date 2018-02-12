package bo.database;

import java.util.ArrayList;
import java.util.List;

import bo.Email;

public class EmailDataBase {

	private EmailDataBase(){}

	private static List<Email> emailList = new ArrayList<>();

	public static void addEmailToDataBase(Email email){
		emailList.add(email);
	}

	public static List<Email> getEmailFromDataBase(){
		return emailList;
	}


}
