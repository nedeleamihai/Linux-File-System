import java.util.List;
import java.util.ArrayList;

public class Useri {
	   public List<String> users;
	   public String userlogat = "root";
	   
	   Useri(){
		   this.userlogat = "root";
		   this.users = new ArrayList<String>();
	   }
	   public void add(String nume) {
	          users.add(nume);
	   }
	   
	   public void remove(String nume){
		      users.remove(nume);
	   }
	   
	   public List<String> getuseri(){
		      return users;
	   }
	   
	   public String getuserlogat(){
		   return userlogat;
	   }
	   public void schimbauserlogat(String usernou){
		      userlogat = usernou;
	   }
	   
}