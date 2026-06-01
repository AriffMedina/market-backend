package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name = "id_categoria")
    private int idCategoria;

    private String descripcion;

    private boolean estado;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
