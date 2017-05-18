package prototypes.googleapi.model.geocode;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author fangda.wang
 */
@Data
@Getter
@Setter
public class Place {

	private String formatted_address;
	private GeoLocation geometry;
	private List<String> types;
	private String place_id;
}
