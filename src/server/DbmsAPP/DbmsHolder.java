package DbmsAPP;

/**
* DbmsAPP/DbmsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dbms.idl
* Thursday, December 15, 2016 11:40:21 PM EET
*/

public final class DbmsHolder implements org.omg.CORBA.portable.Streamable
{
  public DbmsAPP.Dbms value = null;

  public DbmsHolder ()
  {
  }

  public DbmsHolder (DbmsAPP.Dbms initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DbmsAPP.DbmsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DbmsAPP.DbmsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DbmsAPP.DbmsHelper.type ();
  }

}
