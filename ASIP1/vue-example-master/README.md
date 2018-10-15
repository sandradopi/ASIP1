# Vue 

Aplicación web de ejemplo implementada con Vue. Consta de un CRUD que realiza peticiones contra un servidor REST falso ([JSONPlaceholder](https://jsonplaceholder.typicode.com/)), por lo que las peticiones POST/PUT no funcio narán correctamente, aunque devuelven una respuesta falsa correcta. Como *package bundler* se ha usado Parcel, que no requiere configuración.

Los componentes Vue son definidos usando *single-file components*. Es decir, ficheros con extension `.vue` en los que se define el HTML, JS y CSS asociado a cada componente. Más información en la [documentación de Vue](https://vuejs.org/v2/guide/single-file-components.html).

## Ejecución

```bash
npm install
npm run debug
```

Nota: al ejecutarse `debug` se muestran *deprecated warnings* por usar la sintaxis `@import` para cargar ficheros `.css`. Se pueden obviar (hay discusiones entre los desarrolladores del conjunto de librerías *scss* y no se ponen de acuerdo en la sintaxis para cargar ficheros css).

## Scripts

* `debug`: lanza el servidor de aplicaciones de parcel, empaqueta la aplicación y se queda en ejecución, refrescando el explorador web al cambiar el código fuente.
* `build`: empaqueta la aplicación.

## Dependencias y utilidades

### Vue + vue router

Vue es un framework para crear interfaces web. Vue router es la librería que se encarga del enrutado de las diferentes páginas que compongan la aplicación (su función es equivalente a los controladores Spring que gestionan *qué se renderiza* con cada petición enviada).

* [Vue](https://github.com/vuejs/vue)
* [Vue router](https://router.vuejs.org/)

### Parcel

Parcel es un *empaquetador de aplicaciones web* (*web application bundler*). Se encarga de juntar todos los ficheros necesarios para el funcionamiento de una aplicación en un único fichero, *transpilando* el código si es necesario: [SCSS](https://sass-lang.com/) a CSS, TypeScript a JavaScript, etc. 

La alternativa más popular ahora mismo es [webpack](https://webpack.js.org/), pero su uso y configuración es más compleja.

* [Parcel](https://parceljs.org/)

### Bootstrap (vue)

Bootstrap es una librería que permite construir páginas web *agradables* y *responsives* de manera sencilla. Es la librería de este tipo más utilizada.

En nuestro caso usaremos una alternativa, Bootstrap + Vue, que implementa los mismos componentes pero utilizando Vue para ello, de forma que su uso es más natural dentro de una aplicación Vue y su rendimiento mejor que si usáramos otra alternativa.

* [Bootstrap](http://getbootstrap.com/)
* [Bootstrap + Vue](https://bootstrap-vue.js.org/)

### Font Awesome

Colección de iconos de fácil uso. Muchos son de pago, pero la colección gratuita suele tener iconos de sobra para una aplicación prototipo.

* [Font Awesome](https://fontawesome.com/)

### Axios

Librería que nos permite realizar peticiones REST de manera más sencilla que usando el API *fetch*. 

* [Axios](https://github.com/axios/axios)

### EditorConfig

Plugin instalable en casi cualquier editor o IDE que nos permite unificar el estilo de la indentación, los saltos de línea y el encoding de los ficheros, entre otras cosas.

* [EditorConfig](https://editorconfig.org/)
