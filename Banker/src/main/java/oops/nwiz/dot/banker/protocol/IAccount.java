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
     * 转出到指定账户
     * @param accountID the account iD
     * @param total the total
     * @throws RemoteException when the damned RMI mechanism runs into any trouble.
     */
    void       transfer(String accountID, BigDecimal total) throws RemoteException;

    /**
     * 接受转入
     * @param total the total
     * @throws RemoteException the remote exception
     */
    void       acceptTransfer(BigDecimal total) throws RemoteException;

    /**
     * 将该实例发生的所有变化写会远程服务器
     * @throws RemoteException the remote exception
     */
    void       confirm() throws RemoteException;
}
