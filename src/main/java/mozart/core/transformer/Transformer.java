package mozart.core.transformer;

import java.lang.reflect.Field;

import mozart.core.exception.MozartException;

public abstract class Transformer<T> {

	public abstract T transform(Field field, String value) throws MozartException;

	protected T validate(T t, Field field, String value) throws MozartException {
		if (t == null) {
			throw new MozartException(String.format("%s with id %s is not found", field
			    .getType()
			    .getSimpleName(), value));
		}
		return t;
	}
}
