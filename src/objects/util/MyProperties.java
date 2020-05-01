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
            copyConfigOutOfJar(MyProperties.class.getResourceAsStream("/config/config.properties"), "config.properties");
        }
        loadFile(file);


    }

    private static void loadFile(File file) throws IOException {
        FileInputStream ip = new FileInputStream(file);
        prop.load(ip);
    }

    public static void copyJDBCDriver() throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            copyConfigOutOfJar(MyProperties.class.getResourceAsStream("/sqlite-jdbc-3.30.1.jar"), "sqlite-jdbc-3.30.1.jar");
        }
    }

    private static void copyConfigOutOfJar(InputStream inputStream, String fileOutName) throws IOException {
        FileOutputStream out = new FileOutputStream(Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + fileOutName);
        byte[] buffer = new byte[inputStream.available()];
        //noinspection ResultOfMethodCallIgnored
        inputStream.read(buffer);
        out.write(buffer);
        out.flush();
        out.close();
        inputStream.close();

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

