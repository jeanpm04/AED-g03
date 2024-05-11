package ejercicio3;

public class ProblemaCorchetes {
    public static boolean estaBalanceado(String cadena) {
        Stacklink<Character> pila = new Stacklink<>();

        for (char caracter : cadena.toCharArray()) {
            if (caracter == '(' || caracter == '[' || caracter == '{') {//Si se cumple la condición se hace un push
                pila.push(caracter);
            } else if (caracter == ')' || caracter == ']' || caracter == '}') {
                if (pila.isEmpty()) {
                    return false; // Hay un corchete de cierre sin un corchete correspondiente de apertura
                }//Por lo cual no esta balanceada.

                char corcheteApertura = pila.top(); // Obtener el elemento superior de la pila
                if ((caracter == ')' && corcheteApertura != '(') ||
                    (caracter == ']' && corcheteApertura != '[') ||
                    (caracter == '}' && corcheteApertura != '{')) {
                    return false; // Los corchetes no coinciden
                }
                pila.pop(); // Eliminar el elemento superior de la pila
            }
        }
        return pila.isEmpty(); // Verifica si la pila está vacía al finalizar
    }
    public static void main(String[] args) {
        String cadena1 = "()()()[()]()"; // Devuelve true
        String cadena2 = "((()))[]";    // Devuelve true
        String cadena3 = "([])[](";    // Devuelve False
        String cadena4 = "([{)]}";    // Devuelve false
        String cadena5 = "[";    // Devuelve false
        String cadena6 = "[][][]{{{}}}";    // Devuelve false
        

        System.out.println("¿La cadena 1 está balanceada? " + estaBalanceado(cadena1));
        System.out.println("¿La cadena 2 está balanceada? " + estaBalanceado(cadena2));
        System.out.println("¿La cadena 3 está balanceada? " + estaBalanceado(cadena3));
        System.out.println("¿La cadena 4 está balanceada? " + estaBalanceado(cadena4));
        System.out.println("¿La cadena 5 está balanceada? " + estaBalanceado(cadena5));
        System.out.println("¿La cadena 6 está balanceada? " + estaBalanceado(cadena6));
       
    }
}
