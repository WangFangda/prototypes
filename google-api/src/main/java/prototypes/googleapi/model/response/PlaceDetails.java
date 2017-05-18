package prototypes.googleapi.model.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import prototypes.googleapi.model.geocode.Location;
/**
 * @author fangda.wang
 */
@JsonInclude(Include.NON_EMPTY)
@Data
@Getter
@Setter
public class PlaceDetails {

	private String name;
	private String formatted_address;
	private String international_phone_number;
	private List<String> opening_hours;
	private Location location;
	private List<String> types;
}
