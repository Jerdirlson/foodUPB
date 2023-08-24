import model.User;

import login.*;;

public class App {
    public static void main(String[] args) throws Exception { 
        User user = new User();

        user = LoginForm.getAuthenticatedUser("123@gmail.com", "123");

        System.out.println(user.email + " " + user.password +" " + user.name + " " + user.adress + " " + user.phone);
    }
}
