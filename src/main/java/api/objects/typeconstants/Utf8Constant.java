package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class Utf8Constant extends ObjectFromConstantPool
{
	public Utf8Constant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_UTF8);
	}

	public String getString()
	{
		return getConstPool().getUtf8Info(getIndex());
	}
}
