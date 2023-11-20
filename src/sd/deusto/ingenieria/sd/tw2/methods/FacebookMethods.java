package sd.deusto.ingenieria.sd.tw2.methods;

import java.util.ArrayList;
import java.util.HashMap;

public class FacebookMethods {
	
	private ArrayList<Account> account = new ArrayList<>();
	private HashMap<Email, String> map = new HashMap<>();
	
	public void registration(String email, String password) {
		Account c1 = new Account();
		boolean h = true;
		
		c1.setEmail(email);
		c1.setPassword(password);

		//change latter to sending the email to google or facebook for validation
		for (Account account2 : account) {
			if(account2.getName().equals(name)) {
				h = false;
				break;
			}
		}
		if (h) {
			account.add(c1);
			return c1;
		}else {
			return null;
		}
	}
	
	public Account login(String email, String password) {
		Account account = new Account();
		//just until the login with facebook is done
		boolean b = false;
		for (Account acaux : this.account) {
			if (acaux.getEmail().equals(email)) {
				//remove once loggin with facebook or google is done
				if (acaux.checkPassword(password)) {
					account.setEmail(email);
					account.setPassword(password);
					b = true;
					break;
				}
			}
		}
		if (b) {
			return account;
		} else {
			return null;		}
	}
}
