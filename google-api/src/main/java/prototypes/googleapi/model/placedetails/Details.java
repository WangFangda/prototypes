package prototypes.googleapi.model.placedetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author fangda.wang
 */
@Data
@Getter
@Setter
public class Details {
	private String place_id;
	private String international_phone_number;
	private String name;
	private OpeningText opening_hours;
}
