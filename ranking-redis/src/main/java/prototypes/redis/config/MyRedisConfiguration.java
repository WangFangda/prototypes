package prototypes.redis.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author fangda.wang
 */
public class MyRedisConfiguration extends CachingConfigurerSupport {

	@Autowired
	private RedisProperties redisProperties;


	/* Jedis ConnectionFactory */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory()
	{
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisProperties.getHost());
		jedisConnectionFactory.setPort(redisProperties.getPort());
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}

	/* redis template definition */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate()
	{
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		return redisTemplate;
	}
}
