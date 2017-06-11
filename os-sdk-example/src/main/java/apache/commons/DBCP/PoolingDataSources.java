package apache.commons.DBCP;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
//官方示例
public class PoolingDataSources {

    public static void main(String[] args) {
        System.out.println("加载jdbc驱动");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done.");
        //
        System.out.println("设置数据源");
        DataSource dataSource = setupDataSource("jdbc:oracle:thin:@localhost:1521:test");
        System.out.println("Done.");

        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            System.out.println("Creating connection.");
            conn = dataSource.getConnection();
            System.out.println("Creating statement.");
            stmt = conn.createStatement();
            System.out.println("Executing statement.");
            rset = stmt.executeQuery("select * from person");
            System.out.println("Results:");
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next()) {
                for(int i=0;i<=numcols;i++) {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rset != null) {
                rset.close();
            }
            } catch(Exception e) { }
            try { if (stmt != null) {
                stmt.close();
            }
            } catch(Exception e) { }
            try { if (conn != null) {
                conn.close();
            }
            } catch(Exception e) { }
        }
    }

    public static DataSource setupDataSource(String connectURI) {
       /* //设置连接地址
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                connectURI, null);

        // 创建连接工厂
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory);

        //获取GenericObjectPool 连接的实例
        ObjectPool connectionPool = new GenericObjectPool(
                poolableConnectionFactory);

        // 创建 PoolingDriver
        PoolingDataSource dataSource = new PoolingDataSource(connectionPool);

        return dataSource;*/
        return null;
    }
}