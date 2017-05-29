package prototype.bean;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @author fangda.wang
 */
@Data
@AllArgsConstructor
@JsonFilter(FirstLevelObjectProperty.ID)
public class FirstLevelObjectProperty {

	public static final String ID = "info";

	private String state;
	private List<SecondLevelObjectProperty> details;
}
