package mozart.core.validator;

import java.lang.annotation.Annotation;


import mozart.core.annotation.Pattern;
import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;

public class PatternValidator extends Validator {

	@Override
	public void validate(ErrorWrapper wrapper, Annotation annot, String paramName, String value) throws Exception {
		Pattern pattern = (Pattern) annot;

		boolean result = java.util.regex.Pattern
		    .compile(pattern.pattern())
		    .matcher(value)
		    .matches();

		if (!result) {
			wrapper.registerError(new Error(paramName, String.format(
			    "Parameter is not match with specified patter (%)",
			    pattern.pattern())));
		}
	}
}
