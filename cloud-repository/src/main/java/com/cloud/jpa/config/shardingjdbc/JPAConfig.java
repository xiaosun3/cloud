//package com.cloud.jpa.config.shardingjdbc;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import io.shardingsphere.core.api.ShardingDataSourceFactory;
//import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
//import io.shardingsphere.core.api.config.TableRuleConfiguration;
//import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.beans.PropertyVetoException;
//import java.sql.SQLException;
//import java.util.*;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.cloud.jpa.repository")
//@EnableTransactionManagement
//public class JPAConfig {
//
//    @Autowired
//    private Environment env;
//
//    public static final List<String> TABLES = new ArrayList<String>();
//
//    static {
//        TABLES.add("neo_health_check_report");
//
//    }
//
//    private String getActiveProfile() {
//        String[] profiles = env.getActiveProfiles();
//        if (profiles.length != 0) {
//            return profiles[0];
//        } else {
//            return null;
//        }
//    }
//
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.wondersgroup.healthcloud.jpa.entity");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }
//
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        return properties;
//    }
//
//    @Bean(name = "transactionManager")
//    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactory.getObject());
//        return manager;
//    }
//
//    @Bean(name = "hcDataSource")
//    @Profile("!build-test")
//    public DataSource dataSource() throws PropertyVetoException {
//        DataSource shardingDataSource = null;
//        try {
//            shardingDataSource = getShardingDataSource();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return shardingDataSource;
//    }
//
//    DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().addAll(getOrderTableRuleConfiguration());
//        shardingRuleConfig.getBindingTableGroups().addAll(TABLES);
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("identity", new ModuloTableShardingAlgorithm()));
//        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new HashMap<String, Object>(), new Properties());
//    }
//
//    List<TableRuleConfiguration> getOrderTableRuleConfiguration() {
//        List<TableRuleConfiguration> config = new ArrayList<>();
//        for (int i = 0; i < TABLES.size(); i++) {
//            TableRuleConfiguration result = new TableRuleConfiguration();
//            String table = TABLES.get(i);
//            result.setLogicTable(table);
//            result.setActualDataNodes("ds." + table + "_${0..10}");
//            config.add(result);
//        }
//        return config;
//    }
//
//    DataSource getDataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(env.getProperty("mysql.connection.healthrecord.url"));
//        dataSource.setUsername(env.getProperty("mysql.connection.healthrecord.username"));
//        dataSource.setPassword(env.getProperty("mysql.connection.healthrecord.password"));
//        dataSource.setInitialSize(10);
//        dataSource.setMinIdle(1);
//        dataSource.setMaxActive(2000);
//        dataSource.setMaxWait(60000L);
//        dataSource.setTimeBetweenEvictionRunsMillis(60000L);
//        dataSource.setMinEvictableIdleTimeMillis(300000L);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestOnReturn(false);
//        return dataSource;
//    }
//
//
//    Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
//        result.put("ds", getDataSource());
//        return result;
//    }
//
//    @Bean(name = "dataSource")
//    @Profile("build-test")
//    public DataSource unitTestDataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.H2).addScript("db/init.sql").build();
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}
