package com.cloud.jpa.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Autowired
	private Environment env;

	@Bean
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1000);
		config.setMaxIdle(1000);
		config.setMinIdle(1);
		config.setTestOnBorrow(true);
		config.setTestWhileIdle(true);
		config.setTestOnReturn(false);
		config.setBlockWhenExhausted(true);
		config.setJmxEnabled(true);
		config.setJmxNamePrefix("jedis-pool");
		config.setNumTestsPerEvictionRun(100);
		config.setTimeBetweenEvictionRunsMillis(60000);
		config.setMinEvictableIdleTimeMillis(300000);
		config.setTimeBetweenEvictionRunsMillis(600 * 1000);
		return config;
	}

	@Bean
	@Primary
	public JedisConnectionFactory getConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setHostName(env.getProperty("redis.connection.master.url"));
		standaloneConfig.setPort(Integer.parseInt(env.getProperty("redis.connection.master.port")));
		String password = env.getProperty("redis.connection.master.password");
		if(StringUtils.isNotBlank(password)){
			standaloneConfig.setPassword(RedisPassword.of(password));
		}

		//获得默认的连接池构造
		//这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
		//我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
		//修改我们的连接池配置
		jpcf.poolConfig(getRedisConfig());
		//通过构造器来构造jedis客户端配置
		JedisClientConfiguration jedisClientConfiguration = jpcf.build();

		JedisConnectionFactory factory = new JedisConnectionFactory(standaloneConfig,jedisClientConfiguration);
		return factory;
	}

	//	@Bean
	//	public RedisTemplate<?, ?> getRedisTemplate(JedisConnectionFactory connectionFactory, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
	//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
	//		redisTemplate.setConnectionFactory(connectionFactory);
	//		redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
	//		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	//		redisTemplate.setKeySerializer(stringRedisSerializer);
	//		redisTemplate.setHashKeySerializer(stringRedisSerializer);
	//		return redisTemplate;
	//	}


//	@Bean
//    RedisTemplate<String, Integer> intRedisTemplate(
//			JedisConnectionFactory connectionFactory) {
//		RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<String, Integer>();
//		redisTemplate.setConnectionFactory(connectionFactory);
//		return redisTemplate;
//	}


//	@Bean
//    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(
//			ObjectMapper objectMapper) {
//		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
//				Object.class);
//		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//		return jackson2JsonRedisSerializer;
//	}

}
