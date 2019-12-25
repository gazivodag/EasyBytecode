package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import javassist.bytecode.ConstPool;
import statics.ByteCodeUtils;

public class FieldRefConstant extends ObjectFromConstantPool
{
	public FieldRefConstant(ConstPool constPool, int index)
	{
		super(constPool, index, ObjectType.CONST_FIELDREF);
	}

	public ClassConstant getClassConstant()
	{
		int classIndex = getConstPool().getFieldrefClass(getIndex());
		ClassConstant classConstant = (ClassConstant) ByteCodeUtils.getConstant(getConstPool(), classIndex);
		return classConstant;
	}

	public NameAndTypeConstant getNameAndTypeConstant()
	{
		int nameAndTypeIndex = getConstPool().getFieldrefNameAndType(getIndex());
		NameAndTypeConstant nameAndTypeConstant = (NameAndTypeConstant) ByteCodeUtils.getConstant(getConstPool(), nameAndTypeIndex);
		return nameAndTypeConstant;
	}

}
