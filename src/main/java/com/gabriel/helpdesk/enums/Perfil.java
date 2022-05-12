package com.gabriel.helpdesk.enums;

public enum Perfil {
    ADMIN(0, "ADMINISTRADOR"), CLIENTE(1, "CLIENTE"), TECNICO(2, "TECNICO");

    private Integer codigo;
    private String descicao;

    Perfil(int codigo, String descicao) {
        this.codigo = codigo;
        this.descicao = descicao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescicao() {
        return descicao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) return null;

        for (Perfil p : Perfil.values()) {
            if (cod.equals(p.getCodigo())) {
                return p;
            }
        }

        throw new IllegalArgumentException("Perfil inválido!");
    }

}
