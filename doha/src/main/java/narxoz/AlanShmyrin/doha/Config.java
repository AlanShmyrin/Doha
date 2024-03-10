package narxoz.AlanShmyrin.doha;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import narxoz.AlanShmyrin.doha.Templates.AudienceJDBCTemplate;
import narxoz.AlanShmyrin.doha.Templates.ComputersJDBCTemplate;
import narxoz.AlanShmyrin.doha.Templates.UserJDBCTemplate;

@Configuration
@ComponentScan("com.baeldung.jdbc")
public class Config {
	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		
		data.setDriverClassName("com.mysql.cj.jdbc.Driver");
		data.setUrl("jdbc:mysql://localhost:3306/audit");
		data.setUsername("root");
		data.setPassword("admin");
		
		return data;
	}
	@Bean
	public AudienceJDBCTemplate myJDBCTemplate() {
		AudienceJDBCTemplate a = new AudienceJDBCTemplate();
		a.setDataSource(mysqlDataSource());
		return a;
	}
	
	@Bean
	public ComputersJDBCTemplate computersJDBCTemplate() {
		ComputersJDBCTemplate a = new ComputersJDBCTemplate();
		a.setDataSource(mysqlDataSource());
		return a;
	}
	
	@Bean
	public UserJDBCTemplate userJDBCTemplate() {
		UserJDBCTemplate a = new UserJDBCTemplate();
		a.setDataSource(mysqlDataSource());
		return a;
	}
	
	@Bean
	public Graphic appGraph() {
		return new Graphic(myJDBCTemplate(),computersJDBCTemplate(), userJDBCTemplate());
	}
	

}
