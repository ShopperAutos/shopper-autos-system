package com.shopper.autos.system.warehouse.service.infrastructure.entity;

import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseEntity {

    @Id
    private UUID id;
    @Column(name = "warehouse_unique_property_identifier", unique = true)
    private String warehouseUniquePropertyIdentifier;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private WarehouseAddressEntity address;
    private List<String> vehicles;
    @Column(name = "max_capacity")
    private Integer maxCapacity;
    @Column(name = "available_space")
    private Integer availableSpace;
    @Enumerated(EnumType.STRING)
    private WarehouseStatus warehouseStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(warehouseUniquePropertyIdentifier, that.warehouseUniquePropertyIdentifier) && Objects.equals(address, that.address) && Objects.equals(maxCapacity, that.maxCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouseUniquePropertyIdentifier, address, maxCapacity);
    }
}
