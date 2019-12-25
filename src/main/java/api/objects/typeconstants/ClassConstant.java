package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class ClassConstant extends ObjectFromConstantPool
{
	public ClassConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_CLASS);
	}

	public String getQualifiedClassName()
	{
		return getConstPool().getClassInfo(getIndex());
	}
}
