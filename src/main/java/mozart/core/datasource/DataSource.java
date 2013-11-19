package mozart.core.datasource;

import java.util.List;
import java.util.Map;

public interface DataSource<T> {
	public Map<String, T> getMap();

	public List<T> getList();
}
