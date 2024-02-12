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

## Consultar bodega por id

Esta forma de consulta obtiene el detalle de una bodega mediante el `id` el cual puede ser obtenido de la lista de de bodegas.

```
HTTP Method: GET
Access route: /warehouse/{warehouse-id}
Status Code: 200 OK
```

## Consultar bodega por su identificador único de prédio

Esta forma de consulta obtiene el detalle de una bodega mediate el `warehouseUniqueIdentifier` el cual es establecido por el usuario al momento de crear la bodega.

```
HTTP Method: GET
Access route: /warehouse/{warehouse-unique-identifier}/WID
Status Code: 200 OK
```