package mozart.core.pagination;

import java.util.List;

import mozart.core.exception.MozartException;

/**
 * 
 * @author Edward Fernando
 * 
 */
public interface FilterableDao {

	public <T> List<T> filter(FilterableQuery query) throws MozartException;

}
