# Android Clean Architecture (App de Recetas)  
A continuación, describo cómo está estructurada la siguiente aplicación, explicando la arquitectura y tecnologías usadas para el desarrollo. Esta app muestra una lista de recetas con su detalle, al igual que muestra la ubicación en un mapa de donde es originario el platillo.

# Features  
¡Por supuesto! Aquí te muestro una descripción corta de cada tema:

<sub>**1. API SplashScreen**: Api recientemente propuesta por Google que permite a las aplicaciones de Android mostrar una pantalla de bienvenida personalizada al iniciar la aplicación.</sub>

<sub>**2. Common Dimens values**: Se utiliza un archivo con valores comunes, que todo el equipo de desarrollo deberia usar, para estandarizar los valores usados en los componentes.</sub>

<sub>**3. Material Design**: Es un conjunto de directrices de diseño desarrolladas por Google para crear aplicaciones de Android con una apariencia moderna.</sub>

<sub>**4. ViewBinding**: Facilita el enlace en el codigo con las vistas, esto previene errores ya que solo el binding, llamara los elementos de esa clase.</sub>

<sub>**5. Navigation Component**: Facilita la navegación entre pantallas en una aplicación. </sub>

<sub>**6. Dependency Injection**: Se utiliza para proporcionar objetos a una clase en lugar de que la clase los cree. Esto hace que el código sea más fácil de mantener y de probar.</sub>

<sub>**7. MVVM + Clean Architecture**: Arquitectura propuesta por Google que se centra en el patron observable + la arquitectura clean, que separa responsabilidades y hace que el codigo sea mas facil de mantener.</sub>

<sub>**8. Use Cases**: Cada caso de uso tiene una unica responsabilidad, esto hace que el codigo sea mantenible y testeable.</sub>

<sub>**9. Unit Test with MockK**: Dependencia utilizada para manejar las pruebas unitarias de diferentes bloques de codigo en la aplicacion.</sub>
