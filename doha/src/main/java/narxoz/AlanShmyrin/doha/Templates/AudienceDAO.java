package narxoz.AlanShmyrin.doha.Templates;

import javax.sql.DataSource;

public interface AudienceDAO {

	public void setDataSource(DataSource dataSource);
	
	public void crete(int id,String status, String reason);
	
	public void update(int id, String status, String reason);
	
}
