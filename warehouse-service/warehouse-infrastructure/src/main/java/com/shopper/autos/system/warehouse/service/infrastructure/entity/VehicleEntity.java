package com.shopper.autos.system.warehouse.service.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tbl_vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {
    @Id
    private UUID uuid;
}
