package mozart.core.api;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mozart.core.pagination.FilterCriteria;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Wrapper {

	@XmlElement(name = "list")
	private List<?> objectList;

	@XmlElement(name = "paging")
	private FilterCriteria filterCriteria;

	@XmlElement
	private Integer objectCount;

	public Wrapper() {

	}

	public Wrapper(List<?> objectList) {
		this.objectList = objectList;
	}

	public Wrapper(List<?> objectList, FilterCriteria filterCriteria) {
		this.objectList = objectList;
		this.filterCriteria = filterCriteria;
	}

	public FilterCriteria getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(FilterCriteria filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public List<?> getObjectList() {
		return objectList;
	}

	public Integer getObjectCount() {
		return objectList.size();
	}

	public void setObjectCount(Integer objectCount) {
		this.objectCount = objectCount;
	}

}
