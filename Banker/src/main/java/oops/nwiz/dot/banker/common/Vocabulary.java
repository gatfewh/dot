package oops.nwiz.dot.banker.common;

/**
 * The Vocabulary used in project DOT.Banker
 * 注意:
 * 从JDK1.5开始, 这种在类里放置public static final成员来管理常量的方式已经不再被推荐了
 * 应该转而使用enum类型
 * 然而, 为了让代码更直观, 这里还是沿用了这种古老的方法
 */
public class Vocabulary {
    /**
     * The constant BANKER, name of database.
     */
    public static final String BANKER     = "banker";
    /**
     * The constant ACCOUNT, name of collection.
     */
    public static final String ACCOUNT    = "account";
    /**
     * The constant ACCOUNT_S.
     */
    public static final String ACCOUNT_S  = "accounts";
    /**
     * The constant ACCOUNT_ID.
     */
    public static final String ACCOUNT_ID = "accountID";
    /**
     * The constant CREDIT.
     */
    public static final String CREDIT     = "credit";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD   = "password";
    /**
     * The constant BANKER_SERVICE.
     */
    public static final String BANKER_SERVICE = "BankerService";
}
