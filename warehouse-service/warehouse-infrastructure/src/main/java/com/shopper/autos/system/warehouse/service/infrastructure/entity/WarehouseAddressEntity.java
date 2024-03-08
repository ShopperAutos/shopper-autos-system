package com.shopper.autos.system.warehouse.service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_warehouse_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseAddressEntity {
    @Id
    private UUID id;
    /*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouse;*/
    private String country;
    private String state;
    private String city;
    private String address;
    private String additional;
    @Column(name = "zip_code")
    private String zipCode;
    private Double latitude;
    private Double longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseAddressEntity that = (WarehouseAddressEntity) o;
        return Objects.equals(country, that.country) && Objects.equals(state, that.state) && Objects.equals(city, that.city) && Objects.equals(address, that.address) && Objects.equals(zipCode, that.zipCode) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, city, address, zipCode, latitude, longitude);
    }
}
