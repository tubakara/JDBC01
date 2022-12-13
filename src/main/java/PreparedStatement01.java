import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","iderut");
        Statement st = con.createStatement();
       /*
        PreparedStatement interface, birden çok kez çalıştırılabilen önceden derlenmiş bir SQL kodunu temsil eder.
        Paremetrelendirilmiş SQL sorguları(query) ile çalışır. Bur sorguyu 0 veya daha fazla parametre ile kullanabiliriz.

     */

        //1. Örnek: PreparedStatement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. adim: PreparedStatement querysini olustur
        String sql1 = "update companies set number_of_employees=? where company=?";
        //2. adim: PreparedStatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);
        //3. adim: soru isaretleri yerine deger atayacagiz(setInt, setString metodlarini kullanarak)
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");
        //4. adim: Queryyi calistir
        int guncellenenSatirSayisi=pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);
        //tabloyu okumak icin
        String sql2="select * from companies";
       ResultSet rs1=st.executeQuery(sql2);
       while(rs1.next()){
           System.out.println(rs1.getInt(1)+"--"+rs1.getString(2)+"--"+rs1.getInt(3));
       }
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");
        int guncelenenStairSayisi1 = pst1.executeUpdate();
        System.out.println("guncelenenStairSayisi = " + guncelenenStairSayisi1);
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1)+" "+resultSet2.getString(2)+ " "+ resultSet2.getInt(3));
        }
        con.close();
        st.close();
        rs1.close();
        resultSet2.close();


    }
}
