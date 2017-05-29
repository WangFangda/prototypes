package prototype.processing;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lombok.val;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 * @author fangda.wang
 */
@Service
public class FilterMaker {

	private String[] getValidRequestedProperties(final Set<String> requestedProperties,
												 final Set<String> knownProperties) {
		String[] childProps = requestedProperties.isEmpty()
							  ? new String[]{}
							  : knownProperties
										.stream()
										.filter(requestedProperties::contains)
										.toArray(String[]::new);

		if (ArrayUtils.isEmpty(childProps)) {
			childProps = knownProperties.toArray(new String[knownProperties.size()]);
		}

		return childProps;
	}

	public Map<String, String[]> apply(final Map<String, Set<String>> requested,
									   final Map<String, Set<String>> firstLevelProperties,
									   final Map<String, Set<String>> nonFirstLevelProperties) {

		Map<String, String[]> result = new HashMap<>();

		for (Entry<String, Set<String>> entry : firstLevelProperties.entrySet()) {
			val childProps = getValidRequestedProperties(requested.keySet(), entry.getValue());
			result.put(entry.getKey(), childProps);
		}

		for (Entry<String, Set<String>> entry : nonFirstLevelProperties.entrySet()) {

			val requestedChildProps = requested.containsKey(entry.getKey())
									  ? requested.get(entry.getKey())
									  : Collections.<String>emptySet();

			val childProps = getValidRequestedProperties(requestedChildProps, entry.getValue());
			result.put(entry.getKey(), childProps);
		}

		return result;
	}
}
