package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class DoubleConstant extends ObjectFromConstantPool
{
	public DoubleConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_UTF8);
	}

	public double getDouble()
	{
		return getConstPool().getDoubleInfo(getIndex());
	}
}
