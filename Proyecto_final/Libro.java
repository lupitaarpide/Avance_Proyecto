package Proyecto_final;

public class Libro {

    private String titulo;
    private String autor;
    private int stockTotal;
    private int stockPrestado;

    public Libro(String tituloParam, String autorParam, int stockTotalParam) {
        titulo = tituloParam;
        autor = autorParam;
        stockTotal = stockTotalParam;
        stockPrestado = 0;
    }

    public boolean estaDisponible() {
        return stockPrestado < stockTotal;
    }

    public void actualizarStock(boolean prestar) {

        if (prestar) {
            if (estaDisponible()) {
                stockPrestado++;
            }
        } else {
            if (stockPrestado > 0) {
                stockPrestado--;
            }
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public int getStock() {
        return stockTotal - stockPrestado;
    }

    public String getAutor() {
        return autor;
    }
}
