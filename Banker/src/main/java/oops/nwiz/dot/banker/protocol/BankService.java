package oops.nwiz.dot.banker.protocol;

import oops.nwiz.dot.banker.common.Vocabulary;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Random;


/**
 * The BankService is the only implementation of {@link IBankService}, just like {@link Account}.
 * BankService serves as Data Access Layer.
 * Object-Relational Mapping
 * Thanks to the application, all these
 */
public class BankService implements IBankService {

    private Connection connection;
    private static IBankService service;

    public BankService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banker", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("数据库连接失败");
        }
    }

    @Override
    public IAccount getAccount(String accountID) throws RemoteException {
        Statement stmt = null;
        BigDecimal credit;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT credit FROM account WHERE account_id = '" + accountID + "';");
            if (rs.next()) {
                // 成功时返回新的账户实例
                credit = rs.getBigDecimal(Vocabulary.CREDIT);
                return new Account(accountID, credit, this);
            } else {
                // 否则随机创建一个新的账户
                Random random = new Random();
                credit = BigDecimal.valueOf(random.nextInt(710118));
                stmt.execute("INSERT INTO account VALUES (" + accountID + ", " + credit + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("失败: 操作数据库时遭遇致命错误");
        }
        return new Account(accountID, credit, this);
    }

    @Override
    public void update(IAccount account) throws RemoteException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("UPDATE account SET credit = " + account.getCredit() + "WHERE account_id = '" + account.getAccountID() + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 一个非常懒的单例用法
     * @return the instance
     */
    synchronized public static IBankService getInstance() {
        if (service == null) {
            service = new BankService();
        }
        return service;
    }
}
