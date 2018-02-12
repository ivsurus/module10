package core.util;

import java.util.Random;

public class RandomDataHelper {

	public static String getRandomEmailSubject(){
		int  n = new Random().nextInt(1000001) + 1;
		return String.format("testEmail%s", n);
	}

	public static String getRandomEmailBody(){
		int  n = new Random().nextInt(101) + 1;
		 	return String.format("Hello! I'm a test email No%s", n);
	}

	public static String getRandomEmailAdress(){
		int  n = new Random().nextInt(10001) + 1;
		return String.format("qwerty%s@yandex.com", n);
	}

}
