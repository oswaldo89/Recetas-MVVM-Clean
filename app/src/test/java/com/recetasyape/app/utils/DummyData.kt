package com.recetasyape.app.utils

import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Location
import com.recetasyape.app.modules.home.domain.model.Recipe

class DummyData {
    companion object {
        fun get(): List<Category> {
            return listOf(
                Category(
                    1, "Desayunos FIT", listOf(
                        Recipe(
                            1,
                            "Tortilla de claras con espinacas",
                            "Una opción deliciosa y baja en calorías para empezar el día.",
                            4,
                            "BAJA",
                            15,
                            200,
                            "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                            Location(19.4326, -99.1332),
                            5f,
                            listOf("Ingrediente 1", "Ingrediente 2")
                        )
                    )
                )
            )
        }
    }
}