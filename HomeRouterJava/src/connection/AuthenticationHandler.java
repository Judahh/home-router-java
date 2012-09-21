/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 * 
 * @author JH
 */
public class AuthenticationHandler {
	private Auth auth;
	private String passwordVTY;
	private String passwordEnable;
	private String user;

	public AuthenticationHandler(Auth auth) {
		this.auth = auth;
                passwordVTY=null;
                passwordEnable=null;
                user=null;
	}

	public AuthenticationHandler(int i) {
		this.auth = Auth.values()[i];
	}

	public Auth getAuthentication() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}
        
        public String getPassword(boolean Enable) {
            if(Enable){
		return getPasswordB();
            }
            return getPassword();
	}

	public String getPassword() {
            if(passwordVTY==null){
                //abrir janela pedindo pass
            }
            return passwordVTY;
	}
        
        public void setPassword(String password, boolean Enable) {
            if(Enable){
                this.passwordEnable = password;
            }else{
                this.passwordVTY = password;
            }
	}

	public void setPassword(String password) {
		this.passwordVTY = password;
	}

	public String getPasswordB() {
            if(passwordEnable==null){
                //abrir janela pedindo pass
            }
            return passwordEnable;
	}

	public void setPasswordB(String passwordB) {
		this.passwordEnable = passwordB;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Auth getAuthenticationPrompt() {
		return this.auth;
	}

	public Auth[] getAuthValues() {
		return Auth.values();
	}

	public enum Auth {
		Login0, Login1, Login2, Login3, Pass0, Pass1, Pass2, Pass3
	}

	public String getAuth() {
		return getAuth(this.auth);
	}

	public String getAuth(Auth auth) {
		switch (auth) {
		case Login0:
			return "Login: ";
		case Login1:
			return "login: ";
		case Login2:
			return "User: ";
		case Login3:
			return "user: ";
		case Pass0:
			return "Password: ";
		case Pass1:
			return "password: ";
		case Pass2:
			return "Pass: ";
		case Pass3:
			return "pass: ";
		default:
			return "######";
		}
	}
}
