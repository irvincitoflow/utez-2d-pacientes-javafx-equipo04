# utez-2d-pacientes-javafx-equipo04
# Directorio de Pacientes — Sistema CRUD en JavaFX

## 1. Descripción del Proyecto
Este sistema de escritorio ha sido diseñado para la gestión integral de registros en consultorios médicos. La aplicación permite administrar el flujo de información de los pacientes a través de una interfaz gráfica intuitiva, garantizando la integridad de los datos mediante una arquitectura robusta de capas.

Desarrollado como proyecto integrador para la asignatura de **Programación Estructurada**.

---

## 2. Especificaciones Técnicas
* **Lenguaje:** Java 17 o superior.
* **Framework GUI:** JavaFX (FXML para la definición de vistas).
* **Gestión de Diseño:** Scene Builder.
* **Persistencia:** Almacenamiento local mediante archivos de valores separados por comas (`.csv`).
* **Patrón de Diseño:** MVC (Modelo-Vista-Controlador) con capas de Servicio y Repositorio.

---

## 3. Arquitectura del Sistema
El software se divide en módulos especializados para cumplir con el principio de responsabilidad única (SRP):

* **Model (Modelo):** Define la entidad `Paciente` con encapsulamiento estricto.
* **View (Vista):** Archivos `.fxml` que contienen la jerarquía de nodos de la interfaz.
* **Controller (Controlador):** Gestiona el flujo de eventos y la comunicación entre la vista y la lógica.
* **Service (Servicio):** Contiene la lógica de negocio y las validaciones de seguridad.
* **Repository (Repositorio):** Implementa la persistencia de datos (E/S de archivos).

---

## 4. Funcionalidades del Sistema (CRUD)
El sistema implementa las operaciones fundamentales de gestión de datos:

* **Registro (Create):** Captura de datos mediante formularios validados.
* **Consulta (Read):** Visualización en tiempo real mediante un componente `TableView` vinculado a una `ObservableList`.
* **Actualización (Update):** Modificación de registros existentes con restricción de edición en identificadores únicos (CURP).
* **Baja Lógica (Delete/Status):** Gestión de estados `ACTIVO` e `INACTIVO` para preservar la trazabilidad de la información.

---

## 5. Protocolos de Validación
Se han implementado filtros de seguridad en la capa de servicio para asegurar la calidad de la información:
1.  **Integridad de Campos:** Validación de campos no nulos o vacíos.
2.  **Validación de Identidad:** Restricción de duplicidad de CURP mediante búsqueda lineal.
3.  **Lógica Aritmética:** Restricción de edad en el rango de 0 a 120 años.
4.  **Formato Telefónico:** Validación de longitud exacta a 10 dígitos.

---

## 6. Guía de Ejecución

### Prerrequisitos
* Java Development Kit (JDK) 17+.
* Configuración de variables de entorno para JavaFX.

### Instalación y Despliegue
1.  **Clonación del Repositorio:**
2.  **Configuración del IDE:** Importar como proyecto Java y enlazar las librerías de JavaFX (SDK).
3.  **Ejecución:** Iniciar la aplicación desde la clase principal `HelloApplication.java`.

---

## 7. Créditos y Autoría
* **Institución:** Universidad Tecnológica Emiliano Zapata Del Estado de Morelos (UTEZ).
* **Grupo:** 2°D.
* **Desarrolladores:** Irvin Abarca Arenas y Angelina Perez Andres Gerardo