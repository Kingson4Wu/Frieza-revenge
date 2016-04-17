package apache.commons.configuration;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestConfiguration {

    public static void main(String[] args) throws Exception {
        PropertiesConfiguration config = new PropertiesConfiguration("usergui.properties");
        config.setProperty("colors.background", "#000000");
                config.save();

        config.save("usergui.backup.properties");//save a copy
        Integer integer = config.getInteger("window.width",0);

    }
}
