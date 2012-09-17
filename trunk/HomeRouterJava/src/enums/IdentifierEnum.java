package enums;

public enum IdentifierEnum {
	FASTETHERNET0(1), SERIAL0 (2), SERIAL1(3);

	private int valor;

	IdentifierEnum(int valor) {
		this.valor = valor;
	}

	public int valor() {
		return valor;
	}

}
