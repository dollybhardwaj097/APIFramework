package airline;

import org.apitestingframework.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {
    public static Map<String,Object> data ;

    static {
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            data = JsonUtils.getJSONDataAsMap("airlines/Dev/"+env+"/TestEnv.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
