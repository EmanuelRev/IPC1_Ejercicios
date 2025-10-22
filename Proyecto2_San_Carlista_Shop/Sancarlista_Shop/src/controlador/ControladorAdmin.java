package controlador;

import modelo.Usuario;
import modelo.Vendedor;
import vista.VistaActualizarVendedor;
import vista.VistaAdmin;
import vista.VistaGestionVendedores;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utilidades.Validaciones;


//Actualizando metodos
public class ControladorAdmin {

    private VistaAdmin vistaAdmin;
    private VistaGestionVendedores vistaVendedores;
    private VistaActualizarVendedor vistaActualizarVendedor;
    private Usuario[] usuarios;
    private int totalUsuarios;

    public ControladorAdmin(VistaAdmin vistaAdmin, Usuario[] usuarios, int totalUsuarios) {
        this.vistaAdmin = vistaAdmin;
        this.usuarios = usuarios;
        this.totalUsuarios = totalUsuarios;
        configurarListenerAdmin();
    }

    public void configurarListenerAdmin() {
        vistaAdmin.setGestionVendedoresL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionVendedores();
            }
        });

        vistaAdmin.setCerrarSesionL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private void abrirGestionVendedores() {
        System.out.println("Abreindo Operaciones de Vendedores...");
        vistaVendedores = new VistaGestionVendedores();
        configurarListenerVendedores();
        actualizarListaVendedores();
        vistaAdmin.ocultar();
        vistaVendedores.mostrar();
    }

    private void configurarListenerVendedores() {
        vistaVendedores.setCrearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVendedor();
            }
        });

        vistaVendedores.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenu();
            }
        });

        // actualizando metodos x_xx
        vistaVendedores.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirActualizarVendedor();
            }
        });

        vistaVendedores.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVendedor();
            }
        });
    }

    private void abrirActualizarVendedor() {
        vistaActualizarVendedor = new VistaActualizarVendedor();
        configurarListenerActualizarVendedor();
        vistaActualizarVendedor.mostar();
    }

    private void configurarListenerActualizarVendedor() {
        vistaActualizarVendedor.setBuscarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVendedorParaActualizar();
            }
        });
        vistaActualizarVendedor.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVendedor();
            }
        });
        vistaActualizarVendedor.setCancelarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaActualizarVendedor.ocultar();
            }
        });
    }

    private void buscarVendedorParaActualizar() {
        String codigo = vistaActualizarVendedor.getCodigoBuscar();
        if (!utilidades.Validaciones.validarCampoVacio(codigo, "Codigo")) {
            return;
        }
        Usuario usuario = buscarUsuario(codigo);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            vistaActualizarVendedor.setDatosVendedor(vendedor.getCodigo(),
                    vendedor.getNombre(),
                    vendedor.getGenero(),
                    vendedor.getContrasenia());
            utilidades.Validaciones.mostrarExito("Vendedor Encontrado, puede añadir o cambiar datos.");
        } else {
            utilidades.Validaciones.mostrarError("No se encontro un vendedor con ese codigo intente de nuevo.");
            vistaActualizarVendedor.limpiarFormulario();
        }
    } // aqui coloque una llave ya no tiroe error revisar

    private void actualizarVendedor() {
        String codigoOriginal = vistaActualizarVendedor.getCodigoOriginal();
        String nuevoNombre = vistaActualizarVendedor.getNuevoNombre();
        String nuevoGenero = vistaActualizarVendedor.getNuevoGenero();
        String nuevaContrasenia = vistaActualizarVendedor.getNuevaContrasenia();

        // validando datos
        if (!utilidades.Validaciones.validarCampoVacio(nuevoNombre, "nombre") ||
                !utilidades.Validaciones.validarCampoVacio(nuevoGenero, "genero") ||
                !utilidades.Validaciones.validarCampoVacio(nuevaContrasenia, "Contrasenia")) {
            return;
        }
        if (!utilidades.Validaciones.validarGenero(nuevoGenero)) {
            return;
        }
        if (!utilidades.Validaciones.validarContrasenia(nuevaContrasenia)) {
            return;
        }

        Usuario usuario = buscarUsuario(codigoOriginal);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            vendedor.setNombre(nuevoNombre);
            vendedor.setGenero(nuevoGenero);
            vendedor.setContrasenia(nuevaContrasenia);
            utilidades.Validaciones.mostrarExito("Vendedor Actualizado Correctamente");
            vistaActualizarVendedor.ocultar();
            actualizarListaVendedores();
        }
    }

    //Metodo para mata vendedor jaja


    private void eliminarVendedor() {
        String codigo = JOptionPane.showInputDialog("Ingrese el Codigo del vendedor que desea eliminar: ");
        if (codigo == null || codigo.trim().isEmpty()) {
            return;
        }
        codigo = codigo.trim();
        Usuario usuario = buscarUsuario(codigo);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            if (utilidades.Validaciones.confirmarEliminacion(vendedor.getNombre())) {
                for (int i = 0; i < totalUsuarios; i++) {
                    if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                        for (int j = i; j < totalUsuarios - 1; j++) {
                            usuarios[j] = usuarios[j + 1];
                        }
                        usuarios[totalUsuarios - 1] = null;
                        totalUsuarios--;
                        utilidades.Validaciones.mostrarExito("Vendedeor eliminado Exitosamente");
                        actualizarListaVendedores();
                        return;
                    }
                }
            }
        } else {
            utilidades.Validaciones.mostrarError("No se encontro un vendedor con ese codigo.");
        }
    }

    private void crearVendedor() {
        String codigo = vistaVendedores.getCodigo();
        String nombre = vistaVendedores.getNombre();
        String genero = vistaVendedores.getGenero();
        String contrasenia = vistaVendedores.getContrasenia();
        System.out.println("Creando Vendedor..." + codigo);
        if (codigo.isEmpty() || nombre.isEmpty() || genero.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completar Todos los campos");
            return;
        }
        if (!genero.equals("M") && !genero.equals("F")) {
            JOptionPane.showMessageDialog(null, "Genero debe ser Masculino o Femenino");
            return;
        }
        if (buscarUsuario(codigo) != null) {
            JOptionPane.showMessageDialog(null, "El Codigo" + codigo + "Ya Existe");
            return;
        }
        Vendedor nuevoVendedor = new Vendedor(codigo, nombre, genero, contrasenia);
        usuarios[totalUsuarios] = nuevoVendedor;
        totalUsuarios++;
        vistaVendedores.limpiarFormulario();
        actualizarListaVendedores();
        JOptionPane.showMessageDialog(null, "Vendedor Creado Exitosamente:\n" + "Codigo: " + codigo + "\n" + "Nombre: " + nombre);
        System.out.println("Vendedor Creado: " + codigo);
    }

    private void actualizarListaVendedores() {
        StringBuilder lista = new StringBuilder("---Vendedores Regsitrados----\n\n");
        boolean hayVendedores = false;
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] instanceof Vendedor) {
                Vendedor v = (Vendedor) usuarios[i];
                lista.append("Código: ").append(v.getCodigo())
                        .append(" | Nombre: ").append(v.getNombre())
                        .append(" | Género: ").append(v.getGenero())
                        .append("\n");
                hayVendedores = true;
            }
        }
        if (!hayVendedores) {
            lista.append("No hay Vendedores Registrados :c\n");
            lista.append("Para Crear Usuario de Vendedor llenar Formulario.");
        }
        vistaVendedores.mostrarVendedores(lista.toString());
    }

    private Usuario buscarUsuario(String codigo) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    private void regresarMenu() {
        System.out.println("Regresando al Menu Principal...");
        vistaVendedores.ocultar();
        vistaAdmin.mostrar();
    }

    private void cerrarSesion() {
        System.out.println("Cerrando Sesion del Administrador..");
        vistaAdmin.ocultar();
        JOptionPane.showMessageDialog(null, "Sesion Cerrada Exitosamente");
    }
}
