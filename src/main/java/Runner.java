import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan
        Connection connection=JdbcUtils.connectToDataBase("localhost","techpro","postgres","iderut");
        //3. adim Statement olustur
        Statement statement = JdbcUtils.createStatement();
//4. adim
       //JdbcUtils.execute("create table students(name varchar(20), id int, address varchar(80))");

        JdbcUtils.createTable("abc1","classes varchar(20)","teacher_Name varchar(20)","id int");
//5 baglantiyi kapat
        JdbcUtils.closeConnectionAndStatement();
    }
}
