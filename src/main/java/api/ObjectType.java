package api;

import javassist.bytecode.ConstPool;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ObjectType
{
	CONST_UTF8(ConstPool.CONST_Utf8),
	CONST_INTEGER(ConstPool.CONST_Integer),
	CONST_FLOAT(ConstPool.CONST_Float),
	CONST_LONG(ConstPool.CONST_Long),
	CONST_DOUBLE(ConstPool.CONST_Double),
	CONST_CLASS(ConstPool.CONST_Class),
	CONST_STRING(ConstPool.CONST_String),
	CONST_FIELDREF(ConstPool.CONST_Fieldref),
	CONST_METHODREF(ConstPool.CONST_Methodref),
	CONST_INTERFACEMETHODREF(ConstPool.CONST_InterfaceMethodref),
	CONST_NAMEANDTYPE(ConstPool.CONST_NameAndType),
	CONST_METHODHANDLE(ConstPool.CONST_MethodHandle),
	CONST_METHODTYPE(ConstPool.CONST_MethodType),
	CONST_INVOKEDYNAMIC(ConstPool.CONST_InvokeDynamic);

	@Getter
	private int constant;

	public static ObjectType findByConst(int tag)
	{
		for (ObjectType objectType : values())
		{
			if (objectType.getConstant() == tag)
			{
				return objectType;
			}
		}
		return null;
	}

}
