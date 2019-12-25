package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class IntegerConstant extends ObjectFromConstantPool
{
	public IntegerConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_INTEGER);
	}

	public int getInt()
	{
		return getConstPool().getIntegerInfo(getIndex());
	}
}
