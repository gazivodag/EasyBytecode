package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;

public class FloatConstant extends ObjectFromConstantPool
{
	@Getter
	private final float floatConstant;

	public FloatConstant(float floatConstant)
	{
		super(ObjectType.CONST_FLOAT);
		this.floatConstant = floatConstant;
	}
}
