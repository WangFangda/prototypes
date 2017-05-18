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
public class PlaceDetailsApiResponse {
	private Details result;
	private String status;
}
