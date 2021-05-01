package oops.nwiz.dot.banker.protocol;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAccount extends Remote {
    String     getAccountID() throws RemoteException;
    BigDecimal getCredit() throws RemoteException;
    void       transfer(String accountID, BigDecimal total) throws RemoteException;
    void       acceptTransfer(BigDecimal total) throws RemoteException;
}
