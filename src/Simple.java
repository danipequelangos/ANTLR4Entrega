public class Simple{
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        int a = suma(1,1);
        String texto = a+"";
        imprime(texto);
    }

    public static int suma(int a, int b){
        imprime(a+" + "+b+" = ");
        return(a+b);
    }

    public static void imprime(String texto){
        System.out.print(texto);
    }
}