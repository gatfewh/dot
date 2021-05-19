package oops.nwiz.dot.banker.protocol;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * The interface IAccount.
 * In this project, IAccount has only one implementation, i.e., {@link Account}.
 * And the RMI-IIOP mechanism itself is the only reason why this interface actually exists,
 * just like {@link IBankService} and {@link BankService}.
 */
public interface IAccount extends Remote {
    /**
     * Gets account iD.
     *
     * @return the account iD
     * @throws RemoteException the remote exception
     */
    String     getAccountID() throws RemoteException;

    /**
     * Gets credit.
     *
     * @return the credit
     * @throws RemoteException when the damned RMI mechanism runs into any trouble.
     */
    BigDecimal getCredit() throws RemoteException;

    /**
     * Transfer to specified account.
     *
     * @param accountID the account iD
     * @param total the total
     * @throws RemoteException when the damned RMI mechanism runs into any trouble.
     */
    void       transfer(String accountID, BigDecimal total) throws RemoteException;

    /**
     * Accept transfer.
     *
     * @param total the total
     * @throws RemoteException the remote exception
     */
    void       acceptTransfer(BigDecimal total) throws RemoteException;
}
