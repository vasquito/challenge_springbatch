![accenture](docs/images/accenture.png)

# Challenge - Java - Spring Batch  

---


Por favor generar una aplicación de tipo Spring-Bacth.

**Se requiere**:
1. Que reciba como argumento en un archivo de tipo CSV a ser procesado
2. Que se aplique alguna transformación a los datos recibidos
   2.1 Enriquecer con información presente en memoria de la aplicación
   2.2 Remover campos innecesarios
3. Insertar resultado en una tabla (sabor de BBDD a elección)

- Ejemplo de entrada (puede variarla)
```
Punto de venta, importe, cantidad, temperatura
1,322,10,6
2,17398,22,3
1,8492,895,2  
```

- Información presente en la memoria de la aplicación y utilizada para enriquecer
```
Punto de venta: 1<br>
Nombre: Santa Fe<br>
Impuesto: 0.21<br>
---
Punto de venta: 2<br>
Nombre: Tierra del Fuego<br>
Impuesto: 0.105<br>
```

A cada registro se lo podría
1. enriquecer con la información del valor del impuesto según el punto de venta
2. remover valores innecesarios (ej. temperatura)  
   Y finalmente insertarlo en una tabla para que quede

|PUNTO DE VENTA|IMPORTE|CANTIDAD|IMPUESTOS|
|---|---|---|---|
|1|322|10|67.62|
|2|17398|22|1826.79|
|3|8492|895|1784.32|

**NOTA**:
- Se requiere que esta aplicación pueda trabajar con volúmenes de archivos de tamaños elevados (> 10GB) y operar de forma eficiente contra la tabla en la que deposita la información.
- Se apreciarán:
    - scripts que ayuden a la prueba local de la aplicación.
    - utilización de Docker / Docker-compose para facilitar las pruebas en local
    - script generador de archivo voluminoso para probar la aplicación.

**Puede utilizar**:
- cualquier tipo de BBDD relacional
- cualquier versión de JAVA >= 17

---

## Contenido
- [Arquitectura - Challenge](./docs/arquitectura.md)
- [Como compilar y levantar los servicios en el entorno local?](docs/buildAndRunLocal.md)
- [Como deployar al Docker Local?](./docs/deployToDocker.md)




