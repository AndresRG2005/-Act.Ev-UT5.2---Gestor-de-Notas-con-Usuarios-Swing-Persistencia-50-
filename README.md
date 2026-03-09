Gestor de Notas con Usuarios (Java Swing + Persistencia)
Aplicación de escritorio desarrollada en Java Swing que permite gestionar notas de manera visual y organizada.
Incluye usuarios, persistencia en ficheros, contraseñas con hash, filtrado de notas y un sistema completo de CRUD.

Características principales:
Crear notas

Editar notas

Eliminar notas

Borrar todas las notas (con confirmación)

Buscar notas por título (filtrado en tiempo real)

Guardado automático tras cada acción

Contador y mensajes de estado


Usuarios
Registro de nuevos usuarios

Validación de datos

Contraseñas protegidas con SHA‑256

Inicio de sesión con control de errores

Cada usuario tiene sus propias notas

Cerrar sesión y volver al login

Persistencia
Usuarios guardados en data/usuarios.txt

Notas guardadas por usuario en data/notas_<usuario>.txt

Datos conservados entre ejecuciones

Estructura del proyecto
Código
src/
  andresrg/
  gestornotas/
    App.java
    model/
      Nota.java
      Usuario.java
    persistence/
      UsuarioRepository.java
      NotaRepository.java
    security/
      PasswordUtils.java
    ui/
      LoginFrame.java
      RegisterDialog.java
      MainFrame.java

data/
  usuarios.txt
  notas_<usuario>.txt

README.md

Cómo ejecutar
Clona el repositorio o descarga el ZIP.

Abre el proyecto en tu IDE (IntelliJ, NetBeans, Eclipse…).

Ejecuta la clase:

Código
App.java
Aparecerá la pantalla de Login.

Si no tienes usuario, pulsa Registrarse.

Inicia sesión y empieza a gestionar tus notas.


Tecnologías utilizadas
Java 

Swing

Persistencia en ficheros

Hash

Git / GitHub

Autor
Proyecto realizado por Andrés Ramos Guerra
Actividad UT5.2 – Interfaces Gráficas y Persistencia