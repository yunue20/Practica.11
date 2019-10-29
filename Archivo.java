import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Archivo extends JFrame implements ActionListener{
	private JTextField campoEntrada;
	private JTextArea areaSalida;

	public Archivo(){
		super("Prueba de la clase File");

		campoEntrada = new JTextField("Escriba aqui el nombre del archivo o directorio");
		campoEntrada.addActionListener(this);
		areaSalida = new JTextArea();

		ScrollPane panelDesplazable = new ScrollPane();
		panelDesplazable.add(areaSalida);

		Container contenedor = getContentPane();
		contenedor.add(campoEntrada, BorderLayout.NORTH);
		contenedor.add(panelDesplazable, BorderLayout.CENTER);
		setSize(400,400);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent eventoAccion){
		File nombre = new File(eventoAccion.getActionCommand());

		if (nombre.exists()){
			areaSalida.setText(nombre.getName() + " existe\n" +
				(nombre.isFile() ? "es un archivo\n" : "no es un archivo\n") +
				(nombre.isDirectory() ? "es un directorio\n" : "no es un directorio\n") +
				(nombre.isAbsolute() ? "es una ruta absoluta\n" : "no es una ruta absoluta\n") +
				"Ultima modificacion: " + nombre.lastModified() + 
				"\nLongitud: " + nombre.length() +
				"\nRuta: " + nombre.getPath() + 
				"\nRuta absoluta: " + nombre.getAbsolutePath() + 
				"\nPadre: " + nombre.getParent());

			if(nombre.isFile()){
				try{
					BufferedReader entrada = new BufferedReader(new FileReader(nombre));
					StringBuffer bufer = new StringBuffer();
					String texto;
					areaSalida.append("\n\n");

					while((texto = entrada.readLine()) != null){
						bufer.append(texto + "\n");
					}

					areaSalida.append(bufer.toString());
				}catch(IOException excepcion){
					JOptionPane.showMessageDialog(this, "ERROR EN ARCHIVO", "ERROR EN ARCHIVO", JOptionPane.ERROR_MESSAGE);
				}
			}else if (nombre.isDirectory()){
				String directorio[] = nombre.list();
				areaSalida.append("\n\nContenido del directorio:\n");
				for(int i = 0; i < directorio.length; i++){
					areaSalida.append(directorio[i] + "\n");
				}
			}else{
				JOptionPane.showMessageDialog(this, eventoAccion.getActionCommand() + " no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}	

	public static void main(String[] args){
		Archivo aplicacion = new Archivo();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}