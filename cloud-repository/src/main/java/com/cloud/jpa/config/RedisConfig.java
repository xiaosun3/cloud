package com.cloud.jpa.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;

import java.util.*;

@Configuration
public class RedisConfig {

	@Autowired
	private Environment env;

//	@Bean
//	public JedisCluster getJedisCluster(JedisPoolConfig jedisPoolConfig){
//		String[] nodes = env.getProperty("redis_cluster_nodes").split(",");
//		String password = env.getProperty("redis_cluster_password");
//		Set<HostAndPort> nodeSet = new HashSet<>();
//		//分割出集群点
//		for(String node : nodes){
//			String[] hp = node.split(":");
//			nodeSet.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
//		}
//		//maxAttempts 最大重试次数
//		JedisCluster jedisCluster = new JedisCluster(nodeSet,20000,5000,3,password, jedisPoolConfig);
//		return jedisCluster;
//	}

//	@Bean
//	public RedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//		RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
//		String[] nodes = env.getProperty("redis_cluster_nodes").split(",");
//		String password = env.getProperty("redis_cluster_password");
//
//		clusterConfiguration.setMaxRedirects(8);
//		clusterConfiguration.setPassword(password);
//		List<RedisNode> nodeList = new ArrayList<>();
//		//分割出集群节点
//		for(String node : nodes) {
//			String[] hp = node.split(":");
//			nodeList.add(new RedisNode(hp[0], Integer.parseInt(hp[1])));
//		}
//		clusterConfiguration.setClusterNodes(nodeList);
//
//		return new JedisConnectionFactory(clusterConfiguration, jedisPoolConfig);
//	}

	@Bean
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		//#最大连接数
		config.setMaxTotal(1000);
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
		config.setMaxIdle(1000);
		//控制一个pool最少有多少个状态为idle(空闲的)的jedis实例
		config.setMinIdle(15);
		//当资源池用尽后，调用者是否要等待。只有当为true时，下面的maxWaitMillis才会生效
		config.setBlockWhenExhausted(true);
		//当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
		config.setMaxWaitMillis(10000);
		//向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除
		config.setTestOnBorrow(true);
		//向资源池归还连接时是否做连接有效性检测(ping)，无效连接会被移除
		config.setTestOnReturn(true);
		//Idle时进行连接扫描
		config.setTestWhileIdle(true);
		//表示idle object evitor每次扫描的最多的对象数
		config.setNumTestsPerEvictionRun(10);
		//表示idle object evitor两次扫描之间要sleep的毫秒数  设置检测线程的运行时间间隔
		config.setTimeBetweenEvictionRunsMillis(30000);
		//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
		config.setMinEvictableIdleTimeMillis(5 * 60000);

		return config;
	}


//	/**
//	 * 设置数据存入redis 的序列化方式
//	 * </br>redisTemplate序列化默认使用的jdkSerializeable,存储二进制字节码,导致key会出现乱码，所以自定义
//	 * 序列化类
//	 *
//	 * @paramredisConnectionFactory
//	 */
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//		redisTemplate.afterPropertiesSet();
//
//		return redisTemplate;
//	}


//	@Bean
//	public JedisPool redisConnectionFactory() {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(1000);
//		config.setMaxIdle(1000);
//		config.setMinIdle(1);
//		config.setTestOnBorrow(true);
//		config.setTestWhileIdle(true);
//		config.setTestOnReturn(false);
//		config.setBlockWhenExhausted(true);
//		config.setJmxEnabled(true);
//		config.setJmxNamePrefix("jedis-pool");
//		config.setNumTestsPerEvictionRun(100);
//		config.setTimeBetweenEvictionRunsMillis(60000);
//		config.setMinEvictableIdleTimeMillis(300000);
//		config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
//		config.setTimeBetweenEvictionRunsMillis(600 * 1000);
//
//		String host = "127.0.0.1";
//		Integer port = 6379;
//
//		String password = env.getProperty("redis.connection.master.password");
//
//		if (password == null) {
//			return new JedisPool(config, host, port);
//		} else {
//			return new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password);
//		}
//	}

}
