package exercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class teste {
	public static void imprimirVetor(String[] vetor)
	{
		for(int i=0; i<vetor.length; i++)
		{
			System.out.println("v[" + i + "]=" + vetor[i]);
		}
	}
	
	public static int procuraBinaria(String[] vetor, String valor)
	{
		int esq=0, meio=0;
		int dir=vetor.length-1;
		
		while(esq <= dir)
		{
			meio = (esq+dir)/2;
			
			if(vetor[meio] == valor)
				return meio;
			
			if(vetor[meio].compareTo(valor) < 0)
				esq = meio+1;
			else
				dir = meio-1;
		}
		
		return -1;
	}
	
    public static String[] eliminarPalavra(String[] vetor, String palavra) 
    {
        //índice da palavra a eliminar
        int indiceEliminacao = procuraBinaria(vetor, palavra);

        if (indiceEliminacao != -1) 
        {
		    //mover os elementos para trás
		    for(int i = indiceEliminacao; i < vetor.length-1; i++)
		        vetor[i] = vetor[i+1];

		    //último elemento a null para não ficar com valor em excesso
		    vetor[vetor.length-1] = null;
        }
        
        return vetor;
    }
    
	
	public static void main(String[] args) {
		//Criação das estruturas de dados
		String vetor[] = new String[30000];
		Vetor vetorDinamico = new Vetor(1000, 500);
		Lista lista = new Lista();
		
		
		//---------------------------- LEITURA FICHEIRO/CARREGAMENTO DADOS ----------------------------
		String tmp;
		Scanner leitor = null;
		File file = new File("palavras.txt");
		
		try {
			leitor = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
			e.printStackTrace();
		}
		
		int c=0;
		long inicio, fim, tempo;
		
		inicio = System.nanoTime();
		
		while(leitor.hasNextLine())
		{
			tmp = leitor.nextLine();
			
			//adicionar palavras no vetor
			vetor[c] = tmp;
			
			//adicionar palavras no vetor dinâmico
			vetorDinamico.adicionar(tmp);
			
			//adicionar palavras na lista
			lista.inserirCauda(tmp);
			c++;
		}
		
		fim = System.nanoTime();
		tempo = fim - inicio;
		System.out.println("carregamento de dados demorou " + tempo + " nanosegundos");
		leitor.close();
		
		
		//---------------------------- PESQUISA ----------------------------
		String palavra = "repercurtir";
		inicio = System.nanoTime();
		
		System.out.println(procuraBinaria(vetor, palavra));
		vetorDinamico.procura(palavra);
		lista.procurar(palavra);
		
		fim = System.nanoTime();
		tempo = fim - inicio;
		System.out.println("pesquisa por palavra demorou " + tempo + " nanosegundos");
		
		
		//---------------------------- ELIMINAÇÃO ----------------------------
		palavra = "repercurtir";
		inicio = System.nanoTime();
		
		vetor = eliminarPalavra(vetor, palavra);
		vetorDinamico.retirar(palavra);;
		lista.retirar(palavra);
		
		fim = System.nanoTime();
		tempo = fim - inicio;
		System.out.println("eliminação da palavra demorou " + tempo + " nanosegundos");
		
		
		imprimirVetor(vetor);
	}

}
