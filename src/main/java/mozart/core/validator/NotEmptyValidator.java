package mozart.core.validator;

import java.lang.annotation.Annotation;


import mozart.core.exception.ErrorWrapper;

public class NotEmptyValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value) throws Exception {
		isEmpty(wrapper, paramName, value);
	}
}
