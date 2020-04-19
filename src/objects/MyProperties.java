package objects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class MyProperties {

    private static final Properties prop = new Properties();


    public static void loadMyProperties() throws IOException {
        FileInputStream ip = new FileInputStream("src/config/config.properties");
        prop.load(ip);
    }

    public static Locale getLocale() {
        switch ((String) prop.get("lang")) {
            case "ko":
                return new Locale("ko", "KR");
            case "en":
                return new Locale("en", "EN");
            case "de":
                return new Locale("de", "DE");
            default:
                return Locale.getDefault();
        }
    }

    public static void setLocal(String local) throws IOException {
        prop.setProperty("lang", local);
        FileOutputStream up = new FileOutputStream("src/config/config.properties");
        prop.store(up, "");
    }
}

