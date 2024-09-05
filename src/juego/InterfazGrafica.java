package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InterfazGrafica {

	private JFrame frame;
	private JFrame mainFrame;
	private Juego tablero;
	private JuegoConImg juegoConImg;
	private JButton botonVolver;
	private JButton botonAyuda;
	private JButton[][] botones;
	private JLabel cantMov;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				InterfazGrafica window = new InterfazGrafica();
				window.mainFrame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public InterfazGrafica() {
		iniciarJuego();
	}

	private void iniciarJuego() {
		crearMainFrame();
		this.tablero = new Juego();
		initialize();
	}

	private void crearMainFrame() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 600, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(null);
		panel.setBackground(Color.white);

		// Botón con el nombre del juego
		JButton nombreJuego = new JButton("ROMPECABEZAS DESLIZANTE");
		nombreJuego.setBounds(90, 30, 400, 300);
		nombreJuego.setFont(new Font("Impact", Font.BOLD, 30));
		nombreJuego.setForeground(Color.black);
		nombreJuego.setBackground(Color.white);
		nombreJuego.setBorder(BorderFactory.createEmptyBorder());
		nombreJuego.setFocusable(false);
		panel.add(nombreJuego);

		// Botón para jugar con números
		JButton playNros = new JButton("Números");
		playNros.setBounds(195, 350, 200, 30);
		playNros.setFont(new Font("Clear Sans", Font.BOLD, 20));
		playNros.setForeground(Color.BLACK);
		playNros.setBackground(new Color(187, 173, 160));
		playNros.setBorder(BorderFactory.createEmptyBorder());
		playNros.setFocusable(false);
		playNros.setContentAreaFilled(false);
		playNros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				playNros.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playNros.setForeground(Color.BLACK);
			}
		});
		playNros.addActionListener(e -> {
			mainFrame.setVisible(false);
			frame.setVisible(true);
		});
		panel.add(playNros);

		// Botón para jugar con imágenes
		JButton playImg = new JButton("Imágenes");
		playImg.setBounds(235, 380, 120, 30);
		playImg.setFont(new Font("Clear Sans", Font.BOLD, 20));
		playImg.setForeground(Color.BLACK);
		playImg.setBackground(new Color(187, 173, 160));
		playImg.setBorder(BorderFactory.createEmptyBorder());
		playImg.setFocusable(false);
		playImg.setContentAreaFilled(false);
		playImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				playImg.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playImg.setForeground(Color.BLACK);
			}
		});
		playImg.addActionListener(e -> crearSeleccionImagenesFrame());
		panel.add(playImg);

		mainFrame.getContentPane().add(panel);
	}

	private void crearSeleccionImagenesFrame() {
		JFrame seleccionImagenesFrame = new JFrame();
		seleccionImagenesFrame.setBounds(100, 100, 600, 600);
		seleccionImagenesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Asume que tienes imágenes en las rutas especificadas
		String[] rutasImagenes = { "/imagenes/1.jpg", "/imagenes/2.jpg", "/imagenes/3.jpg" };
		JPanel panel = new JPanel(new GridLayout(1, 3));

		for (String ruta : rutasImagenes) {
			try {
				InputStream imgStream = getClass().getResourceAsStream(ruta);
				if (imgStream != null) {
					Image img = ImageIO.read(imgStream);
					ImageIcon icon = new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
					JButton botonImagen = new JButton(icon);
					botonImagen.setForeground(Color.WHITE);
					botonImagen.setBackground(Color.white);
					botonImagen.setBorder(BorderFactory.createEmptyBorder());
					botonImagen.setFocusable(false);
					botonImagen.setContentAreaFilled(false);
					botonImagen.setActionCommand(ruta);
					botonImagen.addActionListener(e -> {
						seleccionImagenesFrame.dispose();
						iniciarJuegoConImagen(ruta);
					});
					panel.add(botonImagen);
				} else {
					System.err.println("No se pudo encontrar el recurso: " + ruta);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		seleccionImagenesFrame.add(panel);
		seleccionImagenesFrame.setVisible(true);
	}

	private void iniciarJuegoConImagen(String rutaImagen) {
		try {
			juegoConImg = new JuegoConImg(rutaImagen);
			frame.setVisible(true);
			mainFrame.setVisible(false);
			actualizarBotonesConImagen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.botones = new JButton[Juego.FIL][Juego.COL];
		
		
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e){
            	volverJuego();
            	
            }
		});
		frame.getContentPane().add(botonVolver, BorderLayout.NORTH);
		botonAyuda = new JButton("Ayuda");
        botonAyuda.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e){
            	tablero.getAyuda();
            	actualizarBotones();            	
            }
		});
		
		frame.getContentPane().add(botonAyuda, BorderLayout.EAST);
		
		
		
		JPanel panel = new JPanel(new GridLayout(Juego.FIL, Juego.COL));

		for (int i = 0; i < Juego.FIL; i++) {
			for (int j = 0; j < Juego.COL; j++) {
				botones[i][j] = new JButton();
				botones[i][j].setFont(new Font("Clear Sans", Font.ITALIC, 50));
				
				botones[i][j].setForeground(Color.black);
				botones[i][j].setOpaque(true);
//			    botones[i][j].setEnabled(false);
				botones[i][j].setFocusable(false);
				panel.add(botones[i][j]);
			}
		}
		

		cantMov = new JLabel("Movimientos: 0");
		frame.getContentPane().add(cantMov, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);

		actualizarBotones();

		frame.requestFocusInWindow();
		frame.addKeyListener(new KeyAdapter() {
					
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
//				case KeyEvent.VK_UP -> tablero.moverCelda(Movimiento.ARRIBA);
//				case KeyEvent.VK_DOWN -> tablero.moverCelda(Movimiento.ABAJO);
//				case KeyEvent.VK_LEFT -> tablero.moverCelda(Movimiento.IZQUIERDA);
//				case KeyEvent.VK_RIGHT -> tablero.moverCelda(Movimiento.DERECHA);
				case KeyEvent.VK_UP -> moverFicha("UP");
				case KeyEvent.VK_DOWN -> moverFicha("DOWN");
				case KeyEvent.VK_LEFT -> moverFicha("LEFT");
				case KeyEvent.VK_RIGHT -> moverFicha("RIGHT");
				

				}
			
			}
		});
		bottonsAction();
		frame.setFocusable(true);
	}
	
	private void bottonsAction() {
		for (int i = 0; i < Juego.FIL; i++) {
			for (int j = 0; j < Juego.COL; j++) {
				final int x = i;
				final int y = j;
		botones[i][j].addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e){
            	switch (nextVerification(x,y)){
                case "UP" -> moverFicha("UP");
                case "DOWN" -> moverFicha("DOWN");
                case "LEFT" -> moverFicha("LEFT");
                case "RIGHT" -> moverFicha("RIGHT");
            }
            }

			
        });
			}
		}
	}
	
	private String nextVerification(int i, int j) {
		if(juegoConImg != null) {
			if(i<3){
	             int valor1 = juegoConImg.getValor(i+1, j);
	             if (valor1 == 0){
	                 return "DOWN";
	         }    }
	         if(i>0){
	             int valor2 = juegoConImg.getValor(i-1, j);
	             if (valor2 == 0){
	                 return "UP";
	         }    }
	         if(j<3){
	             int valor3 = juegoConImg.getValor(i, j+1);
	             if (valor3 == 0){
	                 return "RIGHT";
	         }    }
	         if(j>0){
	             int valor4 = juegoConImg.getValor(i, j-1);
	             if (valor4 == 0){
	                 return "LEFT";
	         }    }
		}else {
		 if(i<3){
             int valor1 = tablero.getValor(i+1, j);
             if (valor1 == 0){
                 return "DOWN";
         }    }
         if(i>0){
             int valor2 = tablero.getValor(i-1, j);
             if (valor2 == 0){
                 return "UP";
         }    }
         if(j<3){
             int valor3 = tablero.getValor(i, j+1);
             if (valor3 == 0){
                 return "RIGHT";
         }    }
         if(j>0){
             int valor4 = tablero.getValor(i, j-1);
             if (valor4 == 0){
                 return "LEFT";
         }    }
		}

     return "";
	}

	private void moverFicha(String direccion) {
		if (juegoConImg != null) {
			switch (direccion) {
//			case "UP" -> tablero.moverCelda(Movimiento.ARRIBA);
//			case "DOWN" -> tablero.moverCelda(Movimiento.ABAJO);
//			case "LEFT" -> juegoConImg.moverCelda(Movimiento.IZQUIERDA);
//			case "RIGHT" -> juegoConImg.moverCelda(Movimiento.DERECHA);
            case "UP" -> juegoConImg.moverArriba();
            case "DOWN" -> juegoConImg.moverAbajo();
            case "LEFT" -> juegoConImg.moverIzquierda();
            case "RIGHT" -> juegoConImg.moverDerecha();
			}
			
			actualizarBotonesConImagen();
		} else {
			switch (direccion) {
			case "UP" -> tablero.moverArriba();
			case "DOWN" -> tablero.moverAbajo();
			case "LEFT" -> tablero.moverIzquierda();
			case "RIGHT" -> tablero.moverDerecha();
			}
			actualizarBotones();
		}

		if ((tablero != null && tablero.partidaGanada()) || (juegoConImg != null && juegoConImg.partidaGanada())) {
			mostrarMensajeFinPartida(true);
			reiniciarJuego();
		}
	}

	private void actualizarBotones() {
		for (int i = 0; i < Juego.FIL; i++) {
			for (int j = 0; j < Juego.COL; j++) {
				int valor = tablero.getValor(i, j);
				if (valor == 0) {
					botones[i][j].setText("");
					botones[i][j].setBackground(new Color(205, 193, 180));
				} else {
					botones[i][j].setText(String.valueOf(valor));
					botones[i][j].setBackground(Color.white);
				}
			}
		}
		cantMov.setText("Movimientos: " + tablero.cantidadDeMovRealizados());
	}

	private void actualizarBotonesConImagen() {
		for (int i = 0; i < Juego.FIL; i++) {
			for (int j = 0; j < Juego.COL; j++) {
				Image parteImagen = juegoConImg.getParteImagen(i, j);
				if (parteImagen != null) {
					botones[i][j].setIcon(new ImageIcon(parteImagen.getScaledInstance(botones[i][j].getWidth(),
							botones[i][j].getHeight(), Image.SCALE_SMOOTH)));
				} else {
					botones[i][j].setIcon(null);
				}
				botones[i][j].setText("");
			}
		}
		cantMov.setText("Movimientos: " + juegoConImg.cantidadDeMovRealizados());
	}

	private void reiniciarJuego() {
		int n = JOptionPane.showOptionDialog(frame, "¿Nuevo Juego?", "Fin del juego", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");

		if (n == JOptionPane.YES_OPTION) {
			// vuelve al menu principal
			iniciarJuego();
		
			frame.setVisible(false);
			mainFrame.setVisible(true);
		} else {
			frame.dispose();
		}
	}
	
	private void volverJuego() {
		int n = JOptionPane.showOptionDialog(frame, "Desea volver al Menu?", "Volver", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");

		if (n == JOptionPane.YES_OPTION) {
			// vuelve al menu principal
			iniciarJuego();
			
		
			frame.setVisible(false);
			mainFrame.setVisible(true);
			
		} else {
			
			JOptionPane.setRootFrame(frame);
			frame.requestFocusInWindow();
			frame.setFocusable(true);
		}
	}

	private void mostrarMensajeFinPartida(Boolean resultado) {
		if (resultado) {
			JOptionPane.showMessageDialog(frame, "¡Ganaste!");
		}
	}
}