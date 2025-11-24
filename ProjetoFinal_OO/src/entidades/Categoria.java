package entidades;

public enum Categoria {
    COMUM(5.0, 1.0),
    LUXO(9.0, 2.2);

    private double tarifaBase;
    private double precoPorKm;

    Categoria(double tarifaBase, double precoPorKm) {
        this.tarifaBase = tarifaBase;
        this.precoPorKm = precoPorKm;
    }

    public double tarifaBase() { return tarifaBase; }
    public double precoPorKm() { return precoPorKm; }
}
