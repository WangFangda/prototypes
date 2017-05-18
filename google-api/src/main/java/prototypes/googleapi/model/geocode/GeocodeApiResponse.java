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
public class GeocodeApiResponse {
	private List<Place> results;
	private String status;
}
