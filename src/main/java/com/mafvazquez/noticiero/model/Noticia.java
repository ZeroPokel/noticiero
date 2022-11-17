package com.mafvazquez.noticiero.model;

public class Noticia {
    
    private int codigo;
    private String titular;
    private String descripcion;
    
    public Noticia() {
    }

    public Noticia(int codigo) {
        this.codigo = codigo;
    }

    public Noticia(int codigo, String titular, String descripcion) {
        this.codigo = codigo;
        this.titular = titular;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Noticia other = (Noticia) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    
}
