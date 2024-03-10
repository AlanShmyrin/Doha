package narxoz.AlanShmyrin.doha.Templates;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import narxoz.AlanShmyrin.doha.Mappers.UserMapper;

@Component
public class UserJDBCTemplate implements AudienceDAO {
	private JdbcTemplate jdbc;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
		
	}

	@Override
	public void crete(int id, String status, String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, String status, String reason) {
		// TODO Auto-generated method stub
		
	}
	// Usernames and passwords in database
	public Integer login(String name, String password) {
		String SQL;
		try {
			SQL = "select access from users where name="+'"'+name+'"'+" and password="+'"'+password+'"';
			return jdbc.query(SQL, new UserMapper()).get(0);
		}catch(IndexOutOfBoundsException e) {
				return 2;
		}
	}

}
