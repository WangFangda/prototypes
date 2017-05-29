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
@JsonFilter(SecondLevelObjectProperty.ID)
public class SecondLevelObjectProperty {

	public static final String ID = "details";

	private String type;
	private Integer number;
	private List<ThirdLevelObjectProperty> subdetails;
}
