package oops.nwiz.dot.banker.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBankService extends Remote {
    IAccount getAccount(String accountID) throws RemoteException;
    void update(IAccount account) throws RemoteException;
}
