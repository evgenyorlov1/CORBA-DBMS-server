CORBA database server application.
1. Create .idl server interface
2. Run  idlj -fall -oldImplBase .idl to create helper classes
3. tnameserv -ORBInitialPort 1050& 
4. java server.DbmsServer -ORBInitialPort 1050& (by default)
5. java client.DbmsClient -ORBInitialPort 1050 (other application)
