package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;

public class DoubleConstant extends ObjectFromConstantPool
{
	@Getter
	private final double doubleConstant;

	public DoubleConstant(double doubleConstant)
	{
		super(ObjectType.CONST_UTF8);
		this.doubleConstant = doubleConstant;
	}
}
