package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseAddressEntity;

import java.util.List;
import java.util.UUID;

public class AddressFactory {

    private static final List<City> cities = List.of(

            new City("Medellin", "Antioquia", "Colombia"),
            new City("Rionegro", "Antioquia", "Colombia"),
            new City("Carmen", "Antioquia", "Colombia"),
            new City("Rionegro", "Santander", "Colombia"),
            new City("Florida Blanca", "Santander", "Colombia"),
            new City("Barrancabermeja", "Santander", "Colombia"),
            new City("Cartagena", "Bolivar", "Colombia"),
            new City("El Carmen", "Bolivar", "Colombia"),
            new City("Cordoba", "Bolivar", "Colombia"),
            new City("Chamo", "Bolivar", "Venezuela"),
            new City("Veneman", "Bolivar", "Venezuela"),
            new City("Cordoba", "Bolivar", "Venezuela"),
            new City("Rionegro", "Santander", "Venezuela"),
            new City("Chamorral", "Santander", "Venezuela"),
            new City("Caracas", "Santander", "Venezuela")
    );

    public static WarehouseAddressEntity createRandomEntity(int index) {
        return WarehouseAddressEntity.builder()
                .id(UUID.randomUUID())
                .country(cities.get(index).countryName)
                .state(cities.get(index).stateName)
                .city(cities.get(index).cityName)
                .address(generateRandomAddress())
                .additional(generateRandomAdditional())
                .zipCode(generateRandomZipCode())
                .latitude(generateRandomLatitude())
                .longitude(generateRandomLongitude())
                .build();
    }

    private static String generateRandomAddress() {
        return "123 Random St";
    }

    private static String generateRandomAdditional() {
        return "Additional Info";
    }

    private static String generateRandomZipCode() {
        return "12345";
    }

    private static Double generateRandomLatitude() {
        return -34.6037;
    }

    private static Double generateRandomLongitude() {
        return -58.3816;
    }


    private static class City {
        protected String cityName;
        protected String stateName;
        protected String countryName;

        public City(String cityName, String stateName, String countryName) {
            this.cityName = cityName;
            this.stateName = stateName;
            this.countryName = countryName;
        }
    }

}
