package narxoz.AlanShmyrin.doha.Templates;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;


@Component
public class ComputersJDBCTemplate implements AudienceDAO {

	private JdbcTemplate jdbc;
	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
		
	}

	@Override
	public void crete(int id, String status, String reason) {
		
		
	}
	public void crete(int id,String videocard, String processor,String freeDiskSpace, String ram) {
		String SQL = "insert into "
				+ "setup_of_compurers(id,videocard, processor, free_disk_space, ram) values(?,?,?,?,?)";
		jdbc.update(SQL, id, videocard, processor, freeDiskSpace, ram);
		System.out.println("added " + id + " " + videocard + " " + processor + " " 
		+ freeDiskSpace + " " + ram);
		return;
		
	}

	@Override
	public void update(int id, String status, String reason) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(int id,String videocard, String processor,String freeDiskSpace, String ram) {
		String SQL = "update setup_of_compurers "
				+ "set videocard=?, processor=?, free_disk_space=?, ram=? where id=?";
		jdbc.update(SQL,videocard, processor, freeDiskSpace, ram, id);
		System.out.println("updated");
		return;
		
	}

}
