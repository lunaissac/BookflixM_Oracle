package controller;

import Services.Encriptar;
import dao.UsuarioDao;
import model.Usuario;
import Services.SessionUtils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    //Objeto de Sesion
    private Usuario usuario = new Usuario();

    //Variables de Logeo
    private String User;
    private String Pass;

    int codBibliotecario;

    public void startSession() throws Exception {
        UsuarioDao dao;
        try {
            dao = new UsuarioDao();
            usuario = dao.startSession(User, Pass);
            codBibliotecario = Integer.parseInt(usuario.getCodigo());
            PrestamoC prestamos = new PrestamoC();
            prestamos.codigoBibliotecario(codBibliotecario);
            System.out.println("Obtengo el CODPER :"+codBibliotecario);
            if (usuario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usuario);
                switch (usuario.getNivel()) {
                    case "A":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Template/TemplateMenu.xhtml");
                        break;
                    case "B":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Template/TemplateMenu.xhtml");
                        break;
                    case "L":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Libro/Libro2.xhtml");
                        break;
                }
            } else {
                setPass(null);
                usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña / Usuario Incorrecto", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM"); // Mandamos al Login
    }

    public void securityLogin() throws IOException {
        Usuario us = SessionUtils.obtenerObjetoSesion();
        if (us != null) {
            switch (us.getNivel()) {
                case "A":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Template/TemplateMenu.xhtml");
                    break;
                case "B":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Template/TemplateMenu.xhtml");
                    break;
                case "L":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM/faces/Libro/Libro2.xhtml");
                    break;
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("/AS2018S3T05_Bookflix/faces/Template/TemplateMenu.xhtml");

            }
        }
    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }
    
    public void irLogin() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/BookflixM");
        System.out.println("Ya Mande");
    }    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
}
