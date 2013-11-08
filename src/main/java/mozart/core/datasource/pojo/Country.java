package mozart.core.datasource.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Country {

	@XmlElement
	private String name;

	@XmlElement
	private String currency;

	@XmlElement
	private String tld;

	@XmlElement
	private String cca2;

	@XmlElement
	private String cca3;

	@XmlElement
	private String ccn3;

	@XmlElement
	private String callingcode;

	@XmlElement
	private String capital;

	@XmlElement
	private String region;

	@XmlElement
	private String subregion;

	@XmlElement
	private String altSpellings;

	@XmlElement
	private String relevance;

	@XmlElement
	private String nationality;

	@XmlElement
	private List<Double> latlng;

	@XmlElement
	private List<String> languages;

	@XmlElement
	private CountryTranslations translations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
		this.tld = tld;
	}

	public String getCca2() {
		return cca2;
	}

	public void setCca2(String cca2) {
		this.cca2 = cca2;
	}

	public String getCca3() {
		return cca3;
	}

	public void setCca3(String cca3) {
		this.cca3 = cca3;
	}

	public String getCcn3() {
		return ccn3;
	}

	public void setCcn3(String ccn3) {
		this.ccn3 = ccn3;
	}

	public String getCallingcode() {
		return callingcode;
	}

	public void setCallingcode(String callingcode) {
		this.callingcode = callingcode;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public String getAltSpellings() {
		return altSpellings;
	}

	public void setAltSpellings(String altSpellings) {
		this.altSpellings = altSpellings;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Double> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public CountryTranslations getTranslations() {
		return translations;
	}

	public void setTranslations(CountryTranslations translations) {
		this.translations = translations;
	}

}
