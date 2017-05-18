package prototypes.googleapi.logic;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prototypes.googleapi.config.GoogleApiConfiguration;
import prototypes.googleapi.enums.ResponseStatus;
import prototypes.googleapi.model.placedetails.PlaceDetailsApiResponse;
/**
 * @author fangda.wang
 */
@Service
public class GooglePlaceDetailsCaller {

	@AllArgsConstructor
	private enum Params {
		KEY("key"), PLACE_ID("placeid");
		public final String value;
	}

	@Autowired
	private GoogleApiConfiguration configuration;

	@Autowired
	private RestTemplate restTemplate;

	public PlaceDetailsApiResponse exec(String placeId) {
		val params = ImmutableMap.<String, String>builder()
							 .put(Params.KEY.value, configuration.key)
							 .put(Params.PLACE_ID.value, placeId)
							 .build();
		val response = restTemplate.getForObject(configuration.placeDetails, PlaceDetailsApiResponse.class, params);

		return ResponseStatus.OK.value.equals(response.getStatus())
			   ? response
			   : new PlaceDetailsApiResponse();
	}
}
