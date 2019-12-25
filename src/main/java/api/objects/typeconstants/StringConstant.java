package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;
import lombok.Setter;

public class StringConstant extends ObjectFromConstantPool
{
	@Getter
	@Setter
	private String stringReference;

	public StringConstant(String stringReference)
	{
		super(ObjectType.CONST_STRING);
		this.stringReference = stringReference;
	}
}
