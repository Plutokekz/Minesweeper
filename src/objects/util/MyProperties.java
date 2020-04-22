package objects.util;

import java.io.*;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;


public class MyProperties {

    private static final Properties prop = new Properties();


    public static void loadMyProperties() throws IOException {
        File file = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "\\config.properties");
        if (!file.exists()) {
            copyConfigOutOfJar();
        }
        loadFile(file);


    }

    private static void loadFile(File file) throws IOException {
        FileInputStream ip = new FileInputStream(file);
        prop.load(ip);
    }

    private static void copyConfigOutOfJar() throws IOException {
        InputStream in = MyProperties.class.getResourceAsStream("/config/config.properties");
        FileOutputStream out = new FileOutputStream(Paths.get(".").toAbsolutePath().normalize().toString() + "\\config.properties");
        byte[] buffer = new byte[in.available()];
        //noinspection ResultOfMethodCallIgnored
        in.read(buffer);
        out.write(buffer);
        out.flush();
        out.close();
        in.close();

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
        FileOutputStream up = new FileOutputStream(Paths.get(".").toAbsolutePath().normalize().toString() + "\\config.properties");
        prop.store(up, "");
    }
}

