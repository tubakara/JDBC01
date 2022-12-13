package Calisma;

import java.sql.*;

public class Exercise01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro", "postgres", "iderut");
        Statement st = con.createStatement();


        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "select country_name from countries where region_id=1";
        ResultSet rst1 = st.executeQuery(sql1);
        while (rst1.next()) {
            System.out.println(rst1.getString("country_name"));
        }
        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql3 = "select country_id,country_name from countries where region_id>2";
        ResultSet sonuc = st.executeQuery(sql3);
        while (sonuc.next()) {
            System.out.println(sonuc.getString("country_id") + "--" + sonuc.getString("country_name"));

        }
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql2 = "SELECT * FROM companies WHERE number_of_employees=(SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql2);
        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt(1) + "--- " + resultSet3.getString(2) + "---" + resultSet3.getInt(3));
        }
        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

    }
}





