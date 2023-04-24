import jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Statement;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Test01 {

    /*
       /*
    Given
        user connects to the database
    When
        user sends the query to get the region ids from countries table

    Then
        verify that number of region id grater than1is 17

     And user closes the connection

     */

    @Test
    public void test01(){
        JdbcUtils.connectToDataBase("localHost","techpro","postgres","iderut");
        JdbcUtils.createStatement();
       List<Object>region_ids= JdbcUtils.getColumnList("region_id","countries");
        System.out.println("region_ids = " + region_ids);


    }
    @Test
    public void test02(){

    }
}
