CORBA database server application. <br/>
1. Create .idl server interface <br/>
2. Run  idlj -fall -oldImplBase .idl to create helper classes<br/>
3. tnameserv -ORBInitialPort 1050&  <br/>
4. java server.DbmsServer -ORBInitialPort 1050& (by default)<br/>
5. java client.DbmsClient -ORBInitialPort 1050 (other application)<br/>
