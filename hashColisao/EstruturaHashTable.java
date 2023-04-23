package hashColisao;

public class EstruturaHashTable implements EstruturaDeDados{
    private Integer tabela[];

    public EstruturaHashTable() {
        // 1000 primeiros índices para as chaves e 100 últimos para o porão
        tabela = new Integer[1100];

        for (int i=0; i<tabela.length; i++) {
            tabela[i] = -1;     // -1 indica que a posição está vazia
        }
    }

    @Override
    public boolean insert(int chave) {
        int keyHash = hash(chave);

        if (tabela[keyHash] == -1 || tabela[keyHash] == -2) {
            tabela[keyHash] = chave;
            return true;

        } else {
            for (int i=1000; i<1100; i++) {
                if (tabela[i] == -1 || tabela[i] == -2) {
                    tabela[i] = chave;
                    break;
                }
            }
            return true;
        }
    }

    @Override
    public boolean delete(int chave) {
        int keyHash = hash(chave);

        if (tabela[keyHash] != -1 && tabela[keyHash] == chave) {
            tabela[keyHash] = -2;   // -2 indica que a posição está disponível depois de um item ser removido
            return true;
        
        } else if (tabela[keyHash] != -1 && tabela[keyHash] != chave) {
            for (int i=1000; i<1100; i++) {
                if (tabela[i] == chave) {
                    tabela[i] = -2;
                    return true;
                }
            }
            return false;

        } else {
            return false;
        }
    }

    @Override
    public int search(int chave) {
        int keyHash = hash(chave);

        if (tabela[keyHash] != -1 && tabela[keyHash] == chave) {
            return keyHash;
        
        } else if (tabela[keyHash] != -1 && tabela[keyHash] != chave) {
            for (int i=1000; i<1100; i++) {
                if (tabela[i] == chave) {
                    return i;
                }
                if (tabela[i] == -1) {
                    return -1;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    private int hash(int key) {
        return key % 1000;
    }
}
