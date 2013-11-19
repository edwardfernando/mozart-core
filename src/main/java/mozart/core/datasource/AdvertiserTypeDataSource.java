package mozart.core.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import mozart.core.datasource.pojo.AdvertiserType;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class AdvertiserTypeDataSource implements DataSource<AdvertiserType> {

	private static Map<String, AdvertiserType> map = Maps.newConcurrentMap();
	private static List<AdvertiserType> list = Lists.newArrayList();

	private static AdvertiserTypeDataSource instance;

	private AdvertiserTypeDataSource() {
		initialize();
	}

	private void initialize() {
		InputStream streams = getClass().getClassLoader().getResourceAsStream(
		    "jsons/advertiser_type.json");

		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(streams, "UTF-8"));
			reader.beginArray();
			while (reader.hasNext()) {
				AdvertiserType type = gson.fromJson(reader, AdvertiserType.class);
				map.put(type.getCode(), type);
				list.add(type);
			}

			reader.endArray();
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Map<String, AdvertiserType> getMap() {
		return map;
	}

	public List<AdvertiserType> getList() {
		return list;
	}

	public static AdvertiserTypeDataSource instance() {
		if (instance == null) {
			instance = new AdvertiserTypeDataSource();
		}
		return instance;
	}

}
