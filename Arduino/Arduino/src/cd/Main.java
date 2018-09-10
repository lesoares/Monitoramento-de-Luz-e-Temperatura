
package cd;

import br.pro.turing.javino.Javino;
import java.util.Scanner;
import org.jfree.data.category.DefaultCategoryDataset;


public class Main {
    public static void main(String[] args) {
        
        /**********DECLARAÇÕES**********/
        
        //JAVINO
        
        //Instancia do javino
        Javino j = new Javino();
        //Atualizar porta, coloquei "COM10" pois não sabia qual era a porta que
        //vamos utilizar
        String port = "COM10";
        //Variavel para dados vindos do arduino
        String data;
        //Numero de capturas realizadas
        int captura = 1;  
        //Comando para envio
        String sendCommand;
        
        //JFREECHART
        
        //Instancia do grafico a ser produzido
        Grafico g = new Grafico();
        
        /**********FIM DECLARAÇÕES**********/
        
        /**********PROGRAMA**********/
        
        //Sem interface grafica ainda (feito no terminal mesmo)
        System.out.println("Escolha entre Luminosidade e Temperatura:");        
        Scanner sc = new Scanner(System.in);        
        //Pega o valor digitado
        String comando = sc.next();        
        System.out.println(comando);                
        /*Criar dataset, utilizado como conteudo para o grafico*/
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();        
        //Envia comando para o arduino
        if(comando.equals("Luminosidade")){
            sendCommand = "ligarLuz";
            j.sendCommand(port, sendCommand);        
        }else{
            sendCommand = "ligarTemperatura";
            j.sendCommand(port, sendCommand);        
        }
        
        //Estou em duvida entre o while e o if
        //Recebe 8 capturas de luminosidade
        while(j.listenArduino(port) && captura < 8)
        {
            //Recupera os dados do arduino
            data = j.getData();
            System.out.println(data);
            //Adiciona valor ao grafico
            dataset.addValue(Double.parseDouble(data), comando, "Captura " + captura);
            //Incrementa o numero de capturas já realizadas
            captura++;
            //Se capturas == 8 entao cria o grafico
            if(captura == 8)
            {
                g.criaGrafico(dataset, "Luminosidade X Captura", comando, "Captura");
            }else{
                //Envia para o arduino e recebe a proxima msg
                j.sendCommand(port, sendCommand);
            }
        }
        
        /**********FIM DO PROGRAMA**********/
        
    }
}
