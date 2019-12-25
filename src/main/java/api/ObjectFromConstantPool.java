package api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class ObjectFromConstantPool
{
	@Getter
	private ObjectType objectType;
}
