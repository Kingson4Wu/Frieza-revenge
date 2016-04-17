package apache.commons.dbutils;

/**
 * Created by kingsonwu on 15/12/26.
 */

import com.kxw.bean.Person;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

//转换成list
public class TestDbUtils {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/ptest";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "ptest";

        DbUtils.loadDriver(jdbcDriver);
        try {
            conn = DriverManager.getConnection(url, user, password);
            QueryRunner qr = new QueryRunner();

            //转换成list
            List results = (List) qr.query(conn, "select id,name from person", new BeanListHandler(Person.class));
            for (int i = 0; i < results.size(); i++) {
                Person p = (Person) results.get(i);
                System.out.println("id:" + p.getId() + ",name:" + p.getName());
            }

            //转换成map
           /* List results = (List) qr.query(conn, "select id,name from person", new MapListHandler());
            for (int i = 0; i < results.size(); i++) {
                Map map = (Map) results.get(i);
                System.out.println("id:" + map.get("id") + ",name:" + map.get("name"));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}