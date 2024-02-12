# Creacion de bodega

para la creación de bodega se debe tener en cuenta la siguiente estructura:

```
HTTP Method: POST
Access route: /warehouse
Status Code: 201 CREATED
```

A continuación se muestra lo que debe llevar en el body del request:

```json
{
    "warehouseUniqueIdentifier": "01-01-0095-0012-000",
    "locationAddress": {
        "country": "Colombia",
        "state": "Antioquia",
        "city": "Rionegro",
        "address": "Vereda llanogrande sector infonavi",
        "additional": "Vereda llanogrande gualandai",
        "zipCode": "054040",
        "coordinate": {
            "latitude": 20.00,
            "longitude": -20.00
        }
    },
    "maxVehicleCapacity": 15
}
```

Se puede observar que el atributo ``` availableSpaceVehicleSpaces ``` no está presente ya que al momento de crear una bodega esta aun no está ocupada.