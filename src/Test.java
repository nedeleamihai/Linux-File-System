import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
       public static void main(String[] args){
    	   BufferedReader br = null;
    	   BashUtils bashUtils = new BashUtils();
    	   CommandSubscriber adduser = bashUtils.getadduserInstance();
    	   CommandSubscriber deluser = bashUtils.getdeluserInstance();
    	   CommandSubscriber chuser = bashUtils.getchuserInstance();
    	   CommandSubscriber cd = bashUtils.getcdInstance();
    	   CommandSubscriber ls = bashUtils.getlsInstance();
    	   CommandSubscriber mkdir = bashUtils.getmkdirInstance();
    	   CommandSubscriber touch = bashUtils.gettouchInstance();
    	   CommandSubscriber chmod = bashUtils.getchmodInstance();
    	   CommandSubscriber rm = bashUtils.getrmInstance();
    	   CommandSubscriber rmdir = bashUtils.getrmdirInstance();
    	   CommandSubscriber writetofile = bashUtils.getwritetofileInstance();
    	   CommandSubscriber cat = bashUtils.getcatInstance();
           
    	   try {
                   br = new BufferedReader(new FileReader("demo_in.txt"));              
                   String x = null; 
                   bashUtils.erori.root.setparinte(bashUtils.erori.root);
                   while ( (x = br.readLine()) != null ) {
                	     Command comanda = new Command(x);
                	     if(x.startsWith("adduser")){
     				    	 adduser.executeCommand(comanda);
                	     }
                	     if(x.startsWith("deluser")){
     				    	 deluser.executeCommand(comanda);
                	     }
                	     if(x.startsWith("chuser")){
     				    	 chuser.executeCommand(comanda);
                	     }
                	     if(x.startsWith("cd")){
     				    	 cd.executeCommand(comanda);
                	     }
                	     if(x.startsWith("ls")){
     				    	 ls.executeCommand(comanda);
                	     }
                	     if(x.startsWith("mkdir")){
     				    	 mkdir.executeCommand(comanda);
                	     }
                	     if(x.startsWith("chmod")){
     				    	 chmod.executeCommand(comanda);
                	     }
                	     if(x.startsWith("touch")){
                	    	 touch.executeCommand(comanda);
                	     }
                	     if(x.startsWith("rm ")){
                	    	 rm.executeCommand(comanda);
                	     }
                	     if(x.startsWith("rmdir")){
                	    	 rmdir.executeCommand(comanda);
                	     }
                	     if(x.startsWith("cat")){
                	    	 cat.executeCommand(comanda);
                	     }
                	     if(x.startsWith("writetofile")){
                	    	 writetofile.executeCommand(comanda);
                	     }
                   }                
           } catch (IOException e) {
                   e.printStackTrace();
           }
           bashUtils.print(bashUtils.erori.root, "");
       }
}