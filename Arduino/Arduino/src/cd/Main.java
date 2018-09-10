
package cd;

import br.pro.turing.javino.Javino;

public class Main {
    public static void main(String[] args) {
        Javino j = new Javino();
        String port = "COM10";
        String data;
        
        j.sendCommand(port, "ligarLuz");
        
        if(j.listenArduino(port))
        {
            data = j.getData();
            System.out.println(data);
        }
    }
}
