package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.border.MatteBorder;

public class InterfazGrafica {

	private JFrame frame;
	private JFrame mainFrame;
	private Juego tablero;
	private JuegoConImg juegoConImg;
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
		mainFrame.setVisible(true);
		
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.white);

		// Botón con el nombre del juego
		tituloNombreDelJuego(panel);

		botonParaJugarConNumeros(panel);

		botonParaJugarConImagenes(panel);

		mainFrame.getContentPane().add(panel);
		
		BotonSalir(panel);
		
		mainFrame.setVisible(true);
			
		
	}
	
	private void BotonSalir(JPanel panel) {
		JButton btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setBorder(new MatteBorder(4,4,2,2,new Color(90,130,180)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setBorder(new MatteBorder(3,3,3,3,new Color(90,130,180)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setForeground(new Color(90, 130, 180));
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 20));
		btnSalir.setFocusable(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(90, 130, 180)));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(195, 450, 200, 35);
		panel.add(btnSalir);
		
	}

	private void tituloNombreDelJuego(JPanel panel) {
		JLabel lblNombreJuego = new JLabel("ROMPECABEZAS DESLIZANTE", JLabel.CENTER);
		lblNombreJuego.setBounds(90, 30, 400, 300);
		lblNombreJuego.setFont(new Font("Impact", Font.BOLD, 30));
		lblNombreJuego.setForeground(Color.black);
		lblNombreJuego.setOpaque(true);  // Necesario para que el fondo se vea
		lblNombreJuego.setBackground(Color.white);
		panel.add(lblNombreJuego);
	}

	private void botonParaJugarConNumeros(JPanel panel) {
		JButton btnPlayNros = new JButton("Números");
		btnPlayNros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayNros.setBounds(195, 350, 200, 35);
		btnPlayNros.setFont(new Font("Clear Sans", Font.BOLD, 20));
		btnPlayNros.setForeground(new Color(90, 130, 180));
		btnPlayNros.setBackground(new Color(187, 173, 160));
		btnPlayNros.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(90, 130, 180)));
		btnPlayNros.setFocusable(false);
		btnPlayNros.setContentAreaFilled(false);
		btnPlayNros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlayNros.setBorder(new MatteBorder(4,4,2,2,new Color(90,130,180)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPlayNros.setBorder(new MatteBorder(3,3,3,3,new Color(90,130,180)));
			}
		});
		btnPlayNros.addActionListener(e -> {
			mainFrame.setVisible(false);
			frame.setVisible(true);
			tablero.start = LocalDateTime.now();
		});
		panel.add(btnPlayNros);
	}

	private void botonParaJugarConImagenes(JPanel panel) {
		JButton btnPlayImg = new JButton("Imágenes");
		btnPlayImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayImg.setBounds(195, 400, 200, 35);
		btnPlayImg.setFont(new Font("Clear Sans", Font.BOLD, 20));
		btnPlayImg.setForeground(new Color(90, 130, 180));
		btnPlayImg.setBackground(new Color(255, 255, 255));
		btnPlayImg.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(90, 130, 180)));
		btnPlayImg.setFocusable(false);
		btnPlayImg.setContentAreaFilled(false);
		btnPlayImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlayImg.setBorder(new MatteBorder(4,4,2,2,new Color(90,130,180)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPlayImg.setBorder(new MatteBorder(3,3,3,3,new Color(90,130,180)));
			}
		});
		btnPlayImg.addActionListener(e -> crearSeleccionImagenesFrame());
		btnPlayImg.addActionListener(e -> mainFrame.dispose());
		panel.add(btnPlayImg);
	}

	private void crearSeleccionImagenesFrame() {
		JFrame seleccionImagenesFrame = new JFrame();
		seleccionImagenesFrame.setBounds(100, 100, 600, 600);
		seleccionImagenesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Asume que tienes imágenes en las rutas especificadas
		String[] rutasImagenes = { "/imagenes/1.jpg", "/imagenes/2.jpg", "/imagenes/3.jpg" };
		JPanel panel = new JPanel(new GridLayout(1, 3));

		pantallaSeleccionImagenes(seleccionImagenesFrame, rutasImagenes, panel);

		seleccionImagenesFrame.getContentPane().add(panel);
		seleccionImagenesFrame.setVisible(true);
	}

	private void pantallaSeleccionImagenes(JFrame seleccionImagenesFrame, String[] rutasImagenes, JPanel panel) {
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
		
		JButton botonVolver = botonVolverAlMenu();
		frame.getContentPane().add(botonVolver, BorderLayout.NORTH);
		
		JButton botonAyuda = botonDeAyuda();
		frame.getContentPane().add(botonAyuda, BorderLayout.EAST);
		
		JPanel panel = new JPanel(new GridLayout(Juego.FIL, Juego.COL));

		crearBotonesEnPanel(panel);
		
		agregarLabelCantidadDeMovimientosAlFrame(panel);

		actualizarBotones();

		darAccionALasFlechasDelTeclado();
		
		bottonsAction();
		
		frame.setFocusable(true);
	}

	private void darAccionALasFlechasDelTeclado() {
		frame.requestFocusInWindow();
		frame.addKeyListener(new KeyAdapter() {
					
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP -> moverFicha("UP");
				case KeyEvent.VK_DOWN -> moverFicha("DOWN");
				case KeyEvent.VK_LEFT -> moverFicha("LEFT");
				case KeyEvent.VK_RIGHT -> moverFicha("RIGHT");
				

				}
			
			}
		});
	}

	private void agregarLabelCantidadDeMovimientosAlFrame(JPanel panel) {
		cantMov = new JLabel("Movimientos: 0");
		frame.getContentPane().add(cantMov, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
	}

	private void crearBotonesEnPanel(JPanel panel) {
		this.botones = new JButton[Juego.FIL][Juego.COL];
		for (int i = 0; i < Juego.FIL; i++) {
			for (int j = 0; j < Juego.COL; j++) {
				botones[i][j] = new JButton();
				botones[i][j].setFont(new Font("Clear Sans", Font.ITALIC, 50));
				
				botones[i][j].setForeground(Color.black);
				botones[i][j].setOpaque(true);
				botones[i][j].setFocusable(false);
				panel.add(botones[i][j]);
			}
		}
	}

	private JButton botonVolverAlMenu() {
		JButton botonVolver = new JButton("Volver Al Menu");
		botonVolver.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e){
            	volverAlMenu();
            	
            }
		});
		return botonVolver;
	}

	private JButton botonDeAyuda() {
		JButton botonAyuda = new JButton("Ayuda");
        botonAyuda.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e){
            	  if (juegoConImg != null) {
                      juegoConImg.getAyuda();
                      actualizarBotonesConImagen();
                      consultaHasGanado();
                  } else {
                          tablero.getAyuda();
                          actualizarBotones();
                          consultaHasGanado();
                      }
                  
            }
		});
		return botonAyuda;
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

		consultaHasGanado();
	}

	private void consultaHasGanado() {
		if ((tablero != null && tablero.partidaGanada()) || (juegoConImg != null && juegoConImg.partidaGanada())) {
			tablero.end = LocalDateTime.now();
			//ventanaInfoGanador();
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
		//cantMov.setText("Movimientos: " + tablero.cantidadDeMovRealizados());
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
		//cantMov.setText("Movimientos: " + juegoConImg.cantidadDeMovRealizados());
	}

	private void reiniciarJuego() {
		int n = JOptionPane.showOptionDialog(frame, "¿Nuevo Juego?", "Fin del juego", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");

		if (n == JOptionPane.YES_OPTION) {
			// vuelve al menu principal y reestablece la clase juego
			if(juegoConImg != null) {
				juegoConImg = null;
				frame.dispose();
				iniciarJuego();
				frame.setVisible(false);
				mainFrame.setVisible(true);
				
			}else {
				
				iniciarJuego();
			
				frame.setVisible(false);
				mainFrame.setVisible(true);
			}
		} else {
			frame.dispose();
		}
	}
	
	private void volverAlMenu() {
		int n = JOptionPane.showOptionDialog(frame, "Desea volver al Menu?", "Volver", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sí", "No" }, "Sí");

		if (n == JOptionPane.YES_OPTION) {
			// vuelve al menu principal y reestablece la clase juego
			if(juegoConImg != null) {
				juegoConImg = null;
				iniciarJuego();
				frame.setVisible(false);
				mainFrame.setVisible(true);
				
			}else {
				tablero = new Juego();
				bottonsAction();
				darAccionALasFlechasDelTeclado();
				actualizarBotones();
				frame.setFocusable(true);
				frame.setVisible(false);
				mainFrame.setVisible(true);
			}
		} else {
			
			JOptionPane.setRootFrame(frame);
			frame.requestFocusInWindow();
			frame.setFocusable(true);
		}
	}
	
	private void mostrarMensajeFinPartida(Boolean resultado) {
		if (resultado) {
			int tiempoDeJuego = (int) ChronoUnit.SECONDS.between(tablero.start, tablero.end);
			JOptionPane.showMessageDialog(frame, "Completaste el rompecabezas en " + tiempoDeJuego + " segundos"
					+ "\nhaciendo " + tablero.cantidadDeMovRealizados() + " movimientos",  "¡Ganaste!",JOptionPane.PLAIN_MESSAGE);
			
		}
	}
}