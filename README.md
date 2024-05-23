¿Es posible lograr el mismo resultado con el pre-orden y pos-orden?

Construir un árbol binario utilizando solamente los recorridos en preorden y posorden no siempre es posible. La razón es que estos dos recorridos no proporcionan 
suficiente información para determinar la estructura única del árbol.


¿Es posible lograr el mismo resultado con el in-orden y pos-orden? 

Sí, es posible reconstruir un árbol binario de manera única utilizando los recorridos inorden y posorden. Esto se debe a que estos dos recorridos
proporcionan suficiente información para determinar la estructura del árbol.

Para entender por qué es posible, se considera lo que cada uno de estos recorridos nos dice:

Inorden (inorder): Visita primero el subárbol izquierdo, luego la raíz y finalmente el subárbol derecho.
Posorden (postorder): Visita primero el subárbol izquierdo, luego el subárbol derecho y finalmente la raíz.
La clave para reconstruir el árbol está en cómo se organizan los nodos en los recorridos inorden y posorden. 
En el recorrido inorden, la raíz siempre está entre los recorridos de sus hijos izquierdo y derecho, mientras que en el recorrido posorden,
la raíz siempre aparece al final, después de sus hijos izquierdo y derecho.
