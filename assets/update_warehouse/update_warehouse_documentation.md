# Modificación de bodega

para la actualización de espacios disponibles de bodega se debe tener en cuenta la siguiente estructura:

```
HTTP METHOD: PUT
Access route: /warehouse/{warehouse-id}
Status Code: 204 NO CONTENT
```
A continuación se muestra lo que debe llevar en el body del request:

```json
{
    "availableSpaceVehicleSpaces": 3
}
```

Se puede observar que el atributo ``` availableSpaceVehicleSpaces ``` es el único que puede cambiar a lo largo del tiempo, el resto son atributos que no pueden ser modificables.