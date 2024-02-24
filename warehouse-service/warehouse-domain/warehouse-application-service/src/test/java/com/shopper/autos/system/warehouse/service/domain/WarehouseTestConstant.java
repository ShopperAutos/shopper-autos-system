package com.shopper.autos.system.warehouse.service.domain;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class WarehouseTestConstant {

    public static final UUID WAREHOUSE_ID = UUID.fromString("b8ff505a-d4d8-11ed-afa1-0242ac120002");
    public static final String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER = "111-222-333-444";
    public static final String WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER = "000-222-333-444";
    public static final String COUNTRY = "Colombia";
    public static final String STATE = "Antioquia";
    public static final String CITY = "Rionegro";
    public static final String ADDRESS = "Vereda San Juan Finca 100";
    public static final String ZIP_CODE = "054040";
    public static final String ADDITIONAL = "Sector mara";
    public static final Double LATITUDE = 20.5;
    public static final Double LONGITUDE = 10.5;
    public static final Integer MAX_CAPACITY = 27;
    public static final Integer AVAILABLE_SPACE = 27;
    public static final Integer INVALID_AVAILABLE_SPACE = 30;

    public static final Integer PAGE = 0;
    public static final Integer SIZE = 1;
    public static final List<String> FIELDS = Arrays.asList("country", "state", "city");
    public static final Integer TOTAL_RESULT = 1;

}
