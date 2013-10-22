package mozart.core.api;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MapEntryType {

	@XmlAttribute
	public String key;

	@XmlAttribute
	public Object value;

	public MapEntryType() {
	}

	public MapEntryType(Map.Entry<String, Object> e) {
		key = e.getKey();
		value = e.getValue();
	}
}
