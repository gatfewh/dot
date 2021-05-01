package oops.nwiz.dot.banker.server;

import oops.nwiz.dot.banker.common.RMISetting;
import oops.nwiz.dot.banker.common.Vocabulary;
import oops.nwiz.dot.banker.protocol.BankService;
import oops.nwiz.dot.banker.protocol.IBankService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class RMIServer {
    public static void main(String args[]) {
        try {
            IBankService bankerService = BankService.getInstance();
            Context initialNamingContext = new InitialContext(RMISetting.defaults());
            PortableRemoteObject.exportObject(bankerService);
            initialNamingContext.rebind(Vocabulary.BANKER_SERVICE, bankerService);
            System.out.println("Server: Ready...");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            e.printStackTrace();
        }

    }
}
