package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.ClienteController;
import vallegrande.edu.pe.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private ClienteController controller = new ClienteController();
    private JTextField txtNom, txtApe, txtDni, txtCor, txtTel, txtPai;

    // Colores definidos para el diseño
    private Color azulCabecera = new Color(234, 99, 86);
    private Color rojoTitulo = new Color(168, 51, 40);

    public ClienteView() {
        setTitle("Mantenimiento de Clientes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // --- TÍTULO ROJO VINO ---
        JLabel titulo = new JLabel("LISTADO DE USUARIOS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(rojoTitulo);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // --- TABLA CON CABECERA AZUL ---
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "DNI", "Correo", "Teléfono", "País"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setGridColor(new Color(230, 230, 230));

        // Estilo de la Cabecera
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(azulCabecera);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setPreferredSize(new Dimension(100, 40));

        // Centrar datos de la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scrollPane.getViewport().setBackground(Color.WHITE);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // --- PANEL DE DATOS (FORMULARIO) ---
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Color.WHITE);

        JPanel pnlCampos = new JPanel(new GridLayout(2, 6, 10, 10));
        pnlCampos.setBackground(Color.WHITE);
        pnlCampos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Datos del Cliente"));

        txtNom = new JTextField(); txtApe = new JTextField(); txtDni = new JTextField();
        txtCor = new JTextField(); txtTel = new JTextField(); txtPai = new JTextField();

        // Agregar etiquetas y campos
        pnlCampos.add(new JLabel(" Nombre:", JLabel.RIGHT)); pnlCampos.add(txtNom);
        pnlCampos.add(new JLabel(" Apellidos:", JLabel.RIGHT)); pnlCampos.add(txtApe);
        pnlCampos.add(new JLabel(" DNI:", JLabel.RIGHT)); pnlCampos.add(txtDni);
        pnlCampos.add(new JLabel(" Correo:", JLabel.RIGHT)); pnlCampos.add(txtCor);
        pnlCampos.add(new JLabel(" Teléfono:", JLabel.RIGHT)); pnlCampos.add(txtTel);
        pnlCampos.add(new JLabel(" País:", JLabel.RIGHT)); pnlCampos.add(txtPai);

        // --- BOTONES ---
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlBotones.setBackground(Color.WHITE);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        // Estilo rápido a botones
        btnAgregar.setPreferredSize(new Dimension(120, 35));
        btnEliminar.setPreferredSize(new Dimension(120, 35));
        btnVolver.setPreferredSize(new Dimension(120, 35));

        // Acciones
        btnAgregar.addActionListener(e -> {
            controller.agregarCliente(txtNom.getText(), txtApe.getText(), txtDni.getText(),
                    txtCor.getText(), txtTel.getText(), txtPai.getText());
            cargarDatos();
            limpiarCampos();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) modelo.getValueAt(fila, 0);
                controller.eliminarCliente(id);
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila");
            }
        });

        btnVolver.addActionListener(e -> { new InicioView().setVisible(true); dispose(); });

        pnlBotones.add(btnAgregar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnVolver);

        panelInferior.add(pnlCampos, BorderLayout.CENTER);
        panelInferior.add(pnlBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        List<Cliente> lista = controller.obtenerClientes();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getApellidos(), c.getDni(), c.getCorreo(), c.getTelefono(), c.getPais()});
        }
    }

    private void limpiarCampos() {
        txtNom.setText(""); txtApe.setText(""); txtDni.setText("");
        txtCor.setText(""); txtTel.setText(""); txtPai.setText("");
    }
}