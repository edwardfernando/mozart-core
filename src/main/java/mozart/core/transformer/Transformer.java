package mozart.core.transformer;

import java.lang.reflect.Field;

import mozart.core.exception.MozartException;

public abstract class Transformer<T> {

	public abstract T transform(Field field, String value) throws MozartException;

}
