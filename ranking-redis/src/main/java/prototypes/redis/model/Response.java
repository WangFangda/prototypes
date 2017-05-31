package prototypes.redis.model;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
/**
 * @author fangda.wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private String type;
	private Set<TypedTuple<String>> data;
}
