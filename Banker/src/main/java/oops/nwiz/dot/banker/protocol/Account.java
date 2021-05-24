package oops.nwiz.dot.banker.protocol;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;

public class Account extends PortableRemoteObject implements IAccount {

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

    public Account(String accountID, BigDecimal credit, IBankService service) throws RemoteException {
        this.accountID = accountID;
        this.credit = credit;
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
    @Override
    public void acceptTransfer(BigDecimal total) throws RemoteException {
        setCredit(getCredit().add(total));
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountID='" + accountID + '\'' +
                ", credit=" + credit +
                ", service=" + service +
                '}';
    }

    public void setCredit(BigDecimal credit) throws RemoteException {
        this.credit = credit;
        if (this.service != null)
            this.service.update(this);
    }
}
