package ia201020415;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Marco
 */
public class ProcesamientoImagen {

    public int origenX = 0;
    public int origenY = 0;
    public int destinoX = 0;
    public int destinoY = 0;
    //Imagen actual que se ha cargado
    public BufferedImage imageActual;
    //Matrices utilizadas
    int[][] mat;
    nodoastar[][] nodos_abiertos;
    nodoastar[][] nodos_cerrados;
    //Nodo destino
    public nodoastar destinof;
    //Arraylist de nodos abiertos o por expandir
    ArrayList<nodoastar> abiertos = new ArrayList();
    //Array de nodos cerrados o nodos ya visitados 
    ArrayList<nodoastar> cerrados = new ArrayList();

    public void ProcesamientoImagen() {
        imageActual = null;
        this.abiertos = new ArrayList();
        this.cerrados = new ArrayList();
    }

    //Operaciones
    public void setOrigen(int x, int y) {
        this.origenX = x;
        this.origenY = y;
    }

    public void setDestino(int x, int y) {
        this.destinoX = x;
        this.destinoY = y;
    }

    public nodoastar isInAbiertos(int x, int y) {
        nodoastar r = null;
        for (nodoastar abierto : abiertos) {
            if (abierto.x == x && abierto.y == y) {
                r = abierto;
                break;
            }
        }

        return r;
    }

    public nodoastar isInCerrados(int x, int y) {
        nodoastar r = null;
        for (nodoastar cerrado : cerrados) {
            if (cerrado.x == x && cerrado.y == y) {
                r = cerrado;
                break;
            }
        }

        return r;
    }

    public void inicializarAlgoritmoAstar() {
        try {
            //Inserta nodo inicial en la lista de abiertos
            nodoastar inicial = new nodoastar(null, this.origenX, this.origenY, this.destinoX, this.destinoY);
            inicial.g = 0;
            abiertos.add(inicial);
            nodos_cerrados[this.origenX][this.origenY] = inicial;
            //Ordenar por el f mas pequeno
            Collections.sort(abiertos);
            //obtener el f mas pequeno
            nodoastar minimo = abiertos.get(0);
            abiertos.remove(0);
            cerrados.add(minimo);
            //Metodo iterativo que ejecuta el mientras estado evaluado no es el final y la lsita de abiertos no es vacia se sigue expandiendo los nodos
            generarSucesores(minimo);
            //inicializa Variables para una proxima iteraci[on del algoritmo
            this.abiertos.clear();
            this.cerrados.clear();
        } catch (Exception e) {
            System.out.println("Error preparaAnalisis: " + e.getMessage() + " >>> " + e.toString());
            e.printStackTrace();
        }
    }

    public void generarSucesores(nodoastar padre) {
        int x = this.origenX;
        int y = this.origenY;
        int alto = mat[0].length - 1;
        int ancho = mat.length - 1;

        nodoastar inicial = padre;
        int num = 1;
        while (true) {
        //Verifica los limites del mapa y obstaculos

            //Nodo mas a la derecha
            int valx = x + num;
            int valy = y;
            //si la posicion x evaluada es FminimoNodo al ancho del laberinto
            if (valx <= ancho) {
                //Evalua si en la posicion x,y evaluada la imagen puede o no pasar por ese punto
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular costos
                        //Costo de llegar del nodo inicial al nodo actual
                        nodoAdyacente.g = 10 + inicial.g;
                        //costo de llegada del nodo actual al nodo objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        //Ingresa el nodo para ser expandido
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis de el optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }
                }
            }

            //Nodo mas a la izquierda
            valx = x - num;
            valy = y;
            if (valx >= 0) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 10 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }

                }
            }

            //Nodo arriba del actual
            valx = x;
            valy = y - num;
            if (valy >= 0) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 10 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }

                }
            }

            //Nodo abajo del actual
            valx = x;
            valy = y + num;
            if (valy <= alto) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 10 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }
                }
            }
            
            //Nodo abajo derecha del actual
            valx = x + num;
            valy = y + num;
            if (valx <= ancho && valy <= alto) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 14 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }

                }
            }
            //Nodo derecha arriba del actual
            valx = x + num;
            valy = y - num;
            if (valx <= ancho && valy >= 0) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 14 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }

                }
            }
            
            //Nodo izquierda abajo del actual
            valx = x - num;
            valy = y + num;
            if (valx >= 0 && valy <= alto) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 14 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }
                }
            }

            //Nodo arriba izquierda del nodo actual
            valx = x - num;
            valy = y - num;
            if (valx >= 0 && valy >= 0) {
                if (mat[valx][valy] == 0 && nodos_cerrados[valx][valy] == null) {
                    nodoastar nodoExpandir = nodos_abiertos[valx][valy];
                    //si no esta en abiertos se agrega para su expansion y si esta se verifica si es un camino optimo
                    if (nodoExpandir == null) {
                        nodoastar nodoAdyacente = new nodoastar(inicial, valx, valy, this.destinoX, this.destinoY);
                        //Calcular Costos
                        //Costo del estado inicial al actual
                        nodoAdyacente.g = 14 + inicial.g;
                        //Costo de del estado actual al objetivo
                        nodoAdyacente.calcularh();
                        nodoAdyacente.calcularf();
                        abiertos.add(nodoAdyacente);
                        nodos_abiertos[valx][valy] = nodoAdyacente;
                    } else {
                        int sumaDeG = nodoExpandir.g + inicial.g;
                        //Analisis del optimo del camino
                        if (sumaDeG <= inicial.g) {
                            nodoExpandir.padre = inicial;
                            nodoExpandir.g = sumaDeG;
                            nodoExpandir.calcularh();
                        }
                    }

                }
            }

            //Ordena los nodos en la lista de abiertos para tener el de f FminimoNodo en la posicion 0
            Collections.sort(abiertos);

            //obtengo el que tiene un f más pequeño
            nodoastar FminimoNodo = abiertos.get(0);
            inicial = FminimoNodo;
            x = FminimoNodo.x;
            y = FminimoNodo.y;

            abiertos.remove(0);
            cerrados.add(FminimoNodo);
            nodos_abiertos[x][y] = null;
            nodos_cerrados[x][y] = FminimoNodo;

            nodoastar ultimo = isInAbiertos(this.destinoX, this.destinoY);
            if (ultimo != null) {
                mensaje("Se ha encontrado la ruta óptima!!!!", "La ruta hacia el punto final ha sido encontrada.");
                destinof = ultimo;
                break;
            } else {
            }
        }
    }

    public BufferedImage generarImagen() {
        nodoastar nodoRuta = this.destinof;
        while (nodoRuta != null) {
            Color colorRuta = new Color(21, 237, 50); // Color white
            int colorSRGB = colorRuta.getRGB();
            this.imageActual.setRGB(nodoRuta.x, nodoRuta.y, colorSRGB);
            this.imageActual.setRGB(nodoRuta.x, nodoRuta.y + 1, colorSRGB);
            this.imageActual.setRGB(nodoRuta.x, nodoRuta.y - 1, colorSRGB);
            this.imageActual.setRGB(nodoRuta.x + 1, nodoRuta.y, colorSRGB);
            this.imageActual.setRGB(nodoRuta.x - 1, nodoRuta.y, colorSRGB);
            nodoRuta = nodoRuta.padre;
        }
        return this.imageActual;
    }
    //Fin de operaciones

    public static void mensaje(String titleBar, String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "Informacion: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage CargarEscenario() {
        BufferedImage bmp = null;
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione la imagen de laberinto a cargar");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP % PNG", "jpg", "gif", "bmp", "png");
        selector.setFileFilter(filtroImagen);
        int flag = selector.showOpenDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                //Devuelve el fichero de la seleccion que realizada
                File imagenSeleccionada = selector.getSelectedFile();
                //se almacena la imagen leida en bmp
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
            imageActual = bmp;
        }
        return bmp;
    }

    public BufferedImage InterpretarEscenario() {
        Color colorAux;
        //Lee pixel por pixel para traducir la imagen
        mat = new int[imageActual.getWidth()][imageActual.getHeight()];
        //inicializa la matriz de nodos abiertos
        nodos_abiertos = new nodoastar[imageActual.getWidth()][imageActual.getHeight()];
        //inicializa la matriz de nodos cerrados
        nodos_cerrados = new nodoastar[imageActual.getWidth()][imageActual.getHeight()];
        for (int i = 0; i < imageActual.getWidth(); i++) {
            for (int j = 0; j < imageActual.getHeight(); j++) {
                //Almacenamos el color del píxel
                colorAux = new Color(this.imageActual.getRGB(i, j));
                if (colorAux.getRed() > 200 && colorAux.getGreen() > 200 && colorAux.getBlue() > 200) {
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = 1;
                }
            }
        }
        mensaje("Interpretacion de Imagen", "Interpretacion Concluida exitosamente");
        //Regresa la imagen para su analisis
        return imageActual;
    }

    public BufferedImage cargaPredefinida(String ruta) {
        BufferedImage bmp = null;
        try {
            //se almacena la imagen leida en bmp
            File imagenSeleccionada = new File(ruta);
            bmp = ImageIO.read(imagenSeleccionada);
            imageActual = bmp;
        } catch (Exception e) {

        }
        return imageActual;
    }

}
