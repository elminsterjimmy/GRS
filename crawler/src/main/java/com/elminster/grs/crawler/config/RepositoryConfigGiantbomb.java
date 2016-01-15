package com.elminster.grs.crawler.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.elminster.grs.giantbomb.dao" },
  entityManagerFactoryRef = "entityManagerFactoryGiantbomb",
  transactionManagerRef = "transactionManagerGiantbomb")
public class RepositoryConfigGiantbomb {

  @Autowired
  private JpaProperties jpaProperties;

  @Bean(name="datasourceGiantbomb")
  @ConfigurationProperties(prefix = "giantbomb.datasource")
  public DataSource customDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name="entityManagerFactoryGiantbomb")
  public LocalContainerEntityManagerFactoryBean customManagerFactory(EntityManagerFactoryBuilder builder) {
    DataSource ds = customDataSource();
    return builder.dataSource(ds)
        .properties(jpaProperties.getHibernateProperties(ds))
        .packages("com.elminster.grs.giantbomb.ds")
        .persistenceUnit("giantbombPersistenceUnit")
        .build();
  }
  
  @Bean(name="transactionManagerGiantbomb")
  public PlatformTransactionManager customTransactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(customManagerFactory(builder).getObject()); 
  }
}
