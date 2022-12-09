import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","****");
        Statement st = con.createStatement();
        System.out.println("Connection Success");
        /*
        Execute() metodu hem DDL(CREATE, DROP,ALTER TABLE) VE DQL(SELECT) ICIN KULLANILIR
        1) EGER EXECUTE() DDL icin false return yapar
        2) eger execute() metodu DQL icin kullanilirsa data alirsak true alamazsak false verir yani ResultSet alindiginda true
        aksi halde false veririr
         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
boolean sql1=st.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println(sql1);// false return eder cunku data cagirmiyoruz

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.


        //2. yol
        String sql2="ALTER TABLE workers ADD worker_adres VARCHAR(80)";
        boolean sql2b=st.execute(sql2);
        System.out.println("sql2b="+sql2b);

        //3. ornek workers table i silin

        String sql3="Drop Table workers";
        st.execute(sql3);

        // baglantiyi kapama
        con.close();
        st.close();









    }

}
