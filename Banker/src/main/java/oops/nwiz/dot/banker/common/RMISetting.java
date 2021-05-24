package oops.nwiz.dot.banker.common;

import javax.naming.Context;
import java.util.Hashtable;

/**
 * The RMI Settings
 * 这些设置在运行时加载, 以免除在执行时写一长串命令行参数的痛苦
 * 同时保证客户端和服务器用的配置能被同时更新
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
