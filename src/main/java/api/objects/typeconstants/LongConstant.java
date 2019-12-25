package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;

public class LongConstant extends ObjectFromConstantPool
{
	@Getter
	private final Long longConstant;

	public LongConstant(long longConstant)
	{
		super(ObjectType.CONST_LONG);
		this.longConstant = longConstant;
	}
}
