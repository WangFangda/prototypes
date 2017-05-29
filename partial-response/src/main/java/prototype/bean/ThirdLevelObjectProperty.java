package prototype.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fangda.wang
 */
@Data
@AllArgsConstructor
@JsonFilter(ThirdLevelObjectProperty.ID)
public class ThirdLevelObjectProperty {

	public static final String ID = "subdetails";

	private String code;
	private String name;
	private BigDecimal price;
}
