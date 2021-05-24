package oops.nwiz.dot.banker.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface IBankService.
 * 在这个工程里, 这个接口只有一个实现, i.e., {@link BankService}.
 * RMI-IIOP 机制本身就是这个接口存在的全部意义.
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
