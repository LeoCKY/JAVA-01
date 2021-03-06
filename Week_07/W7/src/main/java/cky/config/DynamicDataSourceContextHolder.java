package cky.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {

    public static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static final ThreadLocal<String> DATA_SOURCE_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 設置數據源
     */
    public static void setDateSourceType(String dateSourceType) {
        logger.info("設置數據源={}", dateSourceType);
        DATA_SOURCE_CONTEXT_HOLDER.set(dateSourceType);
    }

    /**
     * 獲得數據源
     */
    public static String getDateSourceType() {
        return DATA_SOURCE_CONTEXT_HOLDER.get();
    }

    /**
     * 清空數據源
     */
    public static void clearDateSourceType() {
        DATA_SOURCE_CONTEXT_HOLDER.remove();
    }
}
