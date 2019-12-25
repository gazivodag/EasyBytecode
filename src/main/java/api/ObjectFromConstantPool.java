package api;

import javassist.bytecode.ConstPool;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class ObjectFromConstantPool
{
	@Getter
	private ConstPool constPool;
	@Getter
	private int index;
	@Getter
	private ObjectType objectType;
}
