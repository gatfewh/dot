package oops.nwiz.dot.banker.protocol;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import oops.nwiz.dot.banker.common.Vocabulary;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.rmi.RemoteException;


/**
 * The BankService is the only implementation of {@link IBankService}, just like {@link Account}.
 * BankService serves as Data Access Layer.
 * Object-Relational Mapping
 * Thanks to the application, all these
 */
public class BankService implements IBankService {

    private static IBankService service;
    private MongoCollection collection;

    /**
     * Instantiates a new BankService.
     * This default constructor initialize collection with the one which containing all accounts.
     */
    public BankService() {
        MongoClient client = new MongoClient();
        MongoDatabase banker = client.getDatabase(Vocabulary.BANKER);
        collection = banker.getCollection(Vocabulary.ACCOUNT_S);
    }

    private static Bson buildAccountIDQueryBlock(String accountID) {
        Document bson = new Document();
        bson.put(Vocabulary.ACCOUNT_ID, accountID);
        return bson;
    }

    private static Bson buildVerifyQuery(String accountID) {
        Document bson = (Document) buildAccountIDQueryBlock(accountID);
        return bson;
    }

    @Override
    public IAccount getAccount(String accountID) throws RemoteException {
        FindIterable results = collection.find(buildVerifyQuery(accountID));
        return Account.fromDocument((Document) results.first());
    }

    @Override
    public void update(IAccount account) throws RemoteException {
        Document newAccount = Account.toDocument(account);
        collection.findOneAndUpdate(buildAccountIDQueryBlock(account.getAccountID()), newAccount);
    }

    /**
     * A very lazy singleton implementation.
     * @return the instance
     */
    public static IBankService getInstance() {
        if (service == null) {
            service = new BankService();
        }
        return service;
    }
}
