import org.junit.Assert;
import org.junit.Test;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Assert that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */

    @Test
    public void countryTest() throws SQLException {
       // User connects to the database
        JdbcUtils.connectToDataBase("localHost","techpro","postgres","iderut");
        Statement statement=JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql1="Select region_id from countries";
        ResultSet resultSet1=statement.executeQuery(sql1);
        List<Integer> ids= new ArrayList<>();
        while(resultSet1.next()){
           ids.add(resultSet1.getInt(1));
        }
        System.out.println("ids = " + ids);
        List<Integer>idsGraterThnaOne=new ArrayList<>();
        for(int w:ids){
            if(w>1){
              idsGraterThnaOne.add(w);
            }

        }
        System.out.println("idsGraterThnaOne = " + idsGraterThnaOne);
        // Assert that the number of region ids greater than 1 is 17.
        Assert.assertEquals(17,idsGraterThnaOne.size());
    }
}


