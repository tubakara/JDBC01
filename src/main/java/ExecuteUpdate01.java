import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro", "postgres", "iderut");
        Statement st = con.createStatement();
        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
        String sql2="update companies \n" +
                "set number_of_employees =16000\n" +
                "where number_of_employees<(select avg(number_of_employees)\n" +
                "\t\t\t\t\t\t   from companies)";
        int updateEdilenSatirSayisi=st.executeUpdate(sql2);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);
        ResultSet resultSet1=st.executeQuery("select* from companies");
        while(resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+" "+ resultSet1.getInt(3));

        }

    }


}
