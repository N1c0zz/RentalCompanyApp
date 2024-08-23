package src.main.java.controller.DBHandler;

import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataBaseHandler {

    private MysqlDataSource ds = new MysqlDataSource();

    public DataBaseHandler(){

    }
    
    public MysqlDataSource setSQLDataSource() {
        try {
            this.ds.setServerName("localhost");
            this.ds.setPort(3306);
            this.ds.setUser("root");
            this.ds.setPassword("SQLnicopass03!");
            this.ds.setDatabaseName("progettodatabase");
            this.ds.setUseSSL(false);
            this.ds.setAllowPublicKeyRetrieval(true);
        } catch (SQLException e) {
            System.out.println("ERRORE: " + e.getMessage());
        }

        return this.ds;
    }
}


