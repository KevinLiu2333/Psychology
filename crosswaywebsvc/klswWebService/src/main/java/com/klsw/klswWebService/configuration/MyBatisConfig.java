package com.klsw.klswWebService.configuration;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.*/

/**
 * MyBatis基础配置
 *
 */
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	
	@Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;
	
//	@Autowired
//    @Qualifier("dataSource2")
//    private DataSource dataSource2;
	
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactoryBean1() {
        Configuration configuration = new Configuration();
        configuration.setCallSettersOnNulls(true);
        //防止null值不返回字段
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1);
        bean.setTypeAliasesPackage("com.klsw.apiCommon.api.model");
        bean.setConfiguration(configuration);
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/api/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//    @Bean(name = "sqlSessionFactory2")
//    public SqlSessionFactory sqlSessionFactoryBean2() {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource2);
//        bean.setTypeAliasesPackage("com.klsw.apiCommon.api.model1");
//
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//
//        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
//
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            bean.setMapperLocations(resolver.getResources("classpath:mapper1/api/*.xml"));
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    
    
    
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource1);
    }
}