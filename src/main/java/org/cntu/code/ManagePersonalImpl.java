package org.cntu.code;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.cntu.model.SalesMan;
import org.cntu.model.SocialPerformanceRecord;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManagePersonalImpl implements ManagePersonal{
    private MongoClient client;
    private MongoDatabase supermongo;
    private MongoCollection<Document> salesmen;
    private MongoCollection<Document> socialPerfomanceRecord;

    public ManagePersonalImpl() {
        // Setting up the connection to a local MongoDB with standard port 27017
        // must be started within a terminal with command 'mongod'.
        client = MongoClients.create("mongodb://localhost:27017");

        // Get database 'highPerformance' (creates one if not available)
        supermongo = client.getDatabase("HighPerformance");

        // Get Collection 'salesmen' (creates one if not available)
        salesmen = supermongo.getCollection("Salesmen");
        socialPerfomanceRecord = supermongo.getCollection("SocialPerformanceRecord");
    }

    @Override
    public void createSalesMan(SalesMan record) {
        // CREATE (Storing) the salesman object
        Document document = new Document();
        document.append("firstname" , record.getFirstname());
        document.append("lastname" , record.getLastname());
        document.append("sid" , record.getSid());

        // ... now storing the object
        salesmen.insertOne(document);
    }

    @Override
    public void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        Document document = new Document();
        document.append("goalId", salesMan.getSid());
        document.append("goalDescription", record.getGoalDescription());
        document.append("valueSupervisor", record.getValueSupervisor());
        document.append("valuePeerGroup", record.getValuePeerGroup());
        document.append("year", record.getYear());

        socialPerfomanceRecord.insertOne(document);
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        Document doc = salesmen.find(eq("sid", sid)).first();
        if (doc == null) return null;
        return new SalesMan(doc.getString("firstname"), doc.getString("lastname"), doc.getInteger("sid"));
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        List<SalesMan> salesManList = new ArrayList<>();
        for(Document document : salesmen.find()){
            salesManList.add(new SalesMan(document.getString("firstname"),
                    document.getString("lastname"), document.getInteger("sid")));
        }
        return salesManList;
    }

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        List<SocialPerformanceRecord> records = new ArrayList<>();
        for(Document document : socialPerfomanceRecord.find(eq("goalId", salesMan.getSid()))){
            records.add(new SocialPerformanceRecord(
                    document.getInteger("goalId"),
                    document.getString("goalDescription"),
                    document.getDouble("valueSupervisor"),
                    document.getDouble("valuePeerGroup"),
                    document.getInteger("year")));
        }
        return records;
    }

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan, int year) {
        List<SocialPerformanceRecord> list = new ArrayList<>();
        for (Document doc : socialPerfomanceRecord.find(
                Filters.and(eq("goalId", salesMan.getSid()), eq("year", year)))) {
            list.add(new SocialPerformanceRecord(
                    doc.getInteger("goalId"),
                    doc.getString("goalDescription"),
                    doc.getDouble("valueSupervisor"),
                    doc.getDouble("valuePeerGroup"),
                    doc.getInteger("year")
            ));
        }
        return list;
    }

    @Override
    public void updateSalesmanSocialPerformanceRecord(int sid, String description, double newValueSupervisor, double newValuePeerGroup){
        Document filter = new Document("goalId", sid)
                .append("goalDescription", description);

        Document update = new Document("$set", new Document()
                .append("valueSupervisor", newValueSupervisor)
                .append("valuePeerGroup", newValuePeerGroup));

        socialPerfomanceRecord.updateOne(filter, update);
    }

    @Override
    public void deleteSalesManSocialPerformanceRecord(int sid, String description) {
        socialPerfomanceRecord.deleteOne(Filters.and(eq("goalId", sid), eq("goalDescription", description)));
    }

    @Override
    public void deleteSalesMan(int sid) {
        salesmen.deleteOne(eq("sid", sid));
        socialPerfomanceRecord.deleteMany(eq("goalId", sid));
    }
}
