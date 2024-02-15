# Consulta de bodega

para la lectura de las bodegas se tienen las siguientes condiciones

<p><span style="color: green;">TODO: mostrar la respuesta de los metodos</span></p>

## Consulta de todas las bodegas

Esta forma de consulta retorna todas las bodegas con paginación implementada.

```
HTTP Method: GET
Access route: /warehouse
Status Code: 200 OK
```

Un ejemplo de lo que retorna este método:

```json
[
    {
        "warehouseUniquePropertyIdentifier": "01-01-0095-0012-000",
        "maxCapacity": 15,
        "availableSpace": 5,
        "city": "Rionegro",
        "addres": "Cr XX # 20"
    },
    {
        "warehouseUniquePropertyIdentifier": "01-01-0095-0012-001",
        "maxCapacity": 15,
        "availableSpace": 5,
        "city": "Rionegro",
        "addres": "Cr XX # 21"
    },
]
```

## Consulta de bodegas por ubicación

Esta forma de consulta obtiene una lista de bodegas en base a distintos filtros:
- Pais: Opcional
- Estado: Opcional
- Ciudad: Opcional

```
HTTP Method: GET
Access route: /warehouse?country=optionalCountry&state=optionalState&city=optionalCity
Status Code: 200 OK
```

Es importante mecionar que si el atributo no se desea establecer simplemente se omite tanto el `query param` como el `value`.

El valor que retorna este método es:

```json
[
    {
        "warehouseUniquePropertyIdentifier": "01-01-0095-0012-000",
        "maxCapacity": 15,
        "availableSpace": 5,
        "city": "Rionegro",
        "addres": "Cr XX # 20"
    },
    {
        "warehouseUniquePropertyIdentifier": "01-01-0095-0012-001",
        "maxCapacity": 15,
        "availableSpace": 5,
        "city": "Rionegro",
        "address": "Cr XX # 21"
    },
]
```

## Consultar bodega por UniquePropertyIdentifier

Esta forma de consulta obtiene el detalle de una bodega mediante el `id` el cual puede ser obtenido de la lista de de bodegas.

```
HTTP Method: GET
Access route: /warehouse/{warehouse-unique-property-identifier}
Status Code: 200 OK
```

Lo que retorna este método es lo siguiente:

```json
{
    "warehouseUniquePropertyIdentifier": "01-01-0095-0012-000",
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
    "maxCapacity": 15,
    "availableSpace": 5,
}
```
