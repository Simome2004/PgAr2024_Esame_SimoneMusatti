public enum Carta {
    BANG("Bang", 1, 1),
    MANCATO("Mancato", 1, 6),
    SCHOFIELD("Schofield", 2, 2),
    REMINGTHON ("Reminghton", 2, 3),
    REV_CARABINE("Rev Carabine", 2, 4),
    WINCHESTER("Winchester", 2, 5);


    private final String nome;
    private final int tipo;
    private final int effetto;

    /* legenda tipi: 1 gioca e scarta ... 2 equipaggiabile
    legenda effetti: 1 infligge 1 danno, 2 può attaccare a distanza 2, 3 a distanza 3 e così via fino a 5, 6 è mancato
    0 è


     */

    Carta(String nome, int tipo, int effetto) {
        this.nome = nome;
        this.tipo = tipo;
        this.effetto = effetto;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }

    public int getEffetto() {
        return effetto;
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + "): " + effetto;
    }
}
