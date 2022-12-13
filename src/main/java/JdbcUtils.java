import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    public static void main(String[] args) {
        createTable("abc","name varchar(10)","id int","address varchar(80)");
    }

    private static Connection connection;
    private static Statement statement;


    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName,String username, String password)  {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement(){


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return statement;
    }
    //4. adim: Query calistir
    public static boolean execute(String sql){
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }
    //executeQuery ve ExecuteUpdate method olustur

    //5. adim baglanti ve statemrnti kapat
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("connection and statement are closed");
            }else{
                System.out.println("connection and statement are  not closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //table olusturan method
    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType ){
        StringBuilder columnName_dataValue=new StringBuilder("");
        for(String w :columnName_dataType){
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);//en son virgulu siler



  try {
            statement.execute( "CREATE TABLE "+tableName+"("+columnName_dataValue+")");
      System.out.println("table " +tableName+" was created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





}