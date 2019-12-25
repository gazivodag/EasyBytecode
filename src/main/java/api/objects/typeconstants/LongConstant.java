package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class LongConstant extends ObjectFromConstantPool
{
	public LongConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_LONG);
	}

	public long getLong()
	{
		return getConstPool().getLongInfo(getIndex());
	}
}
