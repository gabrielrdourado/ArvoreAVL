package arvoreavl;

/**
 *
 * @author 
 *         Nome: Cristian Rogério Arioli  -  Matrícula: 201702533531
 *         Nome: Gabriel Ribeiro Dourado  -  Matrícula: 201703074181
 */

public class ArvoreAVL {
 
    private No raiz;
 
    private static class No {
        private int chave;
        private int balanceamento;
        private int altura;
        private No esquerda;
        private No direita;
        private No parente;
 
        No(int chave, No parente) {
            this.chave = chave;
            this.parente = parente;
        }

        public int getAltura() {
            return altura;
        }
        
    }
 
    
    
    public boolean inserir(int chave) {
        if (raiz == null) {
            raiz = new No(chave, null);
            return true;
        }
 
        No n = raiz;
        while (true) {
            if (n.chave == chave)
                return false;
            
            No pai = n;
 
            boolean goLeft = n.chave > chave;
            n = goLeft ? n.esquerda : n.direita;
 
            if (n == null) {
                if (goLeft) {
                    pai.esquerda = new No(chave, pai);
                } else {
                    pai.direita = new No(chave, pai);
                }
                rebalancear(pai);
                break;
            }
        }
        return true;
    }
 
    private void deletar(No no) {
        if (no.esquerda == null && no.direita == null) {
            if (no.parente == null) {
                raiz = null;
            } else {
                No pai = no.parente;
                if (pai.esquerda == no) {
                    pai.esquerda = null;
                } else {
                    pai.direita = null;
                }
                rebalancear(pai);
            }
            return;
        }
 
        if (no.esquerda != null) {
            No filho = no.esquerda;
            while (filho.direita != null) filho = filho.direita;
            no.chave = filho.chave;
            deletar(filho);
        } else {
            No filho = no.direita;
            while (filho.esquerda != null) filho = filho.esquerda;
            no.chave = filho.chave;
            deletar(filho);
        }
    }
 
    public void deletar(int delChave) {
        if (raiz == null)
            return;
 
        No filho = raiz;
        while (filho != null) {
            No no = filho;
            filho = delChave >= no.chave ? no.direita : no.esquerda;
            if (delChave == no.chave) {
                deletar(no);
                return;
            }
        }
    }
 
    private void rebalancear(No n) {
        setBalanceamento(n);
 
        if (n.balanceamento == -2) {
            if (height(n.esquerda.esquerda) >= height(n.esquerda.direita))
                n = rodarDir(n);
            else
                n = rodarEsqDir(n);
 
        } else if (n.balanceamento == 2) {
            if (height(n.direita.direita) >= height(n.direita.esquerda))
                n = rodarEsq(n);
            else
                n = rodarDirEsq(n);
        }
 
        if (n.parente != null) {
            rebalancear(n.parente);
        } else {
            raiz = n;
        }
    }
 
    private No rodarEsq(No a) {
 
        No b = a.direita;
        b.parente = a.parente;
 
        a.direita = b.esquerda;
 
        if (a.direita != null)
            a.direita.parente = a;
 
        b.esquerda = a;
        a.parente = b;
 
        if (b.parente != null) {
            if (b.parente.direita == a) {
                b.parente.direita = b;
            } else {
                b.parente.esquerda = b;
            }
        }
 
        setBalanceamento(a, b);
 
        return b;
    }
 
    private No rodarDir(No a) {
 
        No b = a.esquerda;
        b.parente = a.parente;
 
        a.esquerda = b.direita;
 
        if (a.esquerda != null)
            a.esquerda.parente = a;
 
        b.direita = a;
        a.parente = b;
 
        if (b.parente != null) {
            if (b.parente.direita == a) {
                b.parente.direita = b;
            } else {
                b.parente.esquerda = b;
            }
        }
 
        setBalanceamento(a, b);
 
        return b;
    }
 
    private No rodarEsqDir(No n) {
        n.esquerda = rodarEsq(n.esquerda);
        return rodarDir(n);
    }
 
    private No rodarDirEsq(No n) {
        n.direita = rodarDir(n.direita);
        return rodarEsq(n);
    }
 
    private int height(No n) {
        if (n == null)
            return -1;
        return n.altura;
    }
 
    private void setBalanceamento(No... no) {
        for (No n : no) {
            reAltura(n);
            n.balanceamento = height(n.direita) - height(n.esquerda);
        }
    }
 
    public void imprimirBalanceamento() {
        imprimirBalanceamento(raiz);
    }
 
    private void imprimirBalanceamento(No n) {
        if (n != null) {
            imprimirBalanceamento(n.esquerda);
            System.out.printf("%s ", n.balanceamento);
            imprimirBalanceamento(n.direita);
        }
    }
 
    private void reAltura(No no) {
        if (no != null) {
            no.altura = 1 + Math.max(height(no.esquerda), height(no.direita));
        }
    }
    
    public void emOrdem() {
            emOrdem(raiz);
        }
        protected void emOrdem(No n) {
            if (n != null) {
                 emOrdem(n.esquerda);
                 System.out.print(n.chave+" ");
                 emOrdem(n.direita);
            }
        }
        public void preOrdem() {
            preOrdem(raiz);
        }
        protected void preOrdem(No n) {
            if (n != null) {
                 System.out.print(n.chave + " ");
                 preOrdem(n.esquerda);
                 preOrdem(n.direita);
            }
        }
        public void posOrdem() {
            posOrdem(raiz);
        }
        protected void posOrdem(No n) {
            if (n != null) {
                 posOrdem(n.esquerda);
                 posOrdem(n.direita);
                 System.out.print(n.chave + " ");
            }
        }

    public boolean pesquisaBinaria(int chave){
        return pesquisaBinaria(raiz, chave);
    }    
        
    public boolean pesquisaBinaria(No raiz, int chave) { 
    
    boolean encontrado = false;
    while ((raiz != null) && !encontrado){
        int rval = raiz.chave;
        
        if (chave < rval) {
            raiz = raiz.esquerda;
        }
        else if(chave > rval){
            raiz = raiz.direita;
        }else{
            encontrado = true;
            break;
        }
        encontrado = pesquisaBinaria(raiz, chave);
    }
    return encontrado;
}
    
    public void altura(){
        System.out.println(altura(raiz));
        
    }
    
    private int altura(No n) {
		if (n == null) {
			return -1;
		}
                return n.altura;
	}
}