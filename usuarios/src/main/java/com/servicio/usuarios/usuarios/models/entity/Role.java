package com.servicio.usuarios.usuarios.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
