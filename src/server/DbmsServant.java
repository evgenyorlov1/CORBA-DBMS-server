/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import DbmsAPP._DbmsImplBase;

/**
 *
 * @author pc
 */
public class DbmsServant extends _DbmsImplBase {
    
    
    DbmsAdapter adapter = new DbmsAdapter();
        
    @Override
    public String hello() {
       return "HELLO!";
    }

    @Override
    public String show_dbs() {        
        return adapter.show_dbs();
    }

    @Override
    public String show_tables(String DBname) {
        return adapter.show_tables(DBname);
    }

    @Override
    public void drop_database(String DBname) {
        adapter.drop_database(DBname);
    }

    @Override
    public void drop_table(String DBname, String Tname) {
        adapter.drop_table(DBname, Tname);
    }

    @Override
    public void create_database(String DBname) {
        adapter.create_database(DBname);
    }

    @Override
    public void create_table(String DBname, String Tname, String keyType) {
        adapter.create_table(DBname, Tname, keyType);
    }

    @Override
    public boolean register(String user, String password) {
        return adapter.register(user, password);
    }

    @Override
    public boolean login(String user, String password) {
        return adapter.login(user, password);
    }

    @Override
    public String find(String DBname, String Tname) {
        return adapter.find(DBname, Tname);
    }

    @Override
    public boolean is_unique_name(String DBname) {
        return adapter.is_unique_name(DBname);
    }

    @Override
    public String get_metadata(String DBname, String Tname) {
        return adapter.get_metadata(DBname, Tname);
    }

    @Override
    public String limit(String DBname, String Tname, int num) {
        return adapter.limit(DBname, Tname, num);
    }

    @Override
    public String skip(String DBname, String Tname, int num) {
        return adapter.skip(DBname, Tname, num);
    }

    @Override
    public String sort(String DBname, String Tname, int num) {
        return adapter.sort(DBname, Tname, num);
    }

    @Override
    public int count(String DBname, String Tname) {
        return adapter.count(DBname, Tname);
    }

    @Override
    public void insert(String DBname, String Tname, String keyValue) {
        adapter.insert(DBname, Tname, keyValue);
    }

    @Override
    public void update(String id, String keyVal, String DBname, String Tname) {
        adapter.update(id, keyVal, DBname, Tname);
    }

    @Override
    public void remove(String DBname, String Tname, String id) {
        adapter.remove(DBname, Tname, id);
    }
}
