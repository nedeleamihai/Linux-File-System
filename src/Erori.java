
public class Erori {
	
	CompositePattern root = new CompositePattern("/","d","rwx","r-x","root");
	CompositePattern currentDirectory = root;
	Useri user = new Useri();
	
	public String eroare1(Command c,String[] parts,CompositePattern directorcurent){
		boolean contor = false;
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 contor = false;
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						     contor=true;
						 }
					 }
				 }
			}
		}
		if(contor==true){
			directorcurent = directorcurent.getparinte();
		}
		
		for(CompositePattern i : directorcurent.getSubordinates()){
			if(i.getnume().equals(parts[parts.length-1])&&i.gettip_intrare().equals("d")){
				return "-1: " + c.getCommand() + ": Is a directory";
			}
		}
		return "no errors";
	}
	
	public String eroare2(Command c,String[] parts,CompositePattern directorcurent){
		
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 boolean contor=false;
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
							 directorcurent = i;
							 contor = true;
						 }
					 }
					 if(contor==false){
						 return "-2: " + c.getCommand() + ": No such directory"; 
					 }
				 }
			}
		}
		return "no errors";
	}
	
	public String eroare3(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			 if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{					 
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
					 if(directorcurent.gettip_intrare().equals("f")){
						 return "-3: " + c.getCommand() + ": Not a directory";
					 }
				 }
			 }
		}
		return "no errors";
	}
	
	public String eroare4(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
				if(user.getuserlogat().equals("root")||(directorcurent.getpermisiuni_altiiuseri().startsWith("r"))||
                        (directorcurent.getproprietar().equals(user.getuserlogat()))&&directorcurent.getpermisiuni_curentuser().startsWith("r")){	
					return "no errors";
			    }else{	  
				    return "-4: " + c.getCommand() + ": No rights to read";
				}
	}
	
	public String eroare5(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
				if(user.getuserlogat().equals("root")||((!user.getuserlogat().equals(directorcurent.getproprietar())&&directorcurent.getpermisiuni_altiiuseri().substring(1).startsWith("w")))
						 ||(user.getuserlogat().equals(directorcurent.getproprietar())&&directorcurent.getpermisiuni_curentuser().substring(1).startsWith("w"))){
					return "no errors";
			    }else{	  
				    return "-5: " + c.getCommand() + ": No rights to write";
				}
	}
	
	public String eroare6(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
							 if(user.getuserlogat().equals("root")||(i.getpermisiuni_altiiuseri().endsWith("x")==true)||
									 (user.getuserlogat().equals(i.getproprietar())&&i.getpermisiuni_curentuser().endsWith("x"))){
						              directorcurent = i;
							 }else{
								 return "-6: " + c.getCommand() + ": No rights to execute";
							 }
						 }
					 }
					 
				 }
			}
		}
		return "no errors";
	}
	
	public String eroare7(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
		directorcurent = directorcurent.getparinte();
		String lastpart = parts[parts.length-1];
		
		for(CompositePattern i : directorcurent.getSubordinates()){
			if(i.getnume().equals(lastpart)&&i.gettip_intrare().equals("f")){
				   return "-7: " + c.getCommand() + ": File already exists"; 
			}
		}
		return "no errors";
	}
	
	public String eroare8(Command c,String usernou){	
		boolean contor = false;
		for(String u : user.getuseri()){
		    if(u.equals(usernou)){
			    contor = true;
		    }
	    }
		if(contor){
			return "no errors";
		}else{
		    return "-8: " + c.getCommand() + ": User does not exist";
		}
	}
	
	public String eroare9(Command c,String usernou){		   
		for(String u : user.getuseri()){
		    if(u.equals(usernou)){
			    return "-9: " + c.getCommand() + ": User already exists";
		    }
	    }
		return "no errors";
	}
	
	public String eroare10(Command c){		
		    if(!user.getuserlogat().equals("root")){
			    return "-10: " + c.getCommand() + ": No rights to change user status";
		    }
		    return "no errors";
	}
	
	public String eroare11(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
		directorcurent = directorcurent.getparinte();
		boolean contor = false;
		for(CompositePattern i : directorcurent.getSubordinates()){
			if(i.getnume().equals(parts[parts.length-1])&&i.gettip_intrare().equals("f")){
				contor = true;
			}
		}
		if(contor==false){
			return "-11: " + c.getCommand() + ": No such file";
		}
		return "no errors";
	}
	
	public String eroare12(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
		
		if(directorcurent.getSubordinates().size()==0){
			return "-12: " + c.getCommand() + ": No such file or directory";
		}
		return "no errors";
	}
	
	public String eroare13(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
		if(Verifica_ierarhia_pentru13(directorcurent)==false){
			return "-13: " + c.getCommand() + ": Cannot delete parent or current directory";
		}
		return "no errors";
	}
	
	public String eroare14(Command c,String[] parts,CompositePattern directorcurent){
		for(String s : parts){
			if(s.equals(".")==false){
				 if(s.equals("..")){
					 directorcurent = directorcurent.getparinte();
				 }else{	
					 for(CompositePattern i : directorcurent.getSubordinates()){
						 if(i.getnume().equals(s)){
						     directorcurent = i;
						 }
					 }
				 }
			}
		}
		if(directorcurent.getSubordinates().size()>0){
			return "-14: " + c.getCommand() + ": Non empty directory";
		}
		return "no errors";
	}
	
	public boolean Verifica_ierarhia_pentru13(CompositePattern entitate){
		if(entitate==currentDirectory){
			return false;
		}
		if(entitate.getSubordinates().size()==0){
			for(CompositePattern i : entitate.getSubordinates()){
				if(i==currentDirectory){
					return false;
				}else{
					return Verifica_ierarhia_pentru13(i);
				}
			}
		}
		return true;
	}
	
}
