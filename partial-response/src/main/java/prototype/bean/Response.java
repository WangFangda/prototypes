package prototype.bean;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fangda.wang
 */
@Data
@AllArgsConstructor
@JsonFilter(Response.ID)
public class Response {

	public static final String ID = "response";

	private String nation;
	private FirstLevelObjectProperty info;
}
