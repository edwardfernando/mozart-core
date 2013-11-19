package mozart.core.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import mozart.core.datasource.pojo.Country;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class CountryDataSource implements DataSource<Country> {

	private static Map<String, Country> map = Maps.newConcurrentMap();
	private static List<Country> list = Lists.newArrayList();

	private static CountryDataSource instance;

	private CountryDataSource() {
		initialize();
	}

	private void initialize() {
		InputStream streams = getClass().getClassLoader().getResourceAsStream(
		    "jsons/countries.json");

		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(streams, "UTF-8"));
			reader.beginArray();
			while (reader.hasNext()) {
				Country country = gson.fromJson(reader, Country.class);
				map.put(country.getCca2(), country);
				list.add(country);
			}

			reader.endArray();
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Map<String, Country> getMap() {
		return map;
	}

	public List<Country> getList() {
		return list;
	}

	public static CountryDataSource instance() {
		if (instance == null) {
			instance = new CountryDataSource();
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(CountryDataSource.instance().getMap());
	}
}
