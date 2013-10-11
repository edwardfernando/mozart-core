package mozart.core.validator;

import java.lang.annotation.Annotation;

import mozart.core.annotation.Min;
import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;

public class MinValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value)
	        throws Exception {

		if (isNumber(wrapper, paramName, value)) {
			Min min = (Min) annot;
			if (Double.valueOf(value) < min.value()) {
				wrapper.registerError(new Error(paramName, String.format(
				    "Minimum value for parameter %s is %s",
				    paramName,
				    min.value())));
			}
		}
	}
}
