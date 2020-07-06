package com.unal.una_huella.UNaHuellaLauncher.Entities;


import com.unal.una_huella.UNaHuellaLauncher.ED.DoubleLinkedList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @Size(min = 2, max = 30, message = "Documento inválido")
    private String id_usuario;

    @Column(name = "PRIMER_NOMBRE")
    @NotBlank
    @Size(min = 2, max = 30, message = "Campo obligatorio")
    private String a_primer_nombre;

    @Column(name = "PRIMER_APELLIDO")
    @NotBlank
    @Size(min = 2, max = 30, message = "Campo obligatorio")
    private String b_primer_apellido;

    @Column(name = "DIRECCION")
    @NotBlank
    @Size(min = 2, max = 70, message = "Campo obligatorio")
    private String c_direccion;

    @Column(name = "TELEFONO")
    @NotBlank
    @Size(min = 7, max = 11, message = "Campo obligatorio")
    private String d_telefono;

    @Column(name = "SEGUNDO_NOMBRE", nullable = true)
    @Size(max = 30, message = "Máximo 30 caracteres")
    private String e_segundo_nombre;

    @Column(name = "SEGUNDO_APELLIDO", nullable = true)
    @Size(max = 30, message = "Máximo 30 caracteres")
    private String f_segundo_apellido;

    @Column(name = "CORREO")
    @NotBlank
    @Size(min = 6, max = 50, message = "Campo obligatorio")
    private String g_correo;

    // ATRIBUTOS PROPIOS DE PARTICULAR
    @Column(name = "CANTIDAD_MASCOTAS_INSCRITAS", nullable = true)
    private int h_cantidad_mascotas;
    @Column(name = "ESTRATO", nullable = true)
    @Size (min = 1, max = 1, message = "Estrato mínimo 0, máximo 6")
    private String i_estrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "i_id_dueño")
    @Column(name = "MIS_MASCOTAS")
    private List<Mascota> mismascotas;

    // ATRIBUTOS PROPIOS DE GESTOR
    @Column(name = "FUNCIONES_GESTOR", nullable = true)
    @Size (min = 0, max = 50, message = "Máximo 50 carácteres")
    private String j_funciones;
    @Column(name = "NIVEL_ACCESO_GESTOR", nullable = true)
    @Size (min = 1, max = 1, message = "Mínimo 1, máximo 5")
    private String k_nivel_acceso;

    // ATRIBUTOS PROPIOS DE VETERINARIO
    @Column(name = "TARJETA_PROFESIONAL", nullable = true)
    @Size (min = 8, max = 20, message = "Máximo 20 carácteres")
    private String l_num_tarjetaprof;
    @Column(name = "ESPECIALIZACION", nullable = true)
    @Size (min = 0, max = 50, message = "Máximo 50 carácteres")
    private String m_especializacion;
    @Column(name = "EXPERIENCIA_VET", nullable = true)
    private int n_anos_experiencia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "VET_JORNADA",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "jornada_id"))
    private List<Jornada> o_jornadas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "VET_LUGAR",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "lugar_id"))
    private List<Lugar> p_lugares;

    @Column
    @NotBlank
    @Size(min = 4, max = 60, message = "Mínimo 5 caracteres - Máximo 60 caracteres")
    private String password;

    @Column
    private int role;

    @Transient
    private String confirmPassword;

    @Size
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getA_primer_nombre() {
        return a_primer_nombre;
    }

    public void setA_primer_nombre(String a_primer_nombre) {
        this.a_primer_nombre = a_primer_nombre;
    }

    public String getB_primer_apellido() {
        return b_primer_apellido;
    }

    public void setB_primer_apellido(String b_primer_apellido) {
        this.b_primer_apellido = b_primer_apellido;
    }

    public String getC_direccion() {
        return c_direccion;
    }

    public void setC_direccion(String c_direccion) {
        this.c_direccion = c_direccion;
    }

    public String getD_telefono() {
        return d_telefono;
    }

    public void setD_telefono(String d_telefono) {
        this.d_telefono = d_telefono;
    }

    public String getE_segundo_nombre() {
        return e_segundo_nombre;
    }

    public void setE_segundo_nombre(String e_segundo_nombre) {
        this.e_segundo_nombre = e_segundo_nombre;
    }

    public String getF_segundo_apellido() {
        return f_segundo_apellido;
    }

    public void setF_segundo_apellido(String f_segundo_apellido) {
        this.f_segundo_apellido = f_segundo_apellido;
    }

    public String getG_correo() {
        return g_correo;
    }

    public void setG_correo(String g_correo) {
        this.g_correo = g_correo;
    }

    public int getH_cantidad_mascotas() {
        return h_cantidad_mascotas;
    }

    public void setH_cantidad_mascotas(int h_cantidad_mascotas) {
        this.h_cantidad_mascotas = h_cantidad_mascotas;
    }

    public String getI_estrato() {
        return i_estrato;
    }

    public void setI_estrato(String i_estrato) {
        this.i_estrato = i_estrato;
    }

    public String getJ_funciones() {
        return j_funciones;
    }

    public void setJ_funciones(String j_funciones) {
        this.j_funciones = j_funciones;
    }

    public String getK_nivel_acceso() {
        return k_nivel_acceso;
    }

    public void setK_nivel_acceso(String k_nivel_acceso) {
        this.k_nivel_acceso = k_nivel_acceso;
    }

    public String getL_num_tarjetaprof() {
        return l_num_tarjetaprof;
    }

    public void setL_num_tarjetaprof(String l_num_tarjetaprof) {
        this.l_num_tarjetaprof = l_num_tarjetaprof;
    }

    public String getM_especializacion() {
        return m_especializacion;
    }

    public void setM_especializacion(String m_especializacion) {
        this.m_especializacion = m_especializacion;
    }

    public int getN_anos_experiencia() {
        return n_anos_experiencia;
    }

    public void setN_anos_experiencia(int n_anos_experiencia) {
        this.n_anos_experiencia = n_anos_experiencia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.role =  roles.get(0).getId().intValue();
        this.roles = roles;
    }

    public List<Mascota> getMismascotas() {
        return mismascotas;
    }

    public void setMismascotas(List<Mascota> mismascotas) {
        this.mismascotas = mismascotas;
    }

    public List<Jornada> getO_jornadas() {
        return o_jornadas;
    }

    public void setO_jornadas(List<Jornada> o_jornadas) {
        this.o_jornadas = o_jornadas;
    }

    public List<Lugar> getP_lugares() {
        return p_lugares;
    }

    public void setP_lugares(List<Lugar> p_lugares) {
        this.p_lugares = p_lugares;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario='" + id_usuario + '\'' +
                ", a_primer_nombre='" + a_primer_nombre + '\'' +
                ", b_primer_apellido='" + b_primer_apellido + '\'' +
                ", c_direccion='" + c_direccion + '\'' +
                ", d_telefono='" + d_telefono + '\'' +
                ", e_segundo_nombre='" + e_segundo_nombre + '\'' +
                ", f_segundo_apellido='" + f_segundo_apellido + '\'' +
                ", g_correo='" + g_correo + '\'' +
                ", h_cantidad_mascotas=" + h_cantidad_mascotas +
                ", i_estrato='" + i_estrato + '\'' +
                ", mismascotas=" + mismascotas +
                ", j_funciones='" + j_funciones + '\'' +
                ", k_nivel_acceso='" + k_nivel_acceso + '\'' +
                ", l_num_tarjetaprof='" + l_num_tarjetaprof + '\'' +
                ", m_especializacion='" + m_especializacion + '\'' +
                ", n_anos_experiencia=" + n_anos_experiencia +
                ", o_jornadas=" + o_jornadas +
                ", p_lugares=" + p_lugares +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles +
                '}';
    }
}
