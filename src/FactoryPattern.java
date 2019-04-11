import java.util.List;

public interface FactoryPattern {
	
	   public void add(CompositePattern f);
	   
	   public void remove(CompositePattern f);

	   public String getnume();
	   
	   public String gettip_intrare();
	   
	   public CompositePattern getparinte();
	   
	   public void setparinte(CompositePattern p);
	   
	   public String getpermisiuni_curentuser();
	  
	   public String getpermisiuni_altiiuseri();
	   
	   public String getproprietar();
	   
	   public void schimbapermisiuni_altiiuseri(String noipermisiuni_altiiuseri);
	   
	   public void schimbapermisiuni_curentuser(String noi_permisiuni_curentuser);
	   
	   public void schimbaproprietar(String nouproprietar);
	   
	   public void scrietext(String textintrodus);
	   
	   public String returntext();
	   
	   public List<CompositePattern> getSubordinates();
	   
}
