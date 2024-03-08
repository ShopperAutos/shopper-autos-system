package com.shopper.autos.system.warehouse.service.domain.constant;

import com.shopper.autos.system.domain.constant.DomainConstant;

public class WarehouseDomainConstant {

    public static final String WAREHOUSE = "Warehouse ";
    public static final String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER = "unique property identifier";
    public static final String UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_NULL = WAREHOUSE + WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER + DomainConstant.CANNOT_BE_NULL;
    public static final String UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_EMPTY = WAREHOUSE + WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER + DomainConstant.CANNOT_BE_EMPTY;


    public static final String WAREHOUSE_CREATION_SUCCESS = WAREHOUSE + "created successfully";
    public static final String WAREHOUSE_INVALID_AVAILABLE_SPACE = DomainConstant.INVALID_VALUE + "quantity of available spaces";
    public static final String WAREHOUSE_NOT_FOUND = WAREHOUSE + DomainConstant.NOT_FOUND;

    public static final String WAREHOUSE_WRONG_STATE_INITIALIZATION = WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.INITIALIZATION;
    public static final String WAREHOUSE_WRONG_STATE_APPROVAL = WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.APPROVAL;
    public static final String WAREHOUSE_WRONG_STATE_REJECTION = WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.REJECTION;
    public static final String WAREHOUSE_WRONG_STATE_CANCELLATION = WAREHOUSE + DomainConstant.WRONG_STATE + DomainConstant.CANCELLATION;


    public static final String WAREHOUSE_INITIALIZED = WAREHOUSE + DomainConstant.WITH_ID + DomainConstant.INITIALIZED;
    public static final String WAREHOUSE_APPROVED = WAREHOUSE + DomainConstant.WITH_ID + DomainConstant.APPROVED;
    public static final String WAREHOUSE_REJECTED = WAREHOUSE + DomainConstant.WITH_ID + DomainConstant.REJECTED;
    public static final String WAREHOUSE_CANCELLED = WAREHOUSE + DomainConstant.WITH_ID + DomainConstant.CANCELLED;


    public static final String WAREHOUSE_AVAILABLE_SPACE_UPDATED = "";
}
