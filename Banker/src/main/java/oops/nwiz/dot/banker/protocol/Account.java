package oops.nwiz.dot.banker.protocol;


import oops.nwiz.dot.banker.common.Vocabulary;
import org.bson.Document;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;

public class Account extends PortableRemoteObject implements IAccount {

    private String objectID;
    private String accountID;
    private BigDecimal credit;
    private IBankService service;

    public Account() throws RemoteException {
        super();
    }

    public Account(IBankService service) throws RemoteException {
        super();
        this.service = service;
    }

    public synchronized BigDecimal getCredit() {
        return credit;
    }

    @Override
    public String getAccountID() {
        return accountID;
    }

    public IBankService getService() {
        return service;
    }

    public void setService(IBankService service) {
        this.service = service;
    }

    public synchronized void transfer(String accountID, BigDecimal total) throws RemoteException {
        if (this.credit.compareTo(total) >= 0) {
            IAccount account = service.getAccount(accountID);
            if (account != null) {
                try {
                    account.acceptTransfer(total);
                } catch (RemoteException re) {
                    throw re;
                }
                setCredit(getCredit().subtract(total));
            }
        }
    }

    public static Account fromDocument(Document document) throws RemoteException {
        Account account = new Account();
        account.setCredit(new BigDecimal(document.getDouble(Vocabulary.CREDIT)));
        account.setAccountID(document.getString(Vocabulary.ACCOUNT_ID));
        account.setService(BankService.getInstance());
        return account;
    }

    public static Document toDocument(IAccount account) throws RemoteException {
        Document document = new Document();
        document.put(Vocabulary.ACCOUNT_ID, account.getAccountID());
        document.put(Vocabulary.CREDIT, account.getCredit());
        return document;
    }

    @Override
    public void acceptTransfer(BigDecimal total) throws RemoteException {
        setCredit(getCredit().add(total));
    }

    @Override
    public String toString() {
        return "Account{" +
                "objectID='" + objectID + '\'' +
                ", accountID='" + accountID + '\'' +
                ", credit=" + credit +
                ", service=" + service +
                '}';
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setCredit(BigDecimal credit) throws RemoteException {
        this.credit = credit;
        if (this.service != null)
            this.service.update(this);
    }
}
