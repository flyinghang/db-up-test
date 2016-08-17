package me.ezapps4;

import java.sql.*;

/**
 * Created by sheldon on 8/18/2016.
 */



/**
 *
 * Created by sheldon on 11/08/2016.
 *
 *
 */
public class UpTest {


    private static final int DEFAULT_TIME_OUT = 5 * 60 * 1000; //default 5 min.

    public static void main(String[] argv) {

        // host, port, instance, user, password

        if (argv == null || argv.length < 3) {

            System.out.println("Usage: UpTest host port instance user password timeout(seconds)");
            //parameter error
            System.exit(100);

            return;
        }


        String host = argv[0];
        String port = argv[1];
        String instance = argv[2];

        String userName = argv.length >= 4 ? argv[3] : null;
        String password = argv.length >= 5 ? argv[4] : null;
        int timeout = argv.length >= 6 ? Integer.valueOf(argv[5]) * 1000 : DEFAULT_TIME_OUT;

        if (userName == null || userName.length() == 0) {
            userName = "system";
            password = "oracle";
        }


        long start = System.currentTimeMillis();

        UpTest upTest = new UpTest();

        while (!upTest.testOracleServer(host, port, instance, userName, password)) {

            if (System.currentTimeMillis() - start > timeout) {

                System.out.println(host + " is not ready yet, timeout.");

                System.exit(100);
                return;
            }

            System.out.println(host + " is not ready, retry now.");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(host + " is ready.");
        System.exit(0);

    }


    private boolean testOracleServer(String host,
                                     String port,
                                     String instance,
                                     String userName,
                                     String password) {

        // host, port, instance, user, password

        String connectString = "jdbc:oracle:thin:@" + host + ":" + port + ":" + instance;

        System.out.println("Connecting: " + host);

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Oracle JDBC Driver not found.");
            return false;

        }

        Connection connection = null;

        try {
            try {

                connection = DriverManager.getConnection(
                        connectString, userName, password);


                PreparedStatement stmt = connection.prepareStatement("select count(*) from tabs");
                ResultSet result = stmt.executeQuery();

                while (result.next()) {

                    int tableCount = result.getInt(1);

                    System.out.println("Table count:" + tableCount);

                }


                return true;

            } catch (SQLException e) {

                System.out.println("Connection Failed! " + e.getMessage());


                return false;

            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //do nothing.
                }

            }
        }


    }

}
