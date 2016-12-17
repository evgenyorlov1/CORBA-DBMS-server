package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class DBMS {
    
    private DBMS instance;    
    private ArrayList<User> users = new ArrayList<User>();
    public ArrayList<DataBase> databases = new ArrayList<>();
    private final String admin = "admin";
    private final String user = "user";
    private boolean isAdmin = true; //TESTING true
    
    
    public DBMS() {}       
    
    //TESTED
    public ArrayList<String> show_dbs() {        
        ArrayList<String> databaseNames = new ArrayList<String>();        
        
        for(int i=0; i<databases.size(); i++) {
            databaseNames.add(databases.get(i).name);
        }
        return databaseNames;
    }
    
    //TESTED
    public ArrayList<String> show_tables(String DBname) {
        ArrayList<String> tables = new ArrayList<String>();
        
        for(int i=0; i<databases.size(); i++) {
            if(databases.get(i).name.equals(DBname)) {               
                for(int j=0; j<databases.get(i).tables.size(); j++) {                    
                    tables.add(databases.get(i).tables.get(j).name);
                }
            }
        }
        return tables;
    }
    
    //TESTED
    public void drop_database(String DBname) {
        if(isAdmin) {
            for(int i=0; i<databases.size(); i++) {
                if(databases.get(i).name.equals(DBname)) {
                    databases.remove(i);
                }
            }
        }
    }
    
    //TESTED    
    public void drop_table(String DBname, String Tname) {   
        if(isAdmin) {
            for(int i=0; i<databases.size(); i++) {
                if(databases.get(i).name.equals(DBname)) {
                    for(int j=0; j<databases.get(i).tables.size(); j++) {
                       if(databases.get(i).tables.get(j).name.equals(Tname)) {
                           databases.get(i).tables.remove(j);
                       }
                    }
                }
            }
        }
    }
    
    //TESTED
    public void create_database(String DBname) {
        if(isAdmin) {
            DataBase db = new DataBase(DBname);
            databases.add(db);
        }
    }
    
    //TESTED
    public void create_table(String DBname, String Tname, ArrayList<String[]> keyType) { 
        final String[] id = new String[] {"_id", "integer"};
        keyType.add(id);
        
        if(isAdmin) {            
            for(int i=0; i<databases.size(); i++) {
                if(databases.get(i).name.equals(DBname)) {                    
                    databases.get(i).tables.add(new Table(Tname, keyType));
                }
            }        
        }
    }
    
    //TESTED
    public void save_serialization(String DBname, String fileName) throws Exception {
        for(DataBase db : databases) {
            if (db.name.equals(DBname)) {
                FileOutputStream out = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(db);
                oos.flush();
            }
        }        
    }
    
    //TEST
    public void save_serialization(String DBname, String Tname, String fileName) throws Exception {
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                for(Table tb : db.tables) {
                    if(tb.name.equals(Tname)) {
                        FileOutputStream out = new FileOutputStream(fileName);
                        ObjectOutputStream oos = new ObjectOutputStream(out);
                        oos.writeObject(tb);
                        oos.flush();
                    }
                }
            }
        }
    }
    
    //TESTED
    public void load_serialization(String file) throws Exception {
        
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(in);
        DataBase db = (DataBase) (ois.readObject());
        this.databases.add(db);
    }
    
    //TEST
    public void load_serialization(String DBname, String file) throws Exception {
        
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(in);
        Table tb = (Table) (ois.readObject());        
        
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                db.tables.add(tb);
            }
        }  
    }
    
    //TESTED
//    public void save(String DBname, String fileName) {
//        if(isAdmin) {
//            String json = "";
//            JSONGenerator generator = new JSONGenerator();
//            for(DataBase db : databases) {
//                if(db.name.equals(DBname))
//                    json = generator.database_to_json(db);
//            }
//
//            try {
//                File file = new File(fileName);
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write(json);            
//                fileWriter.flush();
//                fileWriter.close();
//            } catch(Exception e) {}
//        }        
//    }
    
    //TESTED
//    public void save(String DBname, String Tname, String fileName) {
//        if(isAdmin) {
//            String json = "";
//            JSONGenerator generator = new JSONGenerator();
//            for(DataBase db : databases) {
//                if(db.name.equals(DBname))
//                    for(Table tb : db.tables) {
//                        if(tb.name.equals(Tname))
//                            json = generator.table_to_json(tb);
//                    }
//            }
//
//            try {
//                File file = new File(fileName);
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write(json);            
//                fileWriter.flush();
//                fileWriter.close();
//            } catch(Exception e) {}
//        }        
//    }
    
    //TESTED
//    public void load(String file) {
//        BufferedReader br = null;
//        String json = "";
//        try {
//            String sCurrentLine;
//            br = new BufferedReader(
//                    new FileReader(System.getProperty("user.dir").concat("/"+file))
//            );
//            while ((sCurrentLine = br.readLine()) != null) {
//                json = json.concat(sCurrentLine);
//            }
//        } catch(Exception e) {}
//        JSONGenerator generator = new JSONGenerator();
//        DataBase db = generator.json_to_database(json);
//        databases.add(db);
//    }
    
    //TESTED
//    public void load(String DBname, String file) {
//        BufferedReader br = null;
//        String json = "";
//        
//        try {
//            String sCurrentLine;
//            br = new BufferedReader(
//                    new FileReader(System.getProperty("user.dir").concat("/"+file))
//            );
//            while ((sCurrentLine = br.readLine()) != null) {
//                json = json.concat(sCurrentLine);
//            }
//        } catch(Exception e) {}
//        
//        JSONGenerator generator = new JSONGenerator();
//        Table tb = generator.json_to_table(json);
//        
//        for(DataBase db : databases) {
//            if(db.name.equals(DBname)) {
//                db.tables.add(tb);
//            }
//        }                
//    }
    
    
    
    //TESTED
    public boolean register(User login) {        
        users.add(login);
        try {
            serialize();
        } catch (Exception ex) {}        
        return true;
    }
        
    //TESTED
    public boolean login(User login) {        
        try {
            deserialize();            
        } catch (Exception ex) {}            
        
        for(User user : users) {
            if(user.password.equals(login.password) && user.username.equals(login.username)) {
                if(login.username.equals(admin)) {
                    this.isAdmin = true;
                    return true;                    
                } 
                return true;
            }
        }
        return false;
    }
    
    //TESTED
    public ArrayList<ArrayList<String[]>> find(String DBname, String Tname) {
        ArrayList<Record> recs = get_records(DBname, Tname);  
        ArrayList<ArrayList<String[]>> records = new ArrayList<>();
        
        for(int i=0; i<recs.size(); i++) 
            records.add(recs.get(i).keyValue); 
        
        return records;
    }
    
    //TESTED
    public ArrayList<String[]> get_metadata(String DBname, String Tname) {
        System.out.println("SingletonDBMS.get_metadata");        
        ArrayList<String[]> metadata = new ArrayList<>();
                
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                for(Table tb : db.tables) {
                    if(tb.name.equals(Tname))
                        metadata = tb.keyType;                    
                }
            }
        }                                             
        
        return metadata;
    }
    
    //TESTED
    public ArrayList<ArrayList<String[]>> limit(String DBname, String Tname, int num) { 
        System.out.println("SingletonDBMS.limit");
        ArrayList<Record> result = get_records(DBname, Tname);        
        ArrayList<ArrayList<String[]>> records = new ArrayList<>();
        for(int i=0; i<result.size(); i++) 
            records.add(result.get(i).keyValue); 
        
        ArrayList<ArrayList<String[]>> res = new ArrayList<ArrayList<String[]>>();                
        for(int i=0; i<Math.min(num, result.size()); i++) {
            res.add(records.get(i));
        }                
        return res;
    }
            
    //FIX WRONG ORDER
    public ArrayList<ArrayList<String[]>> skip(String DBname, String Tname, int num) {
        System.out.println("SingletonDBMS.skip");
        ArrayList<Record> recs = get_records(DBname, Tname);  
        ArrayList<ArrayList<String[]>> records = new ArrayList<>();        
        for(int i=0; i<recs.size(); i++) 
            records.add(recs.get(i).keyValue); 
        
        ArrayList<ArrayList<String[]>> result = new ArrayList<>();        
        int count = Math.min(records.size(), num);
        for(int i=count; i>0; i--) 
            result.add(records.get(i));
        
        return result;
    }
    
    //TESTED
    public ArrayList<ArrayList<String[]>> sort(String DBname, String Tname, String key, int order) {
        System.out.println("SingletonDBMS.sort");
        ArrayList<Record> recs = get_records(DBname, Tname);  
        ArrayList<ArrayList<String[]>> records = new ArrayList<>();        
        for(int i=0; i<recs.size(); i++) 
            records.add(recs.get(i).keyValue); 
        
        String type = "";
        
        ArrayList<String[]> KeyType = get_metadata(DBname, Tname); 
        for(String[] tp : KeyType) {
            System.out.println(tp[0]);System.out.println(tp[1]);
        }
        for(String[] keyType : KeyType) {
            if(keyType[0].equals(key))
                type = keyType[1];
        }        
        Comparator comparator = new Comparator(type, order);
        
        return bubble_sort(recs, comparator, key);
    }
    
    //TESTED
    public int count(String DBname, String Tname) {
        System.out.println("SingletonDBMS.count");
        int count = 0;
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                for(Table tb : db.tables) {
                    if(tb.name.equals(Tname)) {
                        count = tb.records.size();
                    }
                }
            }
        }
        return count;
    }        
    
    //TESTED
    public void insert(String DBname, String Tname, ArrayList<String[]> keyValue) {
        System.out.println("SingletonDBMS.insert");
        String[] id = new String[2];
        id[0] = "_id";
        for(String[] row : keyValue) 
            id[1] += row[1];        
        
        id[1] = String.valueOf(id[1].hashCode());
        keyValue.add(id);
        
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                for(Table tb : db.tables) {
                    tb.insert(keyValue);
                }
            }
        }                 
    }
    
    //TODO
    public void update(ArrayList<String[]> rows, String DBname, String Tname) {
//        Table tb = new Table(Tname);
//        for(int i=0; i<rows.size(); i++) {
//            int integer = Integer.valueOf(rows.get(i)[0]);
//            float real = Float.valueOf(rows.get(i)[1]);
//            long longint = Long.valueOf(rows.get(i)[2]);
//            char symbol = rows.get(i)[3].charAt(0);
//            String html = rows.get(i)[4];
//            Record record = new Record(integer, real, longint, symbol, html);
//            tb.recordList.add(record);
//        }
//        for(int i=0; i<databases.size(); i++) {
//            if(databases.get(i).name.equals(DBname)) {
//                for(int j=0; j<databases.get(i).tablesList.size(); j++) {
//                    if(databases.get(i).tablesList.get(j).name.equals(Tname)) {
//                       databases.get(i).tablesList.set(j, tb);                        
//                    }
//                }
//            }
//        }
    }
    
    //TESTED
    public void update(String _id, ArrayList<String[]> keyVal, String DBname, String Tname) {
        System.out.println("SingletonDBMS.update");
        ArrayList<Record> records = new ArrayList<Record>();
        System.out.println(keyVal.size());
        for(DataBase db : databases) {
            if(db.name.equals(DBname))
                for(Table tb : db.tables) {
                    if(tb.name.equals(Tname))
                        records = tb.records;
                }
        }
        
        for(Record record : records) {
            if(record.get_by_key("_id").equals(_id)) {
                System.out.println(record.get_by_key("_id"));
                for(String[] kv : keyVal) {
                    System.out.println(kv[0] + ", " + kv[1]);
                    record.set_by_key(kv[0], kv[1]);
                }
            }            
        }
    }
    
    //TESTED
    public boolean is_unique_name(String useState) {
        for(int i=0; i<databases.size(); i++) {
            if(databases.get(i).name.equals(useState))
                return false;
        }
        return true;
    }
    
    //TESTED
    public void remove(String DBname, String Tname, String id) {       
        System.out.println("SingletonDBMS.remove id");
        for(DataBase db : databases) 
            if(db.name.equals(DBname)) 
                for(Table tb : db.tables) 
                    if(tb.name.equals(Tname)) 
                        for(int i=0; i<tb.records.size(); i++) 
                            if(tb.records.get(i).get_by_key("_id").equals(id))
                                tb.records.remove(i);                                                                                                        
    }
    
    //TESTED
    public void remove(String DBname, String Tname, ArrayList<String[]> keyValues) {
        System.out.println("SingletonDBMS.remove key");
        ArrayList<Record> records = new ArrayList<Record>();
        for(DataBase db : databases) 
            if(db.name.equals(DBname)) 
                for(Table tb : db.tables) 
                    if(tb.name.equals(Tname)) 
                        records = tb.records;                
        
        for(Record record : records) {
            int count = keyValues.size();
            for(String[] keyval : keyValues) {                
                String valRec = record.get_by_key(keyval[0]);
                String valRem = keyval[1];
                if(valRec.equals(valRem))
                    count--;
            }
            if(count == 0)
                records.remove(record);
        }
    }
    
    //TESTED
    public ArrayList<Record> get_records(String DBname, String Tname) {
        System.out.println("SingletonDBMS.get_records");
        
        ArrayList<Record> rec = new ArrayList<>();
        
        for(DataBase db : databases) {
            if(db.name.equals(DBname)) {
                for(Table tb : db.tables) {
                    if(tb.name.equals(Tname)) 
                        rec = tb.records;                    
                }
            }
        }
                                                       
        return rec;
    }
    
    //TESTED
    public ArrayList<ArrayList<String[]>> bubble_sort(ArrayList<Record> records, 
            Comparator camparator, String key) {                 
        
        for(int i=0; i<records.size(); i++) {                        
            for(int j=0; j<records.size(); j++) {                
                if(camparator.compare(records.get(i).get_by_key(key),records.get(j).get_by_key(key))) {                
                    Collections.swap(records, j, i);
                }
            }
        }
        
        ArrayList<ArrayList<String[]>> result = new ArrayList<>();        
        for(int i=0; i<records.size(); i++) 
            result.add(records.get(i).keyValue); 
        
        return result;
    }        

    //TESTED
    public void serialize() throws Exception {
        FileOutputStream out = new FileOutputStream("config");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(users);
        oos.flush();            
    }    
    
    //TESTED
    public void deserialize() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        FileInputStream in = new FileInputStream("config");
        ObjectInputStream ois = new ObjectInputStream(in);
        users = (ArrayList<User>) (ois.readObject());
        this.users = users;
    }
        
 }
