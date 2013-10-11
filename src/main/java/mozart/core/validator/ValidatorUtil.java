package mozart.core.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mozart.core.annotation.Date;
import mozart.core.annotation.Email;
import mozart.core.annotation.ExpectParam;
import mozart.core.annotation.HttpParam;
import mozart.core.annotation.IntegerOnly;
import mozart.core.annotation.Length;
import mozart.core.annotation.Max;
import mozart.core.annotation.Min;
import mozart.core.annotation.NotEmpty;
import mozart.core.annotation.NotNull;
import mozart.core.annotation.NumberOnly;
import mozart.core.annotation.Pattern;
import mozart.core.exception.Error;
import mozart.core.exception.ErrorWrapper;
import mozart.core.exception.MozartException;

import com.google.common.collect.Maps;

public class ValidatorUtil {

	private static ValidatorUtil instance;
	public static final String REQUIRED_PARAM_MESSAGE = "Required parameter %s";

	private static Map<Class<? extends Annotation>, Validator> validators = Maps.newHashMap();
	static {
		validators.put(Email.class, new EmailValidator());
		validators.put(IntegerOnly.class, new IntegerOnlyValidator());
		validators.put(Length.class, new LengthValidator());
		validators.put(Max.class, new MaxValidator());
		validators.put(Min.class, new MinValidator());
		validators.put(NotEmpty.class, new NotEmptyValidator());
		validators.put(NotNull.class, new NotNullValidator());
		validators.put(NumberOnly.class, new NumberOnlyValidator());
		validators.put(Pattern.class, new PatternValidator());
		validators.put(Date.class, new DateValidator());
	}

	private ValidatorUtil() {
	}

	public void validateRequest(ExpectParam expectParam, HttpServletRequest request)
	        throws Exception {

		ErrorWrapper wrapper = new ErrorWrapper();

		// Validate mandatory parameters
		if (!expectParam.value().equals(ExpectParam.Ignore.class)) {
			for (Field field : expectParam.value().getDeclaredFields()) {

				HttpParam httpParam = field.getAnnotation(HttpParam.class);
				if (httpParam == null) {
					continue;
				}

				String httpParamValue = httpParam.value();
				String parameterName = httpParamValue.isEmpty() ? field.getName() : httpParamValue;
				String parameterValue = request.getParameter(parameterName);

				if (parameterValue == null) {
					wrapper.registerError(new Error(parameterName, String.format(
					    REQUIRED_PARAM_MESSAGE,
					    parameterName)));
				} else {
					// If not null, then check for annotation
					for (Annotation annot : field.getAnnotations()) {
						if (validators.containsKey(annot.annotationType())) {
							validators.get(annot.annotationType()).validate(
							    wrapper,
							    annot,
							    parameterName,
							    parameterValue);
						}
					}
				}
			}
		}
		// Validate optional parameteres
		for (String opt : expectParam.optional()) {
			String parameterValue = request.getParameter(opt);
			if (!opt.isEmpty() && parameterValue == null) {
				wrapper.registerError(new Error(opt, String.format(REQUIRED_PARAM_MESSAGE, opt)));
			}
		}

		wrapper.setHttpMethod(request.getMethod());
		wrapper.setPathLocation(request.getPathInfo());

		if (wrapper.hasError()) {
			throw new MozartException(wrapper);
		}

	}

	public void registerValidator(Class<? extends Annotation> annotationClass, Validator validator) {
		validators.put(annotationClass, validator);
	}

	public static ValidatorUtil instance() {
		if (instance == null) {
			instance = new ValidatorUtil();
		}
		return instance;
	}

}
