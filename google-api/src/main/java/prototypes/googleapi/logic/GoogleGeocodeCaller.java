package prototypes.googleapi.logic;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prototypes.googleapi.config.GoogleApiConfiguration;
import prototypes.googleapi.enums.ResponseStatus;
import prototypes.googleapi.model.geocode.GeocodeApiResponse;
/**
 * @author fangda.wang
 */
@Service
public class GoogleGeocodeCaller {

	@AllArgsConstructor
	private enum Params {
		KEY("key"), ADDRESS("address");
		public final String value;
	}

	@Autowired
	private GoogleApiConfiguration configuration;

	@Autowired
	private RestTemplate restTemplate;

	public GeocodeApiResponse exec(String address) {
		val params = ImmutableMap.<String, String>builder()
							 .put(Params.KEY.value, configuration.key)
							 .put(Params.ADDRESS.value, address)
							 .build();

		val response = restTemplate.getForObject(configuration.geocode, GeocodeApiResponse.class, params);

		return ResponseStatus.OK.value.equals(response.getStatus())
			   ? response
			   : new GeocodeApiResponse();
	}
}
