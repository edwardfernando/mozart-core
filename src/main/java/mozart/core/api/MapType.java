package mozart.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapType {
	public List<MapEntryType> entry = new ArrayList<MapEntryType>();

	public MapType(Map<String, Object> map) {
		for (Map.Entry<String, Object> e : map.entrySet())
			entry.add(new MapEntryType(e));
	}

	public MapType() {
	}
}
