/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class DbmsAdapter {
    
    DBMS dbms = new DBMS();    
    Gson gson = new Gson();
        
    //TEST
    public String show_dbs() {        
        ArrayList<String> dbs = dbms.show_dbs();
        String json = gson.toJson(dbs, new TypeToken<ArrayList<String>>(){}.getType());
        return json;
    }
    
    //TEST
    public String show_tables(String DBname) {
        ArrayList<String> tables = dbms.show_tables(DBname);
        String json = gson.toJson(tables, new TypeToken<ArrayList<String>>(){}.getType());
        return json;
    }
    
    //TEST
    public void drop_database(String DBname) {
        dbms.drop_database(DBname);
    }

    //TEST
    public void drop_table(String DBname, String Tname) {
        dbms.drop_table(DBname, Tname);
    }

    //TEST
    public void create_database(String DBname) {
        dbms.create_database(DBname);
    }

    //TEST
    public void create_table(String DBname, String Tname, String keyType) {
        ArrayList<String[]> KT = gson.fromJson(keyType, new TypeToken<ArrayList<String[]>>(){}.getType());
        dbms.create_table(DBname, Tname, KT);
    }

    //TEST
    public boolean register(String user, String password) {
        User usr = new User(user, password);
        boolean flag = dbms.register(usr);
        return flag;
    }

    //TEST
    public boolean login(String user, String password) {
        User usr = new User(user, password);
        boolean flag = dbms.login(usr);
        return flag;
    }

    //TEST
    public String find(String DBname, String Tname) {
        ArrayList<ArrayList<String[]>> find = dbms.find(DBname, Tname);
        String json = gson.toJson(find, new TypeToken<ArrayList<ArrayList<String[]>>>(){}.getType());
        return json;
    }
    
    //TEST
    public boolean is_unique_name(String DBname) {
        boolean flag = dbms.is_unique_name(DBname);
        return flag;
    }

    //TEST
    public String get_metadata(String DBname, String Tname) {
        ArrayList<String[]> metadata = dbms.get_metadata(DBname, Tname);
        String json = gson.toJson(metadata, new TypeToken<ArrayList<String[]>>(){}.getType());
        return json;
    }

    //TEST
    public String limit(String DBname, String Tname, int num) {
        ArrayList<ArrayList<String[]>> limit = dbms.limit(DBname, Tname, num);
        String json = gson.toJson(limit, new TypeToken<ArrayList<String[]>>(){}.getType());
        return json;
    }

    //TEST
    public String skip(String DBname, String Tname, int num) {
        ArrayList<ArrayList<String[]>> skip = dbms.skip(DBname, Tname, num);
        String json = gson.toJson(skip, new TypeToken<ArrayList<String[]>>(){}.getType());
        return json;
    }

    //TEST
    //Tname = Tname;Key
    public String sort(String DBname, String Tname, int num) {
        ArrayList<ArrayList<String[]>> sort = dbms.sort(DBname, Tname, Tname, num);
        String json = gson.toJson(sort, new TypeToken<ArrayList<String[]>>(){}.getType());
        return json;
    }

    //TEST
    public int count(String DBname, String Tname) {
        int number = dbms.count(DBname, Tname);
        return number;
    }

    //TEST
    public void insert(String DBname, String Tname, String keyValue) {
        ArrayList<String[]> KV = gson.fromJson(keyValue, new TypeToken<ArrayList<String[]>>(){}.getType());
        dbms.insert(DBname, Tname, KV);
    }

    //TEST
    public void update(String id, String keyVal, String DBname, String Tname) {
        ArrayList<String[]> KV = gson.fromJson(keyVal, new TypeToken<ArrayList<String[]>>(){}.getType());
        dbms.update(id, KV, DBname, Tname);
    }

    //TEST
    public void remove(String DBname, String Tname, String id) {
        dbms.remove(DBname, Tname, id);
    }
    
}
