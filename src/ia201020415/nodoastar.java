/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia201020415;

/**
 *
 * @author rubio
 */
public class nodoastar implements Comparable {

    int x;
    int y;
    int destinox;
    int destinoy;

    public int f;
    public int g;
    public int h;
    public nodoastar padre;

    public nodoastar(nodoastar padre, int x, int y, int destinox, int destinoy) {
        this.padre = padre;
        this.x = x;
        this.y = y;
        this.destinox = destinox;
        this.destinoy = destinoy;
    }

    public int calcularh() {
        this.h = Math.abs(x - destinox) + Math.abs(y - destinoy);
        return h;
    }

    public int calcularf() {
        this.f = this.g + this.h;
        return f;
    }

    @Override
    public int compareTo(Object o) {
        int c = ((nodoastar) o).f;
        return this.f - c;
    }
}
