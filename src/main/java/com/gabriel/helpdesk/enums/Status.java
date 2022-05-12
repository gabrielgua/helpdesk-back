package com.gabriel.helpdesk.enums;

public enum Status {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descicao;

    Status(int codigo, String descicao) {
        this.codigo = codigo;
        this.descicao = descicao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescicao() {
        return descicao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) return null;

        for (Status p : Status.values()) {
            if (cod.equals(p.getCodigo())) {
                return p;
            }
        }

        throw new IllegalArgumentException("Status inválido!");
    }

}
