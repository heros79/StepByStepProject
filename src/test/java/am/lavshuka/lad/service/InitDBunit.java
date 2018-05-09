package am.lavshuka.lad.service;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.io.FileInputStream;
import java.util.ResourceBundle;

/**
 * Created by David on 5/9/2018.
 */
public class InitDBunit extends DBTestCase {

    public InitDBunit(String name) {
        super(name);
        ResourceBundle rb = ResourceBundle.getBundle("db");
        String driver = rb.getString("driver");
        String db = rb.getString("db");
        String host = rb.getString("host");
        String port = rb.getString("port");
        String schema = rb.getString("schema");
        String user = rb.getString("user");
        String password = rb.getString("password");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, db + "://" + host + ":" + port + "/" + schema);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, user);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, password);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/testdata.xml"));
    }
}