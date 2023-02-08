package javaTester;

public class Topic_06_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String username = "admin";
		String password = "admin";
		System.out.println(url);
		String[] arrayUrl = url.split("//");
		url = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
		System.out.println(url);
		
		String firstname = "Loan";
		String lastname = "Hoang";
		System.out.println(firstname + "" + lastname);
		System.out.println(firstname.concat(" ").concat(lastname));
	}

}
