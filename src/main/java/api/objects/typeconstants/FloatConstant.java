package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class FloatConstant extends ObjectFromConstantPool
{
	public FloatConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_FLOAT);
	}

	public float getFloat()
	{
		return getConstPool().getFloatInfo(getIndex());
	}
}
