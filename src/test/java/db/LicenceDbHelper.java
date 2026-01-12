package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class LicenceDbHelper {
    private static final String DB_URL =
            "jdbc:mysql://192.168.0.36:3306/consultant_app?useSSL=false&serverTimezone=UTC";

    private static final String USER = "netroot";
    private static final String PASSWORD = "netroot";

    private static final String FEATURE_NAME = "consultantappshopamount";
    private static final String SHOP_ID = "_shop_34788_94f5bd8b";
    private static final String FEATURE_END_DATE = "2026-06-16";

    private static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /** Полностью очищает лицензии магазина */
    public static void deleteAllLicenses() {
        String sql = "DELETE FROM shop_feature WHERE shopId = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, SHOP_ID);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Ошибка очистки лицензий в БД", e);
        }
    }

    /** Добавляет валидную лицензию */
    public static void insertLicense() {
        String sql = """
                INSERT INTO shop_feature (featureName, featureEndDate, shopId)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, FEATURE_NAME);
            ps.setString(2, FEATURE_END_DATE);
            ps.setString(3, SHOP_ID);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Ошибка добавления лицензии в БД", e);
        }
    }
}
