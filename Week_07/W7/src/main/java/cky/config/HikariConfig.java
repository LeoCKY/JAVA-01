package cky.config;

import cky.enums.AllDataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("cky.mapper")
public class HikariConfig {

    public static final Logger logger = LoggerFactory.getLogger(HikariConfig.class);

    @Value("${mybatis.config-location}")
    private String configLocation;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasespackage;

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public HikariDataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("slave1")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave1")
    public HikariDataSource slave1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("slave2")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave2")
    public HikariDataSource slave2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource masterDataSource
                                        , @Qualifier("slave1") DataSource slave1DataSource
                                        , @Qualifier("slave2") DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(AllDataSourceEnum.MASTER.name(), masterDataSource);
        targetDataSources.put(AllDataSourceEnum.SLAVE1.name(), slave1DataSource);
        targetDataSources.put(AllDataSourceEnum.SLAVE2.name(), slave2DataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * @description: 配置mybatis的mapper和dao的位置
     */
    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(configLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasespackage);
        return sqlSessionFactoryBean;
    }

    /**
     * 事務管理
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
