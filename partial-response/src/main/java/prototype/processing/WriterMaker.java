package prototype.processing;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Map;
import java.util.Map.Entry;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * @author fangda.wang
 */
@Service
public class WriterMaker {

	public ObjectWriter apply(final Map<String, String[]> wantedProperties) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);

		val filters = new SimpleFilterProvider();

		for (Entry<String, String[]> entry: wantedProperties.entrySet()) {
			filters.addFilter(entry.getKey(), SimpleBeanPropertyFilter.filterOutAllExcept(entry.getValue()));
		}

		return mapper.writer(filters);
	}
}
