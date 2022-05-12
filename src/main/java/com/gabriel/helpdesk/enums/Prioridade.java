package com.gabriel.helpdesk.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private Integer codigo;
    private String descicao;

    Prioridade(int codigo, String descicao) {
        this.codigo = codigo;
        this.descicao = descicao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescicao() {
        return descicao;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) return null;

        for (Prioridade p : Prioridade.values()) {
            if (cod.equals(p.getCodigo())) {
                return p;
            }
        }

        throw new IllegalArgumentException("Prioridade inválida!");
    }

}
