package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Calisma.Exercise02.executeQuery;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {

    }


    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection != null) {
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Fail");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement() {


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return statement;
    }

    //4. Adım: Query çalıştır.
    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }

    //ExecuteQuery ve ExecuteUpdate methodları ödev...


    //5. Adım: Bağlantı ve Statement'ı kapat.
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed!");

            } else {
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType) {
        StringBuilder columnName_dataValue = new StringBuilder("");

        for (String w : columnName_dataType) {

            columnName_dataValue.append(w).append(",");

        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length() - 1);

        try {
            statement.execute("CREATE TABLE " + tableName + "(" + columnName_dataValue + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

 //NSERT INTO tableName (columnName1, columnName2...)VALUES (value1,value2...)
 public static void insertDataIntoTable(String tableName, String ...columnName_Values){
        StringBuilder columnName= new StringBuilder("");
        StringBuilder value= new StringBuilder("");

        for(String w: columnName_Values){
            int firstSpace=w.indexOf(" ");
            columnName.append(w.substring(firstSpace)).append(",");
            value.append(w.substring(firstSpace)).append(",");
            }
        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));
        String command= "Insert Into"+tableName+"("+columnName+")"+"Values ("+value+")";

     try {
         statement.execute(command);
         System.out.println("Data inserted successfully into "+tableName);
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

 }


public static List<Object>getColumnList(String columnName,String tableName){
        ResultSet resultSet=null;
        List<Object>columnData= new ArrayList<>();
        String query="select "+columnName +" from"+tableName;
    try {
       resultSet= statement.executeQuery(query);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    while (true) {
        try {
            if (!resultSet.next()) break;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
           columnData.add(resultSet.getObject(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    return columnData;
}





//    public static List<String> executeQuery1(String tableName, String columnName) throws SQLException {
//        String query = "SELECT " + columnName + " FROM " + tableName;
//        List<String> resultList;
//        try (ResultSet rs = executeQuery(query)) {
//
//            resultList = new ArrayList<>();
//            while (rs.next()) {
//                String value = rs.getString(columnName);
//                resultList.add(value);
//            }
//        }
//        return  resultList;
//    }

}




















