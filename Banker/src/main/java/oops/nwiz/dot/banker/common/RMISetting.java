package oops.nwiz.dot.banker.common;

import javax.naming.Context;
import java.util.Hashtable;

/**
 * The RMI settings used to create InitialContext in both RMIServer and clients.
 */
public class RMISetting {
    static private Hashtable<String, String> settings;

    /**
     * @return a hash table containing all essential configurations.
     */
    static public synchronized Hashtable defaults() {
        if (settings == null) {
            settings = new Hashtable<>();
            settings.put(Context.PROVIDER_URL,            "iiop://localhost:1050");
            settings.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
        }
        return settings;
    }
}
