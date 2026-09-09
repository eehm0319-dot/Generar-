package com.example.engine

data class GeneratedBundle(
    val phrase: String,
    val coreAnalysis: String,
    val linkedin: String,
    val tiktok: String,
    val youtube: String,
    val facebook: String
)

object AnalyticalContentEngine {

    /**
     * Generador nativo offline de alta precisión analítica.
     * Incorpora la cosmovisión de Emiliano Hernández:
     * - Pensamiento analítico de primeros principios.
     * - Filosofía digital y sobriedad algorítmica.
     * - Ciencias sociales y dinámica de sistemas.
     * - Transición tecnológica y energética para un mundo sustentable.
     * - Superación personal, esfuerzo invisible y mejora continua.
     * - Ética intergeneracional para las próximas generaciones.
     */
    fun generateContent(
        topic: String,
        tone: String,
        targetPlatformFilter: String = "Todas"
    ): GeneratedBundle {
        val cleanTopic = topic.ifBlank { "Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua" }
        val topicLower = cleanTopic.lowercase()

        // 1. Detección temática analítica
        val isEnergy = topicLower.contains("energ") || topicLower.contains("planeta") || topicLower.contains("clima") || topicLower.contains("sostenib") || topicLower.contains("conciencia")
        val isDigital = topicLower.contains("digital") || topicLower.contains("filosof") || topicLower.contains("algorit") || topicLower.contains("atención") || topicLower.contains("ia") || topicLower.contains("redes")
        val isSocial = topicLower.contains("social") || topicLower.contains("sistema") || topicLower.contains("comunidad") || topicLower.contains("humano") || topicLower.contains("sociedad")
        val isGrowth = topicLower.contains("esfuerzo") || topicLower.contains("mejora") || topicLower.contains("profesional") || topicLower.contains("disciplina") || topicLower.contains("kaizen") || topicLower.contains("crecimiento")

        // 2. Cincelado de la Frase Memorable (1-2 líneas sobrias y profundas)
        val phrase = when {
            isEnergy && isGrowth ->
                "La verdadera soberanía energética y personal comienza al entender que tu energía no se quema en el ruido, se invierte en el legado que otros heredarán."
            isDigital && isSocial ->
                "En una economía de atención algorítmica, recuperar tu pensamiento crítico no es solo superación personal; es un deber cívico hacia las futuras generaciones."
            isEnergy ->
                "La transición energética no es únicamente un desafío de baterías y vatios; es la transformación moral de una civilización que decide no endeudar a sus hijos."
            isDigital ->
                "El algoritmo optimiza para la permanencia inmediata; la sabiduría analítica optimiza para el entendimiento que resiste el paso de las décadas."
            isSocial ->
                "Ningún sistema social colapsa de golpe ni florece por azar: cada cultura es el reflejo exacto de los esfuerzos invisibles de sus ciudadanos."
            isGrowth ->
                "El esfuerzo invisible es la única fuerza termodinámica real detrás de la mejora continua: nadie ve las raíces, pero sostienen el bosque entero."
            else ->
                "El esfuerzo invisible de hoy es la infraestructura ética, cognitiva y energética que sostendrá a las próximas generaciones."
        }

        // 3. Análisis central de primer principio
        val coreAnalysis = """
            Perspectiva Analítica:
            El desafío contemporáneo exige un puente interdisciplinario entre las ciencias sociales y los avances en tecnología energética. Mientras el mundo se debate en la sobreestimulación digital, la ventaja competitiva del profesional consciente radica en su capacidad de ejecutar un esfuerzo invisible sostenido, aplicando modelos de mejora continua orientados al impacto planetario y la ética intergeneracional.
        """.trimIndent()

        // 4. Contenido especializado para LINKEDIN (Crecimiento profesional, sistemas, liderazgo)
        val linkedin = buildString {
            appendLine("🔍 [ANÁLISIS ESTRATÉGICO] $cleanTopic")
            appendLine("Por Emiliano Hernández | Filosofía Digital & Dinámica de Sistemas")
            appendLine()
            appendLine("A menudo medimos el éxito profesional mediante métricas de vanidad: visibilidad inmediata, reconocimientos públicos o velocidad de tracción. Sin embargo, aplicando el pensamiento de primeros principios, el verdadero apalancamiento proviene de lo que llamo «El Esfuerzo Invisible».")
            appendLine()
            appendLine("📌 3 Conclusiones analíticas aplicables a tu carrera y liderazgo:")
            appendLine()
            appendLine("1️⃣ La Termodinámica del Enfoque:")
            appendLine("Así como la transición energética requiere migrar de combustibles volátiles a matrices renovables densas y acumulables, nuestro rendimiento cognitivo debe protegerse de la entropía digital. La atención es tu recurso más valioso; si la fragmentas en el algoritmo, no tendrás capital intelectual para resolver problemas complejos.")
            appendLine()
            appendLine("2️⃣ La Deuda Técnica del Carácter (Mejora Continua):")
            appendLine("En ciencias sociales y computación conocemos el costo del atajo: deuda técnica acumulada. En el desarrollo personal ocurre lo mismo. Dedicar 60 minutos diarios al estudio riguroso fuera de las redes es el esfuerzo invisible que te distinguirá del 99% en un lustro.")
            appendLine()
            appendLine("3️⃣ Conciencia Planetaria como Imperativo Profesional:")
            appendLine("Ya no existe innovación legítima que sea ciega a sus externalidades ambientales y sociales. Diseñar modelos de negocio, proyectos energéticos o herramientas tecnológicas debe responder a una sola prueba ácida: ¿hace este trabajo que el planeta de nuestros hijos sea más viable y próspero?")
            appendLine()
            appendLine("💡 Pregunta para la comunidad profesional:")
            appendLine("En tu sector, ¿cuál es ese 'esfuerzo invisible' o transformación técnica que hoy pocos ven pero que definirá la próxima década?")
            appendLine()
            appendLine("#LiderazgoConsciente #TransiciónEnergética #FilosofiaDigital #MejoraContinua #FuturoSostenible #CienciasSociales #EmilianoHernández")
        }

        // 5. Contenido especializado para TIKTOK (Hook magnético, guion ágil, retención)
        val tiktok = buildString {
            appendLine("📱 GUION TIKTOK / REELS | @EmilianoHernandez")
            appendLine("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            appendLine("⏱️ [00:00 - 00:03] EL GANCHO (Visual & Verbal):")
            appendLine("👉 (Cámara fija en primer plano, mirada analítica, tono calmado pero firme)")
            appendLine("\"La mayoría cree que el éxito es suerte o talento... pero la física y las ciencias sociales demuestran exactamente lo contrario.\"")
            appendLine()
            appendLine("⏱️ [00:03 - 00:18] LA PARADOJA ANALÍTICA:")
            appendLine("\"Vivimos obsesionados con el resultado visible: el premio, el auto, el puesto. Pero en tecnología energética sabemos que un panel solar no produce luz sin una batería que almacena silenciosamente en la sombra. En tu vida ocurre igual.\"")
            appendLine()
            appendLine("⏱️ [00:18 - 00:38] EL DESGLOSE:")
            appendLine("\"Se llama 'Esfuerzo Invisible'. Son las horas donde nadie te aplaude: cuando decides apagar el teléfono a las 9 PM para leer un libro denso, cuando prefieres optimizar un proceso en vez de quejarte, y cuando entiendes que tu huella en el planeta importa más que tu ego en redes.\"")
            appendLine()
            appendLine("⏱️ [00:38 - 00:52] EL LLAMADO GENERACIONAL:")
            appendLine("\"No estamos aquí para acumular 'likes' vacíos; estamos aquí para resolver la crisis energética, reconstruir el tejido social y dejar un mundo del que las próximas generaciones se sientan orgullosas.\"")
            appendLine()
            appendLine("⏱️ [00:52 - 00:60] CALL TO ACTION:")
            appendLine("\"Si estás comprometido con mejorar un 1% cada día, sígueme y comenta cuál es tu mayor esfuerzo invisible hoy.\"")
            appendLine()
            appendLine("#AprendeEnTikTok #FilosofiaDigital #CienciaYTecnologia #SuperacionPersonal #CleanTech #EmilianoHernandez")
        }

        // 6. Contenido especializado para YOUTUBE (Video Essay estructurado / YouTube Shorts)
        val youtube = buildString {
            appendLine("🎥 ESTRUCTURA DE GUION YOUTUBE | Emiliano Hernández")
            appendLine("TÍTULO PROPUESTO: \"Por Qué el Esfuerzo Invisible y la Conciencia Planetaria Construirán el Próximo Siglo\"")
            appendLine("FORMATO: Video Ensayo Analítico / Deep Dive (8 a 12 minutos)")
            appendLine("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            appendLine("📍 [00:00 - 01:30] INTRODUCCIÓN & TESIS CENTRAL")
            appendLine("- Planteamiento: La trampa de la dopamina digital vs. la construcción de infraestructura civilizatoria.")
            appendLine("- Tesis: Solo las civilizaciones que invierten en tecnología energética de base y cultivan ciudadanos con disciplina interna sobreviven a las crisis históricas.")
            appendLine()
            appendLine("📍 [01:30 - 04:15] BLOQUE 1: CIENCIAS SOCIALES Y LA ILUSIÓN DE LA INMEDIATEZ")
            appendLine("- Análisis sociológico del individualismo moderno.")
            appendLine("- El concepto de 'Esfuerzo Invisible': ¿Por qué las catedrales medievales tardaban 150 años en construirse y por qué hoy no toleramos un video de 60 segundos?")
            appendLine()
            appendLine("📍 [04:15 - 07:00] BLOQUE 2: TECNOLOGÍA ENERGÉTICA Y EL DESTINO COMÚN")
            appendLine("- La relación directa entre disponibilidad de energía limpia y equidad social.")
            appendLine("- Descentralización energética: Baterías de estado sólido, microrredes solares y el fin de los monopolios extractivos.")
            appendLine("- La responsabilidad ética de nuestra generación.")
            appendLine()
            appendLine("📍 [07:00 - 09:30] BLOQUE 3: METODOLOGÍA KAIZEN DE CRECIMIENTO PERSONAL")
            appendLine("- Cómo aplicar la mejora continua en la rutina profesional diaria.")
            appendLine("- Desconexión calculada: La filosofía digital aplicada al descanso profundo.")
            appendLine()
            appendLine("📍 [09:30 - 11:00] CONCLUSIÓN & DIÁLOGO COMUNITARIO")
            appendLine("- Síntesis: Dejar un legado no es que recuerden tu nombre; es que tu paso por la Tierra haya aliviado la carga de quienes nacerán mañana.")
            appendLine("- Pregunta fijada para comentarios: \"¿En qué área de tu vida necesitas reemplazar la inmediatez por esfuerzo invisible?\"")
            appendLine()
            appendLine("Descripción para el video:")
            appendLine("En este video exploramos la convergencia entre filosofía digital, ciencias sociales y las nuevas tecnologías de energía limpia, demostrando que el esfuerzo invisible y la mejora continua son las únicas herramientas reales para construir un mundo sustentable. Suscríbete para análisis semanales.")
        }

        // 7. Contenido especializado para FACEBOOK (Narrativa comunitaria, cívica, debate familiar)
        val facebook = buildString {
            appendLine("🌱 REFLEXIÓN ABIERTA | Emiliano Hernández")
            appendLine()
            appendLine("Hace unos días me detuve a reflexionar sobre una contradicción curiosa de nuestra época: nunca tuvimos tantas herramientas tecnológicas a nuestro alcance, y sin embargo, pocas veces hemos sentido tanta incertidumbre sobre el futuro de nuestro planeta y nuestras comunidades.")
            appendLine()
            appendLine("Cuando hablo de «Esfuerzo Invisible», no hablo de trabajar hasta el agotamiento por capricho. Hablo de algo mucho más profundo: de la madre o el padre que educa con el ejemplo cuando nadie los está grabando; del joven profesional que se queda hasta tarde dominando una nueva tecnología energética limpia; del ciudadano que decide cuidar el agua y moderar su consumo aunque su vecino no lo haga.")
            appendLine()
            appendLine("La filosofía digital nos enseña que las pantallas amplifican lo que ya somos. Si somos reactivos, el algoritmo nos polariza. Pero si cultivamos la mejora continua y la conciencia comunitaria, podemos usar la tecnología para tender puentes, democratizar la energía limpia y garantizar que las próximas generaciones hereden un hogar habitable.")
            appendLine()
            appendLine("El progreso no llega por arte de magia. Llega cuando decidimos que el bienestar común vale más que nuestra comodidad momentánea.")
            appendLine()
            appendLine("💬 Me gustaría leerte en los comentarios: ¿Qué hábito o esfuerzo invisible crees que deberíamos rescatar como sociedad para inspirar a los más jóvenes?")
            appendLine()
            appendLine("Comparte esta reflexión si crees que aún estamos a tiempo de construir un mundo mejor.")
            appendLine()
            appendLine("#EmilianoHernández #ConcienciaPlanetaria #EsfuerzoInvisible #FamiliaYSociedad #MejoraContinua #Futuro")
        }

        return GeneratedBundle(
            phrase = phrase,
            coreAnalysis = coreAnalysis,
            linkedin = linkedin,
            tiktok = tiktok,
            youtube = youtube,
            facebook = facebook
        )
    }
}
