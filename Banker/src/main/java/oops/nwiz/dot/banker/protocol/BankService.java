package oops.nwiz.dot.banker.protocol;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import oops.nwiz.dot.banker.common.Vocabulary;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.rmi.RemoteException;

public class BankService implements IBankService {

    private static IBankService service;
    private MongoCollection collection;

    public BankService() {
        MongoClient client = new MongoClient();
        MongoDatabase banker = client.getDatabase(Vocabulary.BANKER);
        collection = banker.getCollection(Vocabulary.ACCOUNT_S);
    }

    private static Bson queryBlockByAccountId (String accountID) {
        Document bson = new Document();
        bson.put(Vocabulary.ACCOUNT_ID, accountID);
        return bson;
    }

    @Override
    public IAccount getAccount(String accountID) throws RemoteException {
        FindIterable results = collection.find(queryBlockByAccountId(accountID));
        return Account.fromDocument((Document) results.first());
    }

    @Override
    public void update(IAccount account) throws RemoteException {
        Document newAccount = Account.toDocument(account);
        collection.findOneAndUpdate(queryBlockByAccountId(account.getAccountID()), newAccount);
    }

    public static IBankService getInstance() {
        if (service == null) {
            service = new BankService();
        }
        return service;
    }
}
