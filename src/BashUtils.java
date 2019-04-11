
public class BashUtils {
	   
	   Erori erori = new Erori();
	   
	   class adduser implements CommandSubscriber{	
		   @Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("adduser")){	
					if(erori.user.getuseri().size()==0){
						erori.user.add("root");
					}
					
					String rezultat = erori.eroare9(c, c.getCommand().substring(8));
					
					if(!rezultat.startsWith("-")){
						rezultat = erori.eroare10(c);
					}
					
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						erori.user.add(c.getCommand().substring(8));
			            CompositePattern directorusernou = new CompositePattern(c.getCommand().substring(8),"d","rwx","---",c.getCommand().substring(8));
			            erori.root.add(directorusernou);
			            directorusernou.setparinte(erori.root);
					}
				}				
		   }
	   }
	   public adduser getadduserInstance () {
	    	adduser instanta = new adduser ();
	        return instanta;
	   }
	   
	   class chuser implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("chuser")){
					
					String rezultat = erori.eroare8(c, c.getCommand().substring(7));
					
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						erori.user.schimbauserlogat(c.getCommand().substring(7));
						if(erori.user.getuserlogat().equals("root")){
							erori.currentDirectory = erori.root;
						}else{
						    for(CompositePattern i : erori.root.getSubordinates()){
							   if(i.getnume().equals(c.getCommand().substring(7))){
								   erori.currentDirectory = i;
							   }
						    }
						}
					}
				}
				
			}
	   }
	   public chuser getchuserInstance () {
			chuser instanta = new chuser();
	        return instanta;
	   }
	   
	   class deluser implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("deluser")){
					
					String rezultat = erori.eroare9(c, c.getCommand().substring(7));
					
					if(!rezultat.startsWith("-")){
						rezultat = erori.eroare10(c);
					}
					
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						erori.user.remove(c.getCommand().substring(8));
			            for(CompositePattern i : erori.root.getSubordinates()){
			            	if(i.getnume().equals(c.getCommand().substring(8))){
			            		if(erori.user.getuseri().size()==1){
			            		   i.schimbaproprietar(erori.user.users.get(0));
			            		}else{
			            		   i.schimbaproprietar(erori.user.users.get(1));
			            		}
			            	}
			            }
					}
					
				}
			}
	   }
	   public deluser getdeluserInstance () {
	    	deluser instanta = new deluser ();
	        return instanta;
	   }
	   
	   class cd implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {	
				if(c.getCommand().startsWith("cd")){
					String rezultat;
					CompositePattern directorcurent;
					if(c.getCommand().substring(3).startsWith("/")){
						String[] parts = c.getCommand().substring(4).split("/");
						
						rezultat = erori.eroare2(c,parts,erori.root);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.root);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.root);
						
					}else{
						String[] parts = c.getCommand().substring(3).split("/");
						
						rezultat = erori.eroare2(c,parts,erori.currentDirectory);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.currentDirectory);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
					}
					
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						erori.currentDirectory = directorcurent;
					}
				}
			}
	   }
	   public cd getcdInstance () {
	    	cd instanta = new cd ();
	        return instanta;
	   }
	   
	   class mkdir implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("mkdir")){	
					String rezultat;
					CompositePattern directorcurent = erori.root;
					String lastpart = "errors";
					if(c.getCommand().substring(6).equals("/")){
						rezultat = "-1: " + c.getCommand() + ": Is a directory";
					}else{
					if(c.getCommand().substring(6).startsWith("/")){
					        String[] parts = c.getCommand().substring(7).split("/");
							String parts2[] = new String[parts.length-1];							
					        for(int i=0;i<parts2.length;i++){
					        	parts2[i]=parts[i];
					        }
					        rezultat = erori.eroare1(c,parts,erori.root);
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare2(c,parts2,erori.root);
							}
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare3(c,parts,erori.root);
							}
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare6(c,parts,erori.root);
							}
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare5(c,parts,erori.root);
							}
							
							directorcurent = GoToSpecificFile(parts,erori.root);
							lastpart = parts[parts.length-1];
					}else{
						   String[] parts = c.getCommand().substring(6).split("/");
						   String parts2[] = new String[parts.length-1];							
					        for(int i=0;i<parts2.length;i++){
					        	parts2[i]=parts[i];
					        }
					        
						   rezultat = erori.eroare1(c,parts,erori.currentDirectory);
						   
						   if(!rezultat.startsWith("-")){
								rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
							}
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare3(c,parts,erori.currentDirectory);
							}							

							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare6(c,parts,erori.currentDirectory);
							}
							
							if(!rezultat.startsWith("-")){
								rezultat = erori.eroare5(c,parts,erori.currentDirectory);
							}
														
							directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
							lastpart = parts[parts.length-1];
					}
					}
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						CompositePattern nouaentitate = new CompositePattern(lastpart,"d","rwx","---",erori.user.getuserlogat());
						directorcurent.add(nouaentitate);
						nouaentitate.setparinte(directorcurent);
					}
				}
			}
	   }
	   public mkdir getmkdirInstance () {
			mkdir instanta = new mkdir();
	        return instanta;
	   }
	   
	   class ls implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("ls")){
					String rezultat;
					CompositePattern directorcurent;
					if(c.getCommand().substring(3).startsWith("/")){
						String[] parts = c.getCommand().substring(4).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
						rezultat = erori.eroare2(c,parts2,erori.root);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare4(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts,erori.root);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.root);
						
					}else{
						String[] parts = c.getCommand().substring(3).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare4(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts,erori.currentDirectory);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
					}
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						for(CompositePattern i : directorcurent.getSubordinates()){
							   System.out.println(i);
						}
					}
				}
			}
	   }
	   public ls getlsInstance () {
			ls instanta = new ls ();
	        return instanta;
	   }
	   
	   
	   class chmod implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("chmod")){
					String rezultat;
					CompositePattern directorcurent;
					String copie;
			    	Integer[] permisiuni = new Integer[2];
					copie = c.getCommand().substring(6, 7);
					permisiuni[0] = Integer.parseInt(copie);
					copie = c.getCommand().substring(7, 8);
					permisiuni[1] = Integer.parseInt(copie);
					if(c.getCommand().substring(9).startsWith("/")){
						String[] parts = c.getCommand().substring(10).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare2(c,parts2,erori.root);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts2,erori.root);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.root);
						
					}else{
						String[] parts = c.getCommand().substring(9).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts2,erori.currentDirectory);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
					}
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
					}else{
						String[] listapermisiuni = {"---","--x","-w-","-wx","r--","r-x","rw-","rwx"};
						String noipermisiuniusercurent = listapermisiuni[permisiuni[0]];
						String noipermisiunialtiuseri = listapermisiuni[permisiuni[1]];
						directorcurent.schimbapermisiuni_altiiuseri(noipermisiunialtiuseri);
						directorcurent.schimbapermisiuni_curentuser(noipermisiuniusercurent);
					}
				}
			}
	   }
	   public chmod getchmodInstance () {
			chmod instanta = new chmod ();
	        return instanta;
	   }
	   
	   class rmdir implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("rmdir")){
					String rezultat;
					CompositePattern directorcurent;
				  if(c.getCommand().substring(6).startsWith("/")){
						String[] parts = c.getCommand().substring(7).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
						rezultat = erori.eroare2(c,parts,erori.root);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare13(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare14(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts2,erori.root);
						}
						directorcurent = GoToSpecificFile(parts,erori.root);
				  }else{
						String[] parts = c.getCommand().substring(6).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare2(c,parts,erori.currentDirectory);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts,erori.currentDirectory);
						}
												
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare13(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare14(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts2,erori.currentDirectory);
						}
						
						directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
				  }	  
				  if(rezultat.startsWith("-")){
						System.out.println(rezultat);
				  }else{
					  CompositePattern parintedirectorcurent = directorcurent.getparinte();
					  parintedirectorcurent.remove(directorcurent);
				  }
			    }
			}
	   }
	   public rmdir getrmdirInstance () {
			rmdir instanta = new rmdir ();
	        return instanta;
	   }
	   
	   class touch implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("touch")){
					String rezultat;
					CompositePattern directorcurent;	
					String lastpart;
					if(c.getCommand().substring(6).startsWith("/")){
						String[] parts = c.getCommand().substring(7).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare5(c,parts2,erori.root);      
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare1(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.root);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare7(c,parts,erori.root);
						}
						directorcurent = GoToSpecificFile(parts2,erori.root);
						lastpart = parts[parts.length-1];
					}else{
						String[] parts = c.getCommand().substring(6).split("/");                        
                        String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
				        rezultat = erori.eroare5(c,parts2,erori.currentDirectory);
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare1(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts,erori.currentDirectory);
						}
						
						if(!rezultat.startsWith("-")){
							rezultat = erori.eroare7(c,parts,erori.currentDirectory);
						}
						directorcurent = GoToSpecificFile(parts2,erori.currentDirectory);
						lastpart = parts[parts.length-1];
					}
					if(rezultat.startsWith("-")){
						System.out.println(rezultat);
				    }else{
					  CompositePattern noufisier = new CompositePattern(lastpart,"f","rwx","---",erori.user.getuserlogat());
					  directorcurent.add(noufisier);
					  noufisier.setparinte(directorcurent);
				    }
				}
			}
	   }
	   public touch gettouchInstance () {
		   touch instanta = new touch ();
	        return instanta;
	   }
	   
	   class rm implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("rm ")){
					String rezultat = "errors";
					CompositePattern directorcurent = erori.root;	
			      if(c.getCommand().substring(3).startsWith("-r")){		
					if(c.getCommand().substring(6).startsWith("/")){
						String[] parts = c.getCommand().substring(7).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
				        rezultat = erori.eroare2(c,parts2,erori.root);
						
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.root);
						}
						
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare13(c,parts,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts2,erori.root);
						}
				        directorcurent = GoToSpecificFile(parts,erori.root);
				        
					}else{
						String[] parts = c.getCommand().substring(6).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
				        rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
						}
						
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare13(c,parts,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare12(c,parts2,erori.currentDirectory);
						}
				        directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
					}      
				  }else{
					  if(c.getCommand().substring(3).startsWith("/")){
							String[] parts = c.getCommand().substring(4).split("/");
							String parts2[] = new String[parts.length-1];							
					        for(int i=0;i<parts2.length;i++){
					        	parts2[i]=parts[i];
					        }
					        
					        rezultat = erori.eroare1(c,parts,erori.root);
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare2(c,parts2,erori.root);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare3(c,parts2,erori.root);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare5(c,parts2,erori.root);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare6(c,parts2,erori.root);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare11(c,parts,erori.root);
							}
					        
					        directorcurent = GoToSpecificFile(parts,erori.root);
					        
					  }else{
							String[] parts = c.getCommand().substring(3).split("/");
							String parts2[] = new String[parts.length-1];							
					        for(int i=0;i<parts2.length;i++){
					        	parts2[i]=parts[i];
					        }
					        
                            rezultat = erori.eroare1(c,parts,erori.currentDirectory);
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare5(c,parts2,erori.currentDirectory);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare6(c,parts2,erori.currentDirectory);
							}
					        
					        if(!rezultat.startsWith("-")){
								rezultat = erori.eroare11(c,parts,erori.currentDirectory);
							}
					        
					        directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
					  }       
			      }
			      if(rezultat.startsWith("-")){
						System.out.println(rezultat);
				  }else{
					  CompositePattern parintedirectorcurent = directorcurent.getparinte();
					  parintedirectorcurent.remove(directorcurent);
				  }
				}			
			}
	   }
	   public rm getrmInstance () {
			rm instanta = new rm ();
	        return instanta;
	   }
	   
	   class writetofile implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("writetofile")){
			      CompositePattern directorcurent;
				  String rezultat = "erors";	
				  String[] path_and_content = c.getCommand().substring(12).split(" ");
				  String path = path_and_content[0];
				  String content = path_and_content[1];
				  for(int i=2;i<path_and_content.length;i++){
					  content = content.concat(" ");
					  content = content.concat(path_and_content[i]);
				  }
				  if(path.startsWith("/")){
						String[] parts = path.substring(1).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
				        rezultat = erori.eroare1(c,parts,erori.root);
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare11(c,parts,erori.root);
						}
				        directorcurent = GoToSpecificFile(parts,erori.root);
				  }else{
						String[] parts = path.split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
				        rezultat = erori.eroare1(c,parts,erori.currentDirectory);
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare5(c,parts,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare11(c,parts,erori.currentDirectory);
						}
				        directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
				  }	
				  if(rezultat.startsWith("-")){
						System.out.println(rezultat);
				  }else{
					    directorcurent.scrietext(content);
				  }
			    }
			}
	   }
	   public writetofile getwritetofileInstance () {
			writetofile instanta = new writetofile ();
	        return instanta;
	   }
	   
	   class cat implements CommandSubscriber{		
			@Override
			public void executeCommand(Command c) {			
				if(c.getCommand().startsWith("cat")){
				  String rezultat;
				  CompositePattern directorcurent;
				  if(c.getCommand().substring(4).startsWith("/")){
						String[] parts = c.getCommand().substring(5).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare1(c,parts,erori.root);
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.root);
						}

				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare4(c,parts,erori.root);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare11(c,parts,erori.root);
						}
				        directorcurent = GoToSpecificFile(parts,erori.root);
				  }else{
						String[] parts = c.getCommand().substring(4).split("/");
						String parts2[] = new String[parts.length-1];							
				        for(int i=0;i<parts2.length;i++){
				        	parts2[i]=parts[i];
				        }
				        
                        rezultat = erori.eroare1(c,parts,erori.currentDirectory);
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare2(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare3(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare6(c,parts2,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare4(c,parts,erori.currentDirectory);
						}
				        
				        if(!rezultat.startsWith("-")){
							rezultat = erori.eroare11(c,parts,erori.currentDirectory);
						}
				        directorcurent = GoToSpecificFile(parts,erori.currentDirectory);
				  }	
				  if(rezultat.startsWith("-")){
						System.out.println(rezultat);
				  }else{
					  System.out.println(directorcurent.returntext());
				  }
			    }
			}
	   }
	   public cat getcatInstance () {
			cat instanta = new cat ();
	        return instanta;
	   }
	   
	   public CompositePattern GoToSpecificFile(String[] parts,CompositePattern file){
		   for(String s : parts){
				if(s.equals(".")==false){
					 if(s.equals("..")){
						 file = file.getparinte();
					 }else{	
						 for(CompositePattern i : file.getSubordinates()){
							 if(i.getnume().equals(s)){
								 file = i;
							 }
						 }
					 }
				}
			}
		   return file;
	   }
	   
	   public void print(CompositePattern r,String tab){
		   System.out.println(tab + r);
		   for(CompositePattern f : r.getSubordinates()){
			   print(f,tab + "\t");
		   }
	   }
}
