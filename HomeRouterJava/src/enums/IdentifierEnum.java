package enums;

public enum IdentifierEnum {
	SERIAL(1), FASTETHERNET(2);

	private int valor;

	IdentifierEnum(int valor) {
		this.valor = valor;
	}

	public int valor() {
		return valor;
	}

}
