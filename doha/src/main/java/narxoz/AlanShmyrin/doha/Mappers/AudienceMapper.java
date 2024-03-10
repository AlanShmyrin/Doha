package narxoz.AlanShmyrin.doha.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class AudienceMapper implements RowMapper<Audience> {
	public Audience mapRow(ResultSet rs, int rowNum) throws SQLException{
		Audience audit = new Audience();
		audit.setId(rs.getInt("id"));
		audit.setReason(rs.getString("reason"));
		audit.setStatus(rs.getString("status"));
		return audit;
	}
}
