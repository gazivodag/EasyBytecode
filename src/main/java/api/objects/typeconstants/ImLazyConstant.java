package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;

public class ImLazyConstant extends ObjectFromConstantPool
{
	public ImLazyConstant(ConstPool constPool, int index, ObjectType objectType)
	{
		super(constPool, index, objectType);
	}
}
