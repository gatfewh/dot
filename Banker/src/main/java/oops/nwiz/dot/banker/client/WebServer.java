package oops.nwiz.dot.banker.client;

import oops.nwiz.dot.banker.common.RMISetting;
import oops.nwiz.dot.banker.protocol.IAccount;
import oops.nwiz.dot.banker.protocol.IBankService;

import javax.naming.Context;
import javax.naming.InitialContext;

import static spark.Spark.*;

public class WebServer {

    private IBankService service;
    private Context context;

    WebServer() {
        try {
            context = new InitialContext(RMISetting.defaults());
            service = (IBankService) context.lookup("BankService");
        } catch (Exception e) {
            System.err.println("Exception " + e + "Caught");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new WebServer().init();
    }

    void init() {
        port(8080);
        get("/getcredit/:id", ((request, response) -> {
            IAccount account = service.getAccount(request.params(":id"));
            if (account != null) {
                return account.getCredit().toString();
            } else {
                return "undefined";
            }
        }));
        get("hello", (request, response) -> "Hi~");
    }
}
