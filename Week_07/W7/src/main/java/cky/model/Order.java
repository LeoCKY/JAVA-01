package cky.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private Integer id;

    private Integer userId;

    private Integer merchId;

    private String orderNo;

    private int status;

    private BigDecimal productAmountTotal;

    private BigDecimal orderAmountTotal;

    private BigDecimal logisticsFee;

    private Date payTime;

    private String postcode;

    private String deliveryAddr;

    private Integer version;

    private Integer createUser;

    private Date createDate;

    private String createIp;

    private Integer updateUser;

    private Date updateDate;

    private String updateIp;

    private Boolean isDel;
}