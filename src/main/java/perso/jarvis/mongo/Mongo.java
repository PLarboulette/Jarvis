package perso.jarvis.mongo;

import com.mongodb.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import java.net.UnknownHostException;
import java.util.List;
import javax.servlet.ServletContextListener;





public class Mongo implements ServletContextListener  {

    private static final Logger log = Logger.getLogger(Mongo.class);

    private static MongoClient mongo;
    private static DB db;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        db = mongo.getDB("Jarvis");
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        mongo.close();
    }


    public static WriteResult insert(DBObject dbObject, String collectionName) {
        return db.getCollection(collectionName).insert(dbObject);
    }

    public static WriteResult save(String collectionName, DBObject dbObject) {
        return db.getCollection(collectionName).save(dbObject);
    }

    public static WriteResult update(String collectionName, DBObject searchQuery, DBObject updateQuery, boolean upsert, boolean multi) {
        return db.getCollection(collectionName).update(searchQuery, updateQuery, upsert, multi);
    }

    public static WriteResult remove(String collectionName, DBObject deleteQuery) {
        return db.getCollection(collectionName).remove(deleteQuery);
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query, BasicDBObject fields, BasicDBObject sort) {
        return db.getCollection(collectionName).find(query, fields).sort(sort).toArray();
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query, BasicDBObject fields) {
        return db.getCollection(collectionName).find(query, fields).toArray();
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query) {
        return db.getCollection(collectionName).find(query).toArray();
    }


}
