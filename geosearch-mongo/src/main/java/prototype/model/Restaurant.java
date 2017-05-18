package prototype.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Defines data record "restaurant".
 *
 * @see <a href="https://docs.mongodb.com/v3.2/core/databases-and-collections/">what is "collection" in MongoDB</a>
 * @author fangda.wang
 */
@Data
@AllArgsConstructor
@Document(collection = "restaurant")
public class Restaurant {
	@Id
	private long id;

	@Indexed
	private String name;

	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D)
	private Point location;
}
