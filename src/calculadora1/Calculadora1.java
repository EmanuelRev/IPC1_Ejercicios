
 
package calculadora1;

import javax.swing.JOptionPane;

/**
 *
 * @author Emanuel
 */
public class Calculadora1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int  A,B,suma,resta,multiplicacion,division;
        char operacion;
        
        
        A = Integer.parseInt(JOptionPane.showInputDialog("Ingrese Primer Numero "));
        B = Integer.parseInt(JOptionPane.showInputDialog("Ingrese Segundo Numero O Elevacion del Primer Numero"));
        
        operacion = JOptionPane.showInputDialog(
    "Seleccione la una operación:\n" +
    "1 - Suma\n" +
    "2 - Resta\n" +
    "3 - Multiplicación\n" +
    "4 - División\n" +
    "5 - Potencia del Primer Numero\n" +
    "6 - Raíz cuadrada del Primer Numero\n" +
    "7 - Salir"
).charAt(0);
        
       switch(operacion) {
           case '1': suma = A+B;
           JOptionPane.showMessageDialog(null,"La Suma es: "+suma);
          break;
          case '2': resta = A-B;
           JOptionPane.showMessageDialog(null,"La Resta es: "+resta);
          break;
           case '3': multiplicacion = A*B;
           JOptionPane.showMessageDialog(null,"La Multiplicacion es: "+multiplicacion);
          break;
          case '4': division = A/B;
           JOptionPane.showMessageDialog(null,"La Division es: "+division);
          break;
          case '5':
                JOptionPane.showMessageDialog(null,"La Potencia es: " + Math.pow(A, B));
                break;
         case '6':
             
    if (A >= 0) {
        JOptionPane.showMessageDialog(null, "Raíz cuadrada de A: " + Math.sqrt(A));
    } else {
        JOptionPane.showMessageDialog(null, "Error: número negativo.");
    }
    break;
    case '7':
                JOptionPane.showMessageDialog(null,"Saliendo de la calculadora...");
                System.exit(0);
                break;
          default: JOptionPane.showMessageDialog(null,"Error Al Ingresar Datos"); break;
          
       
       }
            
            
            
 
        
    }
    
}
