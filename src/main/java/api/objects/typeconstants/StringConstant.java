package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class StringConstant extends ObjectFromConstantPool
{
	public StringConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_STRING);
	}

	public String getString()
	{
		return getConstPool().getStringInfo(getIndex());
	}
}
