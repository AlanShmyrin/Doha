package narxoz.AlanShmyrin.doha.Templates;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import narxoz.AlanShmyrin.doha.Mappers.Audience;
import narxoz.AlanShmyrin.doha.Mappers.AudienceMapper;
import narxoz.AlanShmyrin.doha.Mappers.ComputersMapper;

@Component
public class AudienceJDBCTemplate implements AudienceDAO {
	
	private JdbcTemplate jdbcObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcObject = new JdbcTemplate(dataSource);
		
	}

	@Override
	public void crete(int id, String status, String reason) {
		String SQL = "insert into place(id,status,reason) values(?,?,?)";
		jdbcObject.update(SQL, id, status, reason);
		System.out.println("added " + id + " " + status + " " + reason);
		return;
		
	}

	@Override
	public void update(int id, String status, String reason) {
		String SQL = "update audience_110 set status=?, reason=? where id=?";
		jdbcObject.update(SQL, status, reason, id);
		System.out.println("updated");
		return;
	}
	
	
	public void crete(int id, String name) {
		String SQL = "insert into place(id,name) values(?,?)";
		jdbcObject.update(SQL, id, name);
		System.out.println("added " + id + " " + name);
		return;
		
	}
	
	public void crete(int id, int rated, String text, int placeId) {
		String SQL = "insert into reviews(id,rated,text,place_id) values(?,?,?,?)";
		jdbcObject.update(SQL, id, rated, text, placeId);
		jdbcObject.update("update reviews set place_name = (select name from place where id = " + placeId + ')' + "where place_id = " + placeId);
		System.out.println("added " + id + " " + rated + " " + text + " " + placeId);
		return;
		
	}
	
	public List<Audience> listComputers() {
		String SQL = "select * from audience_110";
		List<Audience> audit = jdbcObject.query(SQL, new AudienceMapper());
		return audit;
	}

	public List<Audience> listComputesrWithSetup() {
		String SQL = "select * from audience_110 "
				+ "inner join setup_of_compurers on audience_110.id=setup_of_compurers.id";
		List<Audience> audit = jdbcObject.query(SQL, new ComputersMapper());
		return audit;
	}

	public Audience getById(int id) {
		String SQL = "select audience_110.id, audience_110.status, audience_110.reason, "
				+ "setup_of_compurers.videocard, setup_of_compurers.processor, "
				+ "setup_of_compurers.free_disk_space, setup_of_compurers.ram "
				+ "from audience_110 "
				+ "inner join setup_of_compurers on audience_110.id=setup_of_compurers.id"
				+ " where audience_110.id="+id;
		return jdbcObject.query(SQL, new ComputersMapper()).get(0);
	}


}
