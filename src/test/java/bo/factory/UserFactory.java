package bo.factory;

import bo.User;

public class UserFactory {

	public static User getModule5testmailboxUser(){
		return new User("module5testmailbox", "qwerty12345");
	}
}
