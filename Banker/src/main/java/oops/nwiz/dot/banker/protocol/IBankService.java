package oops.nwiz.dot.banker.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface IBankService.
 * In this project, IBankService has only one implementation, i.e., {@link BankService}.
 * And the RMI-IIOP mechanism itself is the only reason why this interface actually exists,
 * just like {@link IAccount} and {@link Account}.
 */
public interface IBankService extends Remote {
    /**
     * Get IAccount instance.
     *
     * @param accountID the AccountID
     * @throws RemoteException when the damned RMI mechanism runs into any trouble.
     */
    IAccount getAccount(String accountID) throws RemoteException;

    /**
     * Update specified {@link Account} instance to sync modification to remote server.
     *
     * @param account the account
     * @throws RemoteException when the damned RMI mechanism runs into any trouble.
     */
    void update(IAccount account) throws RemoteException;
}
