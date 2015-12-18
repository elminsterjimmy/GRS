package com.elminster.grs.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.elminster.spring.security.dao", "com.elminster.grs.shared.db.dao" },
  entityManagerFactoryRef = "entityManagerFactoryGRS",
  transactionManagerRef = "transactionManagerGRS")
public class RepositoryConfigGRS {
  
  @Autowired  
  private JpaProperties jpaProperties;

  @Bean(name="datasourceGRS")
  @Primary
  @ConfigurationProperties(prefix = "grs.datasource")
  public DataSource customDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name="entityManagerFactoryGRS")
  @Primary
  public LocalContainerEntityManagerFactoryBean customManagerFactory(EntityManagerFactoryBuilder builder) {
    DataSource ds = customDataSource();
    return builder.dataSource(ds)
        .properties(jpaProperties.getHibernateProperties(ds))
        .packages("com.elminster.grs.shared.db.domain", "com.elminster.spring.security.domain")
        .persistenceUnit("grsPersistenceUnit")
        .build();
  }
  
  @Bean(name="transactionManagerGRS")
  @Primary
  public PlatformTransactionManager customTransactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(customManagerFactory(builder).getObject()); 
  }
}
