package oops.nwiz.dot.banker.client;

import oops.nwiz.dot.banker.common.RMISetting;
import oops.nwiz.dot.banker.common.Vocabulary;
import oops.nwiz.dot.banker.protocol.IAccount;
import oops.nwiz.dot.banker.protocol.IBankService;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.math.BigDecimal;


/**
 * The type Command line client.
 * 简易的命令行版本的客户端
 */
public class CommandLineClient {
    public static void  main( String args[] ) {
        Context ic;
        IBankService service;
        IAccount hi;
        try {
            ic = new InitialContext(RMISetting.defaults());
            service = (IBankService) ic.lookup(Vocabulary.BANKER_SERVICE);
            System.out.println("CommandLineClient: 已连接远程服务.");
            hi = service.getAccount("112358");
            System.out.println(String.format("Account: %s, credit: %s", hi.getAccountID(), hi.getCredit()));
            ic.close();
            System.out.println("Closing context");
        } catch (Exception e) {
            System.err.println("Exception " + e + "Caught");
            e.printStackTrace();
        }
        System.out.println("Bye.");
        System.exit(0);
    }
}
