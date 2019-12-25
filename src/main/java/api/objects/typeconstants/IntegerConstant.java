package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;

public class IntegerConstant extends ObjectFromConstantPool
{
	@Getter
	private final int integer;

	public IntegerConstant(int integer)
	{
		super(ObjectType.CONST_INTEGER);
		this.integer = integer;
	}
}
