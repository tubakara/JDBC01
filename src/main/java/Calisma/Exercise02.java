package Calisma;

import java.sql.*;

public class Exercise02 {
    public static void main(String[] args) {

    }

    private static Connection connection;
    private static Statement st;

    public static Connection connectionToDatabase(String hostName, String databaseName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:pstgresgl://" + hostName + ":5432/" + databaseName + username + password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Statement createStatement() {
        try {
            st = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return st;
    }

    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }


    //    public static void tabloYap(String tableName,String... sutunAdi_dataTipi){
//        StringBuilder sutunDegerleri = new StringBuilder("");
//        for(String w: sutunAdi_dataTipi){
//            sutunDegerleri.append(w).append(",");
//            sutunDegerleri.deleteCharAt(sutunDegerleri.length()-1);
//            try {
//                st.execute("Create table"+tableName+"("+sutunDegerleri+")");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//    }
    public static ResultSet executeQuery(String sql) {
        ResultSet resulSet;
        try {
            resulSet = st.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                if (!resulSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(resulSet.getString(sql));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resulSet;
    }


    //ExecuteQuery
    public static ResultSet ExecuteQuery(String tableName) {
        String str = "select count(*) from information_schema.columns where table_name = '" + tableName + "'";
        int count = 0;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                count = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        String sql1 = "select * from " + tableName;
        ResultSet resultSet2 = null;
        try {
            resultSet2 = st.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!resultSet2.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= count; i++) {
                try {
                    System.out.print(resultSet2.getString(i) + "       ");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println();
        }
        return resultSet2;


    }
}
