package sd.deusto.ingenieria.sd.tw2.methods;
import java.util.HashMap;
import java.util.Map;


public class FacebookMethods {
	public Map<String,String> accounts = new HashMap<String,String>();
	public static FacebookMethods instance;
	private FacebookMethods() {
		this.initFacebook();
	}
	public static FacebookMethods getInstance() {
		if (instance == null) {
			instance = new FacebookMethods();
		}
		
		return instance;
	}
	public boolean register(String email, String password) {
		if (!accounts.containsKey(email)) {
			accounts.put(email, password);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean loginService(String email, String password) {
		if (accounts.containsKey(email) && accounts.get(email).equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void initFacebook() {
		accounts.put("andrea@gmail.com","1234");
		accounts.put("fernando@gmail.com","5678");
	}
}
