package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;
import statics.ByteCodeUtils;

public class NameAndTypeConstant extends ObjectFromConstantPool
{
	public NameAndTypeConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_NAMEANDTYPE);
	}

	public String getNameUtf()
	{
		int nameUtf8Index = getConstPool().getNameAndTypeName(getIndex());
		Utf8Constant nameConstant = (Utf8Constant) ByteCodeUtils.getConstant(getConstPool(), nameUtf8Index);
		return nameConstant.getString();
	}

	public String getDescriptorUtf()
	{
		int descriptorIndex = getConstPool().getNameAndTypeDescriptor(getIndex());
		Utf8Constant descriptorConstant = (Utf8Constant) ByteCodeUtils.getConstant(getConstPool(), descriptorIndex);
		return descriptorConstant.getString();
	}
}
