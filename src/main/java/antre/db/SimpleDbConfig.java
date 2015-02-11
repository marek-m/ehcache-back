package antre.db;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//TODO TEST annotation config
//@Configuration
//public class SimpleDbConfig {
//
//    @Bean
//    public DataSource dataSource()  {    
//System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELLLO");
//        URI dbUri;
//        try {
//            String username = "test";
//            String password = "test";
//            String url = "jdbc:postgresql://localhost/antrejka";
//            String dbProperty = System.getProperty("database.url");
//            if(dbProperty != null) {
//                dbUri = new URI(dbProperty);
//
//                username = dbUri.getUserInfo().split(":")[0];
//                password = dbUri.getUserInfo().split(":")[1];
//                url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//            }     
//
//            BasicDataSource basicDataSource = new BasicDataSource();
//            basicDataSource.setUrl(url);
//            basicDataSource.setUsername(username);
//            basicDataSource.setPassword(password);
//            basicDataSource.setDriverClassName("org.postgresql.Driver");
//
//            return basicDataSource;
//
//        } catch (URISyntaxException e) {
//            return null;
//        }
//    }
//}
