package enums;

public enum TypeEnum {
	STATIC(1), DYNAMIC(2);

	private int valor;

	TypeEnum(int valor) {
		this.valor = valor;
	}

	public int valor() {
		return valor;
	}
}
