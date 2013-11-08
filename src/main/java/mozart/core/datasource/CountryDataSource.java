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

public class CountryDataSource {

	private static Map<String, Country> countriesMap;;
	private static List<Country> countriesList;

	private static CountryDataSource instance;

	private CountryDataSource() {
		initialize();
	}

	private void initialize() {
		countriesMap = Maps.newConcurrentMap();
		countriesList = Lists.newArrayList();

		InputStream is = this
		    .getClass()
		    .getClassLoader()
		    .getResourceAsStream("jsons/countries.json");
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
			reader.beginArray();
			while (reader.hasNext()) {
				Country country = gson.fromJson(reader, Country.class);
				countriesMap.put(country.getCca2(), country);
				countriesList.add(country);
			}

			reader.endArray();
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Map<String, Country> getCountriesMap() {
		return countriesMap;
	}

	public List<Country> getCountriesList() {
		return countriesList;
	}

	public static CountryDataSource instance() {
		if (instance == null) {
			instance = new CountryDataSource();
		}
		return instance;
	}

}
