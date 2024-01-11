package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.data.dto.Dish
import com.recetasyape.app.modules.home.data.dto.Location
import com.recetasyape.app.modules.home.data.dto.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor() {
    suspend operator fun invoke() : List<Category> {
        return listOf(
            Category(
                1,
                "Desayunos FIT",
                listOf(
                    Dish(
                        1,
                        "Tortilla de claras con espinacas",
                        "Una opción deliciosa y baja en calorías para empezar el día. Prepara una tortilla de claras con espinacas y disfruta de un desayuno FIT.",
                        4,
                        "BAJA",
                        15,
                        200,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(19.4326, -99.1332)
                    ),
                    Dish(
                        2,
                        "Avena con frutas frescas",
                        "Una mezcla de avena cocida con trozos de frutas frescas. Este desayuno te proporcionará la energía necesaria para enfrentar el día.",
                        5,
                        "BAJA",
                        10,
                        250,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(20.6764, -88.5686)
                    ),
                    Dish(
                        3,
                        "Batido verde",
                        "Mezcla de espinacas, plátano, manzana y agua. Un batido refrescante y nutritivo para acompañar tus mañanas.",
                        4,
                        "BAJA",
                        5,
                        150,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(25.4384, -100.9737)
                    )
                )
            ),
            Category(
                2,
                "Nuevas Recetas",
                listOf(
                    Dish(
                        4,
                        "Ensalada de quinoa y aguacate",
                        "Una ensalada fresca con quinoa, aguacate, tomate y aderezo de limón. Una opción saludable y deliciosa para tus comidas.",
                        6,
                        "MEDIA",
                        25,
                        300,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(19.0414, -98.2063)
                    ),
                    Dish(
                        5,
                        "Pechuga de pollo al horno con hierbas",
                        "Pechuga de pollo sazonada con hierbas frescas y horneada. Una receta fácil y baja en grasas para tus cenas.",
                        5,
                        "MEDIA",
                        30,
                        280,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(20.9671, -89.6237)
                    ),
                    Dish(
                        6,
                        "Sopa de lentejas",
                        "Sopa reconfortante con lentejas, zanahorias y apio. Perfecta para días fríos y nutritiva para cualquier ocasión.",
                        7,
                        "BAJA",
                        40,
                        220,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(21.1619, -86.8515)
                    )
                )
            )
        )
    }
}