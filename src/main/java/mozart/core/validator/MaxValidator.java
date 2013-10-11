package mozart.core.validator;

import java.lang.annotation.Annotation;

import mozart.core.annotation.Max;
import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;

public class MaxValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value)
	        throws Exception {

		if (isNumber(wrapper, paramName, value)) {
			Max max = (Max) annot;
			if (Double.valueOf(value) > max.value()) {
				wrapper.registerError(new Error(paramName, String.format(
				    "Maximum value for parameter %s is %s",
				    paramName,
				    max.value())));
			}
		}
	}
}
