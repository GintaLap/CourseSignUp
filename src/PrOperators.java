import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrOperators {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pieteikumi_kursiem?serverTimezone=UTC", "root", "password");
            System.out.println("Connection is made!");
            // visu pieteikumu iegūšana
            Statement stmt = conn.createStatement();
            String strSelect = "SELECT * FROM pieteikumi";
            ResultSet rs = stmt.executeQuery(strSelect);
            int id, talrunis;
            String vards, epasts, programma;
            while (rs.next()) {
                id = rs.getInt("id");
                vards = rs.getString("vards");
                talrunis = rs.getInt("talrunis");
                epasts = rs.getString("epasts");
                programma = rs.getString("programma");
                System.out.printf("%d. %s, %d, %s- %s\n",
                        id, vards, talrunis, epasts, programma);
            }

            // meklēšana pēc mācību programmas
            System.out.println();
            strSelect = "SELECT vards, talrunis, epasts FROM pieteikumi WHERE programma = '?'";
            rs = stmt.executeQuery(strSelect);
            while (rs.next()) {
                vards = rs.getString("vards");
                talrunis = rs.getInt("talrunis");
                epasts = rs.getString("epasts");
                System.out.printf("%s, %d, %s \n",
                        vards, talrunis, epasts);
            }
            // meklēšana pēc vārda
            System.out.println();
            strSelect = "SELECT vards, talrunis, epasts, programma FROM pieteikumi WHERE vards = '?'";
            rs = stmt.executeQuery(strSelect);
            while (rs.next()) {
                vards = rs.getString("vards");
                talrunis = rs.getInt("talrunis");
                epasts = rs.getString("epasts");
                System.out.printf("%s, %d, %s\n", vards, talrunis, epasts);
            }
        } catch (Exception e) {
            System.out.println("Connection failed!");
        }
    }
}