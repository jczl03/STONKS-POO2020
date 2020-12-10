package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controlador.Coordinador;

public class VentanaLogin extends JDialog implements ActionListener {
	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JPasswordField campoPass;
    private javax.swing.JComboBox<String> comboUsuarios;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelUser;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JLabel tituloLogin;
    // End of variables declaration//GEN-END:variables
    
    private Coordinador miCoordinador;

    /**
     * Creates new form VentanaLogin
     */
    public VentanaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Login");
        setSize(416,419);
        setLocationRelativeTo(null);//Inicia en el cento de la pantalla
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
        	
        	@Override
        	public void windowClosing(WindowEvent e) {
        		Object[] opciones = {"Continuar","Cerrar"};
        		int n = JOptionPane.showOptionDialog(null, "Seleccione un usuario por favor\nAl cerrar esta ventana la aplicación terminar",
        				"Confirme Por favor",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,opciones,opciones[0]);
        		if(n==JOptionPane.YES_OPTION) {
        			
        		}else if(n==JOptionPane.NO_OPTION) {
        			System.exit(0);//Cerrar sistema
        		}
        	}
        	
        	
		});
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        tituloLogin = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        labelPass = new javax.swing.JLabel();
        labelUser = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        comboUsuarios = new javax.swing.JComboBox<>();
        campoPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        panelLogin.setBackground(new java.awt.Color(204, 204, 204));
        panelLogin.setLayout(null);

        tituloLogin.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tituloLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLogin.setText("Ventana Login");
        panelLogin.add(tituloLogin);
        tituloLogin.setBounds(50, 30, 290, 60);

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Seguridad.png"))); // NOI18N
        panelLogin.add(imagen);
        imagen.setBounds(50, 110, 290, 90);

        labelPass.setText("Pass");
        panelLogin.add(labelPass);
        labelPass.setBounds(60, 270, 60, 14);
        labelPass.setVisible(false);

        labelUser.setText("Usuario");
        panelLogin.add(labelUser);
        labelUser.setBounds(60, 220, 60, 14);

        botonAceptar.setText("Aceptar");
        panelLogin.add(botonAceptar);
        botonAceptar.setBounds(160, 320, 90, 30);
        botonAceptar.addActionListener(this);

        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Administrador", "Usuario" }));
        panelLogin.add(comboUsuarios);
        comboUsuarios.setBounds(140, 220, 190, 30);
        comboUsuarios.addActionListener(this);
        
        panelLogin.add(campoPass);
        campoPass.setBounds(140, 270, 190, 30);
        campoPass.setVisible(false);

        getContentPane().add(panelLogin);
        panelLogin.setBounds(0, 0, 400, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

////////////////////////////////////////////////////////////////////////////////////////
    
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {//Accion de eventos
		
		if(evento.getSource()==comboUsuarios) {
			mostrarElementos();
		}
		
		if(evento.getSource()==botonAceptar) {
			String respuesta=miCoordinador.validarIngreso(comboUsuarios.getSelectedIndex(),campoPass.getText());
			if(respuesta.equals("error")) {
				JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario");
			}else if(respuesta.equals("Pass invalido")) {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectas, por favor verifique credenciales");
			
			}else {
				JOptionPane.showMessageDialog(null, "Bienvenido "+respuesta);
				miCoordinador.asignarPrivilegios(respuesta);
				miCoordinador.cerrarVentanaLogin();
			}
			
		}
	}

	private void mostrarElementos() {//Metodo filtro ense�ar campos pass
		String seleccion = (String) comboUsuarios.getSelectedItem();
		int index = comboUsuarios.getSelectedIndex();
		if(index==0) {
			labelPass.setVisible(false);
			campoPass.setVisible(false);
		}else {
			labelPass.setVisible(true);
			campoPass.setVisible(true);
		}
	}
}
