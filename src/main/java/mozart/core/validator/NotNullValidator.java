package mozart.core.validator;

import java.lang.annotation.Annotation;


import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;

public class NotNullValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value) throws Exception {
		if (value == null) {
			wrapper.registerError(new Error(paramName, "Can not null"));
		}
	}
}