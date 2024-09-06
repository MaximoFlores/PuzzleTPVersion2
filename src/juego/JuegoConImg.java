package juego;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class JuegoConImg extends Juego {
	private BufferedImage imagenOriginal;
	private Image[][] partesImagen;

	public JuegoConImg(String rutaImagen) throws IOException {
		super();
		InputStream imgStream = getClass().getResourceAsStream(rutaImagen);
		imagenOriginal = ImageIO.read(imgStream);
		partesImagen = new Image[FIL][COL];
		dividirImagen();
	}

	private void dividirImagen() {
		int anchoParte = imagenOriginal.getWidth() / COL;
		int altoParte = imagenOriginal.getHeight() / FIL;

		for (int f = 0; f < FIL; f++) {
			for (int c = 0; c < COL; c++) {
				if (f == FIL - 1 && c == COL - 1) {
					partesImagen[f][c] = null; // Casilla vacía
				} else {
					partesImagen[f][c] = imagenOriginal.getSubimage(c * anchoParte, f * altoParte, anchoParte,
							altoParte);
				}
			}
		}
	}

	public Image getParteImagen(int fila, int columna) {
		int valor = getValor(fila, columna);
		if (valor == 0) {
			return null;
		}
		int f = (valor - 1) / COL;
		int c = (valor - 1) % COL;
		return partesImagen[f][c];
	}

	@Override
	public boolean partidaGanada() {
		if (!super.partidaGanada()) {
			return false;
		}

		// Ahora verifica si la imagen también está en orden
		for (int f = 0; f < FIL; f++) {
			for (int c = 0; c < COL; c++) {
				Image parteImagen = partesImagen[f][c];
				if (parteImagen != null) {
					int valor = getValor(f, c);
					int fEsperada = (valor - 1) / COL;
					int cEsperada = (valor - 1) % COL;

					if (f != fEsperada || c != cEsperada) {
						return false;
					}
				}
			}
		}
		return true;
	}

}