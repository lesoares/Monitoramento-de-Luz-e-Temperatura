
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
        
        //JFREECHART
        
        //Instancia do grafico a ser produzido
        Grafico g = new Grafico();
        
        /**********FIM DECLARAÇÕES**********/
        
        /**********PROGRAMA**********/
        
        //Sem interface grafica ainda (feito no terminal mesmo)
        System.out.println("Digite um comando:");        
        Scanner sc = new Scanner(System.in);        
        //Pega o valor digitado
        String comando = sc.next();        
        System.out.println(comando);                
        /*Criar dataset, utilizado como conteudo para o grafico*/
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();        
        //Envia comando para o arduino
        j.sendCommand(port, "ligarLuz");        
        if(j.listenArduino(port) && captura < 8)
        {
            //Recupera os dados do arduino
            data = j.getData();
            System.out.println(data);
            //Adiciona valor ao grafico
            dataset.addValue(Double.parseDouble(data), "Luminosidade", "Captura " + captura);
            //Incrementa o numero de capturas já realizadas
            captura++;
            //Se capturas == 8 entao cria o grafico
            if(captura == 8)
            {
                g.criaGrafico(dataset, "Luminosidade X Captura", "Liminosidade", "Captura");
            }
        }
        
        /**********FIM DO PROGRAMA**********/
        
    }
}
