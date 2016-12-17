package server;



///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dbms;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.*;
//
///**
// *
// * @author pc
// */
//public class JSONGenerator {
//    
//    
//    public String table_to_json(Table tb) {
//        JSONObject resultJson = table_json_object(tb);        
//        return resultJson.toJSONString();
//    }
//    
//    public String database_to_json(DataBase db) {
//        JSONObject resultJson = new JSONObject();
//        JSONArray array = new JSONArray();
//        resultJson.put("name", db.name);
//        resultJson.put("type", "database");
//        
//        for(Table tb : db.tablesList) {
//            JSONObject json = table_json_object(tb);
//            array.add(json);
//        }
//        resultJson.put("tables", array);
//        return resultJson.toJSONString();
//    }
//    
//    //TODO
//    public DataBase json_to_database(String json) {
//        JSONParser parser = new JSONParser();        
//        try {
//            Object obj = parser.parse(json);            
//            JSONObject jsonObj = (JSONObject) obj;            
//            JSONArray tablesJson = (JSONArray) jsonObj.get("tables");
//            DataBase db = new DataBase((String)jsonObj.get("name"));
//            
//            for(int i=0; i<tablesJson.size(); i++) {
//                JSONObject tableJson = (JSONObject) tablesJson.get(i);                
//                Table tb = new Table((String)tableJson.get("name"));  
//                                
//                JSONArray recordsJson = (JSONArray) tableJson.get("records");                
//                for(int j=0; j<recordsJson.size(); j++) {
//                   JSONObject object = (JSONObject) recordsJson.get(j);
//                    int integer = Integer.valueOf(object.get("integer").toString());
//                    float real = Float.valueOf(object.get("real").toString());
//                    long longint = (long)object.get("longint");
//                    char symbol = object.get("symbol").toString().charAt(0);
//                    String html = (String)object.get("html");
//                    Record record = new Record(integer, real, longint, symbol, html);
//                    tb.records.add(record);
//                }
//                                
//                db.tablesList.add(tb);
//            }
//            
//            return db;
//        } catch (ParseException ex) {System.out.println(ex);}
//        return null;
//    }
//    
//    public Table json_to_table(String json) {        
//        JSONParser parser = new JSONParser();                
//        try {
//            Object obj = parser.parse(json);            
//            JSONObject jsonObj = (JSONObject) obj;            
//            JSONArray ja = (JSONArray) jsonObj.get("records");
//            Table tb = new Table((String)jsonObj.get("name"));
//            for(int i=0; i<ja.size(); i++) {
//                JSONObject object = (JSONObject) ja.get(i);
//                int integer = Integer.valueOf(object.get("integer").toString());
//                float real = Float.valueOf(object.get("real").toString());
//                long longint = (long)object.get("longint");
//                char symbol = object.get("symbol").toString().charAt(0);
//                String html = (String)object.get("html");
//                Record record = new Record(integer, real, longint, symbol, html);
//                tb.records.add(record);
//            }            
//            
//            return tb;
//        } catch (ParseException ex) {System.out.println(ex);}
//            
//        return null;
//    }
//    
//    private JSONObject table_json_object(Table tb) {
//        JSONObject resultJson = new JSONObject();
//        
//        JSONArray array = new JSONArray();
//        resultJson.put("name", (String)tb.name);
//        resultJson.put("type", "table");
//        for(Record record : tb.records) {
//            JSONObject rec = new JSONObject();            
//            rec.put("integer",record.integer);
//            rec.put("real",(float)record.real);
//            rec.put("longint",(long)record.longint);
//            rec.put("symbol",String.valueOf(record.symbol));
//            rec.put("html",(String)record.html);
//            array.add(rec);
//        }                
//        resultJson.put("records", array);
//        
//        return resultJson;
//    }
//     
//}
