package exercicios;

public class Vetor {
	private String[] buffer;
	private int nElem;
	private int capMax;
	private int inc;
	
	
	public Vetor() {
		
	}
	
	
	public Vetor(int capInicial) {
		this.buffer = new String[capInicial];
		this.nElem = 0;
		this.capMax = capInicial;
		this.inc = 1;
	}

	
	public Vetor(int capInicial, int incremento) {
		this.buffer = new String[capInicial];
		this.nElem = 0;
		this.capMax = capInicial;
		this.inc = incremento;
	}
	
	
	
	public int getTamanho() { 
		return nElem; 
	}
	
	public int getCapacidade() { 
		return capMax; 
	}
	
	public int getEspacoLivre() { 
		return getCapacidade() - getTamanho(); 
	}
	
	public void setIncremento(int novoInc) {
		inc = novoInc <= 0? inc : novoInc;
	}

	
	
	
	public boolean estaCheio() {
		return nElem == capMax ? true : false;
	}
	
	
	public boolean estaVazio() { 
		return nElem == 0; 
	}
	
	
	public void limpar() {
		nElem = 0;
	}
	
	
	public void adicionar(String x) {
		if(estaCheio())
			aumentar();
		
		buffer[nElem] = x;
		nElem++;
	}
	
	
	public void inserir(String x, int i) {
		if(i > nElem)
			return;
		
		if(estaCheio())
			aumentar();
		
		for(int c = nElem; c > i; c--)
			buffer[c] = buffer[c-1];
		
		buffer[i] = x;
		nElem++;
	}
	
	
	public void retirar(int i) {
		String antigo = buffer[i];
		
		for(int c = i+1; i < nElem; i++)
			buffer[c-1] = buffer[c];
		
		nElem--;
	}
	
	
	//retira o último elemento do vector
	public void retirar(String e) {
		int i = procura(e);
		
		if(i != -1)
			retirar(i);
	}
	
	public void retirarTodas(String e) {
		int i = procura(e);
		
		while(i != -1) {
			retirar(i);
			
			i = procura(e, i);
		}
	}
	
	
	private void aumentar() {
		String novoBuffer[] = new String[capMax + inc];
		
		for(int i = 0; i < nElem; i++)
			novoBuffer[i] = buffer[i];
		
		buffer = novoBuffer;
		capMax += inc;
	}
	
	
	private void diminuir() {
		if(nElem < capMax-inc * 1.5 ) {
			String[] novoBuffer = new String[capMax-inc];
			
			for(int i = 0; i < nElem; i++)
				novoBuffer[i] = buffer[i];
			
			buffer = novoBuffer;
			capMax -= inc;
		}
	}

	
	public int procura(String e, int posIni) {
		for(int i = posIni; i < nElem; i++)
			if(buffer[i].equals(e))
				return i;
		
		return -1;
	}
	
	
	public int procura(String e) {
		return procura(e, 0);
	}
	
	
	public int procuraFim(String e) {
		return procuraFim(e, nElem-1);	
	}
	
	
	public int procuraFim(String e, int posFim) {
		for(int i = posFim; i >= 0; i--)
			if(buffer[i].equals(e))
				return i;
		
		return -1;
	}

	
	public boolean estaPresente(String e) {
		return procura(e) != -1;
	}
	
	
	public int numRepeticoes(String e) {
		int nVezes = 0;
		int proxIdx = procura(e);
		
		while(proxIdx != -1) {
			nVezes++;
			proxIdx = procura(e, proxIdx + 1);
		}
		
		return nVezes;
	}

	public void assegurarCapacidade(int capMin) {
		while(capMax < capMin)
			aumentar();
	}
	
	public boolean setTamanho(int tamanho) {
		// se já tem mais elementos que o tamanho pedido
		// não pode reduzir senão perde informação
		if(tamanho < getTamanho())
			return false;
		
		String novoBuffer[] = new String[tamanho];
		
		for(int i = 0; i < nElem; i++)
			novoBuffer[i] = buffer[i];
		
		buffer = novoBuffer;
		capMax = tamanho;
		
		return true;
	}

	public void ficarTamanhoActual() {
		setTamanho(getTamanho());
	}
	
	
}