import java.util.List;
import java.util.ArrayList;

public class CompositePattern implements FactoryPattern{
	   public String nume;
	   public String tip_intrare;
	   public String permisiuni_curentuser;
	   public String permisiuni_altiiuseri;
	   public String proprietar;
	   public CompositePattern parinte;
	   public List<CompositePattern> descendenti;
	   public String text;
	   
	   public CompositePattern(String nume,String tip_intrare,String permisiuni_curentuser,
			                   String permisiuni_altiiuseri,String proprietar){
		      this.nume = nume;
		      this.tip_intrare = tip_intrare;
		      this.permisiuni_curentuser = permisiuni_curentuser;
		      this.permisiuni_altiiuseri = permisiuni_altiiuseri;
		      this.proprietar = proprietar;
		      this.descendenti = new ArrayList<CompositePattern>();
		      
	   }
	   
	   public void add(CompositePattern f){
		      descendenti.add(f);		      
	   }
	   
	   public void remove(CompositePattern f){
		      descendenti.remove(f);
	   }
	   
	   public String getnume(){
		      return nume;
	   }

	   public String gettip_intrare(){
		      return tip_intrare;
	   }
	   
	   public String getpermisiuni_curentuser(){
		      return permisiuni_curentuser;
	   }
	   
	   public String getpermisiuni_altiiuseri(){
		      return permisiuni_altiiuseri;
	   }
	   
	   public void setparinte(CompositePattern p){
		      parinte=p;
	   }
	   
	   public CompositePattern getparinte(){
		      return parinte;
	   }
	   
	   public String getproprietar(){
		      return proprietar;
	   }
	   
	   public void schimbapermisiuni_curentuser(String noi_permisiuni_curentuser){
		   permisiuni_curentuser = noi_permisiuni_curentuser;
	   }
	   
	   public void schimbapermisiuni_altiiuseri(String noipermisiuni_altiiuseri){
		   permisiuni_altiiuseri = noipermisiuni_altiiuseri;
	   }
	   
	   public void schimbaproprietar(String nouproprietar){
		      proprietar = nouproprietar;
	   }
	   
	   public void scrietext(String textintrodus){
		      text = textintrodus;
	   }
	   
	   public String returntext(){
		      return text;
	   }
	   
	   public List<CompositePattern> getSubordinates(){
		     return descendenti;
	   }
	   
	   public String toString(){
		   return nume + " " + tip_intrare + permisiuni_curentuser + permisiuni_altiiuseri + " " + proprietar;
	   }	   
	   
}