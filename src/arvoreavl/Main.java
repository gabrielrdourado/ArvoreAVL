package arvoreavl;

import com.sun.xml.internal.bind.api.JAXBRIContext;
import java.util.Scanner;

/**
 *
 * @author 
 *         Nome: Cristian Rogério Arioli  -  Matrícula: 201702533531
 *         Nome: Gabriel Ribeiro Dourado  -  Matrícula: 201703074181
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        
        int op;
        int nInserir;
        int nDeletar;
        int nBusca;
        
        Scanner ler = new Scanner(System.in);
        
        System.out.println("### Selecione a operação que deseja realizar: ###");
            System.out.println("\n\n    ==========================================================");
            System.out.println("    |    A árvore é rebalanceada a cada inserção e remoção   |");
            System.out.println("    |                                                        |");
            System.out.println("    |               1 - Inserir                              |");
            System.out.println("    |               2 - Deletar                              |");
            System.out.println("    |               3 - Busca Binaria                        |");
            System.out.println("    |               4 - Em Ordem, Pos Ordem, Pre Ordem       |");
            System.out.println("    |               5 - Balanceamento                        |");
            System.out.println("    |               6 - Altura                               |");
            System.out.println("    |               0 - Sair                                 |");
            System.out.println("    ==========================================================\n");
        
        
        do{ 
            System.out.println("\nSelecione a operação que deseja realizar:\n");
            op = ler.nextInt(); 
            
            switch(op){
            case 1: System.out.print("\nDigite o valor a ser inserido: \n");
                    nInserir = ler.nextInt();
                    arvore.inserir(nInserir);
                    break;
                    
            case 2: System.out.print("\nDigite o valor a ser removido: \n");
                    nDeletar = ler.nextInt();
                    arvore.deletar(nDeletar);
                    break;
                    
            case 3: System.out.print("\nDigite o valor que gostaria de pesquisar: \n");
                    nBusca = ler.nextInt();
                    if (arvore.pesquisaBinaria(nBusca) == true){
                        System.out.println("Número encontrado na árvore!");
                    } else{
                        System.out.println("Numero nao encontrado na árvore!");
                    }
                    break;
                    
            case 4: System.out.print("\nImprimindo Em Ordem: \n");
                    arvore.emOrdem();
                    System.out.print("\nImprimindo Pos Ordem: \n");
                    arvore.posOrdem();
                    System.out.print("\nImprimindo Pre Ordem: \n");
                    arvore.preOrdem();
                    break;
                    
            case 5: System.out.println("\nBalanceamento: \n");
                    arvore.imprimirBalanceamento();
                    break;
                    
            case 6: System.out.print("\nAltura: \n");
                    arvore.altura();
                    break;
                    
                           
        }
        }while(op!=0);
 
    }
    
}
