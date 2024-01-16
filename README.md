# Android Clean Architecture (App de Recetas)  
A continuación, describo cómo está estructurada la siguiente aplicación, explicando la arquitectura y tecnologías usadas para el desarrollo. Esta app muestra una lista de recetas con su detalle, al igual que muestra la ubicación en un mapa de donde es originario el platillo.

# Features

<sub>**1. API SplashScreen**: Api recientemente propuesta por Google que permite a las aplicaciones de Android mostrar una pantalla de bienvenida personalizada al iniciar la aplicación.</sub>  
<sub>**2. Common Dimens values**: Se utiliza un archivo con valores comunes, que todo el equipo de desarrollo deberia usar, para estandarizar los valores usados en los componentes.</sub>  
<sub>**3. Material Design**: Es un conjunto de directrices de diseño desarrolladas por Google para crear aplicaciones de Android con una apariencia moderna.</sub>  
<sub>**4. ViewBinding**: Facilita el enlace en el codigo con las vistas, esto previene errores ya que solo el binding, llamara los elementos de esa clase.</sub>  
<sub>**5. Navigation Component**: Facilita la navegación entre pantallas en una aplicación. </sub>  
<sub>**6. Dependency Injection**: Se utiliza para proporcionar objetos a una clase en lugar de que la clase los cree. Esto hace que el código sea más fácil de mantener y de probar.</sub>  
<sub>**7. MVVM + Clean Architecture**: Arquitectura propuesta por Google que se centra en el patron observable + la arquitectura clean, que separa responsabilidades y hace que el codigo sea mas facil de mantener.</sub>  
<sub>**8. Use Cases**: Cada caso de uso tiene una unica responsabilidad, esto hace que el codigo sea mantenible y testeable.</sub>  
<sub>**9. Unit Test with MockK**: Dependencia utilizada para manejar las pruebas unitarias de diferentes bloques de codigo en la aplicacion.</sub>  


## Características

| Funcionalidad | Descripción |
|---------------|-------------|
| API SplashScreen | ![API SplashScreen](https://img.shields.io/badge/API-SplashScreen-blue) |
| Common Dimens values | ![Common Dimens values](https://img.shields.io/badge/Common-Dimens%20Values-green) |
| FloatingSearchView | ![FloatingSearchView](https://img.shields.io/badge/FloatingSearchView-yellow) |

## Social

- Share Recipe on WhatsApp with image and text

## Diseño

| Tecnología | Descripción |
|------------|-------------|
| Material Design | ![Material Design](https://img.shields.io/badge/Material%20Design-purple) |
| ViewBinding | ![ViewBinding](https://img.shields.io/badge/ViewBinding-orange) |

## Arquitectura

| Concepto | Descripción |
|----------|-------------|
| Dependency Injection | ![DI](https://img.shields.io/badge/Dependency%20Injection-red) |
| MVVM + Clean Architecture | ![MVVM](https://img.shields.io/badge/MVVM-blueviolet) |
| Use cases | ![Use Cases](https://img.shields.io/badge/Use%20Cases-lightgrey) |
| Unit Testing | ![Unit Testing](https://img.shields.io/badge/Unit%20Testing-success) |
| Kotlin DSL multi-module support | ![Kotlin DSL](https://img.shields.io/badge/Kotlin%20DSL-informational) |

## Automatización

- Github Actions for Unit Test
- Validación de valores "hardcodeados" (dimens) en XML

## Reporte de Errores

| Plataforma | Descripción |
|------------|-------------|
| Firebase Crashlytics | ![Crashlytics](https://img.shields.io/badge/Crashlytics-critical) |
| Slack notifications by Crashlytics | ![Slack](https://img.shields.io/badge/Slack-important) |

## Seguridad

| Herramienta | Descripción |
|-------------|-------------|
| Proguard (ofuscación) | ![Proguard](https://img.shields.io/badge/Proguard-yellowgreen) |

