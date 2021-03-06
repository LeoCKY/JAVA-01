package cky.aop;

import cky.annotations.ReadOnly;
import cky.config.DynamicDataSourceContextHolder;
import cky.enums.AllDataSourceEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order(1)
@Component
public class DataSourceAop {

    @Value("${spring.datasource.hikari.master.default-package}")
    private String masterPackage;
    @Value("${spring.datasource.hikari.slave1.default-package}")
    private String secondPackage;

    public static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Pointcut("execution(* cky.dao..*.*(..)) || @annotation(cky.annotations.ReadOnly)")
    public void switchDataSource() {
    }

    @Before("switchDataSource()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ReadOnly chooseDataSource = method.getAnnotation(ReadOnly.class);//獲取方法上的註解
        if (chooseDataSource == null) {
            chooseDataSource = joinPoint.getTarget().getClass().getAnnotation(ReadOnly.class);//獲取類上面的註解
            if (chooseDataSource == null) {
                String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
                if (declaringTypeName.startsWith(masterPackage)) {
                    DynamicDataSourceContextHolder.setDateSourceType(AllDataSourceEnum.MASTER.name());
                    logger.info("使用包默認數據源，包={}，數據源={}", masterPackage, AllDataSourceEnum.MASTER.name());
                } else if (declaringTypeName.startsWith(secondPackage)) {
                    DynamicDataSourceContextHolder.setDateSourceType(AllDataSourceEnum.SLAVE1.name());
                    logger.info("使用包默認數據源，包={}，數據源={}", secondPackage, AllDataSourceEnum.SLAVE1.name());
                } else {
                    DynamicDataSourceContextHolder.setDateSourceType(AllDataSourceEnum.MASTER.name());
                    logger.info("使用包默認數據源，包={}，數據源={}", masterPackage, AllDataSourceEnum.MASTER.name());
                }
                return;
            } else {
                logger.info("類註解生效，切換數據源={}", chooseDataSource.dataSource().name());
            }
        } else {
            logger.info("方法註解生效，切換數據源={}", chooseDataSource.dataSource().name());
        }
    }

    @After("switchDataSource()")
    public void after(JoinPoint point) {
        //清理掉當前設置的數據源，讓默認的數據源不受影響
        DynamicDataSourceContextHolder.clearDateSourceType();
    }


}
