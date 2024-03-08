package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseAddressEntity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AddressFactory {

    private static final Random random = new Random();

    private static final List<String> LATIN_AMERICAN_COUNTRIES = List.of(
            "Argentina", "Brazil", "Chile", "Colombia", "Ecuador", "Mexico", "Peru", "Venezuela"
    );

    private static final List<String> ARGENTINA_STATES = List.of(
            "Buenos Aires", "Cordoba", "Santa Fe", "Mendoza"
    );

    private static final List<String> BRAZIL_STATES = List.of(
            "Sao Paulo", "Rio de Janeiro", "Minas Gerais", "Bahia"
    );

    private static final List<String> CHILE_STATES = List.of(
            "Santiago", "Valparaiso", "Biobio", "Maule"
    );

    private static final List<String> COLOMBIA_STATES = List.of(
            "Bogota", "Antioquia", "Valle del Cauca", "Cundinamarca"
    );

    public static WarehouseAddressEntity createRandomEntity() {
        return WarehouseAddressEntity.builder()
                .id(UUID.randomUUID())
                .country(getRandomElement(LATIN_AMERICAN_COUNTRIES))
                .state(getRandomStateBasedOnCountry())
                .city(getRandomCityBasedOnCountry())
                .address(generateRandomAddress())
                .additional(generateRandomAdditional())
                .zipCode(generateRandomZipCode())
                .latitude(generateRandomLatitude())
                .longitude(generateRandomLongitude())
                .build();
    }

    private static String getRandomElement(List<String> list) {

        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private static String getRandomStateBasedOnCountry() {
        String country = getRandomElement(LATIN_AMERICAN_COUNTRIES);

        switch (country) {
            case "Argentina":
                return getRandomElement(ARGENTINA_STATES);
            case "Brazil":
                return getRandomElement(BRAZIL_STATES);
            case "Chile":
                return getRandomElement(CHILE_STATES);
            case "Colombia":
                return getRandomElement(COLOMBIA_STATES);
            default:
                return "Unknown State";
        }
    }

    private static String getRandomCityBasedOnCountry() {
        String country = getRandomElement(LATIN_AMERICAN_COUNTRIES);

        switch (country) {
            case "Argentina":
                return getRandomCityForArgentina();
            case "Brazil":
                return getRandomCityForBrazil();
            case "Chile":
                return getRandomCityForChile();
            case "Colombia":
                return getRandomCityForColombia();
            default:
                return "Unknown City";
        }
    }

    private static String getRandomCityForArgentina() {
        return getRandomElement(List.of("Buenos Aires", "Cordoba", "Rosario", "Mendoza"));
    }

    private static String getRandomCityForBrazil() {
        return getRandomElement(List.of("Sao Paulo", "Rio de Janeiro", "Salvador", "Brasilia"));
    }

    private static String getRandomCityForChile() {
        return getRandomElement(List.of("Santiago", "Valparaiso", "Concepcion", "Antofagasta"));
    }

    private static String getRandomCityForColombia() {
        return getRandomElement(List.of("Bogota", "Medellin", "Cali", "Cartagena"));
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

}
