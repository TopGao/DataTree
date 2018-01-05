package com.jira;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TreeApplication extends SpringBootServletInitializer{
	/*
	 @Autowired
	 private Environment env;
	
	@Bean
    public DataSource dataSource() {
	    HikariConfig config = new HikariConfig();
	    config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    config.setJdbcUrl(env.getProperty("spring.datasource.url"));
	    config.setUsername(env.getProperty("spring.datasource.username"));
	    config.setPassword(env.getProperty("spring.datasource.password"));
	    return new HikariDataSource(config);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }*/
    
//    @Bean
//    public Interceptor pageHelper(){
//    	return new MybatisSpringPageInterceptor();
//    }

  /*
   *事物
   *   @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(TreeApplication.class, args);
	}
	
	/**
     * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
     * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
     */
	protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(TreeApplication.class);
    }
}
