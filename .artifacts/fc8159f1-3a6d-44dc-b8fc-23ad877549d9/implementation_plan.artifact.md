# Plan de Implementación: Navegación entre dos pantallas

Este plan describe cómo crear un sistema de navegación simple entre dos pantallas utilizando Actividades.

## Cambios Propuestos

### Layouts

#### [MODIFY] [layout.xml](file:///Users/francisco/AndroidStudioProjects/churrosnuevos/app/src/main/res/layout/layout.xml)
Se agregará un `Button` con el ID `btn_next` para iniciar la transición.

#### [NEW] [activity_second.xml](file:///Users/francisco/AndroidStudioProjects/churrosnuevos/app/src/main/res/layout/activity_second.xml)
Se creará un nuevo layout para la segunda pantalla con un mensaje y un botón para regresar.

### Código Java

#### [NEW] [MainActivity.java](file:///Users/francisco/AndroidStudioProjects/churrosnuevos/app/src/main/java/cl/chopan/churrosnuevos/MainActivity.java)
Se creará la actividad principal que cargará `layout.xml` y manejará el click del botón.

#### [NEW] [SecondActivity.java](file:///Users/francisco/AndroidStudioProjects/churrosnuevos/app/src/main/java/cl/chopan/churrosnuevos/SecondActivity.java)
Se creará la segunda actividad que cargará `activity_second.xml`.

### Configuración

#### [MODIFY] [AndroidManifest.xml](file:///Users/francisco/AndroidStudioProjects/churrosnuevos/app/src/main/AndroidManifest.xml)
Se registrarán `MainActivity` (como actividad principal) y `SecondActivity`.

## Plan de Verificación

### Verificación Manual
1. Ejecutar la aplicación.
2. Presionar el botón en la primera pantalla.
3. Verificar que se abra la segunda pantalla.
4. Presionar el botón "Regresar" y verificar que vuelva a la primera.
