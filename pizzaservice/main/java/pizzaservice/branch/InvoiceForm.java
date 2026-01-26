package pizzaservice.branch;

import javax.validation.constraints.NotBlank;

/**
 * A form object for the creation of an Invoice in order to bind errors.
 */
public class InvoiceForm {

	@NotBlank(message= "{InvoiceForm.start.NotBlank}")
	private final String start;

	@NotBlank(message= "{InvoiceForm.end.NotBlank}")
	private final String end;

	public InvoiceForm(String start, String end){
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}
