package com.codewise.hackday.application.config;

import com.codewise.hackday.business.domain.DomainPackageBase;
import com.codewise.hackday.business.domain.client.ClientConverters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackageClasses = DomainPackageBase.class)
@EntityScan(basePackageClasses = DomainPackageBase.class)
public class PersistenceConfig extends AbstractR2dbcConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PersistenceConfig.class);

    @Value("${dataSource.url}")
    private String dbUrl;

    @Value("${dataSource.username}")
    private String dbUsername;

    @Value("${dataSource.password}")
    private String dbPassword;

    @Value("${dataSource.port}")
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Flyway flyway(javax.sql.DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("db/migration")
                .load();

        flyway.migrate();

        return flyway;
    }

    @Bean
    public javax.sql.DataSource dataSource() {
        log.info("Creating data source with url {}", dbUrl);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ClientConverters.AddressReadingConverter(objectMapper));
        converters.add(new ClientConverters.AddressWritingConverter(objectMapper));
        return new R2dbcCustomConversions(getStoreConversions(), converters);
    }

    @Bean
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .host("localhost")
                .database("test")
                .username(dbUsername)
                .password(dbPassword)
                .schema("hackday") // :(
                .port(port)
                .build());
    }
}

