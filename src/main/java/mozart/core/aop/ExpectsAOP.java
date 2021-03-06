package mozart.core.aop;

import javax.servlet.http.HttpServletRequest;

import mozart.core.annotation.ExpectParam;
import mozart.core.validator.ValidatorUtil;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ExpectsAOP {

	@Before("@annotation(expects) && args(request,..)")
	public void inspect(ExpectParam expects, HttpServletRequest request) throws Throwable {
		ValidatorUtil.instance().validateRequest(expects, request);
	}

}
