package prototypes.googleapi.util;
import org.springframework.util.StringUtils;
import prototypes.googleapi.model.geocode.GeocodeApiResponse;
/**
 * @author fangda.wang
 */
public final class ResponseUtils {
	public static boolean isEmpty(final GeocodeApiResponse underTest) {
		if (null == underTest) return true;
		return StringUtils.isEmpty(underTest.getStatus()) && null == underTest.getResults();
	}
}
