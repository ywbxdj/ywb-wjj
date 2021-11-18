package com.zeiet.common.config;

import com.zeiet.common.repository.impl.BaseJpaRepositoryImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class, basePackages = "com.zeiet.**.repository")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    private String AOP_POINTCUT_EXPRESSION = "execution (* com..service.impl.*.*(..))";

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    JpaProperties jpaProperties;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    MybatisProperties mybatisProperties;

    /**
     * Jpa事务管理，一定要用JpaTransactionManager
     * @param entityManagerFactory
     * @return
     */
    @Bean
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager =  new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        String[] locations = mybatisProperties.getMapperLocations();
        Resource[] resources = null;
        for (String location : locations) {
            if (null == resources) {
                resources = getResources(resourceLoader, location);
            } else {
                resources = ArrayUtils.addAll(resources, resources);
            }
        }
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));

        SqlSessionFactory sqlSessionFactory;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            // 打印日志, 否则spring容器会抑制bean创建的异常,只打印warn的不完整的信息,不利于开发调试
            logger.error("SqlSessionFactory创建失败." + e.getMessage(), e);
            throw e;
        }
        return sqlSessionFactory;
    }

    @Bean
    public TransactionInterceptor txAdvice(TransactionManager transactionManager) {
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("doCrud", txAttr_REQUIRED);
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("start*", txAttr_REQUIRED);
        source.addTransactionalMethod("complete*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("reject*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("create*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("import*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }

    private Resource[] getResources(ResourceLoader resourceLoader, String packagePath) throws IOException {
        ResourcePatternResolver resourceResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        return resourceResolver.getResources(packagePath);
    }
}
