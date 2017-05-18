package prototypes.googleapi.model.geocode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author fangda.wang
 */
@Data
@Getter
@Setter
public class Location {
	private double lat;
	private double lng;
}
