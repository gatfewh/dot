package oops.nwiz.dot.banker.common;

/**
 * The Vocabulary used in project DOT.Banker
 * NOTICE:
 * Using classes full of public static final fields to manage constance
 * is no longer a best practice since JDK 1.5.
 * However, to ease the cost of understanding the whole stuff,
 * this trick is used here anyway.
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
