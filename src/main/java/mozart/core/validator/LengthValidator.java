package mozart.core.validator;

import java.lang.annotation.Annotation;


import mozart.core.annotation.Length;
import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;

public class LengthValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value) throws Exception {
		Length length = (Length) annot;
		int valueLength = value.length();

		if (valueLength < length.minLength() || valueLength > length.maxLength()) {
			wrapper.registerError(new Error(paramName, String.format(
			    "Parameter should be between %s and %s characters length",
			    length.minLength(),
			    length.maxLength())));
		}

	}
}
