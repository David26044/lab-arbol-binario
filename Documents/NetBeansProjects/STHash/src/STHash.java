import java.util.Iterator;
import java.util.NoSuchElementException;

public class STHash<Key, Value> implements Iterable<Key> {

    private int n;           // cantidad de pares key-value en la tabla
    private int m;           // tamaño de la tabla
    private Key[] keys;      // arreglo de keys
    private Value[] vals;    // arreglo de values

    public STHash(int m) {
        this.m = m;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    /*
    hash que varia entre 0 y m-1
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void put(Key key, Value value) {
        int hash = hash(key);
        int i = hash;
        do {
            if (keys[i] == null) {
                keys[i] = key;
                vals[i] = value;
                n++;
                break;
            }
            if (keys[i].equals(key) && vals[i] == null) {
                delete(keys[i]);
                break;
            }
            i = (i + 1) % m;
        } while (i != hash);
    }

    public Value get(Key key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
            i = (i + 1) % m;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        int i = hash(key);
        if (!contains(key)) {
            return;
        }
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                keys[i] = null;
                vals[i] = null;
                break;
            }
            i = (i + 1) % m;
        }
        // para reorganizar la tabla
        for (i = (i + 1) % m; keys[i] != null; i = (i + 1) % m) {
            Key temp1 = keys[i];
            Value temp2 = vals[i];
            vals[i] = null;
            keys[i] = null;
            put(temp1, temp2);
        }
        n--;
    }

    @Override
    public Iterator<Key> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<Key> {
        private int actual = 0;

        @Override
        public boolean hasNext() {
            // Avanza hasta encontrar el siguiente índice no nulo
            while (actual < m && keys[actual] == null) {
                actual++;
            }
            return actual < m && keys[actual] != null;
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return keys[actual++];
        }
    }
    public static void main(String[] args) {
        // Crear una tabla de dispersión con un tamaño inicial de 10
        STHash<String, Integer> hashTable = new STHash<>(10);

        // Agregar elementos a la tabla
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("e", 5);

        // Imprimir el tamaño de la tabla
        System.out.println("Tamaño de la tabla: " + hashTable.size());

        // Verificar si la tabla contiene ciertas claves
        System.out.println("¿Contiene la clave 'b'? " + hashTable.contains("b"));
        System.out.println("¿Contiene la clave 'f'? " + hashTable.contains("f"));

        // Obtener valores asociados a claves
        System.out.println("Valor asociado a la clave 'c': " + hashTable.get("c"));
        System.out.println("Valor asociado a la clave 'z': " + hashTable.get("z"));

        // Eliminar un elemento de la tabla
        hashTable.delete("b");
        System.out.println("Tamaño de la tabla después de eliminar 'b': " + hashTable.size());

        // Iterar sobre las claves de la tabla
        System.out.println("Claves en la tabla:");
        for (String key : hashTable) {
            System.out.println(key);
        }
    }
}