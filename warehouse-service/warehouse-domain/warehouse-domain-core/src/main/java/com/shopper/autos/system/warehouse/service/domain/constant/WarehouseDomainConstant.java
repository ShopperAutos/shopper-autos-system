package com.shopper.autos.system.warehouse.service.domain.constant;

import com.shopper.autos.system.domain.constant.DomainConstant;

public class WarehouseDomainConstant {


    public static final String WAREHOUSE_WRONG_STATE_APPROVAL = DomainConstant.WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.APPROVAL;
    public static final String WAREHOUSE_WRONG_STATE_REJECTION = DomainConstant.WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.REJECTION;
    public static final String WAREHOUSE_WRONG_STATE_CANCELLATION = DomainConstant.WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.CANCELLATION;
    public static final String WAREHOUSE_IN_USE = DomainConstant.WAREHOUSE + "in use, " + DomainConstant.WRONG_STATE + DomainConstant.CANCELLATION;
    public static final String WAREHOUSE_INVALID_AVAILABLE_SPACE = "Invalid value for quantity of available space";

    public static final String UNIQUE_PROPERTY_IDENTIFIER = "unique property identifier ";
    public static final String UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_NULL = DomainConstant.WAREHOUSE + UNIQUE_PROPERTY_IDENTIFIER + DomainConstant.CANNOT_BE_NULL;
    public static final String UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_EMPTY = DomainConstant.WAREHOUSE + UNIQUE_PROPERTY_IDENTIFIER + DomainConstant.CANNOT_BE_EMPTY;
    public static final String WAREHOUSE_WRONG_STATE_INITIALIZATION = DomainConstant.WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.INITIALIZATION;
    public static final String WAREHOUSE_INITIALIZED = "%s";
    public static final String WAREHOUSE_APPROVED = "%s";
    public static final String WAREHOUSE_REJECTED = "%s";
    public static final String WAREHOUSE_CANCELLED = "%s";
}
