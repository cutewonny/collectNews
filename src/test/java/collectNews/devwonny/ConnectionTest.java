package collectNews.devwonny;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionTest {

//    KeywordRepository keywordRepository;
//
//    @BeforeEach
//    void beforeEach(){
//        String URL = "jdbc:mysql://localhost:3306/collectNews";
//        String USERNAME = "root";
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl(URL);
//        dataSource.setUsername(USERNAME);
//        dataSource.setPassword("");
//
//        keywordRepository = new MyBatisKeywordRepository(dataSource);
//
//    }

    @Test
    void driverManager() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/collectNews";
        String USERNAME = "root";
        Connection con1 = DriverManager.getConnection(URL, USERNAME, "");

        log.info("connection={}, class={}", con1, con1.getClass());

    }
//
//    @Test
//    void crud(){
//        log.info("start~~~~~~~~~~~~~~~~~~~~~~~~");
//        Keyword keyword = new Keyword(1L, "corona",true);
//
//    }
}
