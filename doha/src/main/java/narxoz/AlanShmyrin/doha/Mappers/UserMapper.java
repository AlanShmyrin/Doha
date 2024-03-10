package narxoz.AlanShmyrin.doha.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<Integer> {
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getInt("access");
	}

}
