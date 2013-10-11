package mozart.core.transformer;

import java.lang.reflect.Field;

import mozart.core.exception.MozartException;

public class StringTransformer extends Transformer<String> {

	@Override
	public String transform(Field field, String value) throws MozartException {
		return value;
	}

}
