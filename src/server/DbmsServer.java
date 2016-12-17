/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import org.omg.CosNaming.*; 
import org.omg.CORBA.*; 
/**
 *
 * @author pc
 */
public class DbmsServer {
    public static void main(String args[]) { 
        try{                
            String[] properities = {"-ORBInitialPort", "1050"};            
            ORB orb = ORB.init(properities, null);    //args                      
            DbmsServant helloRef = new DbmsServant(); 
            orb.connect(helloRef);         
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");        
            NamingContext ncRef = NamingContextHelper.narrow(objRef);             
            NameComponent nc = new NameComponent("Hello", ""); 
            NameComponent path[] = {nc}; 
            ncRef.rebind(path, helloRef); 
            System.out.println("Server started...");
            java.lang.Object sync = new java.lang.Object(); 
            synchronized(sync){ 
              sync.wait(); 
            } 
    } catch(Exception e) { 
        System.err.println("ERROR: " + e); 
        e.printStackTrace(System.out); 
      }   
  } 
}