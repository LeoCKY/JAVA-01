package cky.annorations;


import cky.enums.AllDataSourceEnum;

/**
 * @Description: 設置數據源 優先級：1.方法註解 2.類註解 3.根據包路徑設置的數據源
 */
public @interface ChooseDataSource {

    AllDataSourceEnum dataSource() default AllDataSourceEnum.MASTER;

}
