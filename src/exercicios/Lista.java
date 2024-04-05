package exercicios;

import java.util.*;

public class Lista {
	private int nElem;
	private No cabeca;
	
	private class No {
		No(String e) {
			item = e;
			prox = null;
		}
		
		No prox;
		String item;	
	}
	
	
	
	public Lista() {
		cabeca = null;
		nElem = 0;
	}
	
	
	public void inserirCabeca(String e) {
		No novoNo = new No(e);
		
		novoNo.prox = cabeca;
		cabeca = novoNo;
		
		nElem++;
	}
	
	public void inserirCauda(String e) {
		No novoNo = new No(e);
		
		if(cabeca == null) {
			cabeca = novoNo;
			nElem++;
			
			return;
		}
		
		No ultimo = cabeca;
		
		while(ultimo.prox != null)
			ultimo = ultimo.prox;
		
		ultimo.prox = novoNo;
		nElem++;
	}
	
	
	public void inserirOrdem(String e) {
		No novoNo = new No(e);
		No atual = cabeca;
		No anterior = null;
		
		// procurar o local onde inserir
		while(atual != null && atual.item.compareTo(e) < 0) {
			anterior = atual;
			atual = atual.prox;
		}
		
		novoNo.prox = atual;
		
		if(anterior == null)
			cabeca = novoNo;
		else
			anterior.prox = novoNo;
		
		nElem++;
	}
	
	
	public void inserir(String e, int i) {	
		No novoNo = new No(e);
		No atual = cabeca;
		No anterior = null;
		int posAtual = 0;
		
		while(atual != null && posAtual < i) {
			anterior = atual;
			atual = atual.prox;
			posAtual++;
		}
		
		novoNo.prox = atual;
		
		if(anterior == null)
			cabeca = novoNo;
		else
			anterior.prox = novoNo;
		
		nElem++;
	}
	
	
	public int procurar(String e) {
		No atual = cabeca;
		int pos = 0;
		
		while(atual != null && !Objects.equals(atual.item, e)) {
			atual = atual.prox;
			pos++;
		}
		
		return atual == null ? -1: pos;
	}
	
	public void retirar(String palavra) {
//		if(!eIndiceValido(i))
//			throw new ArrayIndexOutOfBoundsException(i);
		
		No atual = cabeca;
		No anterior = null;
		int pos = 0,
			i = procurar(palavra);
		
		while(atual != null && pos < i) 
		{
			anterior = atual;
			atual = atual.prox;
			pos++;
		}
		
		if(anterior != null)
			anterior.prox = atual.prox;
		else cabeca = atual.prox;
		
		this.nElem--;
	}
}
