package cky.annotations;


import cky.enums.AllDataSourceEnum;

import java.lang.annotation.*;

/**
 * @Description: 設置數據源 優先級：1.方法註解 2.類註解 3.根據包路徑設置的數據源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {

    AllDataSourceEnum dataSource() default AllDataSourceEnum.SLAVE1;
}
