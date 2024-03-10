package narxoz.AlanShmyrin.doha.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ComputersMapper implements RowMapper<Audience> {
	public Audience mapRow(ResultSet rs, int rowNum) throws SQLException{
		Audience audit = new Audience();
		audit.setId(rs.getInt("id"));
		audit.setReason(rs.getString("reason"));
		audit.setStatus(rs.getString("status"));
		audit.setProcessor(rs.getString("processor"));
		audit.setVideocard(rs.getString("videocard"));
		audit.setRam(rs.getString("ram"));
		audit.setFreeDiskSpace(rs.getString("free_disk_space"));
		return audit;
	}
}
